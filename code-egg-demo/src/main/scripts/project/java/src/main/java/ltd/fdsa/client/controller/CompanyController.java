package ltd.fdsa.client.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var; 
import ltd.fdsa.client.view.Result;
import ltd.fdsa.client.entity.Company;
import ltd.fdsa.client.service.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
@Slf4j
public class CompanyController extends BaseController {
    @Autowired
    private CompanyService service;

    @GetMapping(value = "/list")
    public Result<Object> list() {
        return Result.success(this.service.findAll(""));
    }


    @PostMapping(value = "/insert")
    public Result<Object> insert(@RequestBody Company entity) {
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
    public Result<Object> update(@RequestBody Company entity) {
        try {
            return Result.success(this.service.update(entity));
        } catch (Exception ex) {
            return Result.error(ex);
        }
    }

}
