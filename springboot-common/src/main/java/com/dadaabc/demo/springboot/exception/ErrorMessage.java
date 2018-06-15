/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-8 下午6:41
 * LastModified: 18-5-8 下午6:41
 */

package com.vphoto.demo.springboot.exception;

import com.vphoto.demo.springboot.model.enums.ResultEnum;

public class ErrorMessage {
    private ResultEnum error;
    private Object[] msgs;

    public ErrorMessage(ResultEnum errorCode) {
        this.error = errorCode;
    }

    public ErrorMessage(ResultEnum errorCode, Object... msgs) {
        this.error = errorCode;

        this.msgs = msgs;
    }

    public ResultEnum getError() {
        return error;
    }

    public Object[] getMsgs() {
        return msgs;
    }

    public String toString() {
        return String.format(error.getDesc(), msgs);
    }
}
