package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Post; 

public interface PostService  {

    
    List<Post> findAll(String where, Object... param);

    int insert(Post entity);

    int deleteById(Integer id);

    int update(Post entity);

}
