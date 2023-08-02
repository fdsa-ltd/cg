package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Dataset; 

public interface DatasetService  {

    
    List<Dataset> findAll(String where, Object... param);

    int insert(Dataset entity);

    int deleteById(Integer id);

    int update(Dataset entity);

}
