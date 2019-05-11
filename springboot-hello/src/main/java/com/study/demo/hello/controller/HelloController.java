package com.study.demo.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author valiantzh
 * @version 1.0
 */
@RestController
public class HelloController {
    @Autowired
    @RequestMapping("/hello")
    public String index(){
        return "Hello World";
    }
}
