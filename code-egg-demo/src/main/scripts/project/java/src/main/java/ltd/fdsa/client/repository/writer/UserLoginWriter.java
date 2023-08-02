package ltd.fdsa.client.repository.writer;

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
public class UserLoginWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(UserLogin... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_user_login (login_id,\nuser_id,\nprovider,\nprovider_key,\ntoken,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var userId = entity.getUserId();
                var provider = entity.getProvider();
                var key = entity.getKey();
                var token = entity.getToken();
                var endTime = entity.getEndTime();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, id,userId,provider,key,token,endTime,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(UserLogin... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_user_login SET user_id=?,\nprovider=?,\nprovider_key=?,\ntoken=?,\nend_time=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE login_id=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var userId = entity.getUserId();
                var provider = entity.getProvider();
                var key = entity.getKey();
                var token = entity.getToken();
                var endTime = entity.getEndTime();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, userId,provider,key,token,endTime,createTime,updateTime,createBy,updateBy,id);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_user_login WHERE login_id=? \n;";
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
