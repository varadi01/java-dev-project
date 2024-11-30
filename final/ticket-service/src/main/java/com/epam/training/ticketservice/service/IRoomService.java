package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Room;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoomService {
    List<Room> getAllRooms();

    Room getRoomByName(String name);

    void saveRoom(Room room);

    void updateRoom(Room room);

    @Transactional
    void deleteRoom(String name);
}
