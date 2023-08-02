package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Role; 

public interface RoleService  {

    
    List<Role> findAll(String where, Object... param);

    int insert(Role entity);

    int deleteById(Integer id);

    int update(Role entity);

}
