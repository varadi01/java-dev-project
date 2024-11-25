package com.epam.training.ticketservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String title;

    private String genre;

    private int lengthInMinutes;

    @ManyToOne
    private PriceComponent priceComponent;
}
