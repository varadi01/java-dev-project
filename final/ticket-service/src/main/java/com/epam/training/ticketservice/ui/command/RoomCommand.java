package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class RoomCommand {

    private final RoomService roomService;
    private final ViewerService viewerService;

    @ShellMethod(key = "create room")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String createRoom(String name, int numberOfRows, int numberOfColumns) {
        roomService.saveRoom(new Room(name, numberOfRows, numberOfColumns));
        return "Room created";
    }

    @ShellMethod(key = "update room")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String updateRoom(String name, int numberOfRows, int numberOfColumns) {
        roomService.updateRoom(new Room(name, numberOfRows, numberOfColumns));
        return "Room updated";
    }

    @ShellMethod(key = "delete room")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String deleteRoom(String roomName) {
        roomService.deleteRoom(roomName);
        return "Room deleted";
    }

    @ShellMethod(key = "list rooms")
    protected String listRooms() {
        var rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }
        var sb = new StringBuilder();
        for (Room room : rooms) {
            sb.append(room).append("\n");
        }
        return sb.toString();
    }

    private Availability isLoggedInAsPrivileged() {
        return viewerService.isSignedInAsPrivileged() ? Availability.available()
                :
                Availability.unavailable("Not admin or not logged in");
    }
}
