package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.UserRole;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.UserRole;
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
public class UserRoleReader
{
 
    @Autowired
    DataSource ds;

    public List<UserRole> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT user_role_id,\ncid,\nuser_id,\nrole_id,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_role "+
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


    public List<UserRole> query(String where, Object... param) {
        try {
            String sql = "SELECT user_role_id,\ncid,\nuser_id,\nrole_id,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_role "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<UserRole> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT user_role_id,\ncid,\nuser_id,\nrole_id,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_role  WHERE user_role_id=? and \ncid=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<UserRole>> resultSetHandler() {
        return new ResultSetHandler<List<UserRole>>() {
            @Override
            public List<UserRole> handle(ResultSet rs) throws SQLException {
                List<UserRole> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    UserRole entity = new UserRole();
                    entity.setId(rs.getInt("user_role_id"));
                    entity.setUserId(rs.getInt("user_id"));
                    entity.setRoleId(rs.getInt("role_id"));
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
