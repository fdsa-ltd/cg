package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Azome; 

public interface AzomeService  {

    
    List<Azome> findAll(String where, Object... param);

    int insert(Azome entity);

    int deleteById(Integer id);

    int update(Azome entity);

}
