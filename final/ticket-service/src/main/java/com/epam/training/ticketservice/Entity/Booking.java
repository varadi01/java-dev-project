package com.epam.training.ticketservice.Entity;

import com.epam.training.ticketservice.Utils.PriceComponentAttachable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.util.Pair;

import javax.persistence.*;
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
}
