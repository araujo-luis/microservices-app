package uv.airlines.app.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Passenger.
 */
@Entity
@Table(name = "passenger")
public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "lastname", length = 45)
    private String lastname;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnore
    private Set<ReservationPassengers> reservationPassengers = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Passenger name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public Passenger lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<ReservationPassengers> getReservationPassengers() {
        return reservationPassengers;
    }

    public Passenger reservationPassengers(Set<ReservationPassengers> reservationPassengers) {
        this.reservationPassengers = reservationPassengers;
        return this;
    }

    public Passenger addReservationPassengers(ReservationPassengers reservationPassengers) {
        this.reservationPassengers.add(reservationPassengers);
        reservationPassengers.setPassenger(this);
        return this;
    }

    public Passenger removeReservationPassengers(ReservationPassengers reservationPassengers) {
        this.reservationPassengers.remove(reservationPassengers);
        reservationPassengers.setPassenger(null);
        return this;
    }

    public void setReservationPassengers(Set<ReservationPassengers> reservationPassengers) {
        this.reservationPassengers = reservationPassengers;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Passenger)) {
            return false;
        }
        return id != null && id.equals(((Passenger) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Passenger{" + "id=" + getId() + ", name='" + getName() + "'" + ", lastname='" + getLastname() + "'"
                + "}";
    }
}
