package com.github.bearboy.webflux.function;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author handler 处理请求
 */
@Component
public class MyUserHandler {

    public Mono<ServerResponse> getUser(ServerRequest request) {
        String userId = request.pathVariable("user");
        return ServerResponse.ok().bodyValue("user1:" + userId);
    }
}

