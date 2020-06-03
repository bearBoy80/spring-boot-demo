
package com.github.bearboy.spring.common.utils;

import com.github.bearboy.spring.common.exception.JwtException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * @author bearBoy80
 */
public final class NimbusJwtUtils {

    private static final byte[] SECRET = "6MNSobBRCHGIO0fS6MNSobBRCHGIO0fS".getBytes();
    private static final long EXPIRE_TIME = 1000 * 3600;

    @SneakyThrows
    public static String buildJWT(User account) {
        /**
         * 1.创建一个32-byte的密匙
         */
        MACSigner macSigner = new MACSigner(SECRET);
        /**
         * 2. 建立payload 载体
         */
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject("username")
                .issuer("http://localhost:8100")
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .claim("username", account.getUsername())
                .build();
        /**
         * 3. 建立签名
         */
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(macSigner);

        /**
         * 4. 生成token
         */
        String token = signedJWT.serialize();
        return token;
    }

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    public static String vaildToken(String token) throws JwtException {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET);
            //校验是否有效
            if (!jwt.verify(verifier)) {
                throw new JwtException("token校验失败");
            }
            //校验超时
            Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                throw new JwtException("token 过期");
            }

            //获取载体中的数据
            Object account = jwt.getJWTClaimsSet().getClaim("username");
            //是否有username
            if (Objects.isNull(account)) {
                throw new JwtException("username 为空");
            }
            return account.toString();
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        User user = new User("test", "password", new ArrayList<>());
        String token = buildJWT(user);
        System.out.println(token);
        System.out.println(vaildToken(token));
    }
}
