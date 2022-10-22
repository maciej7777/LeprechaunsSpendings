package com.hacktoberfest.leprechaunsspendings.spending.web;

import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingDTO;
import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/spendings")
public class SpendingController {
    private final SpendingService spendingService;

    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @PostMapping
    public void createSaving(@Valid @RequestBody SpendingDTO spending) {
        spendingService.createSpending(spending);
    }
}
