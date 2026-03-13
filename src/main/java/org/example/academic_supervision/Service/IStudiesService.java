package org.example.academic_supervision.Service;

import org.example.academic_supervision.Model.Studies;

import java.util.List;

public interface IStudiesService {

    List<Studies> getAllStudies();

    Studies getStudyById(Long id);

    Studies createStudy(Studies study);

    Studies updateStudy(Long id, Studies study);

    void deleteStudy(Long id);
}