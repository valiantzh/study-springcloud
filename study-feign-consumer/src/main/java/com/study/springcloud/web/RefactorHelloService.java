package com.study.springcloud.web;

import com.study.springcloud.service.HelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author valiantzh
 * @version 1.0
 */
@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends HelloService {
}
