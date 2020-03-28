package com.seifi.jobclube.core.controller;

import com.seifi.jobclube.core.entitys.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test/teststring")
    public String test(){

        return "teststring+1+2+3+4wwwww";
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
