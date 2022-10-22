package com.hacktoberfest.leprechaunsspendings.spending.service;

import com.hacktoberfest.leprechaunsspendings.spending.model.Spending;
import com.hacktoberfest.leprechaunsspendings.spending.model.repo.SpendingRepository;
import com.hacktoberfest.leprechaunsspendings.spending.web.exceptions.SpendingNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

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

    public SpendingDTO getSpending(UUID id) throws SpendingNotFoundException {
        Optional<Spending> spendingFromDatabase = spendingRepository.findById(id);

        spendingRepository.findAll();
        return spendingFromDatabase.map(SpendingMapper::mapSpendingDTO)
                .orElseThrow(SpendingNotFoundException::new);
    }
}
