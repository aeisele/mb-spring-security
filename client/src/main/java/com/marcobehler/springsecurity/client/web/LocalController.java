package com.marcobehler.springsecurity.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/local")
public class LocalController {

    @GetMapping
    public String getLocalResource() {
        return "this is a local (i.e. not remote) call, no OAuth etc. was used";
    }

}
