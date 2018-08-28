package com.vphoto.demo.springboot.facade;

import com.vphoto.demo.springboot.model.convertlab.GroupModel;
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


}
