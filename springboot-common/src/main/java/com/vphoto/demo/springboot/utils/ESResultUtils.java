package com.vphoto.demo.springboot.utils;

import com.vphoto.demo.springboot.model.es.VPESVisitData;
import com.vphoto.demo.springboot.model.es.VPESVisitSource;

import java.util.List;

public class ESResultUtils {

    public static VPESVisitSource convertListToData(List<String> numlist){

        List<String> num = numlist;
        VPESVisitSource vAvgDataSource = new VPESVisitSource();
        if (numlist.size() < 4) return vAvgDataSource;
        vAvgDataSource.setSendFriend(num.get(0));
        vAvgDataSource.setWeigroup(num.get(1));
        vAvgDataSource.setFriendList(num.get(2));
        vAvgDataSource.setDirectVisit(num.get(3));
        return vAvgDataSource;
    }
}
