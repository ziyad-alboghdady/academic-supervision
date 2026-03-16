package org.example.academic_supervision.Service;

import org.example.academic_supervision.DTO.AdvisersDTO;
import org.example.academic_supervision.Exception.ErrorMessages;
import org.example.academic_supervision.Exception.ResourceAlreadyExistsException;
import org.example.academic_supervision.Exception.ResourceNotFoundException;
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
                .map(Advisers::viewAsAdvisersDTO)
                .toList();
    }

    @Override
    public AdvisersDTO getAdviserById(Long id) {
        return advisersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_ADVISER_NOT_FOUND + ": " + id)).viewAsAdvisersDTO();
    }

    @Override
    public AdvisersDTO createAdviser(Advisers adviser) {
        if (adviser.getId() != null && advisersRepository.existsById(adviser.getId())) {
            throw new ResourceAlreadyExistsException(ErrorMessages.ERROR_ADVISER_ALREADY_EXIST + ": " + adviser.getId());
        }
        return advisersRepository.save(adviser).viewAsAdvisersDTO();
    }

    @Override
    public AdvisersDTO updateAdviser(Long id, Advisers adviser) {
        advisersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_ADVISER_NOT_FOUND + ": " + id));
        adviser.setId(id);
        return advisersRepository.save(adviser).viewAsAdvisersDTO();
    }

    @Override
    public void deleteAdviser(Long id) {
        advisersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.ERROR_ADVISER_NOT_FOUND + ": " + id));
        advisersRepository.deleteById(id);
    }
}