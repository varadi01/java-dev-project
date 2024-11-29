package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.entity.Viewer;
import com.epam.training.ticketservice.service.ViewerService;
import com.epam.training.ticketservice.utils.exceptions.ViewerException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class UserCommand {

    private final ViewerService viewerService;

    @ShellMethod(key = "sign up")
    protected String signUp(String username, String password) {
        try {
            viewerService.signupViewer(new Viewer(username, password));
            return "Signed up";
        } catch (ViewerException e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "sign in")
    protected String signIn(String username, String password) {
        try {
            viewerService.signInViewer(new Viewer(username, password));
            return "Signed in";
        } catch (ViewerException e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "sign in privileged")
    protected String signInPrivileged(String username, String password) {
        try {
            viewerService.signInAsPrivileged(new Viewer(username, password));
            return "Signed in as privileged user";
        } catch (ViewerException e) {
            return e.getMessage();
        }
    }

    @ShellMethod(key = "sign out")
    @ShellMethodAvailability("isLoggedIn")
    protected String signOut() {
        if (!viewerService.isSignedIn()) {
            return "You are not signed in";
        }
        viewerService.signOutViewer();
        return "Signed out";
    }

    @ShellMethod(key = "describe account")
    @ShellMethodAvailability("isLoggedIn")
    protected String describe() {
        if (!viewerService.isSignedIn()) {
            return "You are not signed in";
        }
        return viewerService.describe();
    }

    private Availability isLoggedIn() {
        return true ? Availability.available()
                :
                Availability.unavailable("You are not signed in");
    }
}
