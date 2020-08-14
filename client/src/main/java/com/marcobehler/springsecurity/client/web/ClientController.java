package com.marcobehler.springsecurity.client.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public ClientController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping
    public Map<String, Object> clientInfo(Authentication authentication) {
        OAuth2AuthorizedClient authorizedClient =
                this.authorizedClientService.loadAuthorizedClient("mb-client", authentication.getName());

        return Map.of(
                "accessToken", authorizedClient.getAccessToken(),
                "refreshToken", authorizedClient.getRefreshToken(),
                "principalName", authorizedClient.getPrincipalName(),
                "clientRegistration", authorizedClient.getClientRegistration()
        );
    }

}
