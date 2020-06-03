
package com.github.bearboy.spring.common.jwt;

import com.github.bearboy.spring.common.exception.JwtException;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bearBoy80
 * token 默认解析接口实现类
 */
public final class DefaultBearerTokenResolver implements BearerTokenResolver {

    private static final Pattern authorizationPattern = Pattern.compile(
            "^(?<token>[a-zA-Z0-9-._~+/]+)=*$",
            Pattern.CASE_INSENSITIVE);

    private boolean allowFormEncodedBodyParameter = false;

    private boolean allowUriQueryParameter = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public String resolve(HttpServletRequest request) {
        String authorizationHeaderToken = resolveFromAuthorizationHeader(request);
        String parameterToken = resolveFromRequestParameters(request);
        if (authorizationHeaderToken != null) {
            if (parameterToken != null) {
                throw new JwtException("参数类型不匹配");
            }
            return authorizationHeaderToken;
        } else if (parameterToken != null && isParameterTokenSupportedForRequest(request)) {
            return parameterToken;
        }
        return null;
    }

    /**
     * Set if transport of access token using form-encoded body parameter is supported. Defaults to {@code false}.
     *
     * @param allowFormEncodedBodyParameter if the form-encoded body parameter is supported
     */
    public void setAllowFormEncodedBodyParameter(boolean allowFormEncodedBodyParameter) {
        this.allowFormEncodedBodyParameter = allowFormEncodedBodyParameter;
    }

    /**
     * Set if transport of access token using URI query parameter is supported. Defaults to {@code false}.
     * <p>
     * The spec recommends against using this mechanism for sending bearer tokens, and even goes as far as
     * stating that it was only included for completeness.
     *
     * @param allowUriQueryParameter if the URI query parameter is supported
     */
    public void setAllowUriQueryParameter(boolean allowUriQueryParameter) {
        this.allowUriQueryParameter = allowUriQueryParameter;
    }

    private static String resolveFromAuthorizationHeader(HttpServletRequest request) {
        String token = request.getHeader("bearer");
        if (StringUtils.hasText(token)) {
            Matcher matcher = authorizationPattern.matcher(token);
            if (!matcher.matches()) {

                throw new JwtException("token 格式不对");
            }

            return matcher.group("token");
        }
        return null;
    }

    private static String resolveFromRequestParameters(HttpServletRequest request) {
        String[] values = request.getParameterValues("bearer");
        if (values == null || values.length == 0) {
            return null;
        }
        if (values.length == 1) {
            return values[0];
        }
        throw new JwtException("参数类型不匹配");
    }

    private boolean isParameterTokenSupportedForRequest(HttpServletRequest request) {
        return ((this.allowFormEncodedBodyParameter && "POST".equals(request.getMethod()))
                || (this.allowUriQueryParameter && "GET".equals(request.getMethod())));
    }
}