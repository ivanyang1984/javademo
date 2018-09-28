package com.vphoto.demo.springboot.facade;

import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.model.sa.SACityCateDimTotalModel;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface SAApi {

    @ApiOperation(value = "神策数据计算按 城市，类目维度 获取 订单和用户数",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="神策数据计算按 城市，类目维度 获取 订单和用户数")
    @RequestMapping(value = {"/vpsa/city_cate_total"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<SACityCateDimTotalModel>> getCityCateTotal();
}
