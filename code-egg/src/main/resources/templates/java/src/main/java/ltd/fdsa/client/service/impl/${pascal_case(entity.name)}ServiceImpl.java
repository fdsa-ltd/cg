package ${setting('project.package','ltd.fdsa.client')}.service.impl;
 
import ${setting('project.package','ltd.fdsa.client')}.service.${pascal_case(entity.name)}Service;
import ${setting('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};
import ${setting('project.package','ltd.fdsa.client')}.repository.reader.${pascal_case(entity.name)}Reader;
import ${setting('project.package','ltd.fdsa.client')}.repository.writer.${pascal_case(entity.name)}Writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ${pascal_case(entity.name)}ServiceImpl implements ${pascal_case(entity.name)}Service {
    @Autowired
    ${pascal_case(entity.name)}Reader reader;
    @Autowired
    ${pascal_case(entity.name)}Writer writer;    
    @Override
    public List<${pascal_case(entity.name)}> findAll(String where, Object... param) {
        return this.reader.query(where,param);
    }

    @Override
    public int insert(${pascal_case(entity.name)} entity) {
        return this.writer.insert(entity);
    }

    @Override
    public int deleteById(Integer id) {
        return this.writer.delete(id);
    }

    @Override
    public int update(${pascal_case(entity.name)} entity) {
        return this.writer.update(entity);
    }
}
