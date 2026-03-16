package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.SupervisesDTO;
import org.example.academic_supervision.Exception.ErrorMessages;
import org.example.academic_supervision.Exception.ResourceAlreadyExistsException;
import org.example.academic_supervision.Exception.ResourceNotFoundException;
import org.example.academic_supervision.Model.Supervises;
import org.example.academic_supervision.Repository.SupervisesRepository;
import org.example.academic_supervision.Repository.AdvisersRepository;
import org.example.academic_supervision.Repository.StudiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupervisesServiceImpl implements ISupervisesService {

	private final SupervisesRepository supervisesRepository;
	private final AdvisersRepository advisersRepository;
	private final StudiesRepository studiesRepository;

	public SupervisesServiceImpl(SupervisesRepository supervisesRepository,
								 AdvisersRepository advisersRepository,
								 StudiesRepository studiesRepository) {
		this.supervisesRepository = supervisesRepository;
		this.advisersRepository = advisersRepository;
		this.studiesRepository = studiesRepository;
	}

	@Override
	public List<SupervisesDTO> getAllSupervises() {
		return supervisesRepository.findAll().stream()
				.map(Supervises::viewAsSupervisesDTO)
				.toList();
	}

	@Override
	public SupervisesDTO getSupervisesById(Long id) {
		Supervises s = supervisesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_SUPERVISES_NOT_FOUND + ": " + id));
		return s.viewAsSupervisesDTO();
	}

	@Override
	public SupervisesDTO createSupervises(Supervises supervises) {
		if (supervises.getSupervisesId() != null && supervisesRepository.findById(supervises.getSupervisesId()).isPresent()) {
			throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_SUPERVISES_ALREADY_EXIST + ": " + supervises.getSupervisesId());
		}
		return supervisesRepository.save(supervises).viewAsSupervisesDTO();
	}

	@Override
	public SupervisesDTO updateSupervises(Long id, Supervises supervises) {
		supervisesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_SUPERVISES_NOT_FOUND + ": " + id));
		
		supervises.setSupervisesId(id);
		return supervisesRepository.save(supervises).viewAsSupervisesDTO();
	}

	@Override
	public void deleteSupervises(Long id) {
		supervisesRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_SUPERVISES_NOT_FOUND + ": " + id));

		supervisesRepository.deleteById(id);
	}
}
