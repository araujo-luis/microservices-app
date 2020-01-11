package uv.airlines.app.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class FlightScheduleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date takeoffDate;

    private Date arrivalDate;

    private Float flightRate;

    private Long aircraftId;

    private String airportTakeoffId;

    private String airportArrivalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTakeoffDate() {
        return takeoffDate;
    }

    public void setTakeoffDate(Date takeoffDate) {
        this.takeoffDate = takeoffDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Float getFlightRate() {
        return flightRate;
    }

    public void setFlightRate(Float flightRate) {
        this.flightRate = flightRate;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftsId) {
        this.aircraftId = aircraftsId;
    }

    public String getAirportTakeoffId() {
        return airportTakeoffId;
    }

    public void setAirportTakeoffId(String airportsId) {
        this.airportTakeoffId = airportsId;
    }

    public String getAirportArrivalId() {
        return airportArrivalId;
    }

    public void setAirportArrivalId(String airportsId) {
        this.airportArrivalId = airportsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlightScheduleDTO flightScheduleDTO = (FlightScheduleDTO) o;
        if (flightScheduleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), flightScheduleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FlightScheduleDTO{" + "id=" + getId() + ", takeoffDate='" + getTakeoffDate() + "'" + ", arrivalDate='"
                + getArrivalDate() + "'" + ", flightRate=" + getFlightRate() + ", aircraftId=" + getAircraftId()
                + ", airportTakeoff=" + getAirportTakeoffId() + ", airportArrival=" + getAirportArrivalId() + "}";
    }
}
