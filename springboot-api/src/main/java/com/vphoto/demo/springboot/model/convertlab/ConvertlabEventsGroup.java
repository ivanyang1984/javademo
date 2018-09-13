package com.vphoto.demo.springboot.model.convertlab;

import lombok.Data;

import java.util.List;

@Data
public class ConvertlabEventsGroup {

    private String groupName;

    private String color;

    private String icon;

    private String groupLabel;

    private String filterable;

    private List<ConvertlabEvent> events;
}
