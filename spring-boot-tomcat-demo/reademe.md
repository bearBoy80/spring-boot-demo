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

## Tomcat自动配置web.xml
## 基于外部tomcat
基于servlet3.0+ ServletContainerInitializer来实现WebApplicationInitializer的收集，
并在onStartup方法执行WebApplicationInitializer相关的onStartup方法。
spring框架通过SpringServletContainerInitializer来实现，SpringServletContainerInitializer的加载是通过SPI来实现的，servlet容器会自动来加载
## 基于内嵌tomcat
基于TomcatStarter来实现ServletContainerInitializer,并持有Spring相关的ServletContextInitializer实现类，当容器启动时会自动调用tomcatstarter#onstartup方法，从而调用
Spring定义ServletContextInitializer相关类实现。
典型类
 - org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.selfInitialize
 ```java
	private void selfInitialize(ServletContext servletContext) throws ServletException {
        //获取root context
		prepareWebApplicationContext(servletContext);
		registerApplicationScope(servletContext);
        //将servlet context 注册到容器中
		WebApplicationContextUtils.registerEnvironmentBeans(getBeanFactory(), servletContext);
		for (ServletContextInitializer beans : getServletContextInitializerBeans()) {
			beans.onStartup(servletContext);
		}
	}
```