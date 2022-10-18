package com.hacktoberfest.leprechaunsspendings.spending.service;

import com.hacktoberfest.leprechaunsspendings.spending.model.Money;
import com.hacktoberfest.leprechaunsspendings.spending.model.SpendingType;

import java.util.Date;

public class SpendingDTO {
    private String author;
    private Money money;
    private SpendingType spendingType;
    private String title;
    private String description;
    private Date date;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
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
}
