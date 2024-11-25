package com.epam.training.ticketservice.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class Booking {

    @Id
    private Long id;

    @ManyToOne
    private Viewer viewer;

    @ManyToOne
    private Screening screening;

    //meh
    private int bookedRowNumber;

    private int bookedColumnNumber;

    private int price;
}
