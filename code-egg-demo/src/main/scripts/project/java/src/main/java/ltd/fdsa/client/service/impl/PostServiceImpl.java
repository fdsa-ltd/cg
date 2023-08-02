package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.PostService;
import ltd.fdsa.client.entity.Post;
import ltd.fdsa.client.repository.reader.PostReader;
import ltd.fdsa.client.repository.writer.PostWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostReader reader;
    @Autowired
    PostWriter writer;    
    @Override
    public List<Post> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Post entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Post entity) {
        return this.writer.update(entity);
    }
}
