package com.epam.training.ticketservice.Entity;

import com.epam.training.ticketservice.Utils.PriceComponentAttachable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

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
