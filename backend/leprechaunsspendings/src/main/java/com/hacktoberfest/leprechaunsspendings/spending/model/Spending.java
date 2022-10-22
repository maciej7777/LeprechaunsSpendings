package com.hacktoberfest.leprechaunsspendings.spending.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
public class Spending {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    private String author;
    private BigDecimal amount;
    private String currency;
    private SpendingType spendingType;
    private String title;
    private String description;
    private Date date;

    private Spending(String author, BigDecimal amount, String currency, SpendingType spendingType, String title, String description, Date date) {
        this.author = author;
        this.amount = amount;
        this.currency = currency;
        this.spendingType = spendingType;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public SpendingType getSpendingType() {
        return spendingType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public static class Builder {
        private String author;
        private BigDecimal amount;
        private String currency;
        private SpendingType spendingType;
        private String title;
        private String description;
        private Date date;

        public static Builder create() {
            return new Builder();
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withSpendingType(SpendingType spendingType) {
            this.spendingType = spendingType;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Spending build() {
            return new Spending(author, amount, currency, spendingType, title, description, date);
        }

    }
}
