package ltd.fdsa.client.repository.reader; 

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
public class UserReader
{
 
    @Autowired
    DataSource ds;

    public List<User> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT uid,\nusernm,\nmobile_phone,\nemail_address,\npassword,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user "+
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


    public List<User> query(String where, Object... param) {
        try {
            String sql = "SELECT uid,\nusernm,\nmobile_phone,\nemail_address,\npassword,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<User> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT uid,\nusernm,\nmobile_phone,\nemail_address,\npassword,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user  WHERE uid=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
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
