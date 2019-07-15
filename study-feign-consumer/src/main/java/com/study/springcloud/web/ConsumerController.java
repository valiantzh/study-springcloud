package com.study.springcloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author valiantzh
 * @version 1.0
 */
@RestController
public class ConsumerController {
    @Autowired
    HelloService helloService;

    @Autowired
    RefactorHelloService refactorHelloService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return helloService.hello();
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("<br/>");
        sb.append(helloService.hello("ZXY")).append("<br/>");
        sb.append(helloService.hello("ZXY", 30)).append("<br/>");
        sb.append(helloService.hello(new User("zxy", 30))).append("<br/>");

        return sb.toString();
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2() {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.hello("MIMI")).append("<br/>");
        sb.append(refactorHelloService.hello("MIMI", 20)).append("<br/>");
        sb.append(refactorHelloService.hello(new com.study.springcloud.dto.User("MIMI", 20))).append("<br/>");
        return sb.toString();
    }
}
