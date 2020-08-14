package com.marcobehler.springsecurity.resourceserver.web;

import com.marcobehler.springsecurity.resourceserver.model.Transaction;
import com.marcobehler.springsecurity.resourceserver.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return service.findAll();
    }

}
