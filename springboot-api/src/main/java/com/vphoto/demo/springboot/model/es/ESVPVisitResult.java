package com.vphoto.demo.springboot.model.es;


import lombok.Data;

@Data
public class ESVPVisitResult {

    private String localName;

    private String code;

    private String port;

    private String commitMd5;

    private String localAddr;

    private String version;

    private VPESVisitDataObj data;

}
