package com.marcobehler.springsecurity.resourceserver.web;

import com.marcobehler.springsecurity.resourceserver.model.Transaction;
import com.marcobehler.springsecurity.resourceserver.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_read_transactions')")
    public TransactionList getTransactions(@AuthenticationPrincipal Jwt jwt) {

        logger.info("got request including jwt {}", jwt);

        String holderName = jwt.getClaimAsString("name");
        String accountNumber = jwt.getClaimAsString("account_no");
        List<Transaction> transactions = service.findAll();

        return new TransactionList.Builder()
                .withHolderName(holderName)
                .withAccountNumber(accountNumber)
                .withTransactions(transactions)
                .createTransactionList();
    }

}
