package ltd.fdsa.client.repository.reader; 

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
public class RoleReader
{
 
    @Autowired
    DataSource ds;

    public List<Role> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT rid,\ncid,\nname,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_role "+
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


    public List<Role> query(String where, Object... param) {
        try {
            String sql = "SELECT rid,\ncid,\nname,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_role "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<Role> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT rid,\ncid,\nname,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_role  WHERE rid=? and \ncid=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
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
