package com.hacktoberfest.leprechaunsspendings.spending.service;

import com.hacktoberfest.leprechaunsspendings.spending.model.Spending;
import com.hacktoberfest.leprechaunsspendings.spending.model.repo.SpendingRepository;
import org.springframework.stereotype.Service;

@Service
public class SpendingService {
    private final SpendingRepository spendingRepository;

    public SpendingService(SpendingRepository spendingRepository) {
        this.spendingRepository = spendingRepository;
    }

    public SpendingDTO createSpending(SpendingDTO spendingDTO) {
        Spending savedSpending = spendingRepository.save(SpendingMapper.mapSpending(spendingDTO));
        return SpendingMapper.mapSpendingDTO(savedSpending);
    }
}
