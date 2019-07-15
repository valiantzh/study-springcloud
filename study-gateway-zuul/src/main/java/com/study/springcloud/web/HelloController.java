package com.study.springcloud.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author valiantzh
 * @version 1.0
 */
@RestController
public class HelloController {
    @RequestMapping("/local/hello")
    public String hello() {
        return "Hello World Local";
    }

}
