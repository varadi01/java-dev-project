package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "price_component")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceComponent {

    @Id
    private String name;

    private int componentValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(int componentValue) {
        this.componentValue = componentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PriceComponent that)) {
            return false;
        }
        return componentValue == that.componentValue && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, componentValue);
    }
}
