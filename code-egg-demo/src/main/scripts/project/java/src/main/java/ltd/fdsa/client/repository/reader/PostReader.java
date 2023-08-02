package ltd.fdsa.client.repository.reader; 

import ltd.fdsa.client.entity.Post;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException; 
import lombok.var;
import ltd.fdsa.client.entity.Post;
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
public class PostReader
{
 
    @Autowired
    DataSource ds;

    public List<Post> page(int index, int pageSize ,String order,String where, Object... param) {
        try {
            String limit =MessageFormat.format("LIMIT {0} OFFSET {1}", pageSize, pageSize * index);
            String sql = "SELECT post_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_post "+
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


    public List<Post> query(String where, Object... param) {
        try {
            String sql = "SELECT post_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_post "+ where;
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), param);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
 
    public List<Post> queryByPrimaryKey(Object key) {
        try {
            String sql = "SELECT post_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\nFROM t_post  WHERE post_id=? \n;";
            // 实例化QueryRunner并执行DQL语句
            QueryRunner qr = new QueryRunner(this.ds);
            var result = qr.query( sql, resultSetHandler(), key);
            return result;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
 
    ResultSetHandler<List<Post>> resultSetHandler() {
        return new ResultSetHandler<List<Post>>() {
            @Override
            public List<Post> handle(ResultSet rs) throws SQLException {
                List<Post> list = new ArrayList<>(rs.getRow());
                while (rs.next()) {
                    Post entity = new Post();
                    entity.setId(rs.getInt("post_id"));
                    entity.setTitle(rs.getString("title"));
                    entity.setContent(rs.getString("content"));
                    entity.setImage(rs.getString("image"));
                    entity.setGeometry(rs.getString("geometry"));
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
