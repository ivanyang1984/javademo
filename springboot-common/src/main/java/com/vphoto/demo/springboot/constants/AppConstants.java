/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description: 应用常量
 * Version: 1.0
 * Date: 18-5-8 下午6:40
 * LastModified: 18-5-8 下午6:40
 */

package com.vphoto.demo.springboot.constants;

public class AppConstants {

    /**
     * 默认分页页码
     */
    public static final Long DEFAULT_PAGE_NUM = 1L;

    /**
     * 默认分页size
     */
    public static final Long DEFAULT_PAGE_SIZE = 10L;

    /**
     * 最大分页size
     */
    public static final Long MAX_PAGE_SIZE = 1000L;

    /**
     * 缓存key前缀
     */
    public static final String CACHE_KEY_PRE = "vphoto:demo:springboot:";

    /**
     * 缓存超时时间
     */
    public static final Long CACHE_TIME_OUT = 30 * 60 * 1000L;

    /**
     * 神策数据导入URL
     */
    public static final String SA_SERVER_URL = "https://sensors.vphotos.cn:8106/sa?project=default";

    /**
     * Convertlab 应用名
     */
    public static final String CONVERTLAB_APP_NAME = "vphoto";

    /**
     * Convertlab 应用密钥
     */
    public static final String CONVERTLAB_APP_SECRET = "b17aee38d78778fef5dc163c7e317ddb068b315a";

    /**
     * Convertlab 应用ID
     */
    public static final String CONVERTLAB_APP_ID = "cl02da164630fdaaf";

}
