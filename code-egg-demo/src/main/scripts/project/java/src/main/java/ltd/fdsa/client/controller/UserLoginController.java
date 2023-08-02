package ltd.fdsa.client.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.var; 
import ltd.fdsa.client.view.Result;
import ltd.fdsa.client.entity.UserLogin;
import ltd.fdsa.client.service.UserLoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userlogin")
@Slf4j
public class UserLoginController extends BaseController {
    @Autowired
    private UserLoginService service;

    @GetMapping(value = "/list")
    public Result<Object> list() {
        return Result.success(this.service.findAll(""));
    }


    @PostMapping(value = "/insert")
    public Result<Object> insert(@RequestBody UserLogin entity) {
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
    public Result<Object> update(@RequestBody UserLogin entity) {
        try {
            return Result.success(this.service.update(entity));
        } catch (Exception ex) {
            return Result.error(ex);
        }
    }

}
