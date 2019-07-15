package com.study.springcloud.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author valiantzh
 * @version 1.0
 */
@Service
@Slf4j
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback") //指定回调
    public String hello() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
    }
    @HystrixCommand(fallbackMethod = "helloFallback") //指定回调
    public String provider() {
        long start = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();

        // GET
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/provider", String.class).getBody()).append("<br>");

        long end = System.currentTimeMillis();

        log.info("Spend time : " + (end - start) );
        return result.toString();
    }
    @HystrixCommand(fallbackMethod = "helloFallback") //指定回调
    public String provider1() {
        long start = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();

        // GET
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody()).append("<br>");
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/provider", String.class).getBody()).append("<br>");

        Map<String, String> params = new HashMap<>();
        params.put("name", "dada");
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/hello1?name={name}", String.class, params).getBody()).append("<br>");

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                "http://HELLO-SERVICE/hello1?name={name}")
                .build()
                .expand("dodo")
                .encode();
        URI uri = uriComponents.toUri();
        result.append(restTemplate.getForEntity(uri, String.class).getBody()).append("<br>");

        long end = System.currentTimeMillis();

        log.info("Spend time : " + (end - start) );
        return result.toString();
    }

    @HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey")
    public String hello1() {
        return restTemplate.getForEntity("http://HELLO-SERVICE/hello1?name=zxy", String.class).getBody();
    }
    @HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey")
    public String hello2() {
        long start = System.currentTimeMillis();
        StringBuilder result = new StringBuilder();

        // GET
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody()).append("<br>");
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/hello1?name={1}", String.class, "didi").getBody()).append("<br>");

        Map<String, String> params = new HashMap<>();
        params.put("name", "dada");
        result.append(restTemplate.getForEntity("http://HELLO-SERVICE/hello1?name={name}", String.class, params).getBody()).append("<br>");

        UriComponents uriComponents = UriComponentsBuilder.fromUriString(
                "http://HELLO-SERVICE/hello1?name={name}")
                .build()
                .expand("dodo")
                .encode();
        URI uri = uriComponents.toUri();
        result.append(restTemplate.getForEntity(uri, String.class).getBody()).append("<br>");

        // POST
        User user = new User("didi", 20);
        String postResult = restTemplate.postForObject("http://HELLO-SERVICE/hello3", user, String.class);
        result.append(postResult).append("<br>");

        user = new User("didi", 30);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://HELLO-SERVICE/hello3", user, String.class);
        result.append(responseEntity.getBody()).append("<br>");

        long end = System.currentTimeMillis();

        log.info("Spend time : " + (end - start) );
        return result.toString();
    }
    public String helloFallback() {
        return "error";
    }
}
