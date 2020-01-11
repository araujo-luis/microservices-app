package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.FlightSchedule;
import uv.airlines.app.service.dto.FlightScheduleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FlightSchedule} and its DTO
 * {@link FlightScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = { AircraftsMapper.class, AirportsMapper.class })
public interface FlightScheduleMapper extends EntityMapper<FlightScheduleDTO, FlightSchedule> {

    @Mapping(source = "aircraft.id", target = "aircraftId")
    @Mapping(source = "airportTakeoff.id", target = "airportTakeoffId")
    @Mapping(source = "airportArrival.id", target = "airportArrivalId")
    FlightScheduleDTO toDto(FlightSchedule flightSchedule);

    @Mapping(source = "aircraftId", target = "aircraft")
    @Mapping(source = "airportTakeoffId", target = "airportTakeoff")
    @Mapping(source = "airportArrivalId", target = "airportArrival")
    FlightSchedule toEntity(FlightScheduleDTO flightScheduleDTO);

    default FlightSchedule fromId(Long id) {
        if (id == null) {
            return null;
        }
        FlightSchedule flightSchedule = new FlightSchedule();
        flightSchedule.setId(id);
        return flightSchedule;
    }
}
