package ltd.fdsa.client.service;

import java.util.List;
import ltd.fdsa.client.entity.Company; 

public interface CompanyService  {

    
    List<Company> findAll(String where, Object... param);

    int insert(Company entity);

    int deleteById(Integer id);

    int update(Company entity);

}
