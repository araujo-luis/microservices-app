package uv.airlines.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.ReservationPassengers} entity.
 */
public class ReservationPassengersDTO implements Serializable {

    private static final long serialVersionUID = 1417761848757342319L;

    private Long id;

    private Integer luggagesQuanity;

    private Boolean priority;

    @Size(max = 6)
    private String seatNumber;

    private Long reservation;

    private String passengerId;

    private Double flightRate;

    private Boolean paid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLuggagesQuanity() {
        return luggagesQuanity;
    }

    public void setLuggagesQuanity(Integer luggagesQuanity) {
        this.luggagesQuanity = luggagesQuanity;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getReservation() {
        return reservation;
    }

    public void setReservation(Long reservationsId) {
        this.reservation = reservationsId;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passenger) {
        this.passengerId = passenger;
    }

    public Double getFlightRate() {
		return flightRate;
	}

	public void setFlightRate(Double flightRate) {
		this.flightRate = flightRate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservationPassengersDTO reservationPassengersDTO = (ReservationPassengersDTO) o;
        if (reservationPassengersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservationPassengersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservationPassengersDTO{" + "id=" + getId() + ", luggagesQuanity=" + getLuggagesQuanity()
                + ", priority=" + getPriority() + ", seatNumber='" + getSeatNumber() + "'" + ", reservationId="
                + getReservation() + ", passDni=" + getPassengerId() + "}";
    }

    public Double getPrice() {
        return flightRate;
    }

    public void setPrice(Double price) {
        this.flightRate = price;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
