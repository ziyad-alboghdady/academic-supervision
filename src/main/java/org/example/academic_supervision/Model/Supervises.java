package org.example.academic_supervision.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supervises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supervisesId;

    @NotBlank(message = "Student name cannot be empty")
    @Column(nullable = false)
    private String student;

    @Min(value = 0, message = "Performance must be at least 0")
    @Max(value = 100, message = "Performance must be at most 100")
    @Column(nullable = false)
    private Integer performance;

    @ManyToOne
    @JoinColumn(name = "adviser_id", nullable = false)
    private Advisers adviser;

    @ManyToOne
    @JoinColumn(name = "study_id", nullable = false)
    private Studies study;
}