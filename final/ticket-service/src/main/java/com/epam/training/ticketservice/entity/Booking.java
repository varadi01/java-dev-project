package com.epam.training.ticketservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Viewer viewer;

    @ManyToOne
    private Screening screening;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "booking_seats", joinColumns = @JoinColumn(name = "booking_id"))
    @Column(name = "booked_seats", nullable = false)
    private List<String> bookedSeats;

    private int price;

    public Booking(Viewer viewer, Screening screening, List<String> bookedSeats, int price) {
        this.viewer = viewer;
        this.screening = screening;
        this.bookedSeats = bookedSeats;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<String> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking booking)) {
            return false;
        }
        return price == booking.price
                && Objects.equals(id, booking.id)
                && Objects.equals(viewer, booking.viewer)
                && Objects.equals(screening, booking.screening)
                && Objects.equals(bookedSeats, booking.bookedSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, viewer, screening, bookedSeats, price);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Seats ");
        for (int i = 0; i < bookedSeats.size(); i++) {
            sb.append("(").append(bookedSeats.get(i)).append(")");
            if (i != bookedSeats.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(" on ")
                .append(screening.getMovie().getTitle())
                .append(" in room ")
                .append(screening.getRoom().getName())
                .append(" starting at ");
        var time = this.screening.getStartDateTime().toString().replace("T", " ");
        sb.append(time)
                .append(" for ")
                .append(price).append(" HUF");
        return sb.toString();
    }
}
