package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.Service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class PriceCommand {

    private final PriceService priceService;

    @ShellMethod(key = "update base price")
    protected String updateBasePrice(String basePrice) {
        return "";
    }

    @ShellMethod(key = "create price component")
    protected String createPriceComponent(){
        return "";
    }

    @ShellMethod(key = "attach price component to room")
    protected String attachPriceComponentToRoom(){
        return "";
    }

    @ShellMethod(key = "attach price component to movie")
    protected String attachPriceComponentToMovie(){
        return "";
    }

    @ShellMethod(key = "attach price component to screening")
    protected String attachPriceComponentToScreening(){
        return "";
    }

    @ShellMethod(key = "show price for")
    protected String showPrice(){
        return "";
    }

}
