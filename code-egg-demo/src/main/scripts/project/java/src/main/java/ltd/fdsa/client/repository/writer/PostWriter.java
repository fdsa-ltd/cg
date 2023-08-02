package ltd.fdsa.client.repository.writer;

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
public class PostWriter
{
 
    @Autowired
    DataSource ds;

    public int insert(Post... entities) {
        try {
            // 准备SQL语句
            String sql = "INSERT INTO t_post (post_id,\ntitle,\ncontent,\nimage,\ngeometry,\nfrom_time,\nend_time,\ncreate_time,\nupdate_time,\ncreate_by,\nupdate_by\n) VALUES (\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?,\n?\n)";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var title = entity.getTitle();
                var content = entity.getContent();
                var image = entity.getImage();
                var geometry = entity.getGeometry();
                var fromTime = entity.getFromTime();
                var endTime = entity.getEndTime();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, id,title,content,image,geometry,fromTime,endTime,createTime,updateTime,createBy,updateBy);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int update(Post... entities) {
        try {
            // 准备SQL语句
            String sql = "UPDATE t_post SET title=?,\ncontent=?,\nimage=?,\ngeometry=?,\nfrom_time=?,\nend_time=?,\ncreate_time=?,\nupdate_time=?,\ncreate_by=?,\nupdate_by=?\n WHERE post_id=? \n;";
            QueryRunner qr = new QueryRunner(this.ds);
            int result = 0;
            for (var entity : entities) {
                // 实例化QueryRunner并执行DML语句
                var id = entity.getId();
                var title = entity.getTitle();
                var content = entity.getContent();
                var image = entity.getImage();
                var geometry = entity.getGeometry();
                var fromTime = entity.getFromTime();
                var endTime = entity.getEndTime();
                var createTime = entity.getCreateTime();
                var updateTime = entity.getUpdateTime();
                var createBy = entity.getCreateBy();
                var updateBy = entity.getUpdateBy();
                result += qr.update( sql, title,content,image,geometry,fromTime,endTime,createTime,updateTime,createBy,updateBy,id);
            }
            return result;
        } catch (SQLException e) {
            return -1;
        }
    }
    public int delete(int... ids) {
        try {
            // 准备SQL语句
            String sql = "DELETE FROM t_post WHERE post_id=? \n;";
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
