package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {
    //错误代码提示信息
    private ResultCode resultCode;
    //有参数构造
    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
    public ResultCode getResultCode() {
        return resultCode;
    }
}
