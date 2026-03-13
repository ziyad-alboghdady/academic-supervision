package org.example.academic_supervision.Service;

import jakarta.validation.Valid;
import org.example.academic_supervision.Service.IStudiesService;
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
    public List<Studies> getAllStudies() {
        return studiesRepository.findAll();
    }

    @Override
    public Studies getStudyById(Long id) {
        return studiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));
    }

    @Override
    public Studies createStudy(@Valid Studies study) {
        return studiesRepository.save(study);
    }

    @Override
    public Studies updateStudy(Long id, @Valid Studies study) {
        Studies existingStudy = studiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));

        existingStudy.setTitle(study.getTitle());
        existingStudy.setDescription(study.getDescription());

        return studiesRepository.save(existingStudy);
    }

    @Override
    public void deleteStudy(Long id) {
        Studies existingStudy = studiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study not found with id: " + id));

        studiesRepository.delete(existingStudy);
    }
    private Studies mapToEntity(org.example.academic_supervision.dto.StudiesDTO dto) {

        Studies study = new Studies();

        study.setId(dto.getId());
        study.setTitle(dto.getTitle());
        study.setDescription(dto.getDescription());

        return study;
    }
}