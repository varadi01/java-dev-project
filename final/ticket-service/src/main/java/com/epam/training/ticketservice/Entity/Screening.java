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

    public Screening(Movie movie, Room room, LocalDateTime startDateTime) {
        this.movie = movie;
        this.room = room;
        this.startDateTime = startDateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(movie.getTitle());
        sb.append(" (");
        sb.append(movie.getGenre());
        sb.append(", ");
        sb.append(movie.getLengthInMinutes());
        sb.append(" minutes), screened in room ");
        sb.append(room.getName());
        sb.append(", at ");
        sb.append(startDateTime.toString().replace("T", " "));
        return sb.toString();
    }
}
