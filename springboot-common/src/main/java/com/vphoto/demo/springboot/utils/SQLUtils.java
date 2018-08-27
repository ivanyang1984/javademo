package com.vphoto.demo.springboot.utils;

public class SQLUtils {

    public static String getAccountAllFieldsSql() {
        String accountSQL = "select id, " +//! 主键
                "ownerId," +//! 客户所有人
                "entityType," +//! 客户类型
                "accountName," +//! 客户名称
                "level," +//! 客户级别
                "dbcVarchar1," +//! 企业类型
                "parentAccountId," +//! 上级客户
                "industryId," +//! 行业
                "state," +//! 省份
                "city," +//! 市
                "recentActivityRecordTime," +//! 最新活动记录时间
                "recentActivityCreatedBy," +//! 最新跟进人
                "createdAt," +//! 创建日期
                "highSeaId," +//! 所属公海
                "createdBy," +//! 创建人
                "updatedAt," +//! 最新修改日
                "claimTime," +//! 认领日期
                "updatedBy," +//! 最新修改人
                "expireTime," +//! 到期时间
                "srcFlg," +//! 工商注册
                "highSeaStatus," +//! 状态
//                "approvalStatus," +//! 审批状态
                "dimDepart," +//! 所属部门
                "outterDepartId," +//! 外部部门
                "isDisturb," +//! 免打扰
                "lockStatus," +//! 锁定状态
                "address," +//! 寄件地址
                "zipCode," +//! 锁定状态
                "region," +//! 区
                "phone," +//! 电话
                "dbcVarchar2," +//! 手机
                "fax," +//! 传真
                "url," +//! 公司网址
                "longitude," +//! 纬度
                "latitude," +//! 纬度
                "employeeNumber," +//! 总人数
                "annualRevenue," +//! 销售额
                "highSeaAccountSource," +//! 客户来源
                "comment," +//! 备注
                "dbcSelect19," +//! 签约状态
//                "applicantId," +//! 审批提交人
                "dbcVarchar3," +//! 品牌名1
//                "dbcSelect1," +//! 是否大客户
//                "dbcTextarea1," +//! 融资进展
                "dbcSelect2," +//! 客户业务类型
//                "dbcSelect3," +//! 客户业务类型
//                "dbcVarchar5," +//! 客户业务类型
//                "dbcSelect3," +//! 客户业务类型
//                "dbcVarchar5," +//! 客户业务类型
//                "dbcVarchar6," +//! 客户业务类型
//                "dbcVarchar7," +//! 客户业务类型
//                "dbcSelect4," +//! 客户业务类型
//                "dbcVarchar8," +//! 客户业务类型
//                "dbcVarchar9," +//! 客户业务类型
                "dbcVarchar10," +//! 开户行
                "dbcVarchar11," +//! 开票银行账号
//                "dbcVarchar12," +//! 发票寄送地址
                "dbcVarchar13," +//! 寄件联系人
                "dbcVarchar14," +//! 寄件联系人电话
                "dbcVarchar15," +//! 客户来源说明
                "dbcVarchar16," +//! 客户所在城市
//                "dbcVarchar17," +//! 客户业务类型
//                "dbcReal1," +//! 客户业务类型
//                "custCheckbox1," +//! 客户业务类型
                "dbcSelect5," +//! 潜在商机
                "dbcSelect6," +//! 是否标杆企业
                "dbcVarchar18," +//! 品牌名2
                "dbcVarchar19," +//! 品牌名3
                "dbcVarchar20," +//! 地址
                "dbcVarchar21," +//! 介绍人
                "dbcSelect7," +//! 摄影师类型
                "dbcSelect8," +//! 认证摄影师级别
                "dbcSelect9," +//! 数码师类型
                "dbcSelect10," +//! 认证数码师级别
                "dbcSelect11," + //!性别
                "dbcSelect12," +//!来源
                "dbcVarchar22," +//!纳税人识别号
                "dbcVarchar23," +//!开票地址
                "dbcVarchar24," +//!开票电话
                "dbcVarchar25," +//!主要联系人
                "dbcSVarchar1," +//!主要联系人手机
//                "dbcVarchar26," +//!性别
                "dbcVarchar27," +//!国家及城市
                "dbcSelect13," +//!一级行业
                "dbcSelect14," +//!二级行业
                "dbcVarchar28," +//!身份证号
                "dbcSelect15," +//!BD客户级别
                "dbcVarchar29," +//!旗下知名活动IP名称
                "dbcTextarea2," +//!是否有竞品竞争
                "dbcSelect16," +//!合作业务类型
                "dbcSelect17," +//!合作伙伴来源
                "dbcSelect18," +//!是否标杆企业/知名品牌
                "dbcVarchar30," +//!业务后台客户ID
                "dbcVarchar31," +//!CRM客户ID
                "dbcReal2," +//!后台成交总金额
                "dbcDate1" +//!最后成交日期
                " from account ";
        return accountSQL;
    }


    public static String getOpportunityFirstHalfFieldsSql(){
        String opportunitySQL = "select id, " +
                "entityType," +
                "ownerId," +
                "opportunityName," +
                "priceId," +
                "accountId," +
                "opportunityType," +
                "money," +
                "lostStageId," +
                "saleStageId," +
                "winRate," +
                "reasonDesc," +
                "closeDate," +
                "commitmentFlg," +
                "sourceId," +
                "projectBudget," +
                "actualCost," +
                "recentActivityRecordTime," +
                "stageUpdatedAt," +
                "createdAt," +
                "createdBy," +
                "updatedAt," +
                "updatedBy," +
                "comment," +
                "dimDepart," +
                "applicantId," +
                "dbcSelect2," +
                "lockStatus," +
                "dbcVarchar1," +
                "dbcDate1," +
                "dbcVarchar2," +
                "dbcVarchar3," +
                "dbcVarchar4," +
                "dbcVarchar5," +
                "dbcVarchar6," +
                "dbcVarchar7," +
                "dbcVarchar8," +
                "dbcVarchar9," +
                "dbcVarchar10," +
                "dbcDate2," +
                "dbcDate3," +
                "dbcSelect1," +
                "dbcSelect3," +
                "dbcSelect4," +
                "dbcSelect5," +
                "dbcSelect6," +
                "dbcVarchar11," +
                "dbcVarchar12," +
                "dbcDate4," +
                "dbcVarchar13," +
                "dbcSelect7," +
                "dbcVarchar14," +
                "dbcJoin1 " +

                " from opportunity ";
        return opportunitySQL;
    }



    public static String getOpportunitySecondHalfFieldsSql() {
        String opportunitySQL = "select id, " +

                "dbcDate6," +
                "dbcVarchar32," +
                "dbcDate7," +
                "dbcVarchar33," +
                "dbcSelect11," +
                "dbcVarchar34," +
                "dbcVarchar35," +
                "dbcVarchar36," +
                "dbcDate8," +
                "dbcVarchar37," +
                "dbcVarchar38," +
                "dbcDate9," +
                "dbcVarchar39," +
                "dbcSelect14," +
                "custCheckbox2," +
                "dbcVarchar40," +
                "dbcSelect16," +
                "dbcDate10," +
                "custCheckbox3," +
                "custCheckbox4," +
                "dbcVarchar42," +
                "dbcVarchar47," +
                "dbcVarchar48," +
                "dbcVarchar49," +
                "dbcVarchar52," +
                "dbcDate11," +
//                "dbcVarchar53," +
                "custCheckbox5," +
                "dbcSelect8," +
                "dbcSelect13," +
                "dbcVarchar43," +
                "dbcSelect17," +
                "dbcSelect18," +
                "dbcSelect19," +
                "custCheckbox6," +
                "dbcSelect20," +
                "dbcVarchar58," +
                "dbcSelect29," +
                "dbcSelect30," +
                "dbcSelect31," +
                "custCheckbox7," +
                "dbcJoin2," +
                "dbcInteger3," +
                "dbcReal3," +
                "dbcSelect32," +
                "dbcSelect33," +
                "dbcRelation3," +
                "dbcJoin3," +
                "dbcJoin4," +
                "dbcJoin5," +
                "dbcJoin6," +
//                "dbcJoin7," +
//                "dbcJoin8," +
//                "dbcJoin9," +
                "dbcJoin10," +
//                "dbcJoin11," +
                "dbcVarchar46," +
                "dbcTextarea5," +
                "dbcTextarea6," +
                "dbcReal5," +
                "dbcSelect15," +
                "dbcTextarea7," +
                "dbcVarchar45" +
                " from opportunity ";
        return opportunitySQL;
    }

    public static String getOpportunityThirdHalfFieldsSql(){
        String opportunitySQL = "select id, " +

                "dbcVarchar57," +
                "dbcReal2," +
                "dbcSelect26," +
                "dbcVarchar56," +
                "dbcSelect22," +
                "dbcSelect28," +
                "dbcRelation1," +
                "dbcTextarea2," +
                "dbcVarchar54," +
                "dbcVarchar51," +
                "dbcVarchar55," +
                "dbcSelect24," +
                "dbcDate12," +
                "dbcSelect25," +
                "dbcSelect27," +
                "dbcReal1," +
                "dbcVarchar50," +
                "dbcTextarea3," +
                "dbcVarchar25," +
                "dbcSelect21," +
                "dbcSelect23," +
                "dbcVarchar44," +
                "dbcVarchar26," +
                "dbcVarchar27," +
                "dbcDate5," +
                "dbcVarchar28," +
                "dbcSelect9," +
                "dbcVarchar30," +
                "dbcReal4," +
                "dbcSelect10," +
                "dbcVarchar31," +

                "dbcVarchar16," +
                "dbcVarchar17," +
                "dbcVarchar18," +
                "dbcInteger1," +
                "custCheckbox1," +
                "dbcVarchar19," +
                "dbcVarchar20," +
                "dbcVarchar21," +
                "dbcVarchar22," +
                "dbcInteger2," +
                "dbcTextarea1," +
                "dbcTextarea4," +
                "dbcRelation2," +
                "dbcVarchar24 " +

                " from opportunity ";
        return opportunitySQL;
    }

    public static String getOrderFirstHalfFieldSQL(){
        String orderSQL = "select id, " +
                "dbcRelation1," +
                "po," +
                "ro," +
                "entityType," +
                "orderId," +
                "ownerId," +
                "contractId," +
                "accountId," +
                "poStatus," +
                "priceId," +
                "opportunityId," +
                "roStatus," +
                "initAmount," +
                "amount," +
                "effectiveDate," +
                "productsAmount," +
                "deliveryDate," +
                "contactName," +
                "contactTel," +
                "contactAddress," +
                "payments," +
                "cancelReason," +
                "paymentStatus," +
                "createdBy," +
                "overdueStatus," +
                "paymentPercent," +
                "createdAt," +
                "updatedBy," +
                "updatedAt," +
                "comment," +
                "dimDepart," +
                "dbcReal6," +
                "dbcVarchar19," +
                "dbcSelect9," +
                "dbcVarchar20," +
                "dbcReal7," +
                "approvalStatus," +
                "amountInvoiced," +
                "applicantId," +
                "transactionDate," +
                "lockStatus," +
                "amountUnbilled," +
                "listTotal," +
                "totalDiscountAmount," +
                "dbcVarchar1," +
                "orderVersion," +
                "originalOrderVersion," +
                "co," +
                "dbcVarchar7," +
                "originalOrderId," +
                "dbcJoin1," +
                "dbcReal1 " +
                " from _order ";
        return orderSQL;
    }

    public static String getOrderSecondHalfFieldsSQL(){
        String orderSQL = "select id, " +
                "dbcJoin3," +
                "dbcJoin4," +
                "dbcJoin5," +
                "dbcJoin7," +
                "dbcVarchar2," +
                "dbcVarchar3," +
                "dbcJoin8," +
                "dbcJoin9," +
                "dbcTextarea1," +
                "dbcVarchar4," +
                "dbcDate1," +
                "dbcSelect2," +
                "dbcVarchar5," +
                "dbcSelect3," +
                "dbcTextarea2," +
                "dbcSelect4," +
                "dbcInteger1," +
                "dbcVarchar8," +
                "dbcVarchar12," +
                "dbcVarchar13," +
                "dbcVarchar14," +
                "dbcVarchar15," +
                "dbcVarchar16," +
                "dbcSelect5," +
                "dbcSelect6," +
                "dbcSelect7," +
                "dbcReal2," +
                "dbcReal3," +
                "dbcSelect8," +
                "dbcVarchar17," +
                "dbcDate2," +
                "dbcDate3," +
                "dbcRelation2," +
                "dbcJoin6," +
                "dbcJoin10," +
                "dbcVarchar18," +
                "dbcReal4," +
                "dbcReal5," +
                "dbcSelect10," +
                "dbcTextarea3, " +
                "dbcSelect11," +
                "dbcJoin11," +
                "dbcTextarea4," +
                "dbcTextarea5," +
                "dbcVarchar21, " +
                "dbcSelect12," +
                "dbcSelect13," +
                "dbcSelect14," +
                "custCheckbox1," +
                "dbcSelect15," +
                "dbcSelect16 "+
                " from _order ";
        return orderSQL;
    }

    public static String getOrderProductSQL() {
        String orderPorductSQL = "select id, " +
                "unitPrice, " +
                "quantity," +
                "discount," +
                "priceTotal," +
                "comment," +
                "createdBy," +
                "standardPrice," +
                "createdAt," +
                "updatedBy," +
                "updatedAt," +
                "quantityUndelivered," +
                "name," +
                "orderId," +
                "quantityDelivered," +
                "entityType," +
                "settleStatus," +
                "productId," +
                "dbcReal1," +
                "transactionDate," +
                "orderProductId," +
                "amountInvoiced," +
                "listTotal," +
                "totalDiscountAmount," +
                "amountUnbilled," +
                "deltaQuantity," +
                "deltaAmount," +
                "orderVersion," +
                "changeType," +
                "originalOrderId from orderProduct ";

        return orderPorductSQL;
    }

    public static String getContractListSQL() {

        String contractSQL = "select id ," +
                "title," +
                "accountId," +
                "opportunityId," +
                "campaignId," +
                "amount," +
                "status," +
                "startDate," +
                "endDate," +
                "invoiceAmount," +
                "payBack," +
                "notPayment," +
                "contractCode," +
                "ownerId," +
                "participants," +
                "paymentStatus," +
                "overdueStatus," +
                "paymentPercent," +
                "createdAt," +
                "createdBy," +
                "updatedAt," +
                "updatedBy," +
                "dimDepart," +
                "entityType," +
                "lockStatus," +
                "approvalStatus," +
                "applicantId," +
                "contractType," +
                "payMode," +
                "customerSigner," +
                "signerId," +
                "signDate," +
                "dbcReal3," +
                "dbcReal4," +
                "dbcReal2," +
                "dbcReal1," +
                "comment," +
                "dbcSelect1," +
                "dbcSelect2," +
                "dbcRelation1," +
                "dbcVarchar1," +
                "dbcSelect3," +
                "dbcVarchar2," +
                "dbcSelect4," +
                "dbcSelect5 from contract ";
        return contractSQL;
    }

    public static String getLeadsSQL() {

        String leadsSQL = "select id, " +
                "entityType," +
                "ownerId," +
                "status," +
                "name," +
                "gender," +
                "companyName," +
                "post," +
                "phone," +
                "mobile," +
                "dbcVarchar1," +
                "address," +
                "email," +
                "leadSourceId," +
                "campaignId," +
                "recentActivityRecordTime," +
                "recentActivityCreatedBy," +
                "createdAt," +
                "createdBy," +
                "updatedAt," +
                "updatedBy," +
                "lockStatus," +
                "comment," +
                "highSeaId," +
                "claimTime," +
                "dimDepart," +
                "applicantId," +
                "expireTime," +
                "highSeaStatus," +
                "approvalStatus," +
                "leadQuality," +
                "leadScore," +
                "bdType," +
                "bdSourceId," +
                "dbcDate1," +
                "dbcVarchar2," +
                "dbcVarchar3," +
                "dbcVarchar4," +
                "dbcSelect1," +
                "dbcVarchar5," +
                "dbcVarchar6," +
                "dbcVarchar7," +
                "pinyin," +
                "delFlg," +
                "duplicateFlg," +
                "opportunityId," +
                "applyDelayTime," +
                "accountId," +
                "statusUpdatedAt," +
                "releaseTime," +
                "returnTimes," +
                "thawTime," +
                "isDisturb," +
                "releaseReason from lead ";
        return leadsSQL;
    }

    public static String getPaymentSQL() {

        String paymentPlanSQL = "select id," +
                "stage," +
                "amount," +
                "code," +
                "actualTime," +
                "type," +
                "ownerId," +
                "contractId," +
                "orderId," +
                "totalAmount," +
                "accountId," +
                "paymentPercent," +
                "createdBy," +
                "createdAt," +
                "updatedBy," +
                "updatedAt," +
                "dimDepart," +
                "lockStatus," +
                "description," +
                "applicantId," +
                "approvalStatus," +
                "entityType," +
                "dbcSelect1," +
                "dbcReal1," +
                "dbcDate1," +
                "dbcTinyint1," +
                "dbcVarchar1," +
                "invoiceFlg " +
                " from payment ";
        return paymentPlanSQL;
    }


    public static String getUserSQL(){
        String userSQL = "select id," +
                "phone," +
                "entityType," +
                "name," +
                "departId," +
                "employeeCode," +
                "unionId," +
                "gender," +
                "joinAtStr," +
                "birthday," +
                "passwordRuleId," +
                "hiddenYearFlg," +
                "positionName," +
                "rankId," +
                "userManagerId," +
                "languageCode," +
                "timezone," +
                "mobileLocationStatus," +
                "nickName," +
                "statusInt," +
                "lastestLoginAt," +
                "selfIntro," +
                "telephone," +
                "extNo," +
                "expertise," +
                "hometown," +
                "im," +
                "weibo," +
                "hobby," +
                "createdAt," +
                "createdBy," +
                "updatedAt," +
                "updatedBy from user ";

        return userSQL;
    }

}
