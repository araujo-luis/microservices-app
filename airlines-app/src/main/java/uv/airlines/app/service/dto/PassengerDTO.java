package uv.airlines.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.Passenger} entity.
 */
public class PassengerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @Size(max = 45)
    private String name;

    @Size(max = 45)
    private String lastname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PassengerDTO passengerDTO = (PassengerDTO) o;
        if (passengerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), passengerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PassengerDTO{" + "id=" + getId() + ", name='" + getName() + "'" + ", lastname='" + getLastname() + "'"
                + "}";
    }
}
