/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午6:15
 * LastModified: 18-5-8 下午6:15
 */

package com.vphoto.demo.springboot.model.result;


import com.vphoto.demo.springboot.model.enums.ResultEnum;

import java.io.Serializable;


public class ReturnResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;


    public ReturnResult(int code, String msg){
        this(code, msg, null);
    }

    public ReturnResult(ResultEnum resultEnum){
        this(resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    public ReturnResult(ResultEnum resultEnum, T data){
        this(resultEnum.getCode(), resultEnum.getDesc(), data);
    }

    public ReturnResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

