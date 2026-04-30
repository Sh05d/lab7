package org.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "ID can't be empty")
    private String id;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotEmpty(message = "Category can't be empty")
    @Pattern(regexp = "(?i)^(Technology|Business|Design)",message = "Category should be one of these Technology, Business or Design")
    private String category;
    @AssertFalse
    private boolean isPaid;
    @PositiveOrZero
    private double fees;
}
