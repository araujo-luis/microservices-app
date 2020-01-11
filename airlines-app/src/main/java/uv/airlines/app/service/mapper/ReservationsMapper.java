package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.Reservations;
import uv.airlines.app.service.dto.ReservationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservations} and its DTO
 * {@link ReservationsDTO}.
 */
@Mapper(componentModel = "spring", uses = { AgenciesMapper.class, FlightScheduleMapper.class })
public interface ReservationsMapper extends EntityMapper<ReservationsDTO, Reservations> {

    @Mapping(source = "agencies.id", target = "agenciesId")
    @Mapping(source = "flightSchedule.id", target = "flightScheduleId")
    ReservationsDTO toDto(Reservations reservations);

    //@Mapping(target = "reservationPassengers", ignore = true)
    @Mapping(source = "agenciesId", target = "agencies")
    @Mapping(source = "flightScheduleId", target = "flightSchedule")
    Reservations toEntity(ReservationsDTO reservationsDTO);

    default Reservations fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reservations reservations = new Reservations();
        reservations.setId(id);
        return reservations;
    }
}
