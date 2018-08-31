package com.vphoto.demo.springboot.model.es;

import lombok.Data;

@Data
public class VPESVisitData {

    private VPESVisitSource vistAvgScale;

    private VPESVisitSource vistPv;

    private VPESVisitSource vistAvg;

    private VPESVisitSource vistUvScale;

    private VPESVisitSource vistUv;

    private VPESVisitSource vistPvScale;
}
