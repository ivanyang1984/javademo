package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

import java.util.List;

@Data
public class VPXSYContract {

    private String id;

    private String title;

    private String accountId;

    private String opportunityId;

    private String campaignId;

    private String amount;

    private String status;

    private String startDate;

    private String endDate;

    private String invoiceAmount;

    private String payBack;

    private String notPayment;

    private String contractCode;

    private String ownerId;

    private String participants;

    private String paymentStatus;

    private String overdueStatus;

    private String paymentPercent;

    private String createdAt;

    private String createdBy;

    private String updatedAt;

    private String updatedBy;

    private String dimDepart;

    private String entityType;

    private String lockStatus;

    private String approvalStatus;

    private String applicantId;

    private String contractType;

    private String payMode;

    private String customerSigner;

    private String signerId;

    private String signDate;

    private String dbcReal3;

    private String dbcReal4;

    private String dbcReal2;

    private String dbcReal1;

    private String comment;

    private String dbcSelect1;

    private String dbcSelect2;

    private String dbcRelation1;

    private String dbcVarchar1;

    private String dbcSelect3;

    private String dbcVarchar2;

    private String dbcSelect4;

    private String dbcSelect5;

    private VPXSYAttributes attributes;

    private List<VPXSYMembers> members;

    private List<VPXSYMembers> owners;

    private List<VPXSYOrderProduct> products;

    private List<String> paymentStages;

}
