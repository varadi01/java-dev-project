package com.epam.training.ticketservice.utils.singletonvalues;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingletonValueRepository extends JpaRepository<SingletonValue, String> {

    public SingletonValue findByName(String name);
}
