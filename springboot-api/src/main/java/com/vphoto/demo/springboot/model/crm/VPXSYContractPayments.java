package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

@Data
public class VPXSYContractPayments {

    private String count;

    private VPXSYContractLite contract;

    private String stages;

    private String payments;

    private String invoices;
}
