package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.StudiesDTO;

import java.util.List;

public interface IStudiesService {
    List<StudiesDTO> getAllStudies();
    StudiesDTO getStudyById(Long id);
    StudiesDTO createStudy(StudiesDTO studyDTO);
    StudiesDTO updateStudy(Long id, StudiesDTO studyDTO);
    void deleteStudy(Long id);
}