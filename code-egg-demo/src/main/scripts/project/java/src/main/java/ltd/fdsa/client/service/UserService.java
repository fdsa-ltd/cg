package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.User; 

public interface UserService  {

    
    List<User> findAll(String where, Object... param);

    int insert(User entity);

    int deleteById(Integer id);

    int update(User entity);

}
