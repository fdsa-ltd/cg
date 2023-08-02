package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.CompanyService;
import ltd.fdsa.client.entity.Company;
import ltd.fdsa.client.repository.reader.CompanyReader;
import ltd.fdsa.client.repository.writer.CompanyWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyReader reader;
    @Autowired
    CompanyWriter writer;    
    @Override
    public List<Company> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(Company entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(Company entity) {
        return this.writer.update(entity);
    }
}
