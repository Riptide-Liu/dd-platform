package com.riptide.ddplatform.exception;


import com.riptide.ddplatform.enums.ApiEnum;

public class GlobalException extends RuntimeException{

    private ApiEnum apiEnum;

    private String message;

    public GlobalException(ApiEnum apiEnum) {
        super(apiEnum.getMessage());
        this.apiEnum = apiEnum;
        this.message = apiEnum.getMessage();
    }

    public ApiEnum getApiEnum() {
        return apiEnum;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
