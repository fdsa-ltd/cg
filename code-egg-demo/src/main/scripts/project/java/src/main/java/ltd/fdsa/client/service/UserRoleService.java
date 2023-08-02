package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.UserRole; 

public interface UserRoleService  {

    
    List<UserRole> findAll(String where, Object... param);

    int insert(UserRole entity);

    int deleteById(Integer id);

    int update(UserRole entity);

}
