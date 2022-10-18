package com.hacktoberfest.leprechaunsspendings.spending.service;

import com.hacktoberfest.leprechaunsspendings.spending.model.repo.SpendingRepository;
import org.springframework.stereotype.Service;

@Service
public class SpendingService {
    private SpendingRepository spendingRepository;

    public SpendingService(SpendingRepository spendingRepository) {
        this.spendingRepository = spendingRepository;
    }

    public void createSpending(SpendingDTO spendingDTO) {
        spendingRepository.save(SpendingMapper.mapSpending(spendingDTO));
    }
}
