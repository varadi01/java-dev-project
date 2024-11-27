package com.epam.training.ticketservice.Ui.command;

import com.epam.training.ticketservice.Entity.Movie;
import com.epam.training.ticketservice.Entity.Room;
import com.epam.training.ticketservice.Service.RoomService;
import com.epam.training.ticketservice.Service.ViewerService;
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
    protected String createMovie(String name, int numberOfRows, int numberOfColumns) {
        roomService.saveRoom(new Room(name, numberOfRows, numberOfColumns));
        return "Room created";
    }

    @ShellMethod(key = "update room")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String updateMovie(String name, int numberOfRows, int numberOfColumns) {
        roomService.updateRoom(new Room(name, numberOfRows, numberOfColumns));
        return "Room updated";
    }

    @ShellMethod(key = "delete room")
    @ShellMethodAvailability("isLoggedInAsPrivileged")
    protected String deleteMovie(String roomName) {
        roomService.deleteRoom(roomName);
        return "Room deleted";
    }

    @ShellMethod(key = "list rooms")
    protected String listMovies() {
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

    private Availability isLoggedInAsPrivileged(){
        return viewerService.isSignedInAsPrivileged() ? Availability.available()
                :
                Availability.unavailable("Not admin or not logged in");
    }
}
