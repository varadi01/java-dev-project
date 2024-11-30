package test.ticketservice.users;

import com.epam.training.ticketservice.entity.Viewer;
import com.epam.training.ticketservice.repository.ViewerRepository;
import com.epam.training.ticketservice.service.BookingService;
import com.epam.training.ticketservice.service.ViewerService;
import com.epam.training.ticketservice.utils.exceptions.ViewerException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private final ViewerRepository viewerRepository = mock(ViewerRepository.class);

    private final BookingService bookingService = mock(BookingService.class);
    private final ViewerService underTest = new ViewerService(viewerRepository, bookingService);

    private final static Viewer user = new Viewer("username", "password");
    private final static Viewer superuser = new Viewer("admin", "admin");

    @Test
    void testSignUpViewerSavesViewer() {
        //Given
        when(viewerRepository.findByUsername("username")).thenReturn(Optional.empty());
        //When
        try {
            underTest.signupViewer(user);
        } catch (ViewerException e) {
            fail();
        }
        verify(viewerRepository).save(user);
    }

    @Test
    void testSignUpViewerDoesNotSaveViewerWhenViewerByNameAlreadyExists() {
        //Given
        when(viewerRepository.findByUsername("username")).thenReturn(Optional.of(user));
        //When
        try {
            underTest.signupViewer(user);
            fail();
        } catch (ViewerException e) {

        }
        verify(viewerRepository, never()).save(user);
    }

    @Test
    void testSignInViewerSignsInViewer() {
        //Given
        when(viewerRepository.findByUsername("username")).thenReturn(Optional.of(user));
        //When
        try {
            underTest.signInViewer(user);
            if (!underTest.isSignedIn()) {
                fail();
            }

            assertEquals(user, underTest.getSignedInViewer());

        } catch (ViewerException e) {
            fail();
        }
    }

    @Test
    void testSignInViewerDoesNotSignInViewerWhenCredentialsAreIncorrect() {
        //Given
        when(viewerRepository.findByUsername("username")).thenReturn(Optional.of(user));
        //When
        assertThrows(ViewerException.class, () -> underTest.signInViewer(new Viewer("username", "passworddd")));

        if (underTest.isSignedIn()) {
            fail();
        }
    }

    @Test
    void testSignInAsPrivilegedSignInSuperuser() {
        try {
            underTest.signInAsPrivileged(superuser);
            if (!underTest.isSignedIn()) {
                fail();
            }
            if (!underTest.isSignedInAsPrivileged()) {
                fail();
            }
            assertEquals(superuser, underTest.getSignedInViewer());
        } catch (ViewerException e) {
            fail();
        }
    }

    @Test
    void testSignInAsPrivilegedDoesNotSignInSuperuserWithIncorrectCredentials() {
        try {
            underTest.signInAsPrivileged(new Viewer("admin", "addmin"));
            if (underTest.isSignedIn()) {
                fail();
            }
            if (underTest.isSignedInAsPrivileged()) {
                fail();
            }
        } catch (ViewerException e) {

        }
    }

    @Test
    void testDescribeWithNormalViewer() {
        //Given
        when(viewerRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(bookingService.getBookingsByViewer("username")).thenReturn(List.of());
        try {
            underTest.signInViewer(user);
            if (!underTest.isSignedIn()) {
                fail();
            }
        } catch (ViewerException e) {
            fail();
        }
        //When
        var answer = underTest.describe();
        //Then
        if (!answer.contains(user.getUsername())) {
            fail();
        }
    }

    @Test
    void testDescribeWithPrivilegedUser() {
        //Given
        try {
            underTest.signInAsPrivileged(superuser);
            if (!underTest.isSignedIn()) {
                fail();
            }
        } catch (ViewerException e) {
            fail();
        }
        //When
        var answer = underTest.describe();
        //Then
        if (!answer.contains(superuser.getUsername())) {
            fail();
        }
    }

    @Test
    void testSignOut() {
        //Given
        when(viewerRepository.findByUsername("username")).thenReturn(Optional.of(user));
        try {
            underTest.signInViewer(user);
        } catch (ViewerException e) {
            fail();
        }
        //When
        underTest.signOutViewer();
        //Then
        assertFalse(underTest.isSignedIn());
    }
}
