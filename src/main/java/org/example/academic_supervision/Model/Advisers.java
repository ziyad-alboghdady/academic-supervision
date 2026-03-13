package org.example.academic_supervision.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Advisers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Adviser name cannot be empty")
    @Size(min = 3, max = 50, message = "Adviser name must be between 3 and 50 characters")
    @Column(length = 50, nullable = false)
    private String adviserName;

    @NotBlank(message = "Department cannot be empty")
    @Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    @Column(length = 50, nullable = false)
    private String department;

    @OneToMany(mappedBy = "adviser", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Supervises> supervises;
}