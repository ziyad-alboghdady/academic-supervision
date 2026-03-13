package org.example.academic_supervision.Controller;

import jakarta.validation.Valid;
import org.example.academic_supervision.DTO.StudiesDTO;
import org.example.academic_supervision.Service.IStudiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String deleteStudy(@PathVariable Long id) {
        studiesService.deleteStudy(id);
        return "Study deleted successfully";
    }
}