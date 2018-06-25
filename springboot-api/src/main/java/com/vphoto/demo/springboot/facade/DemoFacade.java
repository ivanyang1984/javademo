/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 服务接口定义
 * Version: 1.0
 * Date: 18-5-8 下午6:12
 * LastModified: 18-5-8 下午6:12
 */

package com.vphoto.demo.springboot.facade;

import com.vphoto.demo.springboot.model.DemoModel;
import com.vphoto.demo.springboot.model.IpModel;
import com.vphoto.demo.springboot.model.VBoxLogModel;
import com.vphoto.demo.springboot.model.VPhotoUser;
import com.vphoto.demo.springboot.model.result.ReturnPageResult;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api(value = "测试服务接口", description="测试信息")
@RequestMapping(value="/demo/v1")
public interface DemoFacade {

    @ApiOperation(value = "vbox 日志信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST",
            notes="vbox 日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vBoxLogModel", value = "vboxlog", required = true, dataType = "com.vphoto.demo.springboot.model.VBoxLogModel", paramType = "body")
    })
    @RequestMapping(value = {"/sendLog2sensor"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<VBoxLogModel> importVBoxLog2Sensor(@RequestBody VBoxLogModel vBoxLogModel);

    @ApiOperation(value = "vbox 日志信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST",
            notes="vbox 日志信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vPhotoUser", value = "vPhotoUser", required = true, dataType = "com.vphoto.demo.springboot.model.VPhotoUser", paramType = "body")
    })
    @RequestMapping(value = {"/registerUser2sensor"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<VPhotoUser> registerUser(@RequestBody VPhotoUser vPhotoUser);


    @ApiOperation(value = "新建测试信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST",
            notes="新建测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "demoModel", value = "测试对象", required = true, dataType = "com.vphoto.demo.springboot.model.DemoModel", paramType = "body")
    })
    @RequestMapping(value = {"/demo"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<DemoModel> createDemo(@RequestBody DemoModel demoModel);


    @ApiOperation(value = "查询神策数据测试",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "POST",
            notes="查询神策数据测试")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "测试对象id", required = true, dataType = "Long", paramType = "path")
//    })
    @RequestMapping(value = {"/demo/queryTestSensor"},
            method = {RequestMethod.POST},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult queryTestSensor();


    @ApiOperation(value = "查询测试信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="查询测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "测试对象id", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/demo/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult queryById(@PathVariable("id") Long id);




    @ApiOperation(value = "删除测试信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="删除测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "测试对象id", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/demo/{id}"},
            method = {RequestMethod.DELETE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult deleteById(@PathVariable("id") Long id);



    @ApiOperation(value = "更新测试信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="删除测试信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "测试对象id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "demoModel", value = "测试对象", required = true, dataType = "com.vphoto.demo.springboot.model.DemoModel", paramType = "body")
    })
    @RequestMapping(value = {"/demo/{id}"},
            method = {RequestMethod.PUT},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<DemoModel> updateById(@PathVariable("id") Long id, @RequestBody DemoModel demoModel);




    @ApiOperation(value = "分页查询",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "编号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = true, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value = {"/demo"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<ReturnPageResult<DemoModel>> queryByPage(@RequestParam(name = "code", required = false) String code,
                                                                 @RequestParam(name = "status", required = false) Integer status,
                                                                 @RequestParam("pageNum") Long pageNum,
                                                                 @RequestParam("pageSize") Long pageSize);







    @ApiOperation(value = "查询Ip信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="查询Ip信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ip", value = "ip地址", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/ip"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ReturnResult<IpModel> queryByIp(@RequestParam("ip") String ip);

}

