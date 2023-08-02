package ltd.fdsa.client.service.impl;
 
import ltd.fdsa.client.service.UserFileService;
import ltd.fdsa.client.entity.UserFile;
import ltd.fdsa.client.repository.reader.UserFileReader;
import ltd.fdsa.client.repository.writer.UserFileWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserFileServiceImpl implements UserFileService {
    @Autowired
    UserFileReader reader;
    @Autowired
    UserFileWriter writer;    
    @Override
    public List<UserFile> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(UserFile entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(UserFile entity) {
        return this.writer.update(entity);
    }
}
