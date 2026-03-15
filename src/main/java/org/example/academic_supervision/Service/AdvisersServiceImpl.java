package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.AdvisersDTO;
import org.example.academic_supervision.Model.Advisers;
import org.example.academic_supervision.Repository.AdvisersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisersServiceImpl implements IAdvisersService{
    private final AdvisersRepository advisersRepository;

    public AdvisersServiceImpl(AdvisersRepository advisersRepository) {
        this.advisersRepository = advisersRepository;
    }

    @Override
    public List<AdvisersDTO> getAllAdvisers() {
        return advisersRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }
}