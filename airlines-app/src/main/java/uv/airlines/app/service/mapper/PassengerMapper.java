package uv.airlines.app.service.mapper;

import uv.airlines.app.domain.*;
import uv.airlines.app.service.dto.PassengerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Passenger} and its DTO {@link PassengerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PassengerMapper extends EntityMapper<PassengerDTO, Passenger> {

    @Mapping(target = "reservationPassengers", ignore = true)
    Passenger toEntity(PassengerDTO passengerDTO);

    default Passenger fromId(String id) {
        if (id == null) {
            return null;
        }
        Passenger passenger = new Passenger();
        passenger.setId(id);
        return passenger;
    }
}
