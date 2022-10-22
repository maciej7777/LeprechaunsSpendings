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

    public static SpendingDTO mapSpendingDTO(Spending spending) {
        return SpendingDTO.Builder.create()
                .withId(spending.getId())
                .withAuthor(spending.getAuthor())
                .withDate(spending.getDate())
                .withSpendingType(spending.getSpendingType())
                .withDescription(spending.getDescription())
                .withAmount(spending.getAmount())
                .withCurrency(spending.getCurrency())
                .withTitle(spending.getTitle())
                .build();
    }
}
