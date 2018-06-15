/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-9 上午11:25
 * LastModified: 18-5-9 上午11:25
 */

package com.vphoto.demo.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.vphoto.demo.springboot.constants.AppConstants;
import com.vphoto.demo.springboot.exception.DadaErrorException;
import com.vphoto.demo.springboot.manager.client.dto.IpInfoClientDTO;
import com.vphoto.demo.springboot.manager.domain.DemoModelDomain;
import com.vphoto.demo.springboot.manager.domain.repository.RepositoryContext;
import com.vphoto.demo.springboot.model.DemoModel;
import com.vphoto.demo.springboot.model.IpModel;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.CallResult;
import com.vphoto.demo.springboot.model.result.ReturnPageResult;
import com.vphoto.demo.springboot.service.BaseService;
import com.vphoto.demo.springboot.service.DemoService;
import com.vphoto.demo.springboot.utils.ClazzConverterUtils;
import com.vphoto.demo.springboot.utils.LogModel;
import com.vphoto.demo.springboot.web.context.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl extends BaseService implements DemoService {

    private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Autowired
    private RepositoryContext repositoryContext;

    @Override
    public CallResult<DemoModel> queryById(Long id) {

        final LogModel lm = LogModel.newLogModel("DemoService.queryById")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("id", id);

        logger.info(lm.toJson(false));

        if(null == id || id < 1){
            return this.makeResult(false, ResultEnum.PARAM_ERROR, null, new DadaErrorException(ResultEnum.PARAM_ERROR_NAME, "id"));
        }

        final DemoModelDomain activeInfoDomain = new DemoModelDomain(repositoryContext);
        DemoModel result = activeInfoDomain.queryDemoById(id);

        lm.addMetaDataResult(result);
        logger.info(lm.toJson());
        return this.makeSuccessResult(result);
    }

    @Override
    public CallResult<DemoModel> createDemo(DemoModel demoModel) {
        final LogModel lm = LogModel.newLogModel("DemoService.insertDemo")
                                    .addMetaDataTraceId(HttpContext.getTraceId())
                                    .addMetaData("demoModel", demoModel);
        logger.info(lm.toJson(false));

        if(null == demoModel){
            return this.makeResult(false, ResultEnum.PARAM_ERROR, null, new DadaErrorException(ResultEnum.PARAM_ERROR_NAME, "DemoModel"));
        }

        final DemoModelDomain activeInfoDomain = new DemoModelDomain(repositoryContext);
        try {
            int id = activeInfoDomain.insertDemo(demoModel);
        }catch (DadaErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultEnum(), null, ex);
        }catch (Exception ex){
            return this.makeFailCallResult(lm, ResultEnum.SYS_ERROR, null, ex);
        }

        lm.addMetaDataResult(demoModel);
        logger.info(lm.toJson());

        return this.makeSuccessResult(demoModel);
    }


    @Override
    public CallResult<Integer> deleteById(Long id) {
        final LogModel lm = LogModel.newLogModel("DemoService.deleteById")
                                    .addMetaData("id", id)
                                    .addMetaDataTraceId(HttpContext.getTraceId());
        logger.info(lm.toJson(false));

        final DemoModelDomain activeInfoDomain = new DemoModelDomain(repositoryContext);

        int result;

        try {
            result = activeInfoDomain.deleteById(id);
        }catch (DadaErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultEnum(), null, ex);
        }catch (Exception ex){
            return this.makeFailCallResult(lm, ResultEnum.SYS_ERROR, null, ex);
        }

        lm.addMetaDataResult(result);
        logger.info(lm.toJson());

        return this.makeSuccessResult(result);
    }

    @Override
    public CallResult<Integer> updateById(Long id, DemoModel demoModel) {

        demoModel.setId(id);

        final LogModel lm = LogModel.newLogModel("DemoService.updateById")
                .addMetaData("id", id)
                .addMetaData("demoModel", demoModel)
                .addMetaDataTraceId(HttpContext.getTraceId());

        logger.info(lm.toJson(false));

        final DemoModelDomain activeInfoDomain = new DemoModelDomain(repositoryContext);

        try {
            int result = activeInfoDomain.updateById(demoModel);

            lm.addMetaDataResult(result);
            logger.info(lm.toJson());

            return this.makeSuccessResult(result);
        }catch (DadaErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultEnum(), null, ex);
        }catch (Exception ex){
            return this.makeFailCallResult(lm, ResultEnum.SYS_ERROR, null, ex);
        }

    }


    @Override
    public CallResult<ReturnPageResult<DemoModel>> queryByPage(String code, Integer status, Long pageNum, Long pageSize) {

        if(null == pageNum || pageNum <= 0){
            pageNum = AppConstants.DEFAULT_PAGE_NUM;
        }
        if(null == pageNum || pageSize <= 0 || pageSize > AppConstants.MAX_PAGE_SIZE){
            pageSize = AppConstants.DEFAULT_PAGE_SIZE;
        }
        final LogModel lm = LogModel.newLogModel("DemoService.queryByPage")
                                    .addMetaData("code", code)
                                    .addMetaData("status", status)
                                    .addMetaData("pageNum", pageNum)
                                    .addMetaData("pageSize", pageSize)
                                    .addMetaDataTraceId(HttpContext.getTraceId());

        logger.info(lm.toJson(false));

        final DemoModelDomain activeInfoDomain = new DemoModelDomain(repositoryContext);

        try {
            ReturnPageResult<DemoModel> returnPageResult = activeInfoDomain.queryByPage(code, status,pageNum, pageSize);

            lm.addMetaDataResult(returnPageResult);
            logger.info(lm.toJson());

            return this.makeSuccessResult(returnPageResult);
        }catch (DadaErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultEnum(), null, ex);
        }catch (Exception ex){
            return this.makeFailCallResult(lm, ResultEnum.SYS_ERROR, null, ex);
        }
    }


    @Override
    public CallResult<IpModel> queryByIp(String ip) {

        final LogModel lm = LogModel.newLogModel("DemoService.queryByIp")
                                    .addMetaData("ip", ip)
                                    .addMetaDataTraceId(HttpContext.getTraceId());

        logger.info(lm.toJson(false));

        //调用第三方接口示例
        IpInfoClientDTO ipInfo  = new IpInfoClientDTO();
//        IpInfoClientDTO ipInfo = repositoryContext.getIpClient().getIpInfo("json",ip);
        logger.info("{} ip查询信息: {}", HttpContext.getTraceId(), JSON.toJSONString(ipInfo));
        if(null == ipInfo){
            return this.makeResult(false, ResultEnum.IPINFO_NOT_EXIST, null, null);
        }
        IpModel ipModel = ClazzConverterUtils.converterClass(ipInfo, IpModel.class);

        lm.addMetaDataResult(ipModel);
        logger.info(lm.toJson());

        return this.makeSuccessResult(ipModel);
    }
}
