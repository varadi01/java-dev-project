package com.epam.training.ticketservice.utils.singletonvalues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "singleton_values")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingletonValue {

    @Id
    private String name;

    private String singleValue;
}
