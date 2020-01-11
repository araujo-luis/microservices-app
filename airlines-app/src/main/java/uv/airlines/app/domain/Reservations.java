package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A Reservations.
 */
@Entity
@Table(name = "reservations")

public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "status")
    private Boolean status;

	@Column(name = "reservation_date")
    @Temporal(TemporalType.DATE)
    private Date reservationDate;


    @ManyToOne
    @JsonIgnoreProperties("agencies")
    private Agencies agencies;

    @ManyToOne
    @JsonIgnoreProperties("flightSchedule")
    private FlightSchedule flightSchedule;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}


    public Date getReservationDate() {
        return reservationDate;
    }

    public Reservations reservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
        return this;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }


    public Agencies getAgencies() {
        return agencies;
    }

    public Reservations agencies(Agencies agencies) {
        this.agencies = agencies;
        return this;
    }

    public void setAgencies(Agencies agencies) {
        this.agencies = agencies;
    }

    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    public void setflightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservations)) {
            return false;
        }
        return id != null && id.equals(((Reservations) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Reservations{" + "id=" + getId() + ", reservationDate='" + getReservationDate() + "'" + "}";
    }
}
