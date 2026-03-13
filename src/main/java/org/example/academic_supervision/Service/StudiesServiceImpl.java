package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.StudiesDTO;
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
        return studiesRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public StudiesDTO getStudyById(Long id) {
        Studies study = studiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));
        return mapToDTO(study);
    }

    @Override
    public StudiesDTO createStudy(StudiesDTO dto) {
        Studies study = mapToEntity(dto);
        Studies savedStudy = studiesRepository.save(study);
        return mapToDTO(savedStudy);
    }

    @Override
    public StudiesDTO updateStudy(Long id, StudiesDTO dto) {
        Studies existingStudy = studiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));

        existingStudy.setTitle(dto.getTitle());
        existingStudy.setDescription(dto.getDescription());

        Studies updatedStudy = studiesRepository.save(existingStudy);
        return mapToDTO(updatedStudy);
    }

    @Override
    public void deleteStudy(Long id) {
        Studies existingStudy = studiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));

        studiesRepository.delete(existingStudy);
    }

    private StudiesDTO mapToDTO(Studies study) {
        StudiesDTO dto = new StudiesDTO();
        dto.setId(study.getId());
        dto.setTitle(study.getTitle());
        dto.setDescription(study.getDescription());
        return dto;
    }

    private Studies mapToEntity(StudiesDTO dto) {
        Studies study = new Studies();
        study.setTitle(dto.getTitle());
        study.setDescription(dto.getDescription());
        return study;
    }
}