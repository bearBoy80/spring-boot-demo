package com.github.bearboy.retry;

import com.github.bearboy.retry.service.RemoteRpcServer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Index implements ApplicationContextAware {
    @Autowired
    private RemoteRpcServer server;

    private ApplicationContext context;

    @RequestMapping("/index")
    public String index(String userName) throws Exception {
        return server.getAccountByRpc(userName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
