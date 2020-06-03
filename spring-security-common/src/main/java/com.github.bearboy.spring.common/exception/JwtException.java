

package com.github.bearboy.spring.common.exception;

/**
 * @author bearBoy80
 * jwt 异常
 */
public class JwtException extends RuntimeException {

    /**
     * Constructs a {@code JwtException} using the provided parameters.
     *
     * @param message the detail message
     */
    public JwtException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code JwtException} using the provided parameters.
     *
     * @param message the detail message
     * @param cause   the root cause
     */
    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }
}