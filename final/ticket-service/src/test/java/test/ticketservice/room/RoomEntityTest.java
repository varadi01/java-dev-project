package test.ticketservice.room;

import com.epam.training.ticketservice.entity.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomEntityTest {

    @Test
    void testGettersAndSetters() {
        //Getters
        //Given
        var name = "roomName";
        var rows = 20;
        var columns = 10;
        //When
        Room room = new Room(name, rows, columns);
        //Then
        assertEquals(room.getName(), name);
        assertEquals(room.getNumberOfSeatRows(), rows);
        assertEquals(room.getNumberOfSeatColumns(), columns);
        assertEquals(room.getNumberOfSeats(), rows * columns);

        //Setters
        //Given
        name = "roomNamee";
        rows = 21;
        columns = 11;
        //When
        room.setName(name);
        room.setNumberOfSeatRows(rows);
        room.setNumberOfSeatColumns(columns);
        //Then
        assertEquals(room.getName(), name);
        assertEquals(room.getNumberOfSeatRows(), rows);
        assertEquals(room.getNumberOfSeatColumns(), columns);
        assertEquals(room.getNumberOfSeats(), rows * columns);
    }

    @Test
    void testEquals() {
        //Given
        var name = "roomName";
        var rows = 20;
        var columns = 10;
        //When
        Room room = new Room(name, rows, columns);
        Room room2 = new Room(name, rows, columns);
        //Then
        assertEquals(room, room2);
        assertTrue(room.equals(room2));
        assertTrue(room.equals(room));

        room2.setName("roomName2");

        assertFalse(room.equals(room2));
    }
}
