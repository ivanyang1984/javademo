package com.vphoto.demo.springboot.facade;

import com.sun.tools.javac.util.Convert;
import com.vphoto.demo.springboot.model.convertlab.*;
import com.vphoto.demo.springboot.model.crm.VPXSYUserResponsibility;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface ConvertlabApi {

    @ApiOperation(value = "测试一下convertlab爬虫",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="测试一下convertlab爬虫")
    @RequestMapping(value = {"/convertlab/testCrawlApi"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<String> testCrawlApi();


    @ApiOperation(value = "获取Convertlab 测试环境accessToken",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取Convertlab 测试环境accessToken")
    @RequestMapping(value = {"/convertlab/accessToken"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<String> getConvertlabAccessToken();


    @ApiOperation(value = "获取Convertlab 群组列表",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/lists")
    @RequestMapping(value = {"/convertlab/grouplist"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<GroupModel>> getConvertlabGroupList();


    @ApiOperation(value = "获取Convertlab 群组中的成员",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/listMembers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "listId", value = "listId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "rows", value = "rows", dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/convertlab/groupmembers/{listId}/{rows}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<GroupMember>> getConvertlabGroupmembers(@PathVariable("listId") String listId, @PathVariable(value = "rows",required = false) String rows);


    @ApiOperation(value = "获取Convertlab 单个客户数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/customers/{id}?access_token={access_token}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "customerId", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/convertlab/singleCustomer/{customerId}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<Customer> getConvertlabSingleCustomer(@PathVariable("customerId") String customerId);

    @ApiOperation(value = "获取Convertlab 多个客户数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/customers")
    @RequestMapping(value = {"/convertlab/customersByIds"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<Customer>> getConvertlabCustomerByIds(@PathVariable  String idList, @PathVariable String rows);

    @ApiOperation(value = "获取Convertlab 获取客户身份",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/customeridentities")
    @RequestMapping(value = {"/convertlab/customersIdentityByIds"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<CustomerIdentity>> getConvertlabCustomerIdentitiesByIds(@PathVariable String idList);

    @ApiOperation(value = "获取Convertlab 获取推广人信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/referralDetailsForFan")
    @RequestMapping(value = {"/convertlab/referralDetailsForFan"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<ReferralModel>> getConvertlabReferralDetailsForFan(@PathVariable String openId, @PathVariable String referPlan, @PathVariable(required = false) String eventName);


    @ApiOperation(value = "获取Convertlab 获取推广成绩",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="https://api.convertlab.com/v1/referralDetails")
    @RequestMapping(value = {"/convertlab/referralDetails"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<ReferralModel>> getConvertlabReferralDetails(@PathVariable String referPlan, @PathVariable(required = false) String eventName);
}
