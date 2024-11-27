package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.utils.PriceComponentAttachable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
public class Movie implements PriceComponentAttachable {

    @Id
    private String title;

    private String genre;

    private int lengthInMinutes;

    @ManyToOne
    private PriceComponent priceComponent;

    public Movie(String title, String genre, int lengthInMinutes) {
        this.title = title;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.title);
        sb.append(" (");
        sb.append(this.genre);
        sb.append(", ");
        sb.append(this.lengthInMinutes);
        sb.append(" minutes)");
        return sb.toString();
    }
}
