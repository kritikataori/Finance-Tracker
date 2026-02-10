package com.financetracker.repository;

import com.financetracker.entity.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {
}
