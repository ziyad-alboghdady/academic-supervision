package org.example.academic_supervision.Controller;

import java.util.List;

import org.example.academic_supervision.DTO.SupervisesDTO;
import org.example.academic_supervision.Service.ISupervisesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/supervises")
public class SupervisesController {

	private final ISupervisesService supervisesService;

	public SupervisesController(ISupervisesService supervisesService) {
		this.supervisesService = supervisesService;
	}

@GetMapping("/all")
	public ResponseEntity<List<SupervisesDTO>> getAllSupervises() {
		return new ResponseEntity<>(supervisesService.getAllSupervises(), HttpStatus.OK);
	}

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<SupervisesDTO> getSupervisesById(@PathVariable Long id) {
		if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(supervisesService.getSupervisesById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<SupervisesDTO> createSupervises(@Valid @RequestBody org.example.academic_supervision.Model.Supervises supervises) {
		return new ResponseEntity<>(supervisesService.createSupervises(supervises), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<SupervisesDTO> updateSupervises(@PathVariable Long id, @Valid @RequestBody org.example.academic_supervision.Model.Supervises supervises) {
		if (id == null || id <= 0 || supervises == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(supervisesService.updateSupervises(id, supervises), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteSupervises(@PathVariable Long id) {
		if (id == null || id <= 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		supervisesService.deleteSupervises(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
