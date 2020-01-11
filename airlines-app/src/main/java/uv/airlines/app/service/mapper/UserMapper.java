package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.*;
import uv.airlines.app.service.dto.UserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDTO}.
 */
@Mapper(componentModel = "spring", uses = { RolesMapper.class })
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Mapping(source = "roles.id", target = "rolesId")
    UserDTO toDto(User User);

    @Mapping(source = "rolesId", target = "roles")
    User toEntity(UserDTO UserDTO);

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User User = new User();
        User.setId(id);
        return User;
    }
}
