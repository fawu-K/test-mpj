package com.fawu.common;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author K.faWu
 * @program fawu-bot
 * @description: 返回结果
 * @create 2022-09-13 11:26
 **/
@Data
@Builder
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> Result<T> result(ApiCode apiCode, T data) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return result(apiCode.getCode(), apiCode.getMessage(), data);
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String languageStr = request.getHeader("language");
        if (languageStr != null && !languageStr.isEmpty() && "en".equals(languageStr)) {
            return result(apiCode.getCode(), apiCode.getMessageEn(), data);
        }else {
            return result(apiCode.getCode(), apiCode.getMessage(), data);
        }
    }

    public static <T> Result<T> result(ApiCode apiCode, String msg, T data) {
        if (StringUtils.isEmpty(msg)) {
            return result(apiCode.getCode(), apiCode.getMessage(), data);
        }
        return result(apiCode.getCode(), msg, data);
    }

    public static <T> Result<T> result(Integer code, String msg, T data) {
        return Result.<T>builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 成功
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 成功，返回数据
     */
    public static <T> Result<T> success(T data) {
        return result(ApiCode.SUCCESS, data);
    }

    public static <T> Result<T> fail() {
        return fail(ApiCode.FAIL);
    }

    public static <T> Result<T> fail(ApiCode apiCode) {
        return fail(apiCode, null);
    }

    /**
     * 失败。展示失败原因
     */
    public static <T> Result<T> fail(String msg) {
        return result(ApiCode.FAIL, msg, null);
    }

    public static <T> Result<T> fail(ApiCode apiCode, T data) {
        if (ApiCode.SUCCESS == apiCode) {
            throw new RuntimeException("失败结果状态码不能为" + ApiCode.SUCCESS.getCode());
        }
        return result(apiCode, data);
    }

    /**
     * 自定义错误信息
     *
     * @param errorCode 错误代码
     * @param message   错误提示信息
     * @return ApiResult对象
     */
    public static Result<Boolean> fail(Integer errorCode, String message) {
        return Result.<Boolean>builder().code(errorCode).msg(message).build();
    }
}
