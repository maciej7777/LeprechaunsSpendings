package com.hacktoberfest.leprechaunsspendings.spending.service;

import com.hacktoberfest.leprechaunsspendings.spending.model.Spending;

public class SpendingMapper {
    public static Spending mapSpending(SpendingDTO spendingDTO) {
        return Spending.Builder.create()
                .withAuthor(spendingDTO.getAuthor())
                .withDate(spendingDTO.getDate())
                .withSpendingType(spendingDTO.getSpendingType())
                .withDescription(spendingDTO.getDescription())
                .withAmount(spendingDTO.getMoney().getAmount())
                .withCurrency(spendingDTO.getMoney().getCurrency())
                .withTitle(spendingDTO.getTitle())
                .build();
    }
}
