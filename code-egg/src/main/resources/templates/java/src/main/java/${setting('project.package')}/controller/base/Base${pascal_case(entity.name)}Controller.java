package ${setting('project.package','//todo add project.package')?replace('/','.')}.controller.base;

import ${setting('project.package','//todo add project.package')?replace('/','.')}.entity.${pascal_case(entity.name)};
import ${setting('project.package','//todo add project.package')?replace('/','.')}.service.${pascal_case(entity.name)}Service;

import cn.zhumingwu.base.model.Result;
import cn.zhumingwu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


public class Base${pascal_case(entity.name)}Controller extends BaseController {
    @Autowired
    @Qualifier("${camel_case(entity.name)}Service")
    protected ${pascal_case(entity.name)}Service service;

    //@GetMapping(value = "/list")
    public Result<Object> list() {
        return Result.success(this.service.findAll(""));
    }

    //@PostMapping(value = "/insert")
    public Result<Object> insert(@RequestBody ${pascal_case(entity.name)} entity) {
        try {
            return Result.success(this.service.insert(entity));
        } catch (Exception ex) {
            return Result.error(ex);
        }
    }

    //@GetMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable(value = "id") Integer id) {
        this.service.deleteById(id);
        return Result.success();
    }

    //@GetMapping(value = "/update")
    public Result<Object> update(@RequestBody ${pascal_case(entity.name)} entity) {
        try {
            return Result.success(this.service.update(entity));
        } catch (Exception ex) {
            return Result.error(ex);
        }
    }

}
