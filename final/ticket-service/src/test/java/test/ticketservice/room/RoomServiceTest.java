package test.ticketservice.room;

import com.epam.training.ticketservice.entity.Room;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoomServiceTest {

    private final RoomRepository roomRepository = mock(RoomRepository.class);
    private final RoomService underTest = new RoomService(roomRepository);

    private static final Room room1 = new Room("room1", 10, 20);
    private static final Room room2 = new Room("room2", 12, 18);

    @Test
    void testGetAllRoomsShouldReturnListOfRooms() {
        //Given
        when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        List<Room> expected = List.of(room1, room2);
        //When
        List<Room> actual = underTest.getAllRooms();
        //Then
        assertEquals(expected, actual);
        verify(roomRepository).findAll();
    }

    @Test
    void testGetRoomByNameShouldReturnRoom() {
        //Given
        when(roomRepository.findByName("room1")).thenReturn(room1);
        Room expected = room1;
        //When
        Room actual = underTest.getRoomByName("room1");
        //Then
        assertEquals(expected, actual);
        verify(roomRepository).findByName("room1");
    }

    @Test
    void testGetRoomByNameShouldReturnNullWhenRoomDoesNotExists() {
        //Given
        when(roomRepository.findByName("room3")).thenReturn(null);
        //When
        Room actual = underTest.getRoomByName("room3");
        //Then
        assertNull(actual);
        verify(roomRepository).findByName("room3");
    }

    @Test
    void testSaveRoomShouldSaveRoom() {
        //Given
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(room2);
        when(roomRepository.save(room1)).thenReturn(rooms.add(room1) ? room1 : null);
        List<Room> expected = List.of(room2, room1);
        //When
        underTest.saveRoom(room1);
        //Then
        verify(roomRepository).save(room1);
        assertEquals(expected, rooms);
    }
}
