package com.epam.training.ticketservice.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
public class Room {

    @Id
    private String name;

    private int numberOfSeatRows;

    private int numberOfSeatColumns;

    @Formula("numberOfSeatRows*numberOfSeatColumns")
    private int numberOfSeats;

    @ManyToOne
    private PriceComponent priceComponent;
}
