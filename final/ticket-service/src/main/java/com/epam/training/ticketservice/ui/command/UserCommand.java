package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.Service.ViewerService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class UserCommand {

    private final ViewerService viewerService;

    @ShellMethod(key = "sign up")
    protected String signUp(){
        return "Sign up";
    }

    @ShellMethod(key = "sign in")
    protected String signIn(){
        return "Sign up";
    }

    @ShellMethod(key = "sign in privileged")
    protected String signInPrivileged(){
        return "Sign up";
    }

    @ShellMethod(key = "sign out")
    protected String signOut(){
        return "Sign up";
    }

    @ShellMethod(key = "describe account")
    protected String describe(){
        return "";
    }
}
