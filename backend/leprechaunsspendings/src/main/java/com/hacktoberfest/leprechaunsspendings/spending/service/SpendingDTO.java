package com.hacktoberfest.leprechaunsspendings.spending.service;

import com.hacktoberfest.leprechaunsspendings.spending.model.Spending;
import com.hacktoberfest.leprechaunsspendings.spending.model.SpendingType;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class SpendingDTO {
    private UUID id;
    @NotBlank(message = "Author must be provided")
    private String author;
    @NotNull(message = "Amount and currency must be provided")
    @Valid
    private MoneyDTO money;
    @NotNull(message = "Spending type must be provided")
    private SpendingType spendingType;
    private String title;
    private String description;
    @NotNull(message = "Date must be provided")
    @PastOrPresent(message = "Date must not be from the future")
    private Date date;

    private SpendingDTO(final UUID id,
                        final String author,
                        final MoneyDTO money,
                        final SpendingType spendingType,
                        final String title,
                        final String description,
                        final Date date) {
        this.id = id;
        this.author = author;
        this.money = money;
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

    public void setAuthor(String author) {
        this.author = author;
    }

    public MoneyDTO getMoney() {
        return money;
    }

    public void setMoney(MoneyDTO money) {
        this.money = money;
    }

    public SpendingType getSpendingType() {
        return spendingType;
    }

    public void setSpendingType(SpendingType spendingType) {
        this.spendingType = spendingType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class Builder {
        private UUID id;
        private String author;
        private BigDecimal amount;
        private String currency;
        private SpendingType spendingType;
        private String title;
        private String description;
        private Date date;

        public static SpendingDTO.Builder create() {
            return new SpendingDTO.Builder();
        }

        public SpendingDTO.Builder withId(UUID id) {
            this.id = id;
            return this;
        }

        public SpendingDTO.Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public SpendingDTO.Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public SpendingDTO.Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public SpendingDTO.Builder withSpendingType(SpendingType spendingType) {
            this.spendingType = spendingType;
            return this;
        }

        public SpendingDTO.Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public SpendingDTO.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SpendingDTO.Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public SpendingDTO build() {
            return new SpendingDTO(id, author, new MoneyDTO(amount, currency), spendingType, title, description, date);
        }
    }
}
