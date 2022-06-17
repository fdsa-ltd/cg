package ${setting('project.package','ltd.fdsa.client')}.config;


import lombok.extern.slf4j.Slf4j;
import ${setting('project.package','ltd.fdsa.client')}.view.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> goalException(Exception ex) {
        log.error("系统异常", ex);
        return Result.fail(HttpServletResponse.SC_EXPECTATION_FAILED,ex.getMessage());
    }
}
