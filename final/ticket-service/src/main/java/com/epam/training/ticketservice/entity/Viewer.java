package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "viewer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viewer {

    @Id
    private String username;

    private String password;
}
