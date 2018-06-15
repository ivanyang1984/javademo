/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-9 上午11:21
 * LastModified: 18-5-9 上午11:21
 */

package com.vphoto.demo.springboot.service;

import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.CallResult;
import com.vphoto.demo.springboot.utils.LogModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseService {
    protected static Logger logger = LoggerFactory.getLogger(BaseService.class);


    protected <T> CallResult<T> makeFailCallResult(LogModel logModel, ResultEnum resultEnum, T obj, Throwable throwable){
        logModel.addMetaDataError(throwable.getMessage());
        logger.error(logModel.toJson(), throwable);
        return  this.makeResult(false, resultEnum, null, throwable);
    }

    protected  <T> CallResult<T> makeResult(Boolean isSuccess, ResultEnum resultEnum, T obj, Throwable throwable) {
        return CallResult.makeCallResult(isSuccess, resultEnum,  obj, throwable);
    }

    protected  <T> CallResult<T> makeSuccessResult(T obj) {
        return CallResult.makeCallResult(true, ResultEnum.SUCCESS,  obj, null);
    }


    protected void writeLog(LogModel lm) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson());
        }
    }

    protected void writeLog(LogModel lm, boolean isClear) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson(isClear));
        }
    }

    protected void writeLog(String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    protected void writeErrorLog(LogModel lm, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error(lm.toJson(false), e);
        }
    }

    protected void writeLog(Logger logger, LogModel lm) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson());
        }
    }

    protected void writeLog(Logger logger, LogModel lm, boolean isClear) {
        if (logger.isInfoEnabled()) {
            logger.info(lm.toJson(isClear));
        }
    }

    protected void writeLog(Logger logger, String message) {
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    protected void writeErrorLog(Logger logger, LogModel lm, Throwable e) {
        if (logger.isErrorEnabled()) {
            logger.error(lm.toJson(false), e);
        }
    }

}
