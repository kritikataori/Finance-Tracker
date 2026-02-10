package com.financetracker.repository;

import com.financetracker.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    //find all transactions by an account
    @Query("SELECT t FROM Transaction t WHERE t.account.id = :accountId AND t.user.id = :userId")
    List<Transaction> findTransactionsByAccountIdAndUserId(@Param("accountId") Long accountId, @Param("userId") Long userId);

    //find all transactions for a user with pagination
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId ORDER BY t.transactionDate DESC, t.createdAt DESC")
    List<Transaction> findAllTransactionsForAUser(@Param("userId") Long userId, Pageable pageable);

    //find all transactions for a user in a category
    @Query("SELECT t FROM Transaction t WHERE t.category.id = :categoryId AND t.user.id = :userId ORDER BY t.transactionDate DESC")
    List<Transaction> findTransactionsInACategoryForAUser(@Param("categoryId") Long CategoryId, @Param("userId") Long userId);

    //find a transaction by its id and userId
    @Query("SELECT t FROM Transaction t WHERE t.id = :id AND t.user.id = :userId")
    Optional<Transaction> findTransactionByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    //find transactions by the given date range
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.transactionDate BETWEEN :startDate AND :endDate ORDER BY t.transactionDate DESC")
    List<Transaction> findTransactionsByDateRange(@Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate, @Param("userId") Long userId);

    //calculate spending by category in a date range
    @Query("SELECT t.category.id, t.category.name, SUM(ABS(t.amount)) " +
            "FROM Transaction t WHERE t.user.id = :userId " +
            "AND t.type = 'EXPENSE' " +
            "AND t.transactionDate BETWEEN :startDate AND :endDate " +
            "AND t.category IS NOT NULL " +
            "GROUP BY t.category.id, t.category.name " +
            "ORDER BY SUM(ABS(t.amount)) DESC")
    List<Object[]> calculateSpendingByCategory(
            @Param("userId") Long userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    //calculate total by transaction type and date range
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.user.id = :userId AND t.type = :type AND t.transactionDate BETWEEN :startDate AND :endDate")
    BigDecimal calculateTotalByTransactionTypeAndDateRange(@Param("userId") Long userId, @Param("type") Transaction.TransactionType type, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    //find uncategorized transactions
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId AND t.category IS NULL ORDER BY t.transactionDate DESC")
    List<Transaction> findUncategorizedByUserId(@Param("userId") Long userId);

    //count total transactions for an account
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.account.id = :accountId")
    long countByAccountId(@Param("accountId") Long accountId);

    //advanced search for transactions with filters
    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId " +
            "AND (:accountId IS NULL OR t.account.id = :accountId) " +
            "AND (:categoryId IS NULL OR t.category.id = :categoryId) " +
            "AND (:type IS NULL OR t.type = :type) " +
            "AND (:startDate IS NULL OR t.transactionDate >= :startDate) " +
            "AND (:endDate IS NULL OR t.transactionDate <= :endDate) " +
            "ORDER BY t.transactionDate DESC, t.createdAt DESC")
    Page<Transaction> findTransactionsWithFilters(
            @Param("userId") Long userId,
            @Param("accountId") Long accountId,
            @Param("categoryId") Long categoryId,
            @Param("type") Transaction.TransactionType type,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );
}
