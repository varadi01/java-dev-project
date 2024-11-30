package test.ticketservice.price;

import com.epam.training.ticketservice.entity.PriceComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PriceComponentEntityTest {

    @Test
    void testGettersAndSetters() {
        //Getters
        //Given
        var name = "name";
        var componentValue = 100;
        //When
        PriceComponent priceComponent = new PriceComponent(name, componentValue);
        //Then
        assertEquals(priceComponent.getName(), name);
        assertEquals(priceComponent.getComponentValue(), componentValue);

        //Setters
        //Given
        name = "new name";
        componentValue = 200;
        //When
        priceComponent.setName(name);
        priceComponent.setComponentValue(componentValue);
        //Then
        assertEquals(priceComponent.getName(), name);
        assertEquals(priceComponent.getComponentValue(), componentValue);
    }

    @Test
    void testEquals() {
        //Given
        var name = "name";
        var componentValue = 100;
        //When
        PriceComponent priceComponent = new PriceComponent(name, componentValue);
        PriceComponent otherPriceComponent = new PriceComponent(name, componentValue);
        //Then
        assertEquals(priceComponent, otherPriceComponent);
        assertTrue(priceComponent.equals(otherPriceComponent));
        otherPriceComponent.setName("new name");
        assertFalse(priceComponent.equals(otherPriceComponent));
    }
}
