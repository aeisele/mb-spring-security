package com.marcobehler.springsecurity.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

// tag::websecurityconfigureradapter[]
@Configuration
@EnableWebSecurity
public class ClientAppConfig extends WebSecurityConfigurerAdapter {
// end::websecurityconfigureradapter[]

    // tag::methodheader[]
    @Bean
    public WebClient webClient(ClientRegistrationRepository clientRegistrationRepository,
                               OAuth2AuthorizedClientRepository authorizedClientRepository) {
    // end::methodheader[]

        // tag::filterfunction[]
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2FilterFunction =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(
                        clientRegistrationRepository, authorizedClientRepository);
        // end::filterfunction[]

        // tag::defaultclient[]
        oauth2FilterFunction.setDefaultClientRegistrationId("mb-client");
        // end::defaultclient[]

        // tag::actualclient[]
        return WebClient.builder()
                .apply(oauth2FilterFunction.oauth2Configuration())
                .build();
        // tag::actualclient[]
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/local")
                .permitAll()
                .and()
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .oauth2Login();
        // @formatter:on
    }
}
