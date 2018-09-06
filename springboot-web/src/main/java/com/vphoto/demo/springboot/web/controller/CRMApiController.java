package com.vphoto.demo.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vphoto.demo.springboot.constants.AppConstants;
import com.vphoto.demo.springboot.facade.CRMApi;
import com.vphoto.demo.springboot.model.crm.*;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.utils.DateUtils;
import com.vphoto.demo.springboot.utils.HttpUtils;
import com.vphoto.demo.springboot.utils.SQLUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.vphoto.demo.springboot.constants.AppConstants.*;

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
        String accountRequestSql = CRM_QUERY_URL + tokenStr;

        //! 拼装account全量sql
        String accountSQL1 = SQLUtils.getFristHalfFieldsSql() + SQLUtils.getWhereClause(date);
        String accountSQL2 = SQLUtils.getSecondHalfAccountFieldsSQL() + SQLUtils.getWhereClause(date);

        Map<String,String> params1 = new HashMap<String, String>();
        params1.put("q", accountSQL1);

        Map<String,String> params2 = new HashMap<String, String>();
        params2.put("q", accountSQL2);

        String accountResultJson1 = HttpUtils.sendPost(accountRequestSql,params1);
        String accountResultJson2 = HttpUtils.sendPost(accountRequestSql,params2);

        System.out.println("result1 is: "+ accountResultJson1);
        System.out.println("result2 is: "+ accountResultJson2);

        //! 获取一级结果字段
        VPXSYResult accountResult1 = JSON.parseObject(accountResultJson1, VPXSYResult.class);
        Integer totalSize1 =  accountResult1.getTotalSize();
        Integer count1 = accountResult1.getCount();
        String accountsStr1 = accountResult1.getRecords();

        //! 获取一级结果字段
        VPXSYResult accountResult2 = JSON.parseObject(accountResultJson2, VPXSYResult.class);
        Integer totalSize2 =  accountResult2.getTotalSize();
        Integer count2 = accountResult2.getCount();
        String accountsStr2 = accountResult2.getRecords();

        //！ 获取records数组
        List<VPXSYAccount> accountList1 = JSONArray.parseArray(accountsStr1,VPXSYAccount.class);
        List<VPXSYAccount> accountList2 = JSONArray.parseArray(accountsStr2,VPXSYAccount.class);
        accountList1.addAll(accountList2);

        ReturnResult<List<VPXSYAccount>> returnResult = new ReturnResult<List<VPXSYAccount>>(ResultEnum.SUCCESS);
        returnResult.setMsg("SUCCESS");
        returnResult.setData(accountList1);

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYOpportunity>> getCrmOpportunityListByDate(String date) {
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String opportunityRequestSql = CRM_QUERY_URL+tokenStr;
        //! 处理时间
        Date yesterday = DateUtils.strToDate(date,"");
        Date dateBeforeYesterday = DateUtils.addDays(yesterday,-1);

        Long yes_timestamp = yesterday.getTime();
        Long b_yes_timestamp = dateBeforeYesterday.getTime();

        //! 拼装account全量sql
        String opportunitySQL1 = SQLUtils.getOpportunityFirstHalfFieldsSql() + SQLUtils.getWhereClause(date);
        String opportunitySQL2 = SQLUtils.getOpportunitySecondHalfFieldsSql() + SQLUtils.getWhereClause(date);
        String opportunitySQL3 = SQLUtils.getOpportunityThirdHalfFieldsSql() + SQLUtils.getWhereClause(date);

        Map<String,String> params1 = new HashMap<String, String>();
        params1.put("q", opportunitySQL1);
        String opportunityResultJson1 = HttpUtils.sendPost(opportunityRequestSql,params1);
        System.out.println("result1 is: "+ opportunityResultJson1);

        Map<String,String> params2 = new HashMap<String, String>();
        params2.put("q", opportunitySQL2);
        String opportunityResultJson2 = HttpUtils.sendPost(opportunityRequestSql,params2);
        System.out.println("result2 is: "+ opportunityResultJson2);

        Map<String,String> params3 = new HashMap<String, String>();
        params3.put("q", opportunitySQL3);
        String opportunityResultJson3 = HttpUtils.sendPost(opportunityRequestSql,params3);
        System.out.println("result3 is: "+ opportunityResultJson3);

        List<VPXSYOpportunity> recordList1 = new ArrayList<>();
        List<VPXSYOpportunity> recordList2 = new ArrayList<>();
        List<VPXSYOpportunity> recordList3 = new ArrayList<>();

        int totalCount = 0;
        try{
            //! 获取一级结果字段
            VPXSYResult crmResult1 = JSON.parseObject(opportunityResultJson1, VPXSYResult.class);
            Integer totalSize1 =  crmResult1.getTotalSize();
            Integer count1 = crmResult1.getCount();
            String recordsStr1 = crmResult1.getRecords();
            //！ 获取records数组
            recordList1 = JSONArray.parseArray(recordsStr1,VPXSYOpportunity.class);

            //! 获取一级结果字段
            VPXSYResult crmResult2 = JSON.parseObject(opportunityResultJson2, VPXSYResult.class);
            Integer totalSize2 =  crmResult2.getTotalSize();
            Integer count2 = crmResult2.getCount();
            String recordsStr2 = crmResult2.getRecords();
            //！ 获取records数组
            recordList2 = JSONArray.parseArray(recordsStr2,VPXSYOpportunity.class);

            //! 获取一级结果字段
            VPXSYResult crmResult3 = JSON.parseObject(opportunityResultJson3, VPXSYResult.class);
            Integer totalSize3 =  crmResult3.getTotalSize();
            Integer count3 = crmResult3.getCount();
            String recordsStr3 = crmResult3.getRecords();
            //！ 获取records数组
            recordList3 = JSONArray.parseArray(recordsStr3,VPXSYOpportunity.class);

            recordList1.addAll(recordList2);
            recordList1.addAll(recordList3);

            totalCount = crmResult1.getTotalSize();
        }
        catch (Exception e){

        }

        ReturnResult<List<VPXSYOpportunity>> returnResult = new ReturnResult<List<VPXSYOpportunity>>(ResultEnum.SUCCESS);
        returnResult.setMsg("totalSize:"+totalCount+", recordList1 size :"+recordList1.size());
        returnResult.setData(recordList1);

        return returnResult;
    }


    @Override
    public ReturnResult<List<VPXSYOrder>> getCrmOrderListByDate(String date) {
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;

        //! 拼装account全量sql
        String orderSQL1 = SQLUtils.getOrderFirstHalfFieldSQL() + SQLUtils.getWhereClause(date);

        String orderSQL2 = SQLUtils.getOrderSecondHalfFieldsSQL() + SQLUtils.getWhereClause(date);

        Map<String,String> params1 = new HashMap<String, String>();
        params1.put("q", orderSQL1);

        Map<String,String> params2 = new HashMap<String, String>();
        params2.put("q", orderSQL2);


        String orderResultJson1 = null;
        String orderResultJson2 = null;
        ReturnResult<List<VPXSYOrder>> returnResult = new ReturnResult<List<VPXSYOrder>>(ResultEnum.SUCCESS);

        try {
            orderResultJson1 = HttpUtils.sendPost(crmObjRequestSql,params1);
            System.out.println("result1 is: "+ orderResultJson1);

            orderResultJson2 = HttpUtils.sendPost(crmObjRequestSql,params2);
            System.out.println("result2 is: "+ orderResultJson2);

            //! 获取一级结果字段
            VPXSYResult orderResult1 = JSON.parseObject(orderResultJson1, VPXSYResult.class);
            Integer totalSize1 =  orderResult1.getTotalSize();
            Integer count1 = orderResult1.getCount();
            String orderStr1 = orderResult1.getRecords();

            //! 获取一级结果字段
            VPXSYResult orderResult2 = JSON.parseObject(orderResultJson2, VPXSYResult.class);
            Integer totalSize2 =  orderResult2.getTotalSize();
            Integer count2 = orderResult2.getCount();
            String orderStr2 = orderResult2.getRecords();

            //！ 获取records数组
            List<VPXSYOrder> orderList1 = JSONArray.parseArray(orderStr1,VPXSYOrder.class);
            //！ 获取records数组
            List<VPXSYOrder> orderList2 = JSONArray.parseArray(orderStr2,VPXSYOrder.class);

            orderList1.addAll(orderList2);

            returnResult.setMsg("SUCCESS");
            returnResult.setData(orderList1);
        }catch (Exception e){
            returnResult.setMsg("Faided!"+ e.getCause().toString());
        }


        return returnResult;
    }


//    @Override
//    public ReturnResult<List<VPXSYOrderProduct>> getCrmOrderProductByDate(String date) {
//
//        //！ 刷新token
//        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
//        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
//        //！ 执行post请求
//        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;
//        //! 处理时间
//        Date yesterday = DateUtils.strToDate(date,"");
//        Date dateBeforeYesterday = DateUtils.addDays(yesterday,-1);
//
//        Long yes_timestamp = yesterday.getTime();
//        Long b_yes_timestamp = dateBeforeYesterday.getTime();
//
//        //! 拼装account全量sql
//        String orderProductSQL = SQLUtils.getOrderProductSQL() +
//                " where createdAt >= " + b_yes_timestamp + " and createdAt <" + yes_timestamp +
//                " limit 1000";
//
//        Map<String,String> params = new HashMap<String, String>();
//        params.put("q", orderProductSQL);
//
//        String orderProductResultJson = null;
//        ReturnResult<List<VPXSYOrderProduct>> returnResult = new ReturnResult<List<VPXSYOrderProduct>>(ResultEnum.SUCCESS);
//
//        try{
//            orderProductResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
//            System.out.println("result1 is: "+ orderProductResultJson);
//            //! 获取一级结果字段
//            VPXSYResult orderProductResult = JSON.parseObject(orderProductResultJson, VPXSYResult.class);
//            Integer totalSize =  orderProductResult.getTotalSize();
//            Integer count = orderProductResult.getCount();
//            String orderProductStr = orderProductResult.getRecords();
//
//            //！ 获取records数组
//            List<VPXSYOrderProduct> orderProductList = JSONArray.parseArray(orderProductStr,VPXSYOrderProduct.class);
//            returnResult.setMsg("SUCCESS");
//            returnResult.setData(orderProductList);
//        }catch (Exception e){
//            returnResult.setMsg("Faided!"+ e.getCause().toString());
//        }
//
//        return returnResult;
//    }


    @Override
    public ReturnResult<List<VPXSYContract>> getCrmContractListByDate(String date) {
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;

        //! 拼装account全量sql
        String contractSQL = SQLUtils.getContractListSQL() + SQLUtils.getWhereClause(date);

        Map<String,String> params = new HashMap<String, String>();
        params.put("q", contractSQL);

        String contractResultJson = null;
        ReturnResult<List<VPXSYContract>> returnResult = new ReturnResult<List<VPXSYContract>>(ResultEnum.SUCCESS);

        try{
            contractResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            System.out.println("result1 is: "+ contractResultJson);
            //! 获取一级结果字段
            VPXSYResult contractResult = JSON.parseObject(contractResultJson, VPXSYResult.class);
            Integer totalSize =  contractResult.getTotalSize();
            Integer count = contractResult.getCount();
            String contractResultRecordsStr = contractResult.getRecords();

            //！ 获取records数组
            List<VPXSYContract> contractList = JSONArray.parseArray(contractResultRecordsStr,VPXSYContract.class);
            returnResult.setMsg("SUCCESS");
            returnResult.setData(contractList);
        }catch (Exception e){
            returnResult.setMsg("Faided!"+ e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYLeads>> getCrmLeadsByDate(String date) {
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;

        //! 拼装leads全量sql
        String leadsSQL = SQLUtils.getLeadsSQL() + SQLUtils.getWhereClause(date);

        Map<String,String> params = new HashMap<String, String>();
        params.put("q", leadsSQL);

        String leadsResultJson = null;
        ReturnResult<List<VPXSYLeads>> returnResult = new ReturnResult<List<VPXSYLeads>>(ResultEnum.SUCCESS);

        try{
            leadsResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            System.out.println("result1 is: "+ leadsResultJson);
            //! 获取一级结果字段
            VPXSYResult leadsResult = JSON.parseObject(leadsResultJson, VPXSYResult.class);
            Integer totalSize =  leadsResult.getTotalSize();
            Integer count = leadsResult.getCount();
            String leadsResultRecordsStr = leadsResult.getRecords();

            //！ 获取records数组
            List<VPXSYLeads> leadList = JSONArray.parseArray(leadsResultRecordsStr,VPXSYLeads.class);
            returnResult.setMsg("SUCCESS");
            returnResult.setData(leadList);
        }catch (Exception e){
            returnResult.setMsg("Faided!"+ e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYPayment>> getCrmPaymentByDate(String date) {
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;

        //! 拼装payment全量sql
        String paymentSQL = SQLUtils.getPaymentSQL() + SQLUtils.getWhereClause(date);
        Map<String,String> params = new HashMap<String, String>();
        params.put("q", paymentSQL);

        String paymentResultJson = null;
        ReturnResult<List<VPXSYPayment>> returnResult = new ReturnResult<List<VPXSYPayment>>(ResultEnum.SUCCESS);

        try{
            paymentResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            System.out.println("result1 is: "+ paymentResultJson);
            //! 获取一级结果字段
            VPXSYResult paymentResult = JSON.parseObject(paymentResultJson, VPXSYResult.class);
            Integer totalSize =  paymentResult.getTotalSize();
            Integer count = paymentResult.getCount();
            String paymentResultResultRecordsStr = paymentResult.getRecords();

            //！ 获取records数组
            List<VPXSYPayment> paymentList = JSONArray.parseArray(paymentResultResultRecordsStr,VPXSYPayment.class);
            returnResult.setMsg("SUCCESS");
            returnResult.setData(paymentList);
        }catch (Exception e){
            returnResult.setMsg("Faided!"+ e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYOrderPaymentResult> getCrmPaymentList(@PathVariable String orderId) {

        ReturnResult<VPXSYOrderPaymentResult> returnResult = new ReturnResult<VPXSYOrderPaymentResult>(ResultEnum.SUCCESS);
        if (null == orderId || "".equals(orderId)) {
            returnResult.setData(null);
            returnResult.setMsg("orderId is null");
            returnResult.setCode(ResultEnum.PARAM_ERROR.getCode());
            return returnResult;
        }

        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_ORDER_PAYMENTS+tokenStr;



        Map<String,String> params = new HashMap<String, String>();
        params.put("id", orderId);

        String orderPaymentResultJson = null;
        try {
            orderPaymentResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (orderPaymentResultJson != null) {
                VPXSYOrderPaymentResult orderPaymentResult = JSONArray.parseObject(orderPaymentResultJson,VPXSYOrderPaymentResult.class);
                returnResult.setCode(ResultEnum.SUCCESS.getCode());
                returnResult.setData(orderPaymentResult);
                returnResult.setMsg("SUCCESS");
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYUser>> getCrmUsersByDate(String date) {

        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;


        //! 拼装user全量sql
        String userSQL = SQLUtils.getUserSQL() + SQLUtils.getWhereClause(date);

        Map<String,String> params = new HashMap<String, String>();
        params.put("q", userSQL);

        String userResultJson = null;
        ReturnResult<List<VPXSYUser>> returnResult = new ReturnResult<List<VPXSYUser>>(ResultEnum.SUCCESS);

        try{
            userResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            System.out.println("result1 is: "+ userResultJson);
            //! 获取一级结果字段
            VPXSYResult userResult = JSON.parseObject(userResultJson, VPXSYResult.class);
            Integer totalSize =  userResult.getTotalSize();
            Integer count = userResult.getCount();
            String userResultResultRecordsStr = userResult.getRecords();

            //！ 获取records数组
            List<VPXSYUser> userList = JSONArray.parseArray(userResultResultRecordsStr,VPXSYUser.class);
            returnResult.setMsg("SUCCESS");
            returnResult.setData(userList);
        }catch (Exception e){
            returnResult.setMsg("Faided!"+ e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYResponsibility>> getCrmResponsibilityList() {
        ReturnResult<List<VPXSYResponsibility>> returnResult = new ReturnResult<List<VPXSYResponsibility>>(ResultEnum.SUCCESS);

        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_RESPONSIBILITY_LIST+tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        String responsibilityResultJson = null;
        try {
            responsibilityResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (responsibilityResultJson != null) {
                System.out.println(responsibilityResultJson);
                VPXSYApiResult responsibilityResult = JSON.parseObject(responsibilityResultJson,VPXSYApiResult.class);
                if (responsibilityResult!=null ) {
                    List<VPXSYResponsibility> responsibilities = JSON.parseArray(responsibilityResult.getRecord(),VPXSYResponsibility.class);
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(responsibilities);
                    returnResult.setMsg("SUCCESS");
                }

            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYDeptNode> getCrmDepartTree() {
        ReturnResult<VPXSYDeptNode> returnResult = new ReturnResult<VPXSYDeptNode>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_DEPT_TREE+tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        String deptResultJson = null;
        try {
            deptResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (deptResultJson != null) {
                System.out.println(deptResultJson);
                VPXSYDeptNode deptResult = JSON.parseObject(deptResultJson,VPXSYDeptNode.class);
                if (deptResult!=null ) {

                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(deptResult);
                    returnResult.setMsg("SUCCESS");
                }

            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }


    @Override
    public ReturnResult<VPXSYDept> getCrmDepartTree(@PathVariable  String id) {

        ReturnResult<VPXSYDept> returnResult = new ReturnResult<VPXSYDept>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_DEPT_INFO+tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String deptResultJson = null;
        try {
            deptResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (deptResultJson != null) {
                System.out.println(deptResultJson);
                VPXSYDept deptResult = JSON.parseObject(deptResultJson,VPXSYDept.class);
                if (deptResult!=null ) {

                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(deptResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYContact>> getCrmContact(@PathVariable String date) {
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_QUERY_URL+tokenStr;

        String contactSQL = SQLUtils.getCrmContactSQL() + SQLUtils.getWhereClause(date);

        Map<String,String> params = new HashMap<String, String>();
        params.put("q", contactSQL);

        String contactResultJson = null;
        ReturnResult<List<VPXSYContact>> returnResult = new ReturnResult<List<VPXSYContact>>(ResultEnum.SUCCESS);

        try{
            contactResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            System.out.println("result1 is: "+ contactResultJson);
            //! 获取一级结果字段
            VPXSYResult userResult = JSON.parseObject(contactResultJson, VPXSYResult.class);
            Integer totalSize =  userResult.getTotalSize();
            Integer count = userResult.getCount();
            String contactResultResultRecordsStr = userResult.getRecords();

            //！ 获取records数组
            List<VPXSYContact> contactList = JSONArray.parseArray(contactResultResultRecordsStr,VPXSYContact.class);
            returnResult.setMsg("SUCCESS");
            returnResult.setData(contactList);
        }catch (Exception e){
            returnResult.setMsg("Faided!"+ e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYContact> getCrmContactById(@PathVariable  String id) {


        ReturnResult<VPXSYContact> returnResult = new ReturnResult<VPXSYContact>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_CONTACT_INFO + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String contactResultJson = null;
        try {
            contactResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (contactResultJson != null) {
                System.out.println(contactResultJson);
                VPXSYContact contactResult = JSON.parseObject(contactResultJson,VPXSYContact.class);
                if (contactResult!=null ) {

                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(contactResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYAccount> getCrmCustomerById(@PathVariable  String id) {
        ReturnResult<VPXSYAccount> returnResult = new ReturnResult<VPXSYAccount>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_CUSTOMER_INFO + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String accountResultJson = null;
        try {
            accountResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (accountResultJson != null) {
                System.out.println(accountResultJson);
                VPXSYAccount contactResult = JSON.parseObject(accountResultJson, VPXSYAccount.class);
                if (contactResult!=null ) {

                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(contactResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }


    @Override
    public ReturnResult<VPXSYOpportunity> getCrmOpportunityById(@PathVariable  String id) {
        ReturnResult<VPXSYOpportunity> returnResult = new ReturnResult<VPXSYOpportunity>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_OPPORTUNITY_INFO + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String oppResultJson = null;
        try {
            oppResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (oppResultJson != null) {
                System.out.println(oppResultJson);
                VPXSYOpportunity opportunityResult = JSON.parseObject(oppResultJson, VPXSYOpportunity.class);
                if (opportunityResult!=null ) {

                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(opportunityResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYOrder> getCrmOrderById(@PathVariable String id) {
        ReturnResult<VPXSYOrder> returnResult = new ReturnResult<VPXSYOrder>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_ORDER_INFO + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String orderResultJson = null;
        try {
            orderResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (orderResultJson != null) {
                System.out.println(orderResultJson);
                VPXSYOrder orderResult = JSON.parseObject(orderResultJson, VPXSYOrder.class);
                if (orderResult!=null ) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(orderResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYContract> getCrmContractById(@PathVariable  String id) {
        ReturnResult<VPXSYContract> returnResult = new ReturnResult<VPXSYContract>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_CONTRACT_INFO + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String contractResultJson = null;
        try {
            contractResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (contractResultJson != null) {
                System.out.println(contractResultJson);
                VPXSYContract orderResult = JSON.parseObject(contractResultJson, VPXSYContract.class);
                if (orderResult!=null ) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(orderResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }


    @Override
    public ReturnResult<VPXSYContractPayments> getCrmContractPaymentsInfoById(@PathVariable  String id) {
        ReturnResult<VPXSYContractPayments> returnResult = new ReturnResult<VPXSYContractPayments>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_CONTRACT_PAYMENTS + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String contractPaymentsResultJson = null;
        try {
            contractPaymentsResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (contractPaymentsResultJson != null) {
                System.out.println(contractPaymentsResultJson);
                VPXSYContractPayments orderResult = JSON.parseObject(contractPaymentsResultJson, VPXSYContractPayments.class);
                if (orderResult!=null ) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(orderResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<VPXSYProduct> getCrmProductInfoById(@PathVariable  String id) {
        ReturnResult<VPXSYProduct> returnResult = new ReturnResult<VPXSYProduct>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_PRODUCT + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("id",id);
        String productResultJson = null;
        try {
            productResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (productResultJson != null) {
                System.out.println(productResultJson);
                VPXSYProduct orderResult = JSON.parseObject(productResultJson, VPXSYProduct.class);
                if (orderResult!=null ) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(orderResult);
                    returnResult.setMsg("SUCCESS");
                }
            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYProduct>> getCrmProductList() {
        ReturnResult<List<VPXSYProduct>> returnResult = new ReturnResult<List<VPXSYProduct>>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_PRODUCT_LIST + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        String productResultJson = null;
        try {
            productResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (productResultJson != null) {
                System.out.println(productResultJson);
                VPXSYProductResult productResult = JSON.parseObject(productResultJson, VPXSYProductResult.class);
                if (productResult.getProducts() != null) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(productResult.getProducts());
                    returnResult.setMsg("SUCCESS");
                }

            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<VPXSYUserResponsibility>> getCrmUserResponsibilityById(@PathVariable  String id) {
        ReturnResult<List<VPXSYUserResponsibility>> returnResult = new ReturnResult<List<VPXSYUserResponsibility>>(ResultEnum.SUCCESS);
        //！ 刷新token
        ReturnResult<VPXSYTokenModel> token = this.crmAccessToken();
        String tokenStr = "?access_token=Bearer "+token.getData().getAccess_token();
        //！ 执行post请求
        String crmObjRequestSql = CRM_USER_RESPON_LIST + tokenStr;

        Map<String,String> params = new HashMap<String, String>();
        params.put("userId",id);
        String userResResultJson = null;
        try {
            userResResultJson = HttpUtils.sendPost(crmObjRequestSql,params);
            if (userResResultJson != null) {
                System.out.println(userResResultJson);
                VPXSYResult result = JSON.parseObject(userResResultJson, VPXSYResult.class);
                if (result.getRecords()!= null) {
                    List<VPXSYUserResponsibility> userRespRecords = JSONArray.parseArray(result.getRecords(), VPXSYUserResponsibility.class);
                    if (userRespRecords != null) {
                        returnResult.setCode(ResultEnum.SUCCESS.getCode());
                        returnResult.setData(userRespRecords);
                        returnResult.setMsg("SUCCESS");
                    }
                }


            }

        }catch (Exception e) {
            returnResult.setCode(ResultEnum.SUCCESS.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }
}
