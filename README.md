<h1 align="center"><a href="https://github.com/bearBoy80/springSecurity.git" target="_blank">Spring Boot 相关组件整合</a></h1>
<p align="center">
  <a href="https://travis-ci.com/github/bearBoy80/springSecurity"><img alt="Travis-CI" src="https://travis-ci.com/bearBoy80/springSecurity.svg?branch=master"/></a>
  <a href="https://www.codacy.com/manual/bearBoy80/springSecurity?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=bearBoy80/springSecurity&amp;utm_campaign=Badge_Grade"><img src="https://app.codacy.com/project/badge/Grade/b800466e41b140f28af11764be7b6495"/></a>
  <a href="http://www.betool.vip/"><img alt="author" src="https://img.shields.io/badge/author-bearBoy80-blue"/></a>
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8+-orange.svg"/></a>
  <a href="https://docs.spring.io/spring-boot/docs/2.7.3/reference/htmlsingle"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-2.7.2-brightgreen.svg"/></a>
  <a href="https://github.com/bearBoy80/springSecurity/blob/master/LICENSE"><img alt="LICENSE" src="https://img.shields.io/github/license/bearBoy80/springsecurity.svg"/></a>  
</p>

<p align="center">
  <a href="https://github.com/bearBoy80/springSecurity/stargazers"><img alt="star" src="https://img.shields.io/github/stars/bearBoy80/springsecurity.svg?label=Stars&style=social"/></a>
  <a href="https://github.com/bearBoy80/springSecurity/network/members"><img alt="star" src="https://img.shields.io/github/forks/bearBoy80/springsecurity.svg?label=Fork&style=social"/></a>
  <a href="https://github.com/bearBoy80/springSecurity/watchers"><img alt="star" src="https://img.shields.io/github/watchers/bearBoy80/springsecurity.svg?label=Watch&style=social"/></a>
</p>


# springboot2+ 整合各种组件


## 各 Module 介绍

| Module 名称                                                        | Module 介绍                                                    |
|------------------------------------------------------------------|--------------------------------------------------------------|
| [spring-basic-auth](./spring-basic-auth)                         | spring-boot 的一个http basic 认证 基于spring security 5.X           |
| [spring-form-auth](./spring-form-auth)                           | spring-boot 的一个 form 认证 基于spring security 5.X                |
| [spring-actutor-auth](./spring-form-auth)                        | 基于basic和form混合认证,实现spring boot actutor Endpoints Basic认证     |
| [spring-form-jwt-auth](./spring-form-jwt-auth)                   | spring boot form表单实现jwt认证 基于spring security 5.X              |
| [spring-sms-jwt-auth](./spring-sms-jwt-auth)                     | spring boot 手机短信验证码实现jwt认证 基于spring security 5.X             |
| [spring-security-oauth2-social](./spring-security-oauth2-social) | spring boot 实现第三方oauth2认证（github、gitee） 基于spring security 5.X |
| [spring-boot-saml2-auth](./spring-boot-saml2-auth)               | spring boot 实现第三方saml2认证基于spring security 5.6+               |
| [spring-cache-demo](./spring-cache-demo)                         | spring boot 实现encache 缓存                                     |
| [spring-boot-arg-demo](./spring-boot-arg-demo)                   | spring boot 实现通过ApplicationArguments来获取应用传递参数                |
| [spring-boot-json-demo](./spring-boot-json-demo)                 | spring boot 实现自定义HttpMessageConverter来处理特定类的序列化和反序列化         |
| [spring-boot-task-demo](./spring-boot-task-demo)                 | spring boot 整合spring task                                    |
| [spring-boot-customer-starter](./spring-boot-customer-starter)   | 基于@autoConfigure实现自定义starter                                 |
| [spring-boot-webflux-demo](./spring-boot-react-web-demo)         | 基于webflux实现一个简单的controller                                   |
| [spring-boot-retry-demo](./spring-boot-retry-demo)               | 基于spring-retry实现一个重试controller                               |
| [spring-boot-redisson-demo](./spring-boot-redisson-demo)         | 基于redisson实现连接redis服务                                        |
| [spring-boot-resilience4j-demo](./spring-boot-resilience4j-demo)     | 基于resilience4j实现熔断限流|                                         |