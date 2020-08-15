package com.marcobehler.springsecurity.resourceserver.web;

import com.marcobehler.springsecurity.resourceserver.model.Transaction;

import java.util.List;

public class TransactionList {

    private final String holderName;

    private final String accountNumber;

    private final List<Transaction> transactions;

    private TransactionList(String holderName, String accountNumber, List<Transaction> transactions) {
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.transactions = transactions;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public static class Builder {
        private String holderName;
        private String accountNumber;
        private List<Transaction> transactions;

        public Builder withHolderName(String holderName) {
            this.holderName = holderName;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder withTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public TransactionList createTransactionList() {
            return new TransactionList(holderName, accountNumber, transactions);
        }
    }
}
