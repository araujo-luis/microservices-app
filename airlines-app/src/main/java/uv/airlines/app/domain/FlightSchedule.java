package uv.airlines.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A FlightSchedule.
 */
@Entity
@Table(name = "flight_schedule")
public class FlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "takeoff_date")
    private LocalDateTime takeoffDate;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "flight_rate")
    private Double flightRate;

    @ManyToOne
    @JsonIgnoreProperties("aircraft")
    private Aircrafts aircraft;

    @OneToMany(mappedBy = "flightSchedule")
    @JsonIgnoreProperties("reservations")
    private Set<Reservations> reservations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("airportTakeoff")
    private Airports airportTakeoff;

    @ManyToOne
    @JsonIgnoreProperties("airportArrival")
    private Airports airportArrival;

    public FlightSchedule() {
        super();
    }

    public FlightSchedule(Long id, LocalDateTime takeoffDate, LocalDateTime arrivalDate, Double flightRate,
            Aircrafts aircraft, Airports airportTakeoff, Airports airportArrival) {
        super();
        this.id = id;
        this.takeoffDate = takeoffDate;
        this.arrivalDate = arrivalDate;
        this.flightRate = flightRate;
        this.aircraft = aircraft;
        this.airportTakeoff = airportTakeoff;
        this.airportArrival = airportArrival;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTakeoffDate() {
        return takeoffDate;
    }

    public FlightSchedule takeoffDate(LocalDateTime takeoffDate) {
        this.takeoffDate = takeoffDate;
        return this;
    }

    public void setTakeoffDate(LocalDateTime takeoffDate) {
        this.takeoffDate = takeoffDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public FlightSchedule arrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getFlightRate() {
        return flightRate;
    }

    public FlightSchedule flightRate(Double flightRate) {
        this.flightRate = flightRate;
        return this;
    }

    public void setFlightRate(Double flightRate) {
        this.flightRate = flightRate;
    }

    public Aircrafts getAircraft() {
        return aircraft;
    }

    public FlightSchedule aircraft(Aircrafts aircrafts) {
        this.aircraft = aircrafts;
        return this;
    }

    public void setAircraft(Aircrafts aircrafts) {
        this.aircraft = aircrafts;
    }

    public Airports getAirportTakeoff() {
        return airportTakeoff;
    }

    public FlightSchedule airportTakeoff(Airports airports) {
        this.airportTakeoff = airports;
        return this;
    }

    public void setAirportTakeoff(Airports airports) {
        this.airportTakeoff = airports;
    }

    public Airports getAirportArrival() {
        return airportArrival;
    }

    public FlightSchedule airportArrival(Airports airports) {
        this.airportArrival = airports;
        return this;
    }

    public void setAirportArrival(Airports airports) {
        this.airportArrival = airports;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlightSchedule)) {
            return false;
        }
        return id != null && id.equals(((FlightSchedule) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FlightSchedule{" + "id=" + getId() + ", takeoffDate='" + getTakeoffDate() + "'" + ", arrivalDate='"
                + getArrivalDate() + "'" + ", flightRate=" + getFlightRate() + "}";
    }
}
