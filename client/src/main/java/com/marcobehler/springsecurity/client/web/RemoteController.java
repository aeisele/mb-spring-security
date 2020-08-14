package com.marcobehler.springsecurity.client.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/remote")
public class RemoteController {

    private static final Logger logger = LoggerFactory.getLogger(RemoteController.class);

    private final URI transactionsUri;

    private final WebClient webClient;

    public RemoteController(@Value("${my-resource-server.transactions.uri}") URI transactionsUri, WebClient webClient) {
        this.transactionsUri = transactionsUri;
        this.webClient = webClient;
    }

    @GetMapping
    public Mono<Object> getTransactionsRemotely(@AuthenticationPrincipal Object principal) {
        logger.info("doing remote call as principal {}", principal);

        Mono<Object> bodyMono = webClient.get()
                .uri(transactionsUri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class);

        return bodyMono;
    }

}
