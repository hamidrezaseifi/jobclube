package com.seifi.jobclube.controller;

import com.seifi.jobclube.entitys.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/teststring")
    public String test(){

        return "teststring";
    }

    @GetMapping(value = "/test/testuser" , produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity testUser(){

        UserEntity u = new UserEntity();
        u.setAddress("add");
        u.setFirstName("fn");
        u.setId(1L);
        u.setLastName("ln");
        u.setPhoto("ph");
        u.setUserName("un");

        return u;
    }
}
