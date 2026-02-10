package com.financetracker.repository;

import com.financetracker.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {

    // Find goal by id and user id
    @Query("SELECT g FROM Goal g WHERE g.id = :id AND g.user.id = :userId")
    Optional<Goal> findGoalByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    // Find all goals for user
    @Query("SELECT g FROM Goal g WHERE g.user.id = :userId ORDER BY g.priority DESC, g.targetDate ASC")
    List<Goal> findAllGoalsByUserId(@Param("userId") Long userId);

    // Find active goals for user
    @Query("SELECT g FROM Goal g WHERE g.user.id = :userId AND g.status = 'ACTIVE' ORDER BY g.priority DESC, g.targetDate ASC")
    List<Goal> findActiveGoalsByUserId(@Param("userId") Long userId);

    // Find goals by status
    @Query("SELECT g FROM Goal g WHERE g.user.id = :userId AND g.status = :status ORDER BY g.priority DESC, g.targetDate ASC")
    List<Goal> findGoalsByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Goal.GoalStatus status);

    // Count active goals
    @Query("SELECT COUNT(g) FROM Goal g WHERE g.user.id = :userId AND g.status = 'ACTIVE'")
    long countActiveGoalsByUserId(@Param("userId") Long userId);
}
