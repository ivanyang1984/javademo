package com.vphoto.demo.springboot.facade;

import com.vphoto.demo.springboot.model.VBoxLogModel;
import com.vphoto.demo.springboot.model.crm.VPXSYAccount;
import com.vphoto.demo.springboot.model.crm.VPXSYTokenModel;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "CRM服务接口", description="VPhoto CRM 数据")
@RequestMapping(value="/crm/v1")
public interface CRMApi {

    @ApiOperation(value = "通过密码方式获取token",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST",
            notes="通过密码方式获取token")
    @RequestMapping(value = {"/access_token"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYTokenModel> crmAccessToken();


    @ApiOperation(value = "按天获取CRM客户数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="按天获取CRM客户数据")
    @RequestMapping(value = {"/crmAccountByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYAccount>> getCrmAccountListByDate(@PathVariable("date") String date);
}
