package com.nrx.basicauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure/hello-world")
public class HelloWorldRestController {

    @GetMapping
    public String helloWorld(Authentication authentication)
    {
        System.out.println(authentication.getName());
        return "Secure Hello World";
    }

}
