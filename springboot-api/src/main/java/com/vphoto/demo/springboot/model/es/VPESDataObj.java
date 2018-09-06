package com.vphoto.demo.springboot.model.es;

import lombok.Data;

import java.util.List;

@Data
public class VPESDataObj {

    private String commentNum;

    private List<VPESUserOrderDetail> userOrderDetailList;

    private String albumsVisitorsNum;

    private String total;

    private String talkNum;

    private String photosPV;

    private String albumsPV;

    private String perCapitaVisitsNum;

    private String likeNum;
}
