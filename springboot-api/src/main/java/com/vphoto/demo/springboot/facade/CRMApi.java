package com.vphoto.demo.springboot.facade;

import com.vphoto.demo.springboot.model.VBoxLogModel;
import com.vphoto.demo.springboot.model.crm.*;
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

    @ApiOperation(value = "按天获取CRM销售机会数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="这个接口字段太多，sql切分为3段，所以list里有3个数据根据id来合并成一条")
    @RequestMapping(value = {"/CrmOpportunityByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYOpportunity>> getCrmOpportunityListByDate(@PathVariable("date") String date);

    @ApiOperation(value = "按天获取CRM订单数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="订单")
    @RequestMapping(value = {"/CrmOrderByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYOrder>> getCrmOrderListByDate(@PathVariable("date") String date);

//    @ApiOperation(value = "按天获取CRM订单明细数据",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            httpMethod = "GET",
//            notes="订单里的产品")
//    @RequestMapping(value = {"/CrmOrderProductByDate"},
//            method = {RequestMethod.GET},
//            produces = {MediaType.APPLICATION_JSON_VALUE})
//    ReturnResult<List<VPXSYOrderProduct>> getCrmOrderProductByDate(@PathVariable("date") String date);


    @ApiOperation(value = "按天获取CRM合同数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="每天合同")
    @RequestMapping(value = {"/CrmContractByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYContract>> getCrmContractListByDate(@PathVariable("date") String date);

    @ApiOperation(value = "按天获取CRM线索数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="每天线索")
    @RequestMapping(value = {"/CrmLeadsByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYLeads>> getCrmLeadsByDate(@PathVariable("date") String date);


    @ApiOperation(value = "按天获取CRM订单回款数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="每天新产生的回款计划")
    @RequestMapping(value = {"/CrmPaymentByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYPayment>> getCrmPaymentByDate(@PathVariable("date") String date);

    @ApiOperation(value = "根据订单ID 获取回款信息",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="根据订单ID 获取回款信息 有回款计划 开票信息 回款记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/CrmPaymentListByOrderId/{orderId}"},
            method = {RequestMethod.GET})
    ReturnResult<VPXSYOrderPaymentResult> getCrmPaymentList(@PathVariable String orderId);

    @ApiOperation(value = "按天获取CRM员工数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="按天获取CRM员工数据")
    @RequestMapping(value = {"/CrmUsersByDate"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYUser>> getCrmUsersByDate(@PathVariable("date") String date);


    @ApiOperation(value = "获取CRM职能列表数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM职能列表数据")
    @RequestMapping(value = {"/crm/responsibility/list"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYResponsibility>> getCrmResponsibilityList();


    @ApiOperation(value = "获取CRM部门结构数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM部门结构数据")
    @RequestMapping(value = {"/crm/depart/tree"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYDeptNode> getCrmDepartTree();

    @ApiOperation(value = "获取CRM单个部门数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM单个部门数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value = {"/crm/departById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYDept> getCrmDepartTree(@PathVariable("id") String id);

    @ApiOperation(value = "获取CRM联系人数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM联系人数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "时间", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/contact/{date}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYContact>> getCrmContact(@PathVariable("date") String date);

    @ApiOperation(value = "获取CRM联系人byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="通过联系人id 获取联系人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "联系人ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/contactById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYContact> getCrmContactById(@PathVariable("id") String id);

    @ApiOperation(value = "获取CRM客户byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="通过客户id 获取客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/customerById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYAccount> getCrmCustomerById(@PathVariable("id") String id);


    @ApiOperation(value = "获取CRM销售机会明细byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="通过销售机会id 获取销售机会")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "销售机会ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/opportunityById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYOpportunity> getCrmOpportunityById(@PathVariable("id") String id);


    @ApiOperation(value = "获取CRM订单明细byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="通过订单id 获取订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单id", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/orderById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYOrder> getCrmOrderById(@PathVariable("id") String id);


    @ApiOperation(value = "获取CRM合同byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM合同byId数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "合同id", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/contractById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYContract> getCrmContractById(@PathVariable("id") String id);

    @ApiOperation(value = "获取CRM合同回款列表byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM合同回款列表byId数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "合同id", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/contractPaymentsInfoById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYContractPayments> getCrmContractPaymentsInfoById(@PathVariable("id") String id);


    @ApiOperation(value = "获取CRM产品byId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM产品byId数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品id", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/productInfoById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<VPXSYProduct> getCrmProductInfoById(@PathVariable("id") String id);

    @ApiOperation(value = "获取CRM产品列表数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM产品列表数据")
    @RequestMapping(value = {"/crm/productList"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYProduct>> getCrmProductList();


    @ApiOperation(value = "获取CRM职能关联byUserId数据",
            produces = MediaType.APPLICATION_JSON_VALUE,
            httpMethod = "GET",
            notes="获取CRM职能关联byUserId数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "userId", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value = {"/crm/userResponsibilityById/{id}"},
            method = {RequestMethod.GET},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ReturnResult<List<VPXSYUserResponsibility>> getCrmUserResponsibilityById(@PathVariable("id") String id);
}
