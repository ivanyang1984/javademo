package com.vphoto.demo.springboot.model.es;

import lombok.Data;

import java.util.List;

@Data
public class VPESVisitDataObj {

    private List<String> vistAvgScale;

    private List<String> vistPv;

    private List<String> vistAvg;

    private List<String> vistUvScale;

    private List<String> vistUv;

    private List<String> vistPvScale;
}
