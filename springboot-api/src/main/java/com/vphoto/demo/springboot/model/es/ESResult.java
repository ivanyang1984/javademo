package com.vphoto.demo.springboot.model.es;


import lombok.Data;

@Data
public class ESResult {

    private String localName;

    private String code;

    private VPESDataObj data;

    private String port;

    private String commitMd5;

    private String localAddr;

    private String version;
}
