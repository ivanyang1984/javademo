package com.vphoto.demo.springboot.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.beans.Transient;

@Data
public class VBoxLogModel {

    /**
     * mediatrace log property
     */
    private String timestamp;

    private String version;

    private String msgId;

    private String id;

    private String port;

    private String lifeCycleType;

    private String lifeCycleResult;

    private String digitalId;

    private String digitalVersion;

    private String fsVersion;

    private String taskMessage;

    private String parentPhotoName;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 任务ID
     */
    private String missionId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 任务顺序
     */
    private String taskSeq;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务结果
     */
    private String taskCode;

    /**
     * 文件长度
     */
    private String fileLength;

    /**
     * 文件类型
     */
    private String photoAlbumType;

    /**
     * 图片名
     */
    private String photoName;

    /**
     * 开始时间
     */
    private String taskStartTime;

    /**
     * v盒编号
     */
    private String vboxCode;

    /**
     * 文件种类
     */
    private String fileCategory;

    /**
     * 宽度
     */
    private String width;

    /**
     * 高度
     */
    private String height;

    /**
     * 文件类型
     * 1:缩略图
     * 2:small图
     * 7:预览图
     * 10:底图
     * 11:RAW
     * 12:Hevc
     * 100:小视频
     * 101:提取的视频
     * 110:视频底片
     * 111:压缩后的底片
     * 102:edl提取
     */
    private String fileType;

    /**
     * 任务阶段
     * 1:V盒处理阶段
     * 2:文件服务器处理阶段
     * 3:数码师处理阶段
     */
    private int taskCaterogy;

    /**
     * 任务耗时
     */
    private String taskTime;

    /**
     * 文件编码
     */
    private long filecode;

    /**
     * 机器ip
     */
    private String host;

    /**
     * 开始时间
     */
    private long startTime;

    /**
     * mediatrace
     */
    private String index;

    /**
     * mediatraceLog
     */
    private String type;

    /**
     * syncPip
     */
    private String vboxVersion;


}
