package ltd.fdsa.client.repository.writer;

import ltd.fdsa.client.entity.Blob;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Blob;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.MessageFormat;

@Service
public class BlobWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(Blob... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_blob (blob_id,\ntype,\nsize,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var fileId = entity.getFileId();
                var type = entity.getType();
                var size = entity.getSize();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, fileId,type,size,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(Blob... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_blob SET type=?,\nsize=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE blob_id=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var fileId = entity.getFileId();
                var type = entity.getType();
                var size = entity.getSize();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, type,size,createTime,updateTime,createBy,updateBy,fileId);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_blob WHERE blob_id=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var id : ids) {
                // 实例化QueryRunner并执行DML语句
                result += qr.update( sql, id);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }

    ResultSetHandler<List<Blob>> resultSetHandler() {
        return new ResultSetHandler<List<Blob>>() {
            @Override
            public List<Blob> handle(ResultSet rs) throws SQLException {
                List<Blob> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Blob entity = new Blob();
                    entity.setFileId(rs.getString("blob_id"));
                    entity.setType(rs.getString("type"));
                    // Long ;
                    entity.setCreateTime(rs.getDate("create_time"));
                    entity.setUpdateTime(rs.getDate("update_time"));
                    entity.setCreateBy(rs.getInt("create_by"));
                    entity.setUpdateBy(rs.getInt("update_by"));
                    list.add(entity);
                }
                return list;
            }
        };
    }
}
