package com.vphoto.demo.springboot.model.convertlab;

import lombok.Data;

import java.util.List;

@Data
public class ConvertlabEvent {

    private String name;

    private String label;

    private String attr1;

    private String title;

    private String showInTimeline;

    private String showInFilter;

    private String engaged;

    private String showInFlow;

    private String isDropdown;

    private String forContact;

    private String score;

    private String stage;



    private List<ConvertlabEventProperty> props;

}
