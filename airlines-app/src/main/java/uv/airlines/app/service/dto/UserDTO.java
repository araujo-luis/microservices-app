package uv.airlines.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link uv.airlines.app.domain.User} entity.
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private Long rolesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRolesId() {
        return rolesId;
    }

    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDTO UserDTO = (UserDTO) o;
        if (UserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), UserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserDTO{" + "id=" + getId() + ", username='" + getUsername() + "'" + ", password='" + getPassword()
                + "'" + ", roles=" + getRolesId() + "}";
    }
}
