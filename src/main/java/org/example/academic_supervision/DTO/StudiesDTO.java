package org.example.academic_supervision.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.academic_supervision.Model.Studies;

@Data
public class StudiesDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50)
    private String title;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 5, max = 100)
    private String description;

    private Studies mapToEntity(StudiesDTO dto) {

        Studies study = new Studies();

        study.setTitle(dto.getTitle());
        study.setDescription(dto.getDescription());

        return study;
    }
}