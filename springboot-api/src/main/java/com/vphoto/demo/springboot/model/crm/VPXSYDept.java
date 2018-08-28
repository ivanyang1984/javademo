package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class VPXSYDept {

    private String id;

    private String departCode;

    private String departName;

    private String parentDepartId;

    private String departType;

    private String specialType;

    private String entityType;

    private String createdAt;

    private String createdBy;

    private String updatedAt;

    private String updatedBy;

    private List<Map<String,String>> admins;

    private List<Map<String,String>> members;

}
