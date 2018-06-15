/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午6:40
 * LastModified: 18-5-8 下午6:40
 */

package com.vphoto.demo.springboot.exception;

import com.vphoto.demo.springboot.model.enums.ResultEnum;

public class DadaErrorException extends RuntimeException {
    private int code = -1;

    private ResultEnum resultEnum;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DadaErrorException(String message) {
        super(message);
    }

    public DadaErrorException(ResultEnum error, Object... args) {
        super(String.format(error.getDesc(), args));
        this.resultEnum = error;
        this.code = error.getCode();
    }

    public DadaErrorException(Validation validation) {
        super(validation.getErrorMsg());
        this.code = validation.getErrorCode();
    }

    public int getCode() {
        return this.code;
    }

    public ResultEnum getResultEnum() {
        return this.resultEnum;
    }
}
