package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获异常类
 */
@ControllerAdvice //增强控制器
public class ExceptionCatch {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomException.class);
    //使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //使用builder来构建一个异常类型和错误代码的异常
    private static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    @ExceptionHandler(CustomException.class) //捕获CustomException异常
    @ResponseBody //将返回的对像转化为字符串
    public ResponseResult customException(CustomException customException) {
        LOGGER.error("catch exception:{}", customException.getMessage());//异常信息
        //获取异常代码提示信息
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    //不可预见性异常捕获
    public ResponseResult Exception(Exception exception) {
        LOGGER.error("catch exception:{}", exception.getMessage());//异常信息
        if (EXCEPTIONS==null){
            EXCEPTIONS=builder.build();//构建EXCEPTIONS  map集合
        }

        //从EXCEPTIONS中找不到异常所对应的错误代码,响应给用户9999异常
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode!=null){
            return new ResponseResult(resultCode);
        }else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }

    static {
        //向map中添加异常类型和错误代码
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }
}
