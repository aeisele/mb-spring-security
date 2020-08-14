package com.marcobehler.springsecurity.resourceserver.service;

import com.github.javafaker.Faker;
import com.marcobehler.springsecurity.resourceserver.model.Transaction;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    private static final List<Transaction> TRANSACTIONS = new ArrayList<>();

    @PostConstruct
    public void init() {
        // don't do this in the real world
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            createRandomTransaction(faker);
        }
    }

    private void createRandomTransaction(Faker faker) {
        Transaction tx = new Transaction.Builder()
                .withCreditor(faker.name().fullName())
                .withDebtor(faker.name().fullName())
                .withAmount(BigDecimal.valueOf(faker.random().nextDouble() * 100))
                .withTimestamp(LocalDateTime.ofInstant(faker.date().past(10, TimeUnit.DAYS).toInstant(), ZoneId.systemDefault()))
                .withUsage(faker.commerce().productName())
                .createTransaction();

        TRANSACTIONS.add(tx);
    }

    public List<Transaction> findAll() {
        return List.copyOf(TRANSACTIONS);
    }

}
