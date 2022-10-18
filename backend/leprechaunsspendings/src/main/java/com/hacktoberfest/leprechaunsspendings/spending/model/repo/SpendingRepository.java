package com.hacktoberfest.leprechaunsspendings.spending.model.repo;


import com.hacktoberfest.leprechaunsspendings.spending.model.Spending;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SpendingRepository extends CrudRepository<Spending, UUID> {
}
