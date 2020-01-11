package uv.airlines.app.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Airports.
 */
@Entity
@Table(name = "airports")
public class Airports implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 15)
    private String id;

    @Size(max = 100)
    @Column(name = "type", length = 100)
    private String type;

    @Size(max = 300)
    @Column(name = "name", length = 300)
    private String name;

    @Column(name = "elevation")
    private Integer elevation;

    @Size(max = 10)
    @Column(name = "continent", length = 4)
    private String continent;

    @Size(max = 10)
    @Column(name = "country", length = 4)
    private String country;

    @Size(max = 10)
    @Column(name = "region", length = 10)
    private String region;

    @Size(max = 200)
    @Column(name = "munipality", length = 200)
    private String munipality;

    @Size(max = 10)
    @Column(name = "gpscode", length = 10)
    private String gpscode;

    @Size(max = 10)
    @Column(name = "localcode", length = 10)
    private String localcode;

    @Size(max = 300)
    @Column(name = "coordinates", length = 300)
    private String coordinates;
    
    @OneToMany(mappedBy = "airportTakeoff", fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<FlightSchedule> airportTakeoff = new HashSet<>();

    @OneToMany(mappedBy = "airportArrival", fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<FlightSchedule> airportArrival = new HashSet<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Airports type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Airports name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevation() {
        return elevation;
    }

    public Airports elevation(Integer elevation) {
        this.elevation = elevation;
        return this;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getContinent() {
        return continent;
    }

    public Airports continent(String continent) {
        this.continent = continent;
        return this;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public Airports country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public Airports region(String region) {
        this.region = region;
        return this;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMunipality() {
        return munipality;
    }

    public Airports munipality(String munipality) {
        this.munipality = munipality;
        return this;
    }

    public Airports() {
        super();
    }

    public Airports(String id, @Size(max = 5) String type, @Size(max = 45) String name, Integer elevation,
            @Size(max = 4) String continent, @Size(max = 4) String country, @Size(max = 10) String region,
            @Size(max = 200) String munipality, @Size(max = 10) String gpscode, @Size(max = 10) String localcode,
            @Size(max = 300) String coordinates, Set<FlightSchedule> airportTakeoff,
            Set<FlightSchedule> airportArrival) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
        this.elevation = elevation;
        this.continent = continent;
        this.country = country;
        this.region = region;
        this.munipality = munipality;
        this.gpscode = gpscode;
        this.localcode = localcode;
        this.coordinates = coordinates;
        this.airportTakeoff = airportTakeoff;
        this.airportArrival = airportArrival;
    }

    public void setMunipality(String munipality) {
        this.munipality = munipality;
    }

    public String getGpscode() {
        return gpscode;
    }

    public Airports gpscode(String gpscode) {
        this.gpscode = gpscode;
        return this;
    }

    public void setGpscode(String gpscode) {
        this.gpscode = gpscode;
    }

    public String getLocalcode() {
        return localcode;
    }

    public Airports localcode(String localcode) {
        this.localcode = localcode;
        return this;
    }

    public void setLocalcode(String localcode) {
        this.localcode = localcode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public Airports coordinates(String coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Set<FlightSchedule> getAirportTakeoff() {
        return airportTakeoff;
    }

    public Airports AirportTakeoff(Set<FlightSchedule> flightSchedules) {
        this.airportTakeoff = flightSchedules;
        return this;
    }

    public Airports addAirportTakeoff(FlightSchedule flightSchedule) {
        this.airportTakeoff.add(flightSchedule);
        flightSchedule.setAirportTakeoff(this);
        return this;
    }

    public Airports removeAirportTakeoff(FlightSchedule flightSchedule) {
        this.airportTakeoff.remove(flightSchedule);
        flightSchedule.setAirportTakeoff(null);
        return this;
    }

    public void setAirportTakeoff(Set<FlightSchedule> flightSchedules) {
        this.airportTakeoff = flightSchedules;
    }

    public Set<FlightSchedule> getAirportArrival() {
        return airportArrival;
    }

    public Airports AirportArrival(Set<FlightSchedule> flightSchedules) {
        this.airportArrival = flightSchedules;
        return this;
    }

    public Airports addAirportArrival(FlightSchedule flightSchedule) {
        this.airportArrival.add(flightSchedule);
        flightSchedule.setAirportArrival(this);
        return this;
    }

    public Airports removeAirportArrival(FlightSchedule flightSchedule) {
        this.airportArrival.remove(flightSchedule);
        flightSchedule.setAirportArrival(null);
        return this;
    }

    public void setAirportArrival(Set<FlightSchedule> flightSchedules) {
        this.airportArrival = flightSchedules;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Airports)) {
            return false;
        }
        return id != null && id.equals(((Airports) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Airports{" + "id=" + getId() + ", type='" + getType() + "'" + ", name='" + getName() + "'"
                + ", elevation=" + getElevation() + ", continent='" + getContinent() + "'" + ", country='"
                + getCountry() + "'" + ", region='" + getRegion() + "'" + ", munipality='" + getMunipality() + "'"
                + ", gpscode='" + getGpscode() + "'" + ", localcode='" + getLocalcode() + "'" + ", coordinates='"
                + getCoordinates() + "'" + "}";
    }
}
