package com.riptide.ddplatform.domin;

import com.riptide.ddplatform.enums.ApiEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 全局返回类，接口的返回
 *
 * @param <T>
 */
@Api("全局返回值")
public class APIResult<T> {
    // 状态码
    @ApiModelProperty("状态码")
    private Integer code;
    // 消息：解释状态码
    @ApiModelProperty("消息：解释状态码")
    private String message;

    public APIResult() {
    }

    // 返回的数据
    @ApiModelProperty("返回的数据")


    private T data;

    public APIResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 枚举类构造函数
     *
     * @param apiEnum api状态枚举
     */
    public APIResult(ApiEnum apiEnum) {
        this.code = apiEnum.getCode();
        this.message = apiEnum.getMessage();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}


