package com.financetracker.repository;

import com.financetracker.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //find all active accounts for a user
    @Query("SELECT a FROM Account a WHERE a.user.id = :userId AND a.isActive = true")
    List<Account> findActiveAccountsForAUser(@Param("userId") Long userId);

    //find an account by id
    @Query("SELECT a FROM Account a WHERE a.id = :accountId AND a.user.id = :userId")
    Optional<Account> findAccountByIdAndUserId(@Param("accountId") Long accountId, @Param("userId") Long userId);
}
