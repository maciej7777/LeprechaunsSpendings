package com.hacktoberfest.leprechaunsspendings.spending.web;

import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingDTO;
import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingService;
import com.hacktoberfest.leprechaunsspendings.spending.web.exceptions.SpendingNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/spendings")
public class SpendingController {
    private final SpendingService spendingService;

    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @PostMapping
    public SpendingDTO createSaving(@Valid @RequestBody SpendingDTO spending) {
        return spendingService.createSpending(spending);
    }

    @GetMapping("/{spendingId}")
    public SpendingDTO getSaving(@Valid @PathVariable UUID spendingId) throws SpendingNotFoundException {
        return spendingService.getSpending(spendingId);
    }
}
