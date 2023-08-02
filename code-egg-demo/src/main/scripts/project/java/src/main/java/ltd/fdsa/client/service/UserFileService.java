package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.UserFile; 

public interface UserFileService  {

    
    List<UserFile> findAll(String where, Object... param);

    int insert(UserFile entity);

    int deleteById(Integer id);

    int update(UserFile entity);

}
