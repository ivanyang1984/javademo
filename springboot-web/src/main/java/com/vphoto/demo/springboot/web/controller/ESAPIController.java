package com.vphoto.demo.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vphoto.demo.springboot.facade.ESDataApi;
import com.vphoto.demo.springboot.model.crm.VPXSYAccount;
import com.vphoto.demo.springboot.model.crm.VPXSYResult;
import com.vphoto.demo.springboot.model.crm.VPXSYTokenModel;
import com.vphoto.demo.springboot.model.crm.VPXSYUserResponsibility;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.es.*;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.utils.HttpUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vphoto.demo.springboot.constants.AppConstants.CRM_USER_RESPON_LIST;
import static com.vphoto.demo.springboot.constants.AppConstants.ES_GET_ORDER_PV_UV;
import static com.vphoto.demo.springboot.constants.AppConstants.ES_GET_VISIT_SOURCE;
import static com.vphoto.demo.springboot.utils.ESResultUtils.convertListToData;

@RestController
public class ESAPIController implements ESDataApi {

    @Override
    public ReturnResult<ESResult> getOrderListAndEs(@PathVariable  String orderId) {

        ReturnResult<ESResult> returnResult = new ReturnResult<ESResult>(ResultEnum.SUCCESS);
        String tokenStr = "?token=69f2354a18fbdfdfe8d0a3ef486e123c";//!测试永久token
        //！ 执行post请求
        String esRequestSql = ES_GET_ORDER_PV_UV + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("pageSize","1");
        params.put("pageIndex","1");
        params.put("orderId",orderId);
        params.put("sysUserId","116");
        String esResultJson = null;
        try {
            esResultJson = HttpUtils.sendPost(esRequestSql,params);
            if (esResultJson != null) {
                System.out.println(esResultJson);
                ESResult result = JSON.parseObject(esResultJson, ESResult.class);
                returnResult.setCode(ResultEnum.SUCCESS.getCode());
                returnResult.setData(result);
                returnResult.setMsg("SUCCESS");
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPESVisitData> getVisitSource(@PathVariable  String orderId) {
        ReturnResult<VPESVisitData> returnResult = new ReturnResult<VPESVisitData>(ResultEnum.SUCCESS);
        String tokenStr = "?token=69f2354a18fbdfdfe8d0a3ef486e123c";//!测试永久token
        //！ 执行post请求
        String esRequestSql = ES_GET_VISIT_SOURCE + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("orderId",orderId);
        params.put("sysUserId","116");
        String esResultJson = null;
        try {
            esResultJson = HttpUtils.sendPost(esRequestSql,params);
            if (esResultJson != null) {
                System.out.println(esResultJson);
                ESVPVisitResult result = JSON.parseObject(esResultJson, ESVPVisitResult.class);
                returnResult.setCode(ResultEnum.SUCCESS.getCode());

                VPESVisitData resultData = new VPESVisitData();
                resultData.setVistAvg(convertListToData(result.getData().getVistAvg()));
                resultData.setVistPv(convertListToData(result.getData().getVistPv()));
                resultData.setVistAvgScale(convertListToData(result.getData().getVistAvgScale()));
                resultData.setVistPvScale(convertListToData(result.getData().getVistPvScale()));
                resultData.setVistUv(convertListToData(result.getData().getVistUv()));
                resultData.setVistUvScale(convertListToData(result.getData().getVistUvScale()));
                returnResult.setData(resultData);
                returnResult.setMsg("SUCCESS");
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }
}
