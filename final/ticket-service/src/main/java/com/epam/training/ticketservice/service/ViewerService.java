package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Viewer;
import com.epam.training.ticketservice.repository.ViewerRepository;
import com.epam.training.ticketservice.utils.exceptions.ViewerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewerService implements IViewerService {
    private final ViewerRepository viewerRepository;

    private final BookingService bookingService;

    private boolean isSignedIn = false;
    private boolean isSignedInAsPrivileged = false;

    private Viewer signedInAs;

    @Override
    public void signupViewer(Viewer viewer) throws ViewerException {
        var user = viewerRepository.findByUsername(viewer.getUsername());

        if (user.isPresent()) {
            throw new ViewerException("User with such a name already exists");
        }
        viewerRepository.save(viewer);
    }

    @Override
    public void signInViewer(Viewer viewer) throws ViewerException {
        var user = viewerRepository.findByUsername(viewer.getUsername());

        if (user.isPresent()) {
            if (user.get().getPassword().equals(viewer.getPassword())) {
                isSignedIn = true;
                signedInAs = viewer;
            }
        }

        if (!isSignedIn) {
            throw new ViewerException("Login failed due to incorrect credentials");
        }
    }

    @Override
    public void signInAsPrivileged(Viewer viewer) throws ViewerException {
        if (viewer.getUsername().equals("admin") && viewer.getPassword().equals("admin")) {
            isSignedIn = true;
            isSignedInAsPrivileged = true;
            signedInAs = viewer;
        }

        if (!isSignedIn) {
            throw new ViewerException("Login failed due to incorrect credentials");
        }
    }

    @Override
    public void signOutViewer() {
        signedInAs = null;
        isSignedIn = false;
        isSignedInAsPrivileged = false;
    }

    @Override
    public String describe() {
        if (isSignedInAsPrivileged) {
            return "Signed in with privileged account '" + signedInAs.getUsername() + "'";
        }

        var bookings = bookingService.getBookingsByViewer(signedInAs.getUsername());

        var sb = new StringBuilder("Signed in with account '" + signedInAs.getUsername() + "'").append("\n");
        if (bookings.isEmpty()) {
            sb.append("You have not booked any tickets yet");
            return sb.toString();
        }
        sb.append("Your previous bookings are").append("\n");
        for (var booking : bookings) {
            sb.append(booking.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Boolean isSignedInAsPrivileged() {
        return isSignedInAsPrivileged;
    }

    @Override
    public Boolean isSignedIn() {
        return isSignedIn;
    }

    @Override
    public Viewer getSignedInViewer() {
        return signedInAs;
    }
}
