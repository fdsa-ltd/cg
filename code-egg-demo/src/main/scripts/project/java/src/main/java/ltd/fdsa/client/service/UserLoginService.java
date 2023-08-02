package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.UserLogin; 

public interface UserLoginService  {

    
    List<UserLogin> findAll(String where, Object... param);

    int insert(UserLogin entity);

    int deleteById(Integer id);

    int update(UserLogin entity);

}
