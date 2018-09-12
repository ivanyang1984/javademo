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

    /**
     * crm grant type
     */
    public static final String CRM_GRANT_TYPE = "password";

    /**
     * crm client id
     */
    public static final String CRM_CLIENT_ID = "0b42ebdbbb509489a15aaca051c0f8d3";

    /**
     * crm client secret
     */
    public static final String CRM_CLIENT_SECRET = "9d0f24fff546b374aaa64ded5a641b56";

    /**
     * crm redirect uri
     */
    public static final String CRM_REDIRECT_URI = "https://api.xiaoshouyi.com";

    /**
     * crm username
     */
    public static final String CRM_USERNAME = "wencheng.gao@v.photos";

    /**
     * crm password
     */
    public static final String CRM_PASSWORD = "VPhotos123bbJUqKQO";

    public static final String CRM_QUERY_URL = "https://api.xiaoshouyi.com/data/v1/query";

    public static final String CRM_ORDER_PAYMENTS = "https://api.xiaoshouyi.com/data/v1/objects/order/payment/list";

    public static final String CRM_RESPONSIBILITY_LIST = "https://api.xiaoshouyi.com/data/v1/objects/responsibility/list";

    public static final String CRM_DEPT_TREE = "https://api.xiaoshouyi.com/data/v1/objects/depart/tree";

    public static final String CRM_DEPT_INFO = "https://api.xiaoshouyi.com/data/v1/objects/depart/info";

    public static final String CRM_CONTACT_INFO = "https://api.xiaoshouyi.com/data/v1/objects/contact/info";

    public static final String CRM_CUSTOMER_INFO = "https://api.xiaoshouyi.com/data/v1/objects/account/info";

    public static final String CRM_OPPORTUNITY_INFO = "https://api.xiaoshouyi.com/data/v1/objects/opportunity/info";

    public static final String CRM_ORDER_INFO = "https://api.xiaoshouyi.com/data/v1/objects/order/info";

    public static final String CRM_CONTRACT_INFO = "https://api.xiaoshouyi.com/data/v1/objects/contract/info";

    public static final String CRM_CONTRACT_PAYMENTS = "https://api.xiaoshouyi.com/data/v1/objects/contract/payment/list";

    public static final String CRM_PRODUCT = "https://api.xiaoshouyi.com/data/v1/objects/product/info";

    public static final String CRM_PRODUCT_LIST = "https://api.xiaoshouyi.com/data/v1/objects/product/list";

    public static final String CRM_USER_RESPON_LIST = "https://api.xiaoshouyi.com/data/v1/objects/userresponsibilities/list";

    public static final String ES_GET_ORDER_PV_UV = "http://admin.i.vphotos.cn/vphotosAdmin/admin/statistics/getOrderListAndEs";

    public static final String ES_GET_VISIT_SOURCE = "http://admin.i.vphotos.cn/vphotosAdmin/admin/statistics/getVisitSource";

    public static final String CONVERTLAB_ACCESSTOKEN = "https://api.convertlab.com/security/accesstoken";

    public static final String CONVERTLAB_GROUP_LIST = "https://api.convertlab.com/v1/lists";

    public static final String CONVERTLAB_GROUP_MEMBERS = "https://api.convertlab.com/v1/listMembers";

    public static final String CONVERTLAB_SINGLE_CUSTOMER = "https://api.convertlab.com/v1/customers/";

    public static final String CONVERTLAB_CUSTOMERS = "https://api.convertlab.com/v1/customers";

    public static final String CONVERTLAB_CUSTOMER_IDENTITY = "https://api.convertlab.com/v1/customeridentities";

    public static final String CONVERTLAB_REFERRAL_DETAILS_FOR_FAN = "https://api.convertlab.com/v1/referralDetailsForFan";

    public static final String CONVERTLAB_REFERRAL_DETAILS = "https://api.convertlab.com/v1/referralDetails";

    public static final String CONVERTLAB_REFER_PLAN = "https://app.convertlab.com/referplan/new?accountId=1369&isOpen=1&page=1&rows=20&sidx=dateCreated&sord=desc";
}
