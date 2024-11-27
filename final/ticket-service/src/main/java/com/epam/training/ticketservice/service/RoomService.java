package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomByName(String name) {
        return roomRepository.findByName(name);
    }

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    @Transactional
    public void deleteRoom(String name) {
        roomRepository.deleteByName(name);
    }
}
