package com.epam.training.ticketservice.Entity;

import com.epam.training.ticketservice.Utils.PriceComponentAttachable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
public class Room implements PriceComponentAttachable {

    @Id
    private String name;

    private int numberOfSeatRows;

    private int numberOfSeatColumns;

    @ManyToOne
    private PriceComponent priceComponent;

    public PriceComponent getPriceComponent() {
        return priceComponent != null ? priceComponent : new PriceComponent("_", 0);
    }

    public int getNumberOfSeats() {
        return numberOfSeatRows * numberOfSeatColumns;
    }

    public Room(String name, int numberOfSeatRows, int numberOfSeatColumns) {
        this.name = name;
        this.numberOfSeatRows = numberOfSeatRows;
        this.numberOfSeatColumns = numberOfSeatColumns;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Room ");
        sb.append(this.name);
        sb.append(" with ");
        sb.append(this.getNumberOfSeats());
        sb.append(" seats, ");
        sb.append(numberOfSeatRows);
        sb.append(" rows and ");
        sb.append(numberOfSeatColumns);
        sb.append(" columns");
        return sb.toString();
    }
}
