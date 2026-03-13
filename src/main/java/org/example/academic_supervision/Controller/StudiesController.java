package org.example.academic_supervision.Controller;

import jakarta.validation.Valid;
import org.example.academic_supervision.DTO.StudiesDTO;
import org.example.academic_supervision.Service.IStudiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
@Tag(name = "Studies", description = "Operations related to studies")
@RestController
@RequestMapping("/studies")
public class StudiesController {

    private final IStudiesService studiesService;

    public StudiesController(IStudiesService studiesService) {
        this.studiesService = studiesService;
    }

    @GetMapping
    public List<StudiesDTO> getAllStudies() {
        return studiesService.getAllStudies();
    }

    @GetMapping("/{id}")
    public StudiesDTO getStudyById(@PathVariable Long id) {
        return studiesService.getStudyById(id);
    }

    @PostMapping
    public StudiesDTO createStudy(@Valid @RequestBody StudiesDTO studyDTO) {
        return studiesService.createStudy(studyDTO);
    }

    @PutMapping("/{id}")
    public StudiesDTO updateStudy(@PathVariable Long id, @Valid @RequestBody StudiesDTO studyDTO) {
        return studiesService.updateStudy(id, studyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudy(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
            }

            studiesService.deleteStudy(id);
            return new ResponseEntity<>("Study deleted successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}