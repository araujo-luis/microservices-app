package uv.airlines.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.Airports} entity.
 */
public class AirportsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @Size(max = 5)
    private String type;

    @Size(max = 45)
    private String name;

    private Integer elevation;

    @Size(max = 4)
    private String continent;

    @Size(max = 4)
    private String country;

    @Size(max = 10)
    private String region;

    @Size(max = 200)
    private String munipality;

    @Size(max = 10)
    private String gpscode;

    @Size(max = 10)
    private String localcode;

    @Size(max = 300)
    private String coordinates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevation() {
        return elevation;
    }

    public void setElevation(Integer elevation) {
        this.elevation = elevation;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMunipality() {
        return munipality;
    }

    public void setMunipality(String munipality) {
        this.munipality = munipality;
    }

    public String getGpscode() {
        return gpscode;
    }

    public void setGpscode(String gpscode) {
        this.gpscode = gpscode;
    }

    public String getLocalcode() {
        return localcode;
    }

    public void setLocalcode(String localcode) {
        this.localcode = localcode;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AirportsDTO airportsDTO = (AirportsDTO) o;
        if (airportsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), airportsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AirportsDTO{" + "id=" + getId() + ", type='" + getType() + "'" + ", name='" + getName() + "'"
                + ", elevation=" + getElevation() + ", continent='" + getContinent() + "'" + ", country='"
                + getCountry() + "'" + ", region='" + getRegion() + "'" + ", munipality='" + getMunipality() + "'"
                + ", gpscode='" + getGpscode() + "'" + ", localcode='" + getLocalcode() + "'" + ", coordinates='"
                + getCoordinates() + "'" + "}";
    }
}
