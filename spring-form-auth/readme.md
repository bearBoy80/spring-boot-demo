# 项目介绍
通过springboot 实现form 表单登录。
## 整合步骤
 - 添加依赖
 ```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>
    </dependencies> 
```
- 自定义 WebSecurityConfigurerAdapter
```java
/**
 * @author bearBoy80
 * 定义简单的form 表单登录，
 * 默认登录页面为spring security自带页面
 * @see DefaultLoginPageGeneratingFilter#generateLoginPageHtml(HttpServletRequest, boolean, boolean)
 */
@Configuration
public class SecurityFormAuthConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.   //关闭crsf
                csrf().disable()
                //拦截所有请求
                .authorizeRequests().anyRequest().authenticated().and()
                //设置表单登录
                .formLogin()
               // .loginPage() 自定义登录页面
                //.failureForwardUrl() 认证失败跳转页面url
                //.successForwardUrl() 认证成功跳转页面url
                //.failureHandler() 认证失败拦截器处理，需要自己实现一个AuthenticationFailureHandler,编写自己的业务逻辑处理，
                // 比如登录失败3次，等几分钟在登录，记录登录失败日志等
                //.successHandler() 认证成功拦截器处理，需要自己实现一个AuthenticationFailureHandler
                // 可以在这里写业务逻辑处理，比如登录成功记录日志、
                //设置登录点
                .loginProcessingUrl("/myLogin");
    }
}
```
- 实现UserDetailsService
```java
/**
 * @author bearBoy80
 * 如果想定制用户认证，需要自己实现 UserDetailService类。本例 通过 InMemoryUserDetailsManager来实现简单表单认证
 * @see UserDetailsServiceAutoConfiguration springboot默认实现
 */
@Configuration
@Log
public class UserDetailServiceConfig {

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
```
- 增加userInfo controller
```java
/**
 * @author bearBoy
 * 获取用户认证信息
 */
@RestController
public class UserDetailController {
    @RequestMapping("/userInfo")
    public User userInfo(@AuthenticationPrincipal User user) {
        return user;
    }
}
```
- 启动应用
 运行FormAuthApplication
- 验证
  请求localhost:8100/userInfo,页面跳转到登录页面，输入user/password账户密码登录，登录成功后获取到凭证信息
  
 ![tnJ3B8.th.png](../assert/form-login.png)
 ![tnJ8HS.th.png](../assert/form-succ.png)

## 原理说明
 - form表单登录流程
 
![tn0rtK.md.png](../assert/loginurlauthenticationentrypoint.png)

- form表单认证流程

![tn0sfO.md.png](../assert/usernamepasswordauthenticationfilter.png)

- form表单关键类 

 > UsernamePasswordAuthenticationFilter 拦截登录请求，通过username、password 拼接UsernamePasswordAuthenticationToken，
 调用ProviderManager(AuthenticationManager子类).authenticate 进行认证
 
 > ProviderManager 根据UsernamePasswordAuthenticationToken 选择DaoAuthenticationProvider.authenticate 进行认证
 
 > DaoAuthenticationProvider 类 
 DaoAuthenticationProvider.authenticate(Authentication authentication)实现用户密码验证，并验证用户各种状态。
 认证成功后重新生成 UsernamePasswordAuthenticationToken,否则抛AuthenticationException异常
 
 > UsernamePasswordAuthenticationToken  存储用户凭证信息和权限信息
 
 ## 部署应用到docker
 - 添加docker maven 插件 [fabric8](https://maven.fabric8.io/)
 ```xml
 <plugin>
                     <groupId>io.fabric8</groupId>
                     <artifactId>docker-maven-plugin</artifactId>
                     <version>0.33.0</version>
                     <extensions>true</extensions>
                     <configuration>
                         <verbose>true</verbose>
                         <dockerHost>docker容器IP:2375</dockerHost>
                         <images>
                             <image>
                                 <name>${project.artifactId}</name>
                                 <build>
                                     <dockerFileDir>
                                         ${project.basedir}
                                     </dockerFileDir>
                                 </build>
                             </image>
                         </images>
                     </configuration>
                     <executions>
                         <execution>
                             <id>build</id>
                             <phase>post-integration-test</phase>
                             <goals>
                                 <goal>build</goal>
                             </goals>
                         </execution>
                     </executions>
                 </plugin>
 ```
 - 添加dockerfile文件
 ```text
 # 基于java:8-jdk-alpine 镜像进行创建
 FROM java:8-jdk-alpine
 #维护人
 MAINTAINER bearBoy80
 #复制jar到/usr/app目录
 COPY ./target/spring-form-auth-0.0.1-SNAPSHOT.jar /usr/app/
 #指定镜像工作目录
 WORKDIR /usr/app
 #更新jar包信息
 RUN sh -c 'touch spring-form-auth-0.0.1-SNAPSHOT.jar'
 #暴露8010端口，实际运行镜像需要指定宿主端口 -P或者-p
 EXPOSE 8010
 #启动容器后启动spring-form-auth-0.0.1-SNAPSHOT.jar的应用
 ENTRYPOINT ["java", "-jar", "spring-form-auth-0.0.1-SNAPSHOT.jar"]
 ```
 - 生成镜像
 ```shell
 mvn package fabric8:build
 ```
 - 部署镜像到容器
 ```shell
 docker run -d 镜像id -P
 ```
 