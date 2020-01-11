package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ReservationPassengers.
 */
@Entity
@Table(name = "reservation_passengers")

public class ReservationPassengers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "luggages_quanity")
    private Integer luggagesQuanity;

    @Column(name = "priority")
    private Boolean priority;

    @Column(name = "flight_rate")
    private Double flightRate;

    @Column(name = "paid")
    private Boolean paid;

    @Size(max = 6)
    @Column(name = "seat_number", length = 6)
    private String seatNumber;
    
    @Column(name = "status")
    private Boolean status;
    
    @ManyToOne
    @JsonIgnoreProperties("reservation")
    private Reservations reservation;

    @ManyToOne
    @JsonIgnoreProperties("passenger")
    private Passenger passenger;
    
    public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Double getFlightRate() {
		return flightRate;
	}

	public void setFlightRate(Double flightRate) {
		this.flightRate = flightRate;
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLuggagesQuanity() {
        return luggagesQuanity;
    }

    public ReservationPassengers luggagesQuanity(Integer luggagesQuanity) {
        this.luggagesQuanity = luggagesQuanity;
        return this;
    }

    public void setLuggagesQuanity(Integer luggagesQuanity) {
        this.luggagesQuanity = luggagesQuanity;
    }

    public Boolean getPriority() {
        return priority;
    }

    public ReservationPassengers priority(Boolean priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public ReservationPassengers seatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Reservations getReservation() {
        return reservation;
    }

    public ReservationPassengers reservationId(Reservations reservations) {
        this.reservation = reservations;
        return this;
    }

    public void setReservation(Reservations reservations) {
        this.reservation = reservations;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public ReservationPassengers passenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


    public Boolean isPaid() {
        return this.paid;
    }

    public Boolean getPaid() {
        return this.paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationPassengers)) {
            return false;
        }
        return id != null && id.equals(((ReservationPassengers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReservationPassengers{" + "id=" + getId() + ", luggagesQuanity=" + getLuggagesQuanity() + ", priority="
                + getPriority() + ", seatNumber='" + getSeatNumber() + "'" + "}";
    }
}
