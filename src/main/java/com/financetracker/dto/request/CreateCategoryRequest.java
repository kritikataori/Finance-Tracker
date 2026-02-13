package com.financetracker.dto.request;

import com.financetracker.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "Category name is required")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Category type is required")
    private Category.CategoryType type;

    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "Color must be valid hex code (e.g., #10B981)")
    private String color;

    @Size(max = 50, message = "Icon identifier cannot exceed 50 characters")
    private String icon;
}
