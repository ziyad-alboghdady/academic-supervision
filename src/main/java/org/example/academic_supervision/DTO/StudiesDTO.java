package org.example.academic_supervision.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudiesDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 5, max = 100)
    private String description;
}