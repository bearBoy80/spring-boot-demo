
package com.github.bearboy.spring.common.response;

/**
 * @author:bearBoy80
 * @date: 2020/4/15
 * @sina 1.0
 * @Description: 响应码枚举
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未认证
     */
    UNAUTHORIZED(401),
    /**
     * 请求被拒
     */
    FORBIDDEN(403),
    /**
     * 请求资源不存在
     */
    NOT_FUND(404),
    /**
     * 请求方式不允许
     */
    METHOD_NOT_ALLOWED(405),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }

}
