package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.BlobService;
import ltd.fdsa.client.entity.Blob;
import ltd.fdsa.client.repository.reader.BlobReader;
import ltd.fdsa.client.repository.writer.BlobWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BlobServiceImpl implements BlobService {
    @Autowired
    BlobReader reader;
    @Autowired
    BlobWriter writer;    
    @Override
    public List<Blob> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Blob entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Blob entity) {
        return this.writer.update(entity);
    }
}
