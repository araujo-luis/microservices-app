package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.*;
import uv.airlines.app.service.dto.RolesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Roles} and its DTO {@link RolesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RolesMapper extends EntityMapper<RolesDTO, Roles> {

    //@Mapping(target = "User", ignore = true)
    Roles toEntity(RolesDTO rolesDTO);

    default Roles fromId(String id) {
        if (id == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(id);
        return roles;
    }
}
