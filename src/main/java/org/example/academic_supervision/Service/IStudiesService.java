package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.StudiesDTO;

import org.example.academic_supervision.Model.Studies;

import java.util.List;

public interface IStudiesService {
    List<StudiesDTO> getAllStudies();
    StudiesDTO getStudyById(Long id);
    StudiesDTO createStudy(Studies study);
    StudiesDTO updateStudy(Long id, Studies study);
    void deleteStudy(Long id);
}