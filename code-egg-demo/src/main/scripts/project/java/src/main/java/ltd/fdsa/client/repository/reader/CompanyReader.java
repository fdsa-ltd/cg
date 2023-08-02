package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.Company;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Company;
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
public class CompanyReader
{
 
    @Autowired
    DataSource ds;

    public List<Company> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT cid,\nname,\nremark,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_company "+
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


    public List<Company> query(String where, Object... param) {
        try {
            String sql = "SELECT cid,\nname,\nremark,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_company "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<Company> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT cid,\nname,\nremark,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_company  WHERE cid=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<Company>> resultSetHandler() {
        return new ResultSetHandler<List<Company>>() {
            @Override
            public List<Company> handle(ResultSet rs) throws SQLException {
                List<Company> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Company entity = new Company();
                    entity.setId(rs.getInt("cid"));
                    entity.setName(rs.getString("name"));
                    entity.setRemark(rs.getString("remark"));
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
