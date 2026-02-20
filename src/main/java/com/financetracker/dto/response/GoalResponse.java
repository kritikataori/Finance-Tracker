package com.financetracker.dto.response;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

import com.financetracker.entity.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalResponse {

    private Long id;
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDate targetDate;
    private Integer priority;
    private Goal.GoalStatus status;
    private Double progressPercentage;
    private Long daysRemaining;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Helper method to calculate days remaining
    public void calculateDaysRemaining() {
        if (targetDate != null) {
            this.daysRemaining = ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
        }
    }
}
