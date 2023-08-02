package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.UserLogin;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.UserLogin;
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
public class UserLoginReader
{
 
    @Autowired
    DataSource ds;

    public List<UserLogin> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT login_id,\nuser_id,\nprovider,\nprovider_key,\ntoken,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_login "+
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


    public List<UserLogin> query(String where, Object... param) {
        try {
            String sql = "SELECT login_id,\nuser_id,\nprovider,\nprovider_key,\ntoken,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_login "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<UserLogin> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT login_id,\nuser_id,\nprovider,\nprovider_key,\ntoken,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_login  WHERE login_id=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<UserLogin>> resultSetHandler() {
        return new ResultSetHandler<List<UserLogin>>() {
            @Override
            public List<UserLogin> handle(ResultSet rs) throws SQLException {
                List<UserLogin> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    UserLogin entity = new UserLogin();
                    entity.setId(rs.getInt("login_id"));
                    entity.setUserId(rs.getInt("user_id"));
                    entity.setProvider(rs.getString("provider"));
                    entity.setKey(rs.getString("provider_key"));
                    entity.setToken(rs.getString("token"));
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
