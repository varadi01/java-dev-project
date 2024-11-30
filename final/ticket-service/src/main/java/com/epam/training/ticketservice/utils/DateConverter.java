package com.epam.training.ticketservice.utils;

import java.time.LocalDateTime;

public class DateConverter {

    public static LocalDateTime convertToLocalDateTime(String dateString) {
        dateString = dateString.replace(" ", "T");
        return LocalDateTime.parse(dateString + ":00");
    }
}
