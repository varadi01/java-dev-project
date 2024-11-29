package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.utils.PriceComponentAttachable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;


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
    @JoinColumn(name = "price_component_name")
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
