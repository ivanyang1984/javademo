package com.vphoto.demo.springboot.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
public class ConvertlabConfig {

    @Value("${convertlab.grantType}")
    private String grantType;

    @Value("${convertlab.appid}")
    private String appid;

    @Value("${convertlab.secret}")
    private String secret;

}
