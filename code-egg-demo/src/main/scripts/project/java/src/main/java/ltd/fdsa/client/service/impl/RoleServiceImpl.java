package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.RoleService;
import ltd.fdsa.client.entity.Role;
import ltd.fdsa.client.repository.reader.RoleReader;
import ltd.fdsa.client.repository.writer.RoleWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleReader reader;
    @Autowired
    RoleWriter writer;    
    @Override
    public List<Role> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Role entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Role entity) {
        return this.writer.update(entity);
    }
}
