package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "price_component")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceComponent {

    @Id
    private String name;

    private int componentValue;
}
