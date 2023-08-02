package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.UserRoleService;
import ltd.fdsa.client.entity.UserRole;
import ltd.fdsa.client.repository.reader.UserRoleReader;
import ltd.fdsa.client.repository.writer.UserRoleWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleReader reader;
    @Autowired
    UserRoleWriter writer;    
    @Override
    public List<UserRole> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(UserRole entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(UserRole entity) {
        return this.writer.update(entity);
    }
}
