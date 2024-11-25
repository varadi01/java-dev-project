package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Screening;
import com.epam.training.ticketservice.Repository.ScreeningRepository;
import com.epam.training.ticketservice.Utils.Exceptions.ScreeningException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningService {
    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public void saveScreening(Screening screening) throws ScreeningException {
        //TODO check for overlap
        screeningRepository.save(screening);
    }

    public void deleteScreening(Long screeningId) {
        screeningRepository.deleteById(screeningId);
    }
}