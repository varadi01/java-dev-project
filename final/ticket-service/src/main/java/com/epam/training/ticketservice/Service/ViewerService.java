package com.epam.training.ticketservice.Service;

import com.epam.training.ticketservice.Entity.Viewer;
import com.epam.training.ticketservice.Repository.ViewerRepository;
import com.epam.training.ticketservice.Utils.Exceptions.ViewerException;
import org.springframework.stereotype.Service;

@Service
public class ViewerService {
    private final ViewerRepository viewerRepository;

    private boolean isSignedIn;
    private boolean isSignedInAsPrivileged;

    private Viewer signedInAs;

    public ViewerService(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    public void signupViewer(Viewer viewer) {
        viewerRepository.save(viewer);
    }

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

    public void signOutViewer(Viewer viewer) {
        signedInAs = null;
        isSignedIn = false;
        isSignedInAsPrivileged = false;
    }

    public String describe(Viewer viewer) {
        //TODO list bookings
        if (isSignedInAsPrivileged) {
            return "Signed in with privileged account '" + signedInAs.getUsername() + "'";
        }
        return "Signed in with account '" + signedInAs.getUsername() + "'";
    }
}
