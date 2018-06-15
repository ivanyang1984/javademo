/*
 * ProjectName: springboot-parent
 * Author: tree.yu
 * Description:
 * Version: 1.0
 * Date: 18-5-30 下午6:29
 * LastModified: 18-5-30 下午6:29
 */

package com.vphoto.demo.springboot.web;


import com.vphoto.demo.springboot.web.controller.DemoController;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 * @author tree.yu
 * @version 1.0
 * @description 单元测试，需要启动web容器进行测试
 * @create 2018-05-30 18:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerTest {


    private final static Logger logger = LoggerFactory.getLogger(DemoControllerTest.class);


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void testQuery1() throws Exception{

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/9739", String.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":9739");

    }

    @Test
    public void testQuery2() throws Exception{

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/demo/v1/demo/9739", String.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //故意设置异常
        Assertions.assertThat(responseEntity.getBody()).contains("\"code\":200").contains("\"id\":9732");

    }

}
