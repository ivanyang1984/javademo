package com.vphoto.demo.springboot.model.convertlab;

import lombok.Data;

import java.util.List;

@Data
public class ConvertlabResult {

    private String page;

    private List<GroupModel> rows;

    private Long records;

    private Long total;
}
