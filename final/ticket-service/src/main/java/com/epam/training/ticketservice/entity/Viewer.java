package com.epam.training.ticketservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "viewer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viewer {

    @Id
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Viewer viewer)) {
            return false;
        }
        return Objects.equals(username, viewer.username) && Objects.equals(password, viewer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
