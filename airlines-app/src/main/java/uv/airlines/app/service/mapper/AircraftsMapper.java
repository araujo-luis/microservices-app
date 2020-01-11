package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.*;
import uv.airlines.app.service.dto.AircraftsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Aircrafts} and its DTO {@link AircraftsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AircraftsMapper extends EntityMapper<AircraftsDTO, Aircrafts> {

    //@Mapping(target = "flightSchedules", ignore = true)
    Aircrafts toEntity(AircraftsDTO aircraftsDTO);

    default Aircrafts fromId(Long id) {
        if (id == null) {
            return null;
        }
        Aircrafts aircrafts = new Aircrafts();
        aircrafts.setId(id);
        return aircrafts;
    }
}
