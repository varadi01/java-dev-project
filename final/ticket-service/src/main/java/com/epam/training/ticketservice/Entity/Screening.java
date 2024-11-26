package com.epam.training.ticketservice.Entity;

import com.epam.training.ticketservice.Utils.PriceComponentAttachable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "screening")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screening implements PriceComponentAttachable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_title")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "room_name")
    private Room room;

    private LocalDateTime startDateTime;

    @ManyToOne
    private PriceComponent priceComponent;

    @OneToMany
    private Collection<Booking> bookings;
}
