package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.StudiesDTO;
import org.example.academic_supervision.Model.Studies;
import org.example.academic_supervision.Repository.StudiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudiesServiceImpl implements IStudiesService {

    private static final Logger logger = LoggerFactory.getLogger(StudiesServiceImpl.class);

    private final StudiesRepository studiesRepository;

    public StudiesServiceImpl(StudiesRepository studiesRepository) {
        this.studiesRepository = studiesRepository;
    }

    @Override
    public List<StudiesDTO> getAllStudies() {
        logger.info("Fetching all studies");

        List<StudiesDTO> studies = studiesRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();

        logger.info("Fetched {} studies", studies.size());
        return studies;
    }

    @Override
    public StudiesDTO getStudyById(Long id) {
        logger.info("Fetching study with id: {}", id);

        Studies study = studiesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Study not found with id: {}", id);
                    return new RuntimeException("Study not found with id: " + id);
                });

        logger.info("Study found with id: {}", id);
        return mapToDTO(study);
    }

    @Override
    public StudiesDTO createStudy(StudiesDTO dto) {
        logger.info("Creating new study with title: {}", dto.getTitle());

        Studies study = mapToEntity(dto);
        Studies savedStudy = studiesRepository.save(study);

        logger.info("Study created successfully with id: {}", savedStudy.getId());
        return mapToDTO(savedStudy);
    }

    @Override
    public StudiesDTO updateStudy(Long id, StudiesDTO dto) {
        logger.info("Updating study with id: {}", id);

        Studies existingStudy = studiesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Study not found with id: {}", id);
                    return new RuntimeException("Study not found with id: " + id);
                });

        existingStudy.setTitle(dto.getTitle());
        existingStudy.setDescription(dto.getDescription());

        Studies updatedStudy = studiesRepository.save(existingStudy);

        logger.info("Study updated successfully with id: {}", id);
        return mapToDTO(updatedStudy);
    }

    @Override
    public void deleteStudy(Long id) {
        logger.info("Deleting study with id: {}", id);

        Studies existingStudy = studiesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Study not found with id: {}", id);
                    return new RuntimeException("Study not found with id: " + id);
                });

        studiesRepository.delete(existingStudy);

        logger.info("Study deleted successfully with id: {}", id);
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