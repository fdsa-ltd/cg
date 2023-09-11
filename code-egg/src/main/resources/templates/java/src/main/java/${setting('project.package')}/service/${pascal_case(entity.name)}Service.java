package ${setting('project.package','//todo add project.package')?replace('/','.')}.service;

import ${setting('project.package','//todo add project.package')?replace('/','.')}.entity.${pascal_case(entity.name)};
import ${setting('project.package','//todo add project.package')?replace('/','.')}.repository.${pascal_case(entity.name)}Repository;

import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ${pascal_case(entity.name)}Service {
    @Resource
    @Getter
    protected ${pascal_case(entity.name)}Repository repository;
    

    public List<${pascal_case(entity.name)}> findAll(String where, Object... param) {
        return this.repository.query(where,param);
    }

    public int insert(${pascal_case(entity.name)} entity) {
        return this.repository.insert(entity);
    }

    public int deleteById(Integer id) {
        return this.repository.delete(id);
    }

    public int update(${pascal_case(entity.name)} entity) {
        return this.repository.update(entity);
    }
}
