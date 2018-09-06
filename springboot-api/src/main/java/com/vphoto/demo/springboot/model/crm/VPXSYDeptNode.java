package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

import java.util.List;

@Data
public class VPXSYDeptNode {

    private String id;

    private String name;

    private String departCode;

    private List<VPXSYDeptNode> subs;
}
