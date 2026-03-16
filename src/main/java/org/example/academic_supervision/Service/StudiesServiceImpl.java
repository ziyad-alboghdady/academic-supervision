package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.StudiesDTO;
import org.example.academic_supervision.Exception.ErrorMessages;
import org.example.academic_supervision.Exception.ResourceAlreadyExistsException;
import org.example.academic_supervision.Exception.ResourceNotFoundException;
import org.example.academic_supervision.Model.Studies;
import org.example.academic_supervision.Repository.StudiesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudiesServiceImpl implements IStudiesService {

    private final StudiesRepository studiesRepository;

    public StudiesServiceImpl(StudiesRepository studiesRepository) {
        this.studiesRepository = studiesRepository;
    }

    @Override
    public List<StudiesDTO> getAllStudies() {
        return studiesRepository.findAll().stream()
                .map(Studies::viewAsStudiesDTO)
                .toList();
    }

    @Override
    public StudiesDTO getStudyById(Long id) {
        return studiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_STUDY_NOT_FOUND + ": " + id)).viewAsStudiesDTO();
    }

    @Override
    public StudiesDTO createStudy(Studies study) {
        if (studiesRepository.findById(study.getId()).isPresent()) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_STUDY_ALREADY_EXIST + ": " + study.getId());
        }
        return studiesRepository.save(study).viewAsStudiesDTO();
    }

    @Override
    public StudiesDTO updateStudy(Long id, Studies study) {
        studiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_STUDY_NOT_FOUND + ": " + id));
        study.setId(id);
        return studiesRepository.save(study).viewAsStudiesDTO();
    }

    @Override
    public void deleteStudy(Long id) {
        studiesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_STUDY_NOT_FOUND + ": " + id));
        studiesRepository.deleteById(id);
    }
}