package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.UserFile;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.UserFile;
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
public class UserFileReader
{
 
    @Autowired
    DataSource ds;

    public List<UserFile> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT file_id,\nblob_id,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_file "+
             where +
             order +
             limit;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }


    public List<UserFile> query(String where, Object... param) {
        try {
            String sql = "SELECT file_id,\nblob_id,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_file "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<UserFile> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT file_id,\nblob_id,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_file  WHERE file_id=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<UserFile>> resultSetHandler() {
        return new ResultSetHandler<List<UserFile>>() {
            @Override
            public List<UserFile> handle(ResultSet rs) throws SQLException {
                List<UserFile> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    UserFile entity = new UserFile();
                    entity.setFileId(rs.getString("file_id"));
                    entity.setBlobId(rs.getString("blob_id"));
                    entity.setUserId(rs.getString("user_id"));
                    entity.setPath(rs.getString("path"));
                    entity.setType(rs.getString("type"));
                    // Long ;
                    entity.setWidth(rs.getInt("width"));
                    entity.setHeight(rs.getInt("height"));
                    entity.setTags(rs.getString("end_time"));
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
