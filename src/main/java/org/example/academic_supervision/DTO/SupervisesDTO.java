package org.example.academic_supervision.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SupervisesDTO {
	private Long supervisesId;

	@NotBlank(message = "Student name cannot be empty")
	private String student;

	@Min(value = 0, message = "Performance must be at least 0")
	@Max(value = 100, message = "Performance must be at most 100")
	private Integer performance;

	private Long adviserId;
	private Long studyId;
}
