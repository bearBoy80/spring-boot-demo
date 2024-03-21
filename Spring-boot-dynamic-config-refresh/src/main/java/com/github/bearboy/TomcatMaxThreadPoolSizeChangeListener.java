package com.github.bearboy;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.AbstractProtocol;
import org.apache.coyote.ProtocolHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration(proxyBeanMethods = false)
public class TomcatMaxThreadPoolSizeChangeListener implements ApplicationContextAware, TomcatConnectorCustomizer, ApplicationListener<EnvironmentChangeEvent> {

    private final String poolSize = "server.tomcat.threads.max";

    @Autowired
    private Environment environment;

    private ProtocolHandler protocolHandler;

    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent event) {
        //判断事件源是不是来自当前容器上下文
        if (this.applicationContext.equals(event.getSource())) {
            if (event.getKeys().contains(poolSize)) {
                System.out.println("接收到 server port 端口变化");
                int maxThreads = Integer.parseInt(environment.getProperty(poolSize));
                if (protocolHandler instanceof AbstractProtocol) {
                    AbstractProtocol protocol = (AbstractProtocol) protocolHandler;
                    protocol.setMaxThreads(maxThreads);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void customize(Connector connector) {
        this.protocolHandler = connector.getProtocolHandler();
    }
}
