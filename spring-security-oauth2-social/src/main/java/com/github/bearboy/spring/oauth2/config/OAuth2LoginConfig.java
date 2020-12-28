package com.github.bearboy.spring.oauth2.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class OAuth2LoginConfig {
    private static final String DEFAULT_REDIRECT_URL = "{baseUrl}/{action}/oauth2/code/{registrationId}";

    @EnableWebSecurity
    public static class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest().authenticated()
                    )
                    .oauth2Login(withDefaults());
        }
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties properties) {
        List<ClientRegistration> registrations = new ArrayList<>(
                OAuth2ClientPropertiesRegistrationAdapter.getClientRegistrations(properties).values());
        return new InMemoryClientRegistrationRepository(registrations);
        //return new InMemoryClientRegistrationRepository(this.githubClientRegistration(properties), this.gitteClientRegistration(properties));
    }

    private ClientRegistration githubClientRegistration(OAuth2ClientProperties properties) {
        String clientId = properties.getRegistration().get("github").getClientId();
        String clientSecret = properties.getRegistration().get("github").getClientSecret();
        ClientRegistration.Builder builder = getBuilder("github",
                ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
        builder.clientId(clientId);
        builder.clientSecret(clientSecret);
        builder.scope("read:user");
        builder.authorizationUri("https://github.com/login/oauth/authorize");
        builder.tokenUri("https://github.com/login/oauth/access_token");
        builder.userInfoUri("https://api.github.com/user");
        builder.userNameAttributeName("id");
        builder.clientName("GitHub");
        return builder.build();
    }

    private ClientRegistration gitteClientRegistration(OAuth2ClientProperties properties) {
        String clientId = properties.getRegistration().get("gitte").getClientId();
        String clientSecret = properties.getRegistration().get("gitte").getClientSecret();
        ClientRegistration.Builder builder = getBuilder("gitte",
                ClientAuthenticationMethod.BASIC, DEFAULT_REDIRECT_URL);
        builder.clientId(clientId);
        builder.clientSecret(clientSecret);
        builder.scope("user_info");
        builder.authorizationUri("https://gitee.com/oauth/authorize");
        builder.tokenUri("https://gitee.com/oauth/token");
        builder.userInfoUri("https://gitee.com/api/v5/user");
        builder.userNameAttributeName("id");
        builder.clientName("Gitte");
        return builder.build();
    }

    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method, String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(redirectUri);
        return builder;
    }
}