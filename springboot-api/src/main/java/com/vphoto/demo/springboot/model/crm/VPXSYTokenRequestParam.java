package com.vphoto.demo.springboot.model.crm;

import lombok.Data;

@Data
public class VPXSYTokenRequestParam {

    private String grant_type;

    private String client_id;

    private String client_secret;

    private String redirect_uri;

    private String username;

    private String password;
}
