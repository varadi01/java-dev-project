package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.utils.PriceComponentAttachable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

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
    @JoinColumn(name = "price_component_name")
    private PriceComponent priceComponent;

    @OneToMany
    private Collection<Booking> bookings;

    public PriceComponent getPriceComponent() {
        return priceComponent != null ? priceComponent : new PriceComponent("_", 0);
    }

    public Screening(Movie movie, Room room, LocalDateTime startDateTime) {
        this.movie = movie;
        this.room = room;
        this.startDateTime = startDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public void setPriceComponent(PriceComponent priceComponent) {
        this.priceComponent = priceComponent;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Screening screening)) {
            return false;
        }
        return Objects.equals(id, screening.id)
                && Objects.equals(movie, screening.movie)
                && Objects.equals(room, screening.room)
                && Objects.equals(startDateTime, screening.startDateTime)
                && Objects.equals(priceComponent, screening.priceComponent)
                && Objects.equals(bookings, screening.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, room, startDateTime, priceComponent, bookings);
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
