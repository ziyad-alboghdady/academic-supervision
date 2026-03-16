package org.example.academic_supervision.Controller;

import jakarta.validation.Valid;
import org.example.academic_supervision.DTO.StudiesDTO;
import org.example.academic_supervision.Model.Studies;
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

    @GetMapping("/all")
    public ResponseEntity<List<StudiesDTO>> getAllStudies() {
        return new ResponseEntity<>(studiesService.getAllStudies(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<StudiesDTO> getStudyById(@PathVariable Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studiesService.getStudyById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<StudiesDTO> createStudy(@Valid @RequestBody Studies study) {
        return new ResponseEntity<>(studiesService.createStudy(study), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudiesDTO> updateStudy(@PathVariable Long id, @Valid @RequestBody Studies study) {
        if (id == null || id <= 0 || study == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(studiesService.updateStudy(id, study), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudy(@PathVariable Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        studiesService.deleteStudy(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}