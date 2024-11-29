package com.epam.training.ticketservice.utils.singletonvalues;

import org.springframework.stereotype.Service;

@Service
public class SingletonValueService {

    private final SingletonValueRepository singletonValueRepository;

    public SingletonValueService(SingletonValueRepository singletonValueRepository) {
        this.singletonValueRepository = singletonValueRepository;
        saveSingletonValue(new SingletonValue("base_price", "1500"));
    }

    public SingletonValue getSingletonValueByName(String name) {
        return singletonValueRepository.findByName(name);
    }

    public void saveSingletonValue(SingletonValue singletonValue) {
        singletonValueRepository.save(singletonValue);
    }

    public void updateSingletonValue(SingletonValue singletonValue) {
        singletonValueRepository.save(singletonValue);
    }
}
