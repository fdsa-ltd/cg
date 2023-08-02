package ltd.fdsa.client.repository.writer;

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
public class UserRoleWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(UserRole... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_user_role (user_role_id,\ncid,\nuser_id,\nrole_id,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var userId = entity.getUserId();
                var roleId = entity.getRoleId();
                var cid = entity.getCid();
                var status = entity.getStatus();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, id,cid,userId,roleId,status,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(UserRole... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_user_role SET user_id=?,\nrole_id=?,\nstatus=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE user_role_id=? and \ncid=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var userId = entity.getUserId();
                var roleId = entity.getRoleId();
                var cid = entity.getCid();
                var status = entity.getStatus();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, userId,roleId,status,createTime,updateTime,createBy,updateBy,id,cid);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_user_role WHERE user_role_id=? and \ncid=? \n;";
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
