package com.riptide.ddplatform.util;


import com.riptide.ddplatform.domin.APIResult;
import com.riptide.ddplatform.enums.ApiEnum;
import org.springframework.stereotype.Component;

/**
 * 帮助构造APIResult
 * 工具类
 */
@Component
public class ResultGenerator {

    public static APIResult genSuccess() {
        return new APIResult(ApiEnum.SUCCESS);
    }

    public static APIResult genSuccess(String message) {
        return new APIResult(ApiEnum.SUCCESS.getCode(), message);
    }

    /**
     * 返回成功带数据
     * @param o 数据
     * @return 结果
     */
    public static APIResult genSuccess(Object o) {
        APIResult<Object> res = new APIResult(ApiEnum.SUCCESS);
        res.setData(o);
        return res;
    }

    public static APIResult genSuccess(String message, Object o) {
        APIResult<Object> res = new APIResult(ApiEnum.SUCCESS.getCode(), message);
        res.setData(o);
        return res;
    }


    public static APIResult genSuccess(ApiEnum apiEnum) {
        return new APIResult(apiEnum);
    }

    public static APIResult genFailed() {
        return new APIResult(ApiEnum.FAILED);
    }

    public static APIResult genFailed(ApiEnum apiEnum) {
        return new APIResult(apiEnum);
    }

    public static APIResult genFailed(String message) {
        return new APIResult(ApiEnum.FAILED.getCode(), message);
    }

    public static APIResult genFailed(String message, Object o) {
        APIResult<Object> res = new APIResult(ApiEnum.FAILED.getCode(), message);
        res.setData(o);
        return res;
    }
}
