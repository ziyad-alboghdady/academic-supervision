package org.example.academic_supervision.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Studies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @NotBlank(message = "Description is required")
    @Size(max = 100, message = "Description cannot exceed 100 characters")
    @Column(length = 100)
    private String description;


    @NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    @Column(length = 50)
    private String title;


    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<Supervises> supervises;

}
