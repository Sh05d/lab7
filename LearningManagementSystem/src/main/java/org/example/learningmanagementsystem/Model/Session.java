package org.example.learningmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Session {
    @NotEmpty(message = "ID can't be empty")
    private String id;
    @NotEmpty(message = "Title can't be empty")
    private String title;
    @NotEmpty(message = "Course ID can't be empty")
    private String courseId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Positive
    private int durationHours;
    @NotEmpty(message = "contentUrl can't be null")
    @URL
    private String contentUrl;
}
