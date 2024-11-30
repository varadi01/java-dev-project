package test.ticketservice.users;

import com.epam.training.ticketservice.entity.Viewer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    void testGettersAndSetters() {
        //Getters
        //Given
        var username = "username";
        var password = "password";
        //When
        Viewer viewer = new Viewer(username, password);
        //Then
        assertEquals(viewer.getUsername(), username);
        assertEquals(viewer.getPassword(), password);

        //Setters
        //Given
        username = "new username";
        password = "new password";
        //When
        viewer.setUsername(username);
        viewer.setPassword(password);
        //Then
        assertEquals(viewer.getUsername(), username);
        assertEquals(viewer.getPassword(), password);
    }

    @Test
    void testEquals() {
        //Given
        var username = "username";
        var password = "password";
        //When
        Viewer viewer = new Viewer(username, password);
        Viewer otherViewer = new Viewer(username, password);
        //Then
        assertEquals(viewer, otherViewer);
        assertTrue(viewer.equals(viewer));
        assertTrue(viewer.equals(otherViewer));
        otherViewer.setUsername("new username");
        assertFalse(viewer.equals(otherViewer));
    }
}
