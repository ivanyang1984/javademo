/*
 *
 *  * ProjectName: springboot-parent
 *  * Author: tree.yu
 *  * Description:
 *  * Version: 1.0
 *  * Date: 18-5-9 上午9:39
 *  * LastModified: 18-5-9 上午9:39
 *
 */

package com.vphoto.demo.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import com.vphoto.demo.springboot.constants.AppConstants;
import com.vphoto.demo.springboot.exception.Validation;
import com.vphoto.demo.springboot.facade.DemoFacade;
import com.vphoto.demo.springboot.model.DemoModel;
import com.vphoto.demo.springboot.model.IpModel;
import com.vphoto.demo.springboot.model.VBoxLogModel;
import com.vphoto.demo.springboot.model.VPhotoUser;
import com.vphoto.demo.springboot.model.convertlab.GroupModel;
import com.vphoto.demo.springboot.model.convertlab.ReferralModel;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.CallResult;
import com.vphoto.demo.springboot.model.result.ReturnPageResult;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.service.DemoService;
import com.vphoto.demo.springboot.utils.HttpUtils;
import com.vphoto.demo.springboot.utils.LogModel;
import com.vphoto.demo.springboot.utils.ObjectUtils;
import com.vphoto.demo.springboot.web.context.HttpContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sensorsdata.analytics.javasdk.*;

import static com.vphoto.demo.springboot.constants.AppConstants.CONVERTLAB_APP_ID;
import static com.vphoto.demo.springboot.constants.AppConstants.CONVERTLAB_APP_SECRET;
import static com.vphoto.demo.springboot.constants.AppConstants.SA_SERVER_URL;

@RestController
public class DemoController extends BaseController implements DemoFacade {

    @Autowired
    private DemoService demoService;

    /**
     * convert api请求token
     */
    private String convertlabAccessToken;

    public static SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.DebugConsumer("https://sensors.vphotos.cn:8106/sa?project=default", true));

    @Override
    public ReturnResult<VBoxLogModel> importVBoxLog2Sensor(@RequestBody VBoxLogModel vBoxLogModel) {
        ReturnResult<VBoxLogModel> returnResult = new ReturnResult<VBoxLogModel>(
                ResultEnum.SUCCESS);



        String userId = vBoxLogModel.getVboxCode();
        String eventName = vBoxLogModel.getTaskType();


//        Map<String, Object> properties = new HashMap<String, Object>();
//        // mediatracelog
//        properties.put("Timestamp", vBoxLogModel.getTimestamp());
//        properties.put("Version", vBoxLogModel.getVersion());
//        properties.put("FileName", vBoxLogModel.getFileName());
//        properties.put("OrderId", vBoxLogModel.getOrderId());  // 订单ID
//        properties.put("TaskSeq", vBoxLogModel.getTaskSeq());  // 任务工序
//        properties.put("MsgId", vBoxLogModel.getMsgId());  // 消息编号
//        properties.put("Type", vBoxLogModel.getType());   // 日志类别
//        properties.put("TaskCode", vBoxLogModel.getTaskCode());  // 任务状态码 0 成功 其他都是失败
//        properties.put("Host", vBoxLogModel.getHost());   // 在哪台机器
//        properties.put("StartTime", vBoxLogModel.getStartTime());// 开始时间
//        properties.put("Id", vBoxLogModel.getId());  // ID
//        properties.put("FileLength", vBoxLogModel.getFileLength());  // 文件字节长度
//        properties.put("Height", vBoxLogModel.getHeight());  // 图片高度
//        properties.put("MissionId", vBoxLogModel.getPhotoAlbumType());  // 任务ID
//        properties.put("PhotoAlbumType", vBoxLogModel.getId());  // 相册类型2：调图 4：直传
//        properties.put("FileCode", vBoxLogModel.getFilecode());  // 文件编码
//        properties.put("Index", vBoxLogModel.getIndex());  // ES 索引
//        properties.put("PhotoName", vBoxLogModel.getPhotoName());  // 照片名字
//        properties.put("VBoxVersion", vBoxLogModel.getVboxVersion());  // VBox 版本
//        properties.put("TaskStartTime", vBoxLogModel.getTaskStartTime());  // 任务开始时间
//        properties.put("VBoxCode", vBoxLogModel.getVboxCode());  // vbox号
//        properties.put("FileCategory", vBoxLogModel.getFileCategory());  // 文件类目 1.图片 2.视频
//        properties.put("Width", vBoxLogModel.getWidth());  // 图片宽度
//        // 1：缩略图 2：small图 7：预览图 10: 底图 11:RAW 12:Hevc
//        // 100:小视频  101:提取的视频  110: 视频底片 111:压缩后的底片 102:edl提取
//        properties.put("FileType", vBoxLogModel.getFileType());
//        properties.put("TaskCategory", vBoxLogModel.getTaskCaterogy()); // 1:V盒处理阶段 2:文件服务器处理阶段 3:数码师处理阶段
//        properties.put("TaskTime", vBoxLogModel.getTaskTime()); //! 任务耗时
//        properties.put("port", vBoxLogModel.getPort());//! 端口
//        properties.put("LifeCycleType", vBoxLogModel.getLifeCycleType());
//        properties.put("LifeCycleResult", vBoxLogModel.getLifeCycleResult());
//        properties.put("DigitalId", vBoxLogModel.getDigitalId());
//        properties.put("DigitalVersion", vBoxLogModel.getDigitalVersion());
//        properties.put("FsVersion", vBoxLogModel.getFsVersion()); //! 文件系统版本
//        properties.put("TaskMessage", vBoxLogModel.getTaskMessage());
//        properties.put("ParentPhotoName", vBoxLogModel.getParentPhotoName());

        try {
            Map<String, Object> properties = ObjectUtils.objectToMap(vBoxLogModel);
            properties.put("logId",vBoxLogModel.getId());
            properties.remove("id");
            sa.track(userId, true, eventName, properties);
        } catch (Exception e) {
            returnResult.setCode(ResultEnum.SYS_ERROR.getCode());
            returnResult.setMsg("log import failed! logContent:["+ vBoxLogModel.toString() +"]+ ex: "+e.getCause().toString());

        }finally {
            sa.flush();
        }

        return returnResult;
    }

    @Override
    public String getTokenIfNeeded() {
        if (StringUtils.isBlank(convertlabAccessToken)) {
            String accessTokenUrl = "https://api.convertlab.com/security/accesstoken";
//            Map<String,String> param = new HashMap<String,String>();
//            param.put("grant_type","client_credentials");
//            param.put("appid",CONVERTLAB_APP_ID);
//            param.put("secret",CONVERTLAB_APP_SECRET);
//            convertlabAccessToken = HttpUtils.sendGet(accessTokenUrl,param);
            accessTokenUrl = "https://api.convertlab.com/security/accesstoken?grant_type=client_credentials&appid=cl02da164630fdaaf&secret=b17aee38d78778fef5dc163c7e317ddb068b315a";
            String tokenResultJson = HttpUtils.sendGet(accessTokenUrl);
            Map json = JSONObject.parseObject(tokenResultJson);
            convertlabAccessToken = json.get("access_token").toString();
        }
        return convertlabAccessToken;
    }

    @Override
    public ReturnResult<List<ReferralModel>> getWhoReferred() {
        ReturnResult<List<ReferralModel>> returnResult = new ReturnResult<List<ReferralModel>>(
                ResultEnum.SUCCESS);
        Map<String,String> param = new HashMap<String,String>();
        param.put("access_token", getTokenIfNeeded());
        param.put("openId", "oCrKCs2dOgOCyLBIpmCjqxEWw-lo");
        param.put("referPlan", "d3fa8182171d43d98217c4526ca74655");
        param.put("eventName", "open_page");
        String referralJsonStr = HttpUtils.doGet("https://api.convertlab.com/v1/referralDetailsForFan",param);
        Map json = JSONObject.parseObject(referralJsonStr);
        System.out.print(json.get("rows"));
        List referralList = JSONArray.parseArray(json.get("rows").toString(),Object.class);
//        List<ReferralModel> referralList = JSON.parse(json.get("rows").toString(),ReferralModel.class);
        System.out.println(referralList.toString());
        returnResult.setData(referralList);
        returnResult.setMsg("success");
        return returnResult;
    }

    @Override
    public ReturnResult getMembersByListId(@PathVariable("listId") Long listId) {
        ReturnResult<List<ReferralModel>> returnResult = new ReturnResult<List<ReferralModel>>(
                ResultEnum.SUCCESS);
        Map<String,String> param = new HashMap<String,String>();
        param.put("listId","56387");
        param.put("access_token", getTokenIfNeeded());
        String membersInGroup = HttpUtils.doGet("https://api.convertlab.com/v1/listservice/members",param);
        Map json = JSONObject.parseObject(membersInGroup);
        System.out.print(json.get("rows"));
        List membersInGroupList = JSONArray.parseArray(json.get("rows").toString(),Object.class);
        returnResult.setData(membersInGroupList);
        returnResult.setMsg("success");
        return returnResult;
    }

    @Override
    public ReturnResult<List<GroupModel>> getGroups() {
        ReturnResult<List<GroupModel>> returnResult = new ReturnResult<List<GroupModel>>(
                ResultEnum.SUCCESS);
        Map<String,String> param = new HashMap<String,String>();
        param.put("access_token", getTokenIfNeeded());
        String groups = HttpUtils.doGet("https://api.convertlab.com/v1/lists",param);
        Map json = JSONObject.parseObject(groups);
        System.out.print(json.get("rows"));
        List groupList = JSONArray.parseArray(json.get("rows").toString(),Object.class);
        returnResult.setData(groupList);
        returnResult.setMsg("success");
        return returnResult;
    }

    @Override
    public ReturnResult<VPhotoUser> registerUser(@RequestBody VPhotoUser vPhotoUser) {
        ReturnResult<VPhotoUser> returnResult = new ReturnResult<VPhotoUser>(
                ResultEnum.SUCCESS);

        SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.DebugConsumer("https://sensors.vphotos.cn:8106/sa?project=default", false));

        String registerId = vPhotoUser.getUserId();
        Map<String, Object> profiles = new HashMap<String, Object>();
        try{
            sa.profileSet(registerId, true, profiles);    // 此时传入的是注册ID了
            // 2.3 立刻刷新一下，让数据传到SA中
            sa.flush();
        }catch (InvalidArgumentException e){
            returnResult.setMsg("add user failed!");
            returnResult.setCode(ResultEnum.SYS_ERROR.getCode());
        }
        return returnResult;
    }

    @Override
    public ReturnResult<DemoModel> createDemo(@RequestBody DemoModel demoModel){

        LogModel lm = LogModel.newLogModel("DemoController.createDemo")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "POST")
                .addMetaData("demoModel", demoModel);

        logger.info(lm.toJson(false));

        Validation.newValidation()
                .addError((null == demoModel), ResultEnum.PARAM_ERROR_NAME, "DemoModel")
                .addError((StringUtils.isEmpty(demoModel.getCode())), ResultEnum.PARAM_ERROR_NAME, "code")
                .isValidThrowException();

        CallResult<DemoModel> callResult = demoService.createDemo(demoModel);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());

            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult<DemoModel> returnResult = new ReturnResult<DemoModel>(
                ResultEnum.SUCCESS, callResult.getBusinessResult());

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult<DemoModel> queryById(@PathVariable("id") Long id){
        LogModel lm = LogModel.newLogModel("DemoController.queryById")
                                .addMetaDataTraceId(HttpContext.getTraceId())
                                .addMetaData("method", "GET")
                                .addMetaData("id", id);
        logger.info(lm.toJson(false));

        Validation.newValidation()
                .addError((null == id), ResultEnum.PARAM_ERROR_NAME, "id")
                .isValidThrowException();

        CallResult<DemoModel> callResult = demoService.queryById(id);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());

            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult<DemoModel> returnResult = new ReturnResult<DemoModel>(
                ResultEnum.SUCCESS, callResult.getBusinessResult());

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }


    @Override
    public ReturnResult deleteById(@PathVariable("id") Long id) {
        LogModel lm = LogModel.newLogModel("DemoController.deleteById")
                            .addMetaDataTraceId(HttpContext.getTraceId())
                            .addMetaData("method", "GET")
                            .addMetaData("id", id);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == id), ResultEnum.PARAM_ERROR_NAME, "id")
                .isValidThrowException();

        CallResult<Integer> callResult = demoService.deleteById(id);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult returnResult = new ReturnResult(
                ResultEnum.SUCCESS);

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult updateById(@PathVariable("id") Long id, @RequestBody DemoModel demoModel) {
        LogModel lm = LogModel.newLogModel("DemoController.updateById")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "PUT")
                .addMetaData("id", id)
                .addMetaData("demoModel", demoModel);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                .addError((null == id), ResultEnum.PARAM_ERROR_NAME, "id")
                .addError((null == demoModel), ResultEnum.PARAM_ERROR_NAME, "demoModel")
                .addError((StringUtils.isEmpty(demoModel.getCode())), ResultEnum.PARAM_ERROR_NAME, "code")
                .isValidThrowException();

        CallResult<Integer> callResult = demoService.updateById(id, demoModel);
        lm.addMetaData("callResult", callResult);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<DemoModel>(callResult.getResultEnum());
        }

        ReturnResult returnResult = new ReturnResult(
                ResultEnum.SUCCESS);

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }


    @Override
    public ReturnResult<ReturnPageResult<DemoModel>> queryByPage(@RequestParam(name = "code", required = false) String code,
                                                                 @RequestParam(name = "status", required = false) Integer status,
                                                                 @RequestParam("pageNum") Long pageNum,
                                                                 @RequestParam("pageSize") Long pageSize) {
        LogModel lm = LogModel.newLogModel("DemoController.queryByPage")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "GET")
                .addMetaData("code", code)
                .addMetaData("status", status)
                .addMetaData("pageNum", pageNum)
                .addMetaData("pageSize", pageSize);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                    .addError((null == pageNum || 0 >= pageNum) , ResultEnum.PARAM_ERROR_NAME_MSG, "pageNum", "正整数")
                    .addError((null == pageSize || 0 >= pageSize || AppConstants.MAX_PAGE_SIZE < pageSize),
                                ResultEnum.PARAM_RANGE_ERROR, "pageSize", "1", AppConstants.MAX_PAGE_SIZE.toString())
                    .isValidThrowException();

        CallResult<ReturnPageResult<DemoModel>> callResult = demoService.queryByPage(code, status, pageNum, pageSize);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<>(callResult.getResultEnum());
        }

        ReturnResult<ReturnPageResult<DemoModel>> returnResult = new ReturnResult<>(
                callResult.getResultEnum(), callResult.getBusinessResult());

        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;
    }

    @Override
    public ReturnResult<IpModel> queryByIp(@RequestParam("ip") String ip) {

        LogModel lm = LogModel.newLogModel("DemoController.queryByIp")
                .addMetaDataTraceId(HttpContext.getTraceId())
                .addMetaData("method", "GET")
                .addMetaData("ip", ip);

        logger.info(lm.toJson(false));


        Validation.newValidation()
                    .addError(StringUtils.isEmpty(ip), ResultEnum.PARAM_ERROR_NAME, "ip")
                    .isValidThrowException();

        CallResult<IpModel> callResult = demoService.queryByIp(ip);

        if(!callResult.isSuccess()){
            logger.info(lm.toJson());
            return new ReturnResult<>(callResult.getResultEnum());
        }

        ReturnResult<IpModel> returnResult = new ReturnResult<>(callResult.getResultEnum(), callResult.getBusinessResult());
        lm.addMetaDataResult(returnResult);
        logger.info(lm.toJson());

        return returnResult;

    }

    @Override
    public ReturnResult queryTestSensor() {
        ReturnResult<Object> returnResult = new ReturnResult<Object>(ResultEnum.SUCCESS);

        Map<String,String> params = new HashMap<String, String>();
        params.put("q","SELECT * FROM events LIMIT 10");
        params.put("format","json");
//        String testJson = HttpUtils.sendPost(
//                "https://sensors.vphotos.cn/api/sql/query?project=default&token=301826a574b38b1474037df2e3b6cab35914382b3839bdd0dd21926c7af4364a",
////                params);
        String testJson = "{\n" +
                "    \"store\": {\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"category\": \"reference\",\n" +
                "                \"author\": \"Nigel Rees\",\n" +
                "                \"title\": \"Sayings of the Century\",\n" +
                "                \"price\": 8.95\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Evelyn Waugh\",\n" +
                "                \"title\": \"Sword of Honour\",\n" +
                "                \"price\": 12.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"Herman Melville\",\n" +
                "                \"title\": \"Moby Dick\",\n" +
                "                \"isbn\": \"0-553-21311-3\",\n" +
                "                \"price\": 8.99\n" +
                "            },\n" +
                "            {\n" +
                "                \"category\": \"fiction\",\n" +
                "                \"author\": \"J. R. R. Tolkien\",\n" +
                "                \"title\": \"The Lord of the Rings\",\n" +
                "                \"isbn\": \"0-395-19395-8\",\n" +
                "                \"price\": 22.99\n" +
                "            }\n" +
                "        ],\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        }\n" +
                "    },\n" +
                "    \"expensive\": 10\n" +
                "}";
        Object json = JSONObject.parse(testJson);
        returnResult.setMsg("testSensor");
        returnResult.setCode(200);
        returnResult.setData(json);
        return returnResult;
    }

}