package uv.airlines.app.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.Roles} entity.
 */
public class RolesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @Size(max = 20)
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RolesDTO rolesDTO = (RolesDTO) o;
        if (rolesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rolesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RolesDTO{" + "id=" + getId() + ", name='" + getName() + "'" + "}";
    }
}
