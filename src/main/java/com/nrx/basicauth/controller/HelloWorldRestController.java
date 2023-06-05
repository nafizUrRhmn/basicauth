package com.nrx.basicauth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    private static final Logger logger = LogManager.getLogger("myy");
    @GetMapping("/public/hello")
    public String helloWorld(Authentication authentication)
    {
        logger.info("Info");
        logger.error("Error");
        logger.warn("warn");
        return "Secure Hello World";
    }

}
