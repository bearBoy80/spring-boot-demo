# Tomcat扩展点
## WebServerFactoryCustomizer
### 内部参考实现
    - TomcatServletWebServerFactoryCustomizer
    - ServletWebServerFactoryCustomizer
基于WebServerFactoryCustomizer是最灵活的扩展点
## TomcatContextCustomizer
### 内部参考实现
    - DisableReferenceClearingContextCustomizer
## TomcatConnectorCustomizer
### 内部参考实现
   - SslConnectorCustomizer
   - CompressionConnectorCustomizer
   - CompressionConnectorCustomizer
## TomcatProtocolHandlerCustomizer