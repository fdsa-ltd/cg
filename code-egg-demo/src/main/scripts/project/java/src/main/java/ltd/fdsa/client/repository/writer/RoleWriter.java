package ltd.fdsa.client.repository.writer;

import ltd.fdsa.client.entity.Role;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Role;
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
public class RoleWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(Role... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_role (rid,\ncid,\nname,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var name = entity.getName();
                var cid = entity.getCid();
                var status = entity.getStatus();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, id,cid,name,status,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(Role... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_role SET name=?,\nstatus=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE rid=? and \ncid=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var name = entity.getName();
                var cid = entity.getCid();
                var status = entity.getStatus();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, name,status,createTime,updateTime,createBy,updateBy,id,cid);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_role WHERE rid=? and \ncid=? \n;";
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

    ResultSetHandler<List<Role>> resultSetHandler() {
        return new ResultSetHandler<List<Role>>() {
            @Override
            public List<Role> handle(ResultSet rs) throws SQLException {
                List<Role> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Role entity = new Role();
                    entity.setId(rs.getInt("rid"));
                    entity.setName(rs.getString("name"));
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
