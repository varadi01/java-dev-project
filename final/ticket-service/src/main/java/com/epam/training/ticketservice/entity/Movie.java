package com.epam.training.ticketservice.entity;

import com.epam.training.ticketservice.utils.PriceComponentAttachable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Objects;


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
    @JoinColumn(name = "price_component_name")
    private PriceComponent priceComponent;

    public PriceComponent getPriceComponent() {
        return priceComponent != null ? priceComponent : new PriceComponent("_", 0);
    }

    public Movie(String title, String genre, int lengthInMinutes) {
        this.title = title;
        this.genre = genre;
        this.lengthInMinutes = lengthInMinutes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public void setPriceComponent(PriceComponent priceComponent) {
        this.priceComponent = priceComponent;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie movie)) {
            return false;
        }
        return lengthInMinutes == movie.lengthInMinutes
                && Objects.equals(title, movie.title)
                && Objects.equals(genre, movie.genre)
                && Objects.equals(priceComponent, movie.priceComponent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, lengthInMinutes, priceComponent);
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
