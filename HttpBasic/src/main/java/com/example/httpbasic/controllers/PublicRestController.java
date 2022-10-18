package com.example.httpbasic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
public class PublicRestController {

    public PublicRestController(){}

    @GetMapping("test1")
    public String test1(){
        return "ApiTest 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "ApiTest 2";
    }

}
