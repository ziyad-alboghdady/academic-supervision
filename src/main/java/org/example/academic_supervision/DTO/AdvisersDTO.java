package org.example.academic_supervision.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvisersDTO {
    private Long id;
    private String name;
    private String department;
}