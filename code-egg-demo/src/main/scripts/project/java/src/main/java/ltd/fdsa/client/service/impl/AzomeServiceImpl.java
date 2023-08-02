package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.AzomeService;
import ltd.fdsa.client.entity.Azome;
import ltd.fdsa.client.repository.reader.AzomeReader;
import ltd.fdsa.client.repository.writer.AzomeWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AzomeServiceImpl implements AzomeService {
    @Autowired
    AzomeReader reader;
    @Autowired
    AzomeWriter writer;    
    @Override
    public List<Azome> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Azome entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Azome entity) {
        return this.writer.update(entity);
    }
}
