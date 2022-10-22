package com.hacktoberfest.leprechaunsspendings.spending.service;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MoneyDTO {
    @NotNull(message = "Amount must be provided")
    @DecimalMin(value = "0", inclusive = false, message = "Spending must be greater then 0")
    private BigDecimal amount;
    @NotNull(message = "Currency must be provided")
    private String currency;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
