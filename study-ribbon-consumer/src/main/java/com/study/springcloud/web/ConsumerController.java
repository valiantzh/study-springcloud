package com.study.springcloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author valiantzh
 * @version 1.0
 */
@RestController
@Slf4j
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hello-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        log.info("/hello-consumer");
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }
    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    public String provider(){
        log.info("/provider");
        return helloService.provider();
    }
    @RequestMapping(value = "/provider1", method = RequestMethod.GET)
    public String provider1(){
        log.info("/provider1");
        return helloService.provider1();
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        log.info("/hello");
        return helloService.hello();
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello1() {
        log.info("/hello1");
        return helloService.hello1();
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2() {
        log.info("/hello2");
        return helloService.hello2();
    }
}
