package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.Dataset;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Dataset;
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
public class DatasetReader
{
 
    @Autowired
    DataSource ds;

    public List<Dataset> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT dataset_id,\ncid,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_dataset "+
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


    public List<Dataset> query(String where, Object... param) {
        try {
            String sql = "SELECT dataset_id,\ncid,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_dataset "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<Dataset> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT dataset_id,\ncid,\nuser_id,\npath,\ntype,\nsize,\nwidth,\nheight,\nend_time,\nstatus,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_user_dataset  WHERE dataset_id=? and \ncid=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<Dataset>> resultSetHandler() {
        return new ResultSetHandler<List<Dataset>>() {
            @Override
            public List<Dataset> handle(ResultSet rs) throws SQLException {
                List<Dataset> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Dataset entity = new Dataset();
                    entity.setFileId(rs.getString("dataset_id"));
                    entity.setUserId(rs.getString("user_id"));
                    entity.setPath(rs.getString("path"));
                    entity.setType(rs.getString("type"));
                    // Long ;
                    entity.setWidth(rs.getInt("width"));
                    entity.setHeight(rs.getInt("height"));
                    entity.setTags(rs.getString("end_time"));
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
