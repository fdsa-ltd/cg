package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.DatasetService;
import ltd.fdsa.client.entity.Dataset;
import ltd.fdsa.client.repository.reader.DatasetReader;
import ltd.fdsa.client.repository.writer.DatasetWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DatasetServiceImpl implements DatasetService {
    @Autowired
    DatasetReader reader;
    @Autowired
    DatasetWriter writer;    
    @Override
    public List<Dataset> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Dataset entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Dataset entity) {
        return this.writer.update(entity);
    }
}
