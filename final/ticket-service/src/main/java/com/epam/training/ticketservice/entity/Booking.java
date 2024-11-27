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

import java.util.List;

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

    @ElementCollection(targetClass = String.class)
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Seats ");
        for (int i = 0; i < bookedSeats.size(); i++) {
            sb.append(bookedSeats.get(i));
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
