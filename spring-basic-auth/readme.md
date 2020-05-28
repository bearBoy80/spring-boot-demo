#项目说明
## HTTP Basic 认证
### Basic 认证是 Web 服务器于客户端之间进行认证的一种方式， 最初是在HTTP 1.0 规范（RFC 1945）中定义，后续的有关安全的信息可以在HTTP 1.1规范（RFC 2616）和HTTP认证规范（RFC 2617）中找到。
### Basic 认证过程
#### 当客户端请求需要进行 Basic 认证的资源，服务器就会返回带有 401 unauthorized 状态码以及 WWW-Authenticate 首部字段的响应。WWW-Authenticate 内容包含了认证方式（Basic）及 Request-URI 安全域字符串（realm）。
```http request
HTTP/1.1 401 Unauthorized
WWW-Authenticate: Basic realm="realm"
```
-----
客户端收到 401 状态码并要求进行 Basic 认证后，就需要用户 ID 和密码发送给服务器，发送的字符串内容是由用户 ID 和密码通过冒号（:）连接后，再经过 Base64 进行编码处理得到。然后把这个字符串写入 Authorization 首部字段，并发送请求。

-----
客户端是浏览器的情况下，这个阶段会弹出对话框，要求输入用户 ID 和密码，确定后就会自动完成编码发送请求。

----
```http request
GET /index.html HTTP/1.1
Host: localhost
Authorization: Basic aGFyYm9yOmhhcmJvcg==
```
服务器接收到包含 Authorization 字段的请求后，会对其正确性进行验证。如验证通过，则返回一条包含 Request-URI 资源的响应。

Basic 认证优点
在假定客户端和服务器之间的连接安全的情况下，通过Basic 认证来实现身份认证非常简单，只需要对Web服务器（比如Apache、NGINX）简单的配置即可轻松实现。客户端如果是浏览器，也无需做什么处理。

Basic 认证缺点
Basic 采用了 Base64 编码处理，但这并非加密处理，可以轻易地进行解码。在没有进行加密的 HTTP 通信中使用 Basic 认证，可以轻易被窃听而得到认证的用户 ID 和密码。

在Basic 认证过后，只能通过关闭浏览器进行认证注销操作，这样切换用户时就会比较麻烦

----
## spring boot 整合httpBasic 步骤
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
    </dependencies>
```
- 继承WebSecurityConfigurerAdapter 重写 configure(HttpSecurity http),并对特定url进行认证
```java
@Configuration
public class HttpBasicSecurityconfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/hello", "/").and().
                authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
```
- 覆盖spring-security默认用户密码
```properties
spring.security.user.name= test
spring.security.user.password=test123456
```
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
COPY ./target/spring-basic-auth-0.0.1-SNAPSHOT.jar /usr/app/
#指定镜像工作目录
WORKDIR /usr/app
#更新jar包信息
RUN sh -c 'touch spring-basic-auth-0.0.1-SNAPSHOT.jar'
#暴露8010端口，实际运行镜像需要指定宿主端口 -P或者-p
EXPOSE 8010
#启动容器后启动spring-basic-auth-0.0.1-SNAPSHOT.jar的应用
ENTRYPOINT ["java", "-jar", "spring-basic-auth-0.0.1-SNAPSHOT.jar"]
```
- 生成镜像
```shell
mvn package fabric8:build
```
- 部署镜像到容器
```shell
docker run -d 镜像id -P
```