package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.UserLoginService;
import ltd.fdsa.client.entity.UserLogin;
import ltd.fdsa.client.repository.reader.UserLoginReader;
import ltd.fdsa.client.repository.writer.UserLoginWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    UserLoginReader reader;
    @Autowired
    UserLoginWriter writer;    
    @Override
    public List<UserLogin> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(UserLogin entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(UserLogin entity) {
        return this.writer.update(entity);
    }
}
