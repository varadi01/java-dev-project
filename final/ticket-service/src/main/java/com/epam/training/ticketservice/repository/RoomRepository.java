package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    public Room findByName(String name);

    public void deleteByName(String roomName);
}
