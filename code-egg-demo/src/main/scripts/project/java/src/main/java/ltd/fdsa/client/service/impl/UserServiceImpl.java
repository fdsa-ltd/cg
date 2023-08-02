package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.UserService;
import ltd.fdsa.client.entity.User;
import ltd.fdsa.client.repository.reader.UserReader;
import ltd.fdsa.client.repository.writer.UserWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserReader reader;
    @Autowired
    UserWriter writer;    
    @Override
    public List<User> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(User entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(User entity) {
        return this.writer.update(entity);
    }
}
