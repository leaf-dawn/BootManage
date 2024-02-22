package com.book.constants;

/**
 * @Author 李嘉劲
 * 请求码
 */
public enum ResultCode {
    /**
     * 响应结果码
     */
    CUSTOM_SIMPLE_ERROR_MESSAGE(1, "自定义错误消息(不需要用户确定,过几秒后消失)"),
    CUSTOM_IMPORTANT_ERROR_MESSAGE(2, "重要的自定义错误消息(需要用户确定,直到用户确定才消失)"),
    SUCCESS(200, "响应成功"),
    ERROR(500, "系统错误"),
    NOT_PERMISSION(405, "当前用户无访问权限"),
    NOT_FOUND(404, "对应路径找不到"),
    IDENTITY_INVALID(403, "当前用户状态失效"),
    BAG_REQUEST(400, "错误请求"),
    NOT_TOKEN_REQUEST(401, "非法请求头！，缺少token"),
    TOO_MANY_REQUEST(429,"请求频繁");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
