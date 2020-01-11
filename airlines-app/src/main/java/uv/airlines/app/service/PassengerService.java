package uv.airlines.app.service;

import uv.airlines.app.service.dto.PassengerDTO;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    PassengerDTO save(PassengerDTO passengerDTO);

    List<PassengerDTO> findAll();

    Optional<PassengerDTO> findOne(String id);

    void delete(String id);

	String getRandomPassenger();
}
