package com.study.springcloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author valiantzh
 * @version 1.0
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private Registration registration; // 服务注册
    @Autowired
    private DiscoveryClient client;    // 服务发现客户端



    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() throws Exception {
        //ServiceInstance instance = client.getInstances()
        ServiceInstance instance = serviceInstance();
        log.info("/hello, host:" + instance.getHost() +
                ", service_id:" + instance.getServiceId());
        return "Hello World";
    }

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String hello(@RequestParam String name) {
        //ServiceInstance instance = client.getLocalServiceInstance();
        ServiceInstance instance = serviceInstance();
        log.info("/hello1, host:" + instance.getHost() +
                ", service_id:" + instance.getServiceId());
        return "Hello " + name;
    }

    @RequestMapping("/provider")
    public String provider() throws InterruptedException {
        ServiceInstance instance = serviceInstance();
        // 测试超时触发断路器,Hystrix默认超时时间2000ms
        int sleepTime = new Random().nextInt(3000);
        log.info("sleepTime:" + sleepTime);
        Thread.sleep(sleepTime);

        log.info("provider service, host = " + instance.getHost()
                + ", service_id = " + instance.getServiceId());
        return "Hello,Provider!";
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        //ServiceInstance instance = client.getLocalServiceInstance();
        ServiceInstance instance = serviceInstance();
        log.info("/hello2, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello(@RequestBody User user) {
        //ServiceInstance instance = client.getLocalServiceInstance();
        ServiceInstance instance = serviceInstance();
        log.info("/hello3, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "Hello "+ user.getName() + ", " + user.getAge();
    }
    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            /*for(ServiceInstance itm : list){
                if(itm.getPort() == 2001)
                    return itm;
            }*/
            return list.get(0);
        }
        return null;
    }
}
