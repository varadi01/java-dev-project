package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Repository.RoomRepository;
import org.springframework.stereotype.Service;

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

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(String name) {
        roomRepository.deleteByName(name);
    }
}
