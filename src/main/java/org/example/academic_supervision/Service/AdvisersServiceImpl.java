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

    @Override
    public AdvisersDTO getAdviserById(Long id) {
        Advisers adviser = advisersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Adviser not found: " + id));
        return convertToDTO(adviser);
    }

    @Override
    public AdvisersDTO createAdviser(Advisers adviser) {
        if (adviser.getId() != null && advisersRepository.existsById(adviser.getId())) {
            throw new RuntimeException("Adviser already exists: " + adviser.getId());
        }
        Advisers savedAdviser = advisersRepository.save(adviser);
        return convertToDTO(savedAdviser);
    }

    @Override
    public AdvisersDTO updateAdviser(Long id, Advisers adviser) {
        if (!advisersRepository.existsById(id)) {
            throw new RuntimeException("Adviser not found: " + id);
        }
        adviser.setId(id);
        Advisers updatedAdviser = advisersRepository.save(adviser);
        return convertToDTO(updatedAdviser);
    }

    @Override
    public void deleteAdviser(Long id) {
        if (!advisersRepository.existsById(id)) {
            throw new RuntimeException("Adviser not found: " + id);
        }
        advisersRepository.deleteById(id);
    }
}