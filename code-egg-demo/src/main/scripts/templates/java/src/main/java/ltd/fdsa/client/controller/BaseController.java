package ${setting('project.package','ltd.fdsa.client')}.controller;

import lombok.extern.slf4j.Slf4j;

import ${setting('project.package','ltd.fdsa.client')}.view.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class BaseController {
    @GetMapping(value = "/list")
    public Result<Object> list() {
        return Result.success(null);
    }
}
