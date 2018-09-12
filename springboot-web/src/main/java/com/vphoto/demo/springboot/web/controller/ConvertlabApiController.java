package com.vphoto.demo.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.vphoto.demo.springboot.facade.ConvertlabApi;
import com.vphoto.demo.springboot.model.convertlab.*;
import com.vphoto.demo.springboot.model.crm.VPXSYResult;
import com.vphoto.demo.springboot.model.crm.VPXSYTokenModel;
import com.vphoto.demo.springboot.model.crm.VPXSYUserResponsibility;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.utils.HttpUtils;
import com.vphoto.demo.springboot.web.config.ConvertlabConfig;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;


import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vphoto.demo.springboot.constants.AppConstants.*;


@RestController
public class ConvertlabApiController implements ConvertlabApi {

    @Autowired
    private ConvertlabConfig cbConfig;

    @Override
    public ReturnResult<String> testCrawlApi() {
        ReturnResult<String> resultString = new ReturnResult<String>(ResultEnum.SUCCESS);

        // 登陆 Url
        String loginUrl = "https://app.convertlab.com/login.html";
        // 需登陆后访问的 Url
        String dataUrl = "https://app.convertlab.com/index.html?showMyTenants=true";
        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(loginUrl);

        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = {
                new NameValuePair("username", "13816209387"),
                new NameValuePair("password", "Hello1234")
        };
        postMethod.setRequestBody(data);
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(postMethod);

            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
                System.out.println("cookies = "+c.toString());
            }
            if(statusCode==302){//重定向到新的URL
                System.out.println("模拟登录成功");
                // 进行登陆后的操作
                GetMethod getMethod = new GetMethod(dataUrl);
                // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
                // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
                postMethod.setRequestHeader("Referer", "https://app.convertlab.com/login.html");
                postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
                httpClient.executeMethod(getMethod);
                // 打印出返回数据，检验一下是否成功
                String text = getMethod.getResponseBodyAsString();
//                System.out.println(text);

                //! 尝试获取想要的接口
                Map<String,String> headers = new HashMap<String,String>();

                headers.put("tenantId","1587");
                headers.put("cookie",tmpcookies.toString());
                headers.put("Referer","https://app.convertlab.com/application/plugin/refer/public.html");
                headers.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

                String testJson = HttpUtils.sendPost("https://app.convertlab.com/referplan/fansNum",null,null,"{\"accountId\":1369,\"isOpen\":1}",headers);
                resultString.setMsg(testJson);
            }
            else {
                System.out.println("登录失败");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return resultString;
    }



    public String refreshAccessToken() {
        String grantType = cbConfig.getGrantType();
        String appId = cbConfig.getAppid();
        String secret = cbConfig.getSecret();
        String accessTokenUrl = CONVERTLAB_ACCESSTOKEN + "?grant_type="+grantType+"&appid="+appId+"&secret="+secret;
        System.out.println(accessTokenUrl);
        String tokenResultJson = HttpUtils.sendGet(accessTokenUrl);
        Map json = JSONObject.parseObject(tokenResultJson);
        String convertlabAccessToken = json.get("access_token").toString();
        return convertlabAccessToken;
    }

    @Override
    public ReturnResult<String> getConvertlabAccessToken() {
        ReturnResult<String> returnResult = new ReturnResult<String>(ResultEnum.SUCCESS);
        String accessToken = refreshAccessToken();
        if (!"".equals(accessToken)) {
            returnResult.setMsg("success");
            returnResult.setCode(200);
            returnResult.setData(accessToken);
        }
        return returnResult;
    }

    @Override
    public ReturnResult<List<GroupModel>> getConvertlabGroupList() {
        ReturnResult<List<GroupModel>> returnResult = new ReturnResult<List<GroupModel>>(ResultEnum.SUCCESS);
        //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_GROUP_LIST+"?access_token="+ token;
//        Map<String,String> param = new HashMap<String,String>();
//        param.put("access_token", "4a94148068f82e2e717ccafc6e913dac");
        String resultJson = null;
        try {
            resultJson = HttpUtils.sendGet(requestUrl);
            if (resultJson != null) {
                System.out.println(resultJson);
                ConvertlabResult result = JSON.parseObject(resultJson, ConvertlabResult.class);
                if (result.getRows() != null) {
                    List<GroupModel> rows = result.getRows();
                    if (rows != null) {
                        returnResult.setCode(ResultEnum.SUCCESS.getCode());
                        returnResult.setData(rows);
                        returnResult.setMsg("SUCCESS");
                    }
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<GroupMember>> getConvertlabGroupmembers(@PathVariable  String listId, @PathVariable(value="rows",required = false) String rows) {
        ReturnResult<List<GroupMember>> returnResult = new ReturnResult<List<GroupMember>>(ResultEnum.SUCCESS);
        //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_GROUP_MEMBERS+"?access_token="+ token;
        Map<String, String> param = new HashMap<String, String>();
        param.put("listId", listId);
        if (!"".equals(rows) && !"undefined".equals(rows) && Long.parseLong(rows) > 0) {
            param.put("rows", rows);
        }
        String resultJson = null;
        try {
            resultJson = HttpUtils.doGet(requestUrl, param);
            if (resultJson != null) {
                System.out.println(resultJson);
                ConvertlabGroupMembersResult result = JSON.parseObject(resultJson, ConvertlabGroupMembersResult.class);

                if (result.getItems() != null) {
                    List<GroupMember> items = result.getItems();
                    if (items != null) {
                        returnResult.setCode(ResultEnum.SUCCESS.getCode());
                        returnResult.setData(items);
                        returnResult.setMsg("SUCCESS");
                    }
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<Customer> getConvertlabSingleCustomer(@PathVariable  String customerId) {

        ReturnResult<Customer> returnResult = new ReturnResult<Customer>(ResultEnum.SUCCESS);
        if ("".equals(customerId)) {
            returnResult.setData(null);
            returnResult.setCode(ResultEnum.PARAM_ERROR.getCode());
            returnResult.setMsg("customerId is empty!");
            return returnResult;
        }
                //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_SINGLE_CUSTOMER + customerId +"?access_token="+ token;

        String resultJson = null;
        try {
            resultJson = HttpUtils.doGet(requestUrl,null);
            if (resultJson != null) {
                System.out.println(resultJson);
                Customer result = JSON.parseObject(resultJson, Customer.class);

                if (result != null) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(result);
                    returnResult.setMsg("SUCCESS");
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }


    @Override
    public ReturnResult<List<Customer>> getConvertlabCustomerByIds(String idList, String rows) {
        ReturnResult<List<Customer>> returnResult = new ReturnResult<List<Customer>>(ResultEnum.SUCCESS);
        if ("".equals(idList)) {
            returnResult.setData(null);
            returnResult.setCode(ResultEnum.PARAM_ERROR.getCode());
            returnResult.setMsg("customerIds is empty!");
            return returnResult;
        }
        //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_CUSTOMERS +"?access_token="+ token;
        Map<String,String> param = new HashMap<String,String>();
        param.put("idList",idList);
        if (null!=rows && !"".equals(rows)) {
            param.put("rows",rows);
        }
        String resultJson = null;
        try {
            resultJson = HttpUtils.doGet(requestUrl,param);
            if (resultJson != null) {
                System.out.println(resultJson);
                ConvertlabCustomersResult result = JSON.parseObject(resultJson, ConvertlabCustomersResult.class);
                if (result.getRows() != null) {
                    List<Customer> customers = result.getRows();
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(customers);
                    returnResult.setMsg("SUCCESS");
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<CustomerIdentity>> getConvertlabCustomerIdentitiesByIds(String idList) {
        ReturnResult<List<CustomerIdentity>> returnResult = new ReturnResult<List<CustomerIdentity>>(ResultEnum.SUCCESS);
        if ("".equals(idList)) {
            returnResult.setData(null);
            returnResult.setCode(ResultEnum.PARAM_ERROR.getCode());
            returnResult.setMsg("idList is empty!");
            return returnResult;
        }
        //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_CUSTOMER_IDENTITY +"?access_token="+ token;
        Map<String,String> param = new HashMap<String,String>();
        param.put("customerIds",idList);

        String resultJson = null;
        try {
            resultJson = HttpUtils.doGet(requestUrl,param);
            if (resultJson != null) {
                System.out.println(resultJson);
                List<CustomerIdentity> result = JSONArray.parseArray(resultJson, CustomerIdentity.class);
                if (result != null) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(result);
                    returnResult.setMsg("SUCCESS");
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }

    @Override
    public ReturnResult<List<ReferralModel>> getConvertlabReferralDetailsForFan(String openId, String referPlan, String eventName) {
        ReturnResult<List<ReferralModel>> returnResult = new ReturnResult<List<ReferralModel>>(ResultEnum.SUCCESS);
        if ("".equals(openId) || "".equals(referPlan)) {
            returnResult.setData(null);
            returnResult.setCode(ResultEnum.PARAM_ERROR.getCode());
            returnResult.setMsg("openId or referPlan is empty!");
            return returnResult;
        }
        //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_REFERRAL_DETAILS_FOR_FAN +"?access_token="+ token;
        Map<String,String> param = new HashMap<String,String>();
        param.put("openId", openId);
        param.put("referPlan", referPlan);
        if (null != eventName) {
            param.put("eventName", eventName);
        }else {
            param.put("eventName", "wechat_scan");
        }

        String resultJson = null;
        try {
            resultJson = HttpUtils.doGet(requestUrl,param);
            if (resultJson != null) {
                System.out.println(resultJson);
                ReferResult referResult = JSONArray.parseObject(resultJson, ReferResult.class);
                System.out.println(referResult);
                if (referResult != null) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(referResult.getRows());
                    returnResult.setMsg("SUCCESS");
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }

        return returnResult;
    }


    @Override
    public ReturnResult<List<ReferralModel>> getConvertlabReferralDetails(String referPlan, String eventName) {
        ReturnResult<List<ReferralModel>> returnResult = new ReturnResult<List<ReferralModel>>(ResultEnum.SUCCESS);
        if ("".equals(referPlan)) {
            returnResult.setData(null);
            returnResult.setCode(ResultEnum.PARAM_ERROR.getCode());
            returnResult.setMsg("referPlan is empty!");
            return returnResult;
        }
        //！ 刷新token
        String token = this.refreshAccessToken();
        //！ 执行post请求
        String requestUrl = CONVERTLAB_REFERRAL_DETAILS +"?access_token="+ token;
        Map<String,String> param = new HashMap<String,String>();
        param.put("referPlan", referPlan);
        if (null != eventName) {
            param.put("eventName", eventName);
        }

        String resultJson = null;
        try {
            resultJson = HttpUtils.doGet(requestUrl,param);
            if (resultJson != null) {
                System.out.println(resultJson);
                ReferResult referResult = JSONArray.parseObject(resultJson, ReferResult.class);

                if (referResult != null) {
                    returnResult.setCode(ResultEnum.SUCCESS.getCode());
                    returnResult.setData(referResult.getRows());
                    returnResult.setMsg("SUCCESS");
                }
            }
        }catch (Exception e) {
            returnResult.setCode(ResultEnum.FAILURE.getCode());
            returnResult.setMsg("FAILED:"+e.getCause().toString());
        }
        return returnResult;
    }

    @Override
    public ReturnResult<List<FissionReferPlan>> getAllFissionReferPlan() {
        ReturnResult<List<FissionReferPlan>> resultString = new ReturnResult<List<FissionReferPlan>>(ResultEnum.SUCCESS);

        // 登陆 Url
        String loginUrl = "https://app.convertlab.com/login.html";
        // 需登陆后访问的 Url
        String dataUrl = "https://app.convertlab.com/index.html?showMyTenants=true";
        HttpClient httpClient = new HttpClient();

        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式
        PostMethod postMethod = new PostMethod(loginUrl);

        // 设置登陆时要求的信息，用户名和密码
        NameValuePair[] data = {
                new NameValuePair("username", "13816209387"),
                new NameValuePair("password", "Hello1234")
        };
        postMethod.setRequestBody(data);
        try {
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode=httpClient.executeMethod(postMethod);

            // 获得登陆后的 Cookie
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
                System.out.println("cookies = "+c.toString());
            }
            if(statusCode==302){//重定向到新的URL
                System.out.println("模拟登录成功");
                // 进行登陆后的操作
                GetMethod getMethod = new GetMethod(dataUrl);
                // 每次访问需授权的网址时需带上前面的 cookie 作为通行证
                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据
                // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外
                postMethod.setRequestHeader("Referer", "https://app.convertlab.com/login.html");
                postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
                httpClient.executeMethod(getMethod);
                // 打印出返回数据，检验一下是否成功
                String text = getMethod.getResponseBodyAsString();
//                System.out.println(text);

                //! 尝试获取想要的接口
                Map<String,String> headers = new HashMap<String,String>();
                headers.put("host","app.convertlab.com");
                headers.put("tenantId","1587");
                headers.put("cookie",tmpcookies.toString());
                headers.put("Referer","https://app.convertlab.com/application/plugin/refer/public.html");
                headers.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36");

                String testJson = HttpUtils.sendGet(CONVERTLAB_REFER_PLAN,null, headers);
                List<FissionReferPlan> fissionReferPlans = JSONArray.parseArray(testJson, FissionReferPlan.class);
                resultString.setMsg("SUCCESS");
                resultString.setData(fissionReferPlans);
                resultString.setCode(ResultEnum.SUCCESS.getCode());

            }
            else {
                System.out.println("登录失败");
            }
        }
        catch (Exception e) {
            resultString.setCode(ResultEnum.FAILURE.getCode());
            resultString.setMsg("FAILED:"+e.getCause().toString());
        }

        return resultString;
    }
}
