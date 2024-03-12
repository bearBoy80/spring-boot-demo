package com.bearboy.openfeign.web;

import com.bearboy.openfeign.interfaces.RemoteHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private RemoteHelloService remoteHelloService;

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echoMessage(@RequestParam(name = "msg") String message) {
        System.out.println("收到请求信息:" + message);
        return "echo " + message;
    }

    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String message(@RequestParam(name = "msg") String message) {
       return remoteHelloService.echoService(message);
    }
}
