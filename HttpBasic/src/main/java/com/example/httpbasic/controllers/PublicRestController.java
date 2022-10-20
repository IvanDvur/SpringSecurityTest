package com.example.httpbasic.controllers;

import com.example.httpbasic.models.Client;

import com.example.httpbasic.repos.ClientRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
public class PublicRestController {

    ClientRepository clientRepository;
    public PublicRestController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping("test1")
    public String test1(){
        return "ApiTest 1";
    }

    @GetMapping("test2")
    public String test2(){
        return "ApiTest 2";
    }

    @GetMapping("users")
    public List<Client> allUsers(){
        return this.clientRepository.findAll();
    }

}
