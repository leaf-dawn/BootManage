package com.book.pojo.vo;

import com.book.constants.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @Author 李嘉劲
 * 统一响应结构体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {
        "successResult",
        "errorResult"
})
public class Result<T> implements Cloneable {

    /**
     * 自定义状态码
     */
    private Integer code;

    /**
     * 返回消息提示
     */
    private String message;

    /**
     * 泛型，返回消息实体类对象
     */
    private T data;

    /**
     * 是否为success
     * @return
     */
    public boolean isSuccessResult() {
        return Objects.equals(ResultCode.SUCCESS.getCode(), code);
    }

    /**
     * 是否为error
     * @return
     */
    public boolean isErrorResult() {
        return !isSuccessResult();
    }

    /**
     * 建造者模式1
     * @param code
     * @return
     */
    public static <T> Builder<T> code(Integer code) {
        return new Builder<>(code);
    }

    /**
     * 建造者模式 , 成功的情况
     * @param <T>
     * @return
     */
    public static <T> Builder<T> success() {
        return code(ResultCode.SUCCESS.getCode());
    }

    /**
     * 建造者模式 , 普通异常
     * @param <T>
     * @return
     */
    public static <T> Builder<T> simpleError() {
        return code(ResultCode.CUSTOM_SIMPLE_ERROR_MESSAGE.getCode());
    }

    /**
     * 建造者模式 , 重要异常(需要用户确定的那种)
     * @param <T>
     * @return
     */
    public static <T> Builder<T> importantError() {
        return code(ResultCode.CUSTOM_IMPORTANT_ERROR_MESSAGE.getCode());
    }

    /**
     * 按照enum进行创建Result
     * @param codeEnum 枚举
     * @return
     */
    public static <T> Result<T> getErrorMessageByEnum(ResultCode codeEnum) {
        return new Result<>(
                codeEnum.getCode(),
                codeEnum.getMessage(),
                null
        );
    }

    /**
     * 请求成功，返回自定义code
     * @param successCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildSuccessData(
            Integer successCode,
            T data
    ) {
        return new Result<T>(
                successCode,
                "",
                data
        );
    }

    /**
     * 返回操作成功的相关信息
     * @param message
     * @return
     */
    public static <T> Result<T> buildSuccessMessage(String message) {
        return new Result<>(
                ResultCode.SUCCESS.getCode(),
                message,
                null
        );
    }

    /**
     * 返回操作成功的相关消息和数据
     * @param message
     * @param data
     * @return
     */
    public static <T> Result<T> buildSuccessResult(
            String message,
            T data
    ) {
        return new Result<>(
                ResultCode.SUCCESS.getCode(),
                message,
                data
        );
    }

    /**
     * 返回一个空的成功结果 , 通常用于{@link #isSuccessResult()}和{@link #isErrorResult()}的判断
     * @return
     */
    public static <T> Result<T> buildSuccessResult() {
        return new Result<>(
                ResultCode.SUCCESS.getCode(),
                null,
                null
        );
    }

    /**
     * 返回操作成功的相关数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> buildSuccessData(T data) {
        return buildSuccessData(ResultCode.SUCCESS.getCode(), data);
    }

    /**
     * 返回操作错误的相关信息
     * @param errorCode 错误码
     * @param message   错误信息
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> buildErrorMessage(
            Integer errorCode,
            String message
    ) {
        return new Result<>(
                errorCode,
                message,
                null
        );
    }

    /**
     * 返回操作错误的相关信息
     * @param message     错误信息
     * @param isImportant 是否为重要信息
     * @return
     */
    public static <T> Result<T> buildErrorMessage(
            String message,
            boolean isImportant
    ) {
        return buildErrorMessage(isImportant ? ResultCode.CUSTOM_IMPORTANT_ERROR_MESSAGE.getCode() : ResultCode.CUSTOM_SIMPLE_ERROR_MESSAGE.getCode(), message);
    }

    /**
     * 简单消息(需要前端简单提示几秒)
     * @param message
     * @return
     */
    public static <T> Result<T> buildSimpleErrorMessage(String message) {
        return buildErrorMessage(message, false);
    }

    /**
     * 重要消息(需要前端用户确定)
     * @param message
     * @return
     */
    public static <T> Result<T> buildImportantErrorMessage(String message) {
        return buildErrorMessage(message, true);
    }

    /**
     * 建造者模式
     * @param <T>
     */
    public static class Builder<T> {

        private final Result<T> Result;

        public Builder(Integer code) {
            Result = new Result<T>();
            Result.setCode(code);
        }

        public Builder<T> message(String message) {
            Result.setMessage(message);
            return this;
        }

        public Builder<T> data(T data) {
            Result.setData(data);
            return this;
        }

        public Result<T> build() {
            return Result;
        }
    }

}
