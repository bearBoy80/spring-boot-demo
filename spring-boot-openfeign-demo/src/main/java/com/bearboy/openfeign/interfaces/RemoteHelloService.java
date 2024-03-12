package com.bearboy.openfeign.interfaces;

import com.bearboy.openfeign.config.FooConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "localhost:8080",name = "remoteHelloService",configuration = FooConfiguration.class)
public interface RemoteHelloService {
    @RequestMapping(value = "/echo",method = RequestMethod.GET)
    String echoService(@RequestParam("msg") String msg);
}
