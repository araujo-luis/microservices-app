package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.Agencies;
import uv.airlines.app.service.dto.AgenciesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agencies} and its DTO {@link AgenciesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgenciesMapper extends EntityMapper<AgenciesDTO, Agencies> {

    //@Mapping(target = "reservations", ignore = true)
    Agencies toEntity(AgenciesDTO agenciesDTO);

    default Agencies fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agencies agencies = new Agencies();
        agencies.setId(id);
        return agencies;
    }
}
