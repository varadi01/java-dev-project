package com.epam.training.ticketservice.utils;

import java.time.LocalDateTime;

public class DateConverter {

    public static LocalDateTime convertToLocalDateTime(String dateString) {
        //TODO replace all usages
        dateString = dateString.replace(" ", "T");
        return LocalDateTime.parse(dateString + ":00"); //test
    }
}
