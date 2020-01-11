package uv.airlines.app.service.dto;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.Aircrafts} entity.
 */
@AllArgsConstructor
@NoArgsConstructor
public class AircraftsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Size(max = 45)
    private String number;

    private Integer capacity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AircraftsDTO aircraftsDTO = (AircraftsDTO) o;
        if (aircraftsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aircraftsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AircraftsDTO{" + "id=" + getId() + ", number='" + getNumber() + "'" + ", capacity=" + getCapacity()
                + "}";
    }
}
