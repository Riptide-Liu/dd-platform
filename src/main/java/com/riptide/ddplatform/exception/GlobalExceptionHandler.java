package com.riptide.ddplatform.exception;


import com.riptide.ddplatform.domin.APIResult;

import com.riptide.ddplatform.enums.ApiEnum;
import com.riptide.ddplatform.util.ResultGenerator;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;


import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice  //全局捕获
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 统一异常处理
     * @param e 异常
     * @return 错误500
     */


    @ExceptionHandler(value = BadCredentialsException.class)
    public APIResult exceptionHandler(BadCredentialsException e) {
        // 打印调用栈信息
        e.printStackTrace();
        // 还可以接其他异常
        return ResultGenerator.genFailed(e.getMessage());
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    public APIResult exceptionHandler(AccessDeniedException e) {
        // 打印调用栈信息
        e.printStackTrace();
        // 还可以接其他异常
        return ResultGenerator.genFailed("不允许访问或权限不足！");
    }

    @ExceptionHandler(value = Exception.class)
    public APIResult exceptionHandler(Exception e) {
        // 打印调用栈信息
        e.printStackTrace();
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException)e;
            if (globalException.getApiEnum() == null)
                return ResultGenerator.genFailed();
            return ResultGenerator.genFailed(globalException.getApiEnum());
        }
        // 还可以接其他异常
        return ResultGenerator.genFailed();
    }
//
//    @ExceptionHandler(value = ArithmeticException.class)
//    public APIResult arithmeticExceptionHandler(ArithmeticException e) {
//        e.printStackTrace();
//        return ResultGenerator.genFailed(ApiEnum.DIVIDE_BY_ZERO);
//    }
//
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public APIResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 这里在构造错误信息
        StringBuilder sb = new StringBuilder();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        boolean first = true;
        for (ObjectError error : allErrors) {
            if (first) first = false;
            else sb.append(",");
            sb.append(error.getDefaultMessage());
        }
        // 返回错误信息
        return ResultGenerator.genFailed(sb.toString(), e.getMessage());
    }
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public APIResult HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        // 返回错误信息
        return ResultGenerator.genFailed("参数类型错误", e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public APIResult ConstraintViolationExceptionHandler(ConstraintViolationException exception) {

        String message = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        // 返回错误信息
        return ResultGenerator.genFailed("参数类型错误", message);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public APIResult MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {

        // 返回错误信息
        return ResultGenerator.genFailed("参数类型错误", e.getMessage());
    }

}
