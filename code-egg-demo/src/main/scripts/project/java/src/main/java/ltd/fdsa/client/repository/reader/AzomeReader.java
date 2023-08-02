package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.Azome;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Azome;
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
public class AzomeReader
{
 
    @Autowired
    DataSource ds;

    public List<Azome> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT file_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_zone "+
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


    public List<Azome> query(String where, Object... param) {
        try {
            String sql = "SELECT file_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_zone "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<Azome> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT file_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_zone  WHERE file_id=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<Azome>> resultSetHandler() {
        return new ResultSetHandler<List<Azome>>() {
            @Override
            public List<Azome> handle(ResultSet rs) throws SQLException {
                List<Azome> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Azome entity = new Azome();
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
