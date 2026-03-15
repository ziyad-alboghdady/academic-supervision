package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.AdvisersDTO;
import org.example.academic_supervision.Model.Advisers;

import java.util.List;

public interface IAdvisersService {
    List<AdvisersDTO> getAllAdvisers();
    AdvisersDTO getAdviserById(Long id);
    AdvisersDTO createAdviser(Advisers adviser);
    AdvisersDTO updateAdviser(Long id, Advisers adviser);
    void deleteAdviser(Long id);
}