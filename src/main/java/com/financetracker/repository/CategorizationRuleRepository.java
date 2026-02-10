package com.financetracker.repository;

import com.financetracker.entity.CategorizationRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorizationRuleRepository extends JpaRepository<CategorizationRule, Long> {

    // Find all system rules (user_id is null)
    @Query("SELECT r FROM CategorizationRule r WHERE r.user IS NULL AND r.isActive = true ORDER BY r.priority DESC")
    List<CategorizationRule> findAllSystemRules();

    // Find user's custom rules
    @Query("SELECT r FROM CategorizationRule r WHERE r.user.id = :userId AND r.isActive = true ORDER BY r.priority DESC")
    List<CategorizationRule> findAllByUserId(@Param("userId") Long userId);

    // Find all applicable rules for a user (system + user's custom), ordered by priority
    @Query("SELECT r FROM CategorizationRule r WHERE (r.user IS NULL OR r.user.id = :userId) AND r.isActive = true ORDER BY r.priority DESC")
    List<CategorizationRule> findAllApplicableRules(@Param("userId") Long userId);
}
