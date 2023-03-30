package com.riptide.ddplatform.enums;


public enum ApiEnum {
    SUCCESS(200, "成功"),
    ADD_SUCCESS(200, "添加成功"),
    EDIT_SUCCESS(200, "编辑成功"),
    DELETE_SUCCESS(200, "删除成功"),
    GET_SUCCESS(200, "获取成功"),
    SELECT_SUCCESS(200, "查询成功"),
    ADD_FAILED(500, "添加失败"),
    EDIT_FAILED(500, "编辑失败"),
    DELETE_FAILED(500, "删除失败"),
    GET_FAILED(500, "获取失败"),
    SELECT_FAILED(500, "查询失败"),
    FAILED(500, "失败"),
    LOGIN_SUCCESS(200, "登录成功"),
    ACCESS_DENIED(403, "权限不足"),
    FILE_UPLOAD_SUCCESS(205, "文件上传成功"),
    FILE_DELETE_SUCCESS(210, "文件删除成功"),
    FILE_UPLOAD_FAILED(510, "文件上传失败"),
    FILE_DELETE_FAILED(522, "文件删除失败"),
    FILE_SIZE_EXCEEDED(666, "文件大小请勿超过10MB"),
    PASSWORD_NOT_MATCH(10002, "密码不匹配"),
    USERNAME_REGISTED(10003, "用户名已经存在"),
    VERIFYCODE_ERROR(10004, "验证码错误"),
    USERNAME_PASSWORD_ERROR(10005, "用户名或密码错误"),
    TOKEN_NOT_MATCH(600, "非法的TOKEN"),
    TOKEN_TIMEOUT(605, "TOKEN已过期"),
    PRODUCT_ALREADY_EXIST(10006, "已经存在请勿重复添加！"),
    PRODUCT_NOT_EXIST(10007, "不存在"),
    USER_NOT_LEAGLE(10008, "用户信息不合法！"),
    ;

    private final Integer code;
    private final String message;

    ApiEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
