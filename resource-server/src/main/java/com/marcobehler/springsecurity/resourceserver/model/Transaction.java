package com.marcobehler.springsecurity.resourceserver.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final String creditor;
    private final String debtor;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final String usage;

    private Transaction(String creditor, String debtor, BigDecimal amount, LocalDateTime timestamp, String usage) {
        this.creditor = creditor;
        this.debtor = debtor;
        this.amount = amount;
        this.timestamp = timestamp;
        this.usage = usage;
    }

    public String getCreditor() {
        return creditor;
    }

    public String getDebtor() {
        return debtor;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getUsage() {
        return usage;
    }

    public static class Builder {
        private String creditor;
        private String debtor;
        private BigDecimal amount;
        private LocalDateTime timestamp;
        private String usage;

        public Builder withCreditor(String creditor) {
            this.creditor = creditor;
            return this;
        }

        public Builder withDebtor(String debtor) {
            this.debtor = debtor;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withUsage(String usage) {
            this.usage = usage;
            return this;
        }

        public Transaction createTransaction() {
            return new Transaction(creditor, debtor, amount, timestamp, usage);
        }

    }
}
