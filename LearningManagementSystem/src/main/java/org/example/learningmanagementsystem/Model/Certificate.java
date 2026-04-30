package org.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Certificate {
    @NotEmpty(message = "ID can't be empty")
    private String id;
    @NotEmpty(message = "Student ID can't be empty")
    private String studentId;
    @NotEmpty(message = "Course ID can't be empty")
    private String courseId;
    @AssertFalse
    private boolean isIssued;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;
}
