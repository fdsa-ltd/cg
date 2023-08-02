package ltd.fdsa.client.repository.writer;

import ltd.fdsa.client.entity.Attachment;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Attachment;
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
public class AttachmentWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(Attachment... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_file (file_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var fileId = entity.getFileId();
                var usernm = entity.getUsernm();
                var mobilePhone = entity.getMobilePhone();
                var emailAddress = entity.getEmailAddress();
                var password = entity.getPassword();
                var fromTime = entity.getFromTime();
                var endTime = entity.getEndTime();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, fileId,usernm,mobilePhone,emailAddress,password,fromTime,endTime,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(Attachment... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_file SET title=?,\ncontent=?,\nimage=?,\ngeometry=?,\nfrom_time=?,\nend_time=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE file_id=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var fileId = entity.getFileId();
                var usernm = entity.getUsernm();
                var mobilePhone = entity.getMobilePhone();
                var emailAddress = entity.getEmailAddress();
                var password = entity.getPassword();
                var fromTime = entity.getFromTime();
                var endTime = entity.getEndTime();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, usernm,mobilePhone,emailAddress,password,fromTime,endTime,createTime,updateTime,createBy,updateBy,fileId);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_file WHERE file_id=? \n;";
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

    ResultSetHandler<List<Attachment>> resultSetHandler() {
        return new ResultSetHandler<List<Attachment>>() {
            @Override
            public List<Attachment> handle(ResultSet rs) throws SQLException {
                List<Attachment> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Attachment entity = new Attachment();
                    entity.setFileId(rs.getInt("file_id"));
                    entity.setUsernm(rs.getString("title"));
                    entity.setMobilePhone(rs.getString("content"));
                    entity.setEmailAddress(rs.getString("image"));
                    entity.setPassword(rs.getString("geometry"));
                    entity.setFromTime(rs.getString("from_time"));
                    entity.setEndTime(rs.getString("end_time"));
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
