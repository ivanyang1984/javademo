package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

@Data
public class VPXSYTokenModel {

    private String id;

    private String access_token;

    private String issued_at;

    private String token_type;
}
