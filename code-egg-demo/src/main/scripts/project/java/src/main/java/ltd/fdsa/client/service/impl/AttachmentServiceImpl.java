package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.AttachmentService;
import ltd.fdsa.client.entity.Attachment;
import ltd.fdsa.client.repository.reader.AttachmentReader;
import ltd.fdsa.client.repository.writer.AttachmentWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentReader reader;
    @Autowired
    AttachmentWriter writer;    
    @Override
    public List<Attachment> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Attachment entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Attachment entity) {
        return this.writer.update(entity);
    }
}
