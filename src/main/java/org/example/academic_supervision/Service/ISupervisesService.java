package org.example.academic_supervision.Service;

import java.util.List;

import org.example.academic_supervision.DTO.SupervisesDTO;

import org.example.academic_supervision.Model.Supervises;

public interface ISupervisesService {
        List<SupervisesDTO> getAllSupervises();
        SupervisesDTO getSupervisesById(Long id);
        SupervisesDTO createSupervises(Supervises supervises);
        SupervisesDTO updateSupervises(Long id, Supervises supervises);
	void deleteSupervises(Long id);
}
