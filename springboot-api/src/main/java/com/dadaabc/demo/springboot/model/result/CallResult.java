/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 逻辑层返回对象
 * Version: 1.0
 * Date: 18-5-9 上午11:16
 * LastModified: 18-5-9 上午11:16
 */

package com.vphoto.demo.springboot.model.result;

import com.vphoto.demo.springboot.model.enums.ResultEnum;

public class CallResult<T> {

    private boolean     isSuccess; //接口执行成功,取到结果,返回true
    private ResultEnum resultEnum;  //结果状态信息
    private T           businessResult;   //返回的查询结果
    private Throwable   throwable;        //返回异常信息

    private CallResult(
            boolean isSuccess,
            ResultEnum  resultEnum,
            T businessResult,
            Throwable throwable) {
        super();
        this.isSuccess = isSuccess;
        this.resultEnum = resultEnum;
        this.businessResult = businessResult;
        this.throwable = throwable;
    }

    public static <T>CallResult<T> makeCallResult(
            boolean isSuccess,
            ResultEnum  resultEnum,
            T businessResult,
            Throwable throwable){
        return new CallResult<T>(isSuccess, resultEnum, businessResult, throwable);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public ResultEnum getResultEnum() {
        return resultEnum;
    }

    public T getBusinessResult() {
        return businessResult;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
