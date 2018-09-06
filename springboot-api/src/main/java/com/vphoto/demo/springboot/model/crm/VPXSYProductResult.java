package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

import java.util.List;

@Data
public class VPXSYProductResult {

    private String totalSize;

    private String count;

    private List<VPXSYProduct> products;
}
