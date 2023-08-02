package ltd.fdsa.client.repository.writer;

import ltd.fdsa.client.entity.Dataset;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Dataset;
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
public class DatasetWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(Dataset... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_user_dataset (dataset_id,\ncid,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var fileId = entity.getFileId();
                var userId = entity.getUserId();
                var path = entity.getPath();
                var type = entity.getType();
                var size = entity.getSize();
                var width = entity.getWidth();
                var height = entity.getHeight();
                var tags = entity.getTags();
                var cid = entity.getCid();
                var status = entity.getStatus();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, fileId,cid,userId,path,type,size,width,height,tags,status,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(Dataset... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_user_dataset SET user_id=?,\npath=?,\ntype=?,\nsize=?,\nwidth=?,\nheight=?,\nend_time=?,\nstatus=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE dataset_id=? and \ncid=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var fileId = entity.getFileId();
                var userId = entity.getUserId();
                var path = entity.getPath();
                var type = entity.getType();
                var size = entity.getSize();
                var width = entity.getWidth();
                var height = entity.getHeight();
                var tags = entity.getTags();
                var cid = entity.getCid();
                var status = entity.getStatus();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, userId,path,type,size,width,height,tags,status,createTime,updateTime,createBy,updateBy,fileId,cid);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_user_dataset WHERE dataset_id=? and \ncid=? \n;";
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

    ResultSetHandler<List<Dataset>> resultSetHandler() {
        return new ResultSetHandler<List<Dataset>>() {
            @Override
            public List<Dataset> handle(ResultSet rs) throws SQLException {
                List<Dataset> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Dataset entity = new Dataset();
                    entity.setFileId(rs.getString("dataset_id"));
                    entity.setUserId(rs.getString("user_id"));
                    entity.setPath(rs.getString("path"));
                    entity.setType(rs.getString("type"));
                    // Long ;
                    entity.setWidth(rs.getInt("width"));
                    entity.setHeight(rs.getInt("height"));
                    entity.setTags(rs.getString("end_time"));
                    entity.setCid(rs.getInt("cid"));
                    // Byte ;
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
