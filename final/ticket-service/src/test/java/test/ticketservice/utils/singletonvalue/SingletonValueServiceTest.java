package test.ticketservice.utils.singletonvalue;

import com.epam.training.ticketservice.utils.singletonvalues.SingletonValue;
import com.epam.training.ticketservice.utils.singletonvalues.SingletonValueRepository;
import com.epam.training.ticketservice.utils.singletonvalues.SingletonValueService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SingletonValueServiceTest {

    private final SingletonValueRepository singletonValueRepository = mock(SingletonValueRepository.class);
    private final SingletonValueService underTest = new SingletonValueService(singletonValueRepository);

    @Test
    void testGetSingletonValueShouldReturnValue() {
        //Given
        when(singletonValueRepository.findByName("test")).thenReturn(new SingletonValue("test", "value"));
        var expected = new SingletonValue("test", "value");
        //When
        var actual = underTest.getSingletonValueByName("test");
        //Then
        assertEquals(expected, actual);
        assertEquals(expected.getSingleValue(), actual.getSingleValue());
        verify(singletonValueRepository).findByName("test");
    }

    @Test
    void testSaveAndUpdateSingletonValueUpdatesValue() {
        //Given
        var stv = new SingletonValue("test", "value");
        //When
        underTest.saveSingletonValue(stv);
        //Then
        verify(singletonValueRepository).save(stv);
    }
}
