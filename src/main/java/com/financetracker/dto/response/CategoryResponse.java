package com.financetracker.dto.response;

import com.financetracker.entity.Category;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private Long id;
    private String name;
    private Category.CategoryType type;
    private String color;
    private String icon;
    private Boolean isSystem;
    private Boolean isActive;
    private Long transactionCount;
    private LocalDateTime createdAt;
}
