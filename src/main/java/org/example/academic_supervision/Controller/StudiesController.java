package org.example.academic_supervision.Controller;

import jakarta.validation.Valid;
import org.example.academic_supervision.DTO.StudiesDTO;
import org.example.academic_supervision.Service.IStudiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studies")
public class StudiesController {

    private final IStudiesService studiesService;

    public StudiesController(IStudiesService studiesService) {
        this.studiesService = studiesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudiesDTO>> getAllStudies() {
        try {
            List<StudiesDTO> studies = studiesService.getAllStudies();

            if (studies == null || studies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(studies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StudiesDTO> getStudyById(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            StudiesDTO study = studiesService.getStudyById(id);
            return new ResponseEntity<>(study, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<StudiesDTO> createStudy(@Valid @RequestBody StudiesDTO studyDTO) {
        try {
            if (studyDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (studyDTO.getTitle() == null || studyDTO.getTitle().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (studyDTO.getDescription() == null || studyDTO.getDescription().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            StudiesDTO createdStudy = studiesService.createStudy(studyDTO);
            return new ResponseEntity<>(createdStudy, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudiesDTO> updateStudy(@PathVariable Long id,
                                                  @Valid @RequestBody StudiesDTO studyDTO) {
        try {
            if (id == null || id <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (studyDTO == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (studyDTO.getTitle() == null || studyDTO.getTitle().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if (studyDTO.getDescription() == null || studyDTO.getDescription().trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            StudiesDTO updatedStudy = studiesService.updateStudy(id, studyDTO);
            return new ResponseEntity<>(updatedStudy, HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudy(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            studiesService.deleteStudy(id);
            return new ResponseEntity<>("Study deleted successfully", HttpStatus.OK);

        } catch (RuntimeException e) {
            return new ResponseEntity<>("Study not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}