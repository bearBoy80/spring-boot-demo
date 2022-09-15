package com.github.bearboy.retry.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RemoteRpcServer {
    /**
     * 定义重试条件:RuntimeException异常
     * 回退重试策略:每次重试间隔在100-500ms
     *
     * @param userName
     * @return
     * @throws RuntimeException
     */
    @Retryable(include = RuntimeException.class, listeners = {"retryListener1"}, backoff = @Backoff(delay = 100, maxDelay = 500))
    public String getAccountByRpc(String userName) throws RuntimeException {
        System.out.println(LocalDateTime.now() + "start getAccountByRpc.....");
        if ("retry".equals(userName)) {
            throw new RuntimeException("retry demo");
        }
        return "account" + userName;
    }
}
