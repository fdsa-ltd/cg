package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Attachment; 

public interface AttachmentService  {

    
    List<Attachment> findAll(String where, Object... param);

    int insert(Attachment entity);

    int deleteById(Integer id);

    int update(Attachment entity);

}
