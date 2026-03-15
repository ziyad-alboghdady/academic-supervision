package org.example.academic_supervision.Controller;

import jakarta.validation.Valid;
import org.example.academic_supervision.DTO.AdvisersDTO;
import org.example.academic_supervision.Model.Advisers;
import org.example.academic_supervision.Service.IAdvisersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adviser")
public class AdvisersController {
    private final static Logger logger = LoggerFactory.getLogger(AdvisersController.class);
    private final IAdvisersService advisersService;

    public AdvisersController(IAdvisersService advisersService) {
        this.advisersService = advisersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdvisersDTO>> getAllAdvisers() {
        return new ResponseEntity<>(advisersService.getAllAdvisers(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}", produces = "application/json")
    public ResponseEntity<AdvisersDTO> getAdviser(@PathVariable Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        logger.info("Get adviser by id {}", id);
        return new ResponseEntity<>(advisersService.getAdviserById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AdvisersDTO> addAdviser(@Valid @RequestBody Advisers adviser) {
        return new ResponseEntity<>(advisersService.createAdviser(adviser), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdvisersDTO> updateAdviser(@PathVariable Long id, @Valid @RequestBody Advisers adviser) {
        if (id == null || id <= 0 || adviser == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(advisersService.updateAdviser(id, adviser), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdviser(@PathVariable Long id) {
        if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        advisersService.deleteAdviser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
