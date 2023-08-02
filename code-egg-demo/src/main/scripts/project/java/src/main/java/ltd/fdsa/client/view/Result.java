package ltd.fdsa.client.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.servlet.http.HttpServletResponse;

/**
 * 响应数据(结果)最外层对象
 *
 * @date 2018/10/15
 */
@Data
@ApiModel("响应结果")
public class Result<T> {
    /**
     * 状态码
     */
    @ApiModelProperty(notes = "状态码（200成功、400错误）")
    private int code;
    /**
     * 提示信息
     */
    @ApiModelProperty(notes = "提示信息")
    private String message;

    /**
     * 响应数据
     */
    @ApiModelProperty(notes = "响应数据")
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static <T> Result<T> success(T... data) {
        if (data.length > 0) {
            return new Result<T>(HttpServletResponse.SC_OK, "OK", data[0]);
        }
        return new Result<T>(HttpServletResponse.SC_OK, "OK", null);

    }

    public static <T> Result<T> fail(int code, String message, T... data) {
        if (data.length > 0) {
            return new Result<T>(code, message, data[0]);

        }
        return new Result<T>(code, message, null);
    }

    public static <T> Result<T> error(Exception ex, int... code) {
        if (code.length > 0) {
            return new Result<T>(code[0], ex.getMessage(), null);
        }
        return new Result<T>(HttpServletResponse.SC_EXPECTATION_FAILED, ex.getMessage(), null);
    }
}
