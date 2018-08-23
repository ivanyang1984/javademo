package com.vphoto.demo.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.vphoto.demo.springboot.constants.AppConstants;
import com.vphoto.demo.springboot.facade.CRMApi;
import com.vphoto.demo.springboot.model.crm.VPXSYAccount;
import com.vphoto.demo.springboot.model.crm.VPXSYAccountResult;
import com.vphoto.demo.springboot.model.crm.VPXSYTokenModel;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.utils.DateUtils;
import com.vphoto.demo.springboot.utils.HttpUtils;
import com.vphoto.demo.springboot.utils.SQLUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vphoto.demo.springboot.constants.AppConstants.CRM_QUERY_URL;

@RestController
public class CRMApiController implements CRMApi {



    @Override
    public ReturnResult<VPXSYTokenModel> crmAccessToken() {
        Map<String,String> params = new HashMap<String, String>();
        params.put("grant_type",AppConstants.CRM_GRANT_TYPE);
        params.put("client_id",AppConstants.CRM_CLIENT_ID);
        params.put("client_secret",AppConstants.CRM_CLIENT_SECRET);
        params.put("redirect_uri",AppConstants.CRM_REDIRECT_URI);
        params.put("username",AppConstants.CRM_USERNAME);
        params.put("password",AppConstants.CRM_PASSWORD);

        String crmTokenRequest = "https://api.xiaoshouyi.com/oauth2/token.action";
        String testJson = HttpUtils.sendPost(crmTokenRequest,params);
        VPXSYTokenModel tokenObject = JSON.parseObject(testJson,VPXSYTokenModel.class);

//        System.out.println("access_token:"+tokenObject.getAccess_token());
        ReturnResult<VPXSYTokenModel> result = new ReturnResult<VPXSYTokenModel>(ResultEnum.SUCCESS);

        if (tokenObject != null) {
            result.setCode(200);
            result.setMsg("success");
        }else {
            result.setCode(506);
            result.setMsg("failed");
        }
        result.setData(tokenObject);
        return result;
    }


    @Override
    public ReturnResult<List<VPXSYAccount>> getCrmAccountListByDate(String date) {

        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String accountRequestSql = CRM_QUERY_URL+tokenStr;
        //! 处理时间
        Date yesterday = DateUtils.strToDate(date,"");
        Date dateBeforeYesterday = DateUtils.addDays(yesterday,-1);

        Long yes_timestamp = yesterday.getTime();
        Long b_yes_timestamp = dateBeforeYesterday.getTime();

        //! 拼装account全量sql
        String accountSQL = SQLUtils.getAccountAllFieldsSql() +
                " where createdAt >= " + b_yes_timestamp + " and createdAt <" + yes_timestamp +
                " limit 1000";

        Map<String,String> params = new HashMap<String, String>();
        params.put("q", accountSQL);

        String accountResultJson = HttpUtils.sendPost(accountRequestSql,params);

        System.out.println("result is: "+ accountResultJson);

        //! 获取一级结果字段
        VPXSYAccountResult accountResult = JSON.parseObject(accountResultJson, VPXSYAccountResult.class);
        Integer totalSize =  accountResult.getTotalSize();
        Integer count = accountResult.getCount();
        String accountsStr = accountResult.getRecords();

        //！ 获取records数组
        List<VPXSYAccount> accountList = JSONArray.parseArray(accountsStr,VPXSYAccount.class);
//        for (VPXSYAccount vpxsyAccount : accountList) {
//            System.out.println(vpxsyAccount.getAccountName());
//        }
        ReturnResult<List<VPXSYAccount>> returnResult = new ReturnResult<List<VPXSYAccount>>(ResultEnum.SUCCESS);
        returnResult.setMsg("SUCCESS");
        returnResult.setData(accountList);

        return returnResult;
    }
}
