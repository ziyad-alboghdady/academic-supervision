package org.example.academic_supervision.Controller;

import jakarta.validation.Valid;
import org.example.academic_supervision.Model.Studies;
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
    public List<Studies> getAllStudies() {
        return studiesService.getAllStudies();
    }

    @GetMapping("/{id}")
    public Studies getStudyById(@PathVariable Long id) {
        return studiesService.getStudyById(id);
    }

    @PostMapping
    public Studies createStudy(@Valid @RequestBody Studies study) {
        return studiesService.createStudy(study);
    }

    @PutMapping("/{id}")
    public Studies updateStudy(@PathVariable Long id, @Valid @RequestBody Studies study) {
        return studiesService.updateStudy(id, study);
    }

    @DeleteMapping("/{id}")
    public String deleteStudy(@PathVariable Long id) {
        studiesService.deleteStudy(id);
        return "Study deleted successfully";
    }
}