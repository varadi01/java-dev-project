package test.ticketservice.utils.dateconverter;

import com.epam.training.ticketservice.utils.DateConverter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateConverterTest {

    @Test
    void testDateConverterConvertsCorrectly() {
        //Given
        var timeString = "2000-12-12T12:00";
        var expected = LocalDateTime.of(2000, 12, 12, 12, 0);
        //When
        var actual = DateConverter.convertToLocalDateTime(timeString);
        //Then
        assertEquals(expected, actual);
    }
}
