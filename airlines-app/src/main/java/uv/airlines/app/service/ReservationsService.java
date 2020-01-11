package uv.airlines.app.service;

import uv.airlines.app.service.dto.ReservationsDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationsService {

    ReservationsDTO save(ReservationsDTO reservationsDTO);

    List<ReservationsDTO> findAll();

    Optional<ReservationsDTO> findOne(Long id);

    void delete(Long id);

	Long getRandomReservation();
	
	String cancelReservation(Long id);
}
