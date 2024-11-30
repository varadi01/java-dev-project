package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.entity.Viewer;
import com.epam.training.ticketservice.utils.exceptions.ViewerException;

public interface IViewerService {
    void signupViewer(Viewer viewer) throws ViewerException;

    void signInViewer(Viewer viewer) throws ViewerException;

    void signInAsPrivileged(Viewer viewer) throws ViewerException;

    void signOutViewer();

    String describe();

    Boolean isSignedInAsPrivileged();

    Boolean isSignedIn();

    Viewer getSignedInViewer();
}
