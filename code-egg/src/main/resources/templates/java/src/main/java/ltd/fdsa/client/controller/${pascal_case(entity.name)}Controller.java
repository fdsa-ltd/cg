package ${dict('project.package','ltd.fdsa.client')}.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var; 
import ${dict('project.package','ltd.fdsa.client')}.view.Result;
import ${dict('project.package','ltd.fdsa.client')}.entity.${pascal_case(entity.name)};
import ${dict('project.package','ltd.fdsa.client')}.service.${pascal_case(entity.name)}Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${entity.name?lower_case}")
@Slf4j
public class ${pascal_case(entity.name)}Controller extends BaseController {
    @Autowired
    private ${pascal_case(entity.name)}Service service;

    @GetMapping(value = "/list")
    public Result<Object> list() {
        return Result.success(this.service.findAll(""));
    }


    @PostMapping(value = "/insert")
    public Result<Object> insert(@RequestBody ${pascal_case(entity.name)} entity) {
        try {
            return Result.success(this.service.insert(entity));
        } catch (Exception ex) {
            return Result.error(ex);
        }
    }

    @GetMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable(value = "id") Integer id) {
        this.service.deleteById(id);
        return Result.success();
    }

    @GetMapping(value = "/update")
    public Result<Object> update(@RequestBody ${pascal_case(entity.name)} entity) {
        try {
            return Result.success(this.service.update(entity));
        } catch (Exception ex) {
            return Result.error(ex);
        }
    }

}
