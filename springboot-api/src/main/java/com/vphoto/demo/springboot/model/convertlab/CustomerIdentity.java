package com.vphoto.demo.springboot.model.convertlab;

import lombok.Data;

@Data
public class CustomerIdentity {

    private String identityType;

    private String identityValue;

    private String customerId;

    private String identityName;
}
