package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Blob; 

public interface BlobService  {

    
    List<Blob> findAll(String where, Object... param);

    int insert(Blob entity);

    int deleteById(Integer id);

    int update(Blob entity);

}
