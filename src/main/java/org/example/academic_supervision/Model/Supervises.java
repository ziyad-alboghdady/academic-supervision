package org.example.academic_supervision.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    public Supervises(org.example.academic_supervision.DTO.SupervisesDTO supervisesDTO) {
        this.supervisesId = supervisesDTO.getSupervisesId();
        this.student = supervisesDTO.getStudent();
        this.performance = supervisesDTO.getPerformance();
    }

    public org.example.academic_supervision.DTO.SupervisesDTO viewAsSupervisesDTO() {
        org.example.academic_supervision.DTO.SupervisesDTO dto = new org.example.academic_supervision.DTO.SupervisesDTO();
        dto.setSupervisesId(this.supervisesId);
        dto.setStudent(this.student);
        dto.setPerformance(this.performance);
        if (this.adviser != null) dto.setAdviserId(this.adviser.getId());
        if (this.study != null) dto.setStudyId(this.study.getId());
        return dto;
    }
}