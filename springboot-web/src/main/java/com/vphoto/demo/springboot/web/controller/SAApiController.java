package com.vphoto.demo.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vphoto.demo.springboot.facade.SAApi;
import com.vphoto.demo.springboot.model.enums.ResultEnum;
import com.vphoto.demo.springboot.model.result.ReturnResult;
import com.vphoto.demo.springboot.model.sa.SACityCateDimTotalModel;
import com.vphoto.demo.springboot.utils.HttpUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SAApiController implements SAApi {
    @Override
    public ReturnResult<List<SACityCateDimTotalModel>> getCityCateTotal() {

        ReturnResult<List<SACityCateDimTotalModel>> result = new ReturnResult<List<SACityCateDimTotalModel>>(ResultEnum.SUCCESS);

        Map<String,String> params = new HashMap<String, String>();
//        params.put("q","select case when ordercity is null then '其他' else ordercity end as ordercity_alias, photoalbumcategory,count(distinct albumorderId) as num_of_order, count(distinct_id) as UV from events where event = 'AlbumLaunch' and estype = 'wechatlog' group by photoalbumcategory, ordercity order by num_of_order desc;");
//        params.put("q","select * from events where event = 'AlbumLaunch' and estype = 'wechatlog' limit 1");
        params.put("q","select case when ordercity is null then '其他' else ordercity end as ordercity_alias,photoalbumcategory,count(albumorderId) as num_of_order, count(distinct_id) as UV from events where event = 'AlbumLaunch' and estype = 'wechatlog' group by ordercity,photoalbumcategory");
        params.put("format","json");
        String testJson = HttpUtils.sendPost(
                "https://sensors.vphotos.cn/api/sql/query?project=default&token=3de4619e956a2d4b67134055937cc112fb895dc2aaf4242671021a8ec04a790c",
                params);

        System.out.println(testJson);

        String ss = "["+testJson+"]".replace("\n",",");
        List<SACityCateDimTotalModel> saResult = JSONArray.parseArray(ss,SACityCateDimTotalModel.class);
        result.setCode(200);
        result.setData(saResult);

        return result;
    }
}
