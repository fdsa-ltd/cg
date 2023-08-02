package ltd.fdsa.client.repository.writer;

import ltd.fdsa.client.entity.User;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.User;
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
public class UserWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(User... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_user (uid,\nusernm,\nmobile_phone,\nemail_address,\npassword,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var usernm = entity.getUsernm();
                var mobilePhone = entity.getMobilePhone();
                var emailAddress = entity.getEmailAddress();
                var password = entity.getPassword();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, id,usernm,mobilePhone,emailAddress,password,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(User... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_user SET usernm=?,\nmobile_phone=?,\nemail_address=?,\npassword=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE uid=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var usernm = entity.getUsernm();
                var mobilePhone = entity.getMobilePhone();
                var emailAddress = entity.getEmailAddress();
                var password = entity.getPassword();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, usernm,mobilePhone,emailAddress,password,createTime,updateTime,createBy,updateBy,id);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_user WHERE uid=? \n;";
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

    ResultSetHandler<List<User>> resultSetHandler() {
        return new ResultSetHandler<List<User>>() {
            @Override
            public List<User> handle(ResultSet rs) throws SQLException {
                List<User> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    User entity = new User();
                    entity.setId(rs.getInt("uid"));
                    entity.setUsernm(rs.getString("usernm"));
                    entity.setMobilePhone(rs.getString("mobile_phone"));
                    entity.setEmailAddress(rs.getString("email_address"));
                    entity.setPassword(rs.getString("password"));
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
