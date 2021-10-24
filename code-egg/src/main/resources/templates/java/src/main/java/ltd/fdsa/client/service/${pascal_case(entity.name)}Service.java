package ${dict('project.package','ltd.fdsa.client')}.service;

import java.util.List;
import ${dict('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)}; 

public interface ${pascal_case(entity.name)}Service  {

    
    List<${pascal_case(entity.name)}> findAll(String where, Object... param);

    int insert(${pascal_case(entity.name)} entity);

    int deleteById(Integer id);

    int update(${pascal_case(entity.name)} entity);

}
