package com.vphoto.demo.springboot.facade;

import com.vphoto.demo.springboot.model.crm.VPXSYAccount;
import com.vphoto.demo.springboot.model.es.ESResult;
import com.vphoto.demo.springboot.model.es.ESVPVisitResult;
import com.vphoto.demo.springboot.model.es.VPESVisitData;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Api(value = "相册服务接口", description="VPhoto 相册PVUV 数据")
@RequestMapping(value="/es/v1")
public interface ESDataApi {

    @ApiOperation(value = "获取订单列表和ES",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取订单列表和ES")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/getOrderListAndEs/{orderId}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<ESResult> getOrderListAndEs(@PathVariable("orderId") String orderId);


    @ApiOperation(value = "获取访问方式",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取访问方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/getVisitSource/{orderId}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPESVisitData> getVisitSource(@PathVariable("orderId") String orderId);
}
