package com.bearboy.tomcat.customizer;

import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * 设置SSLHostConfig
 * 默认tomcat protocolHandler 为 Http11NioProtocol，
 * @see org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
 */
@Configuration(proxyBeanMethods = false)
public class MyTomcatProtocolHandlerCustomizer implements TomcatProtocolHandlerCustomizer<Http11NioProtocol> {
    @Override
    public void customize(Http11NioProtocol protocolHandler) {
        System.out.println("MyTomcatProtocolHandlerCustomizer start");
        //由于TomcatConnectorCustomizer晚于MyTomcatProtocolHandlerCustomizer，此时查询的结果是off
        System.out.println(protocolHandler.getCompression());
    }
}
