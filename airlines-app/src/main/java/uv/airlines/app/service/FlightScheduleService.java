package uv.airlines.app.service;

import uv.airlines.app.service.dto.FlightScheduleDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FlightScheduleService {

    FlightScheduleDTO save(FlightScheduleDTO flightScheduleDTO);

    List<FlightScheduleDTO> findAll();

    Optional<FlightScheduleDTO> findOne(Long id);

    void delete(Long id);

    List<FlightScheduleDTO> findFlights(Map<String, String> allParams);
    
    List<FlightScheduleDTO> findPendingFlights(LocalDateTime today, Long idAgency);
    
    List<FlightScheduleDTO> findFlownFlights(LocalDateTime today, Long idAgency);

	Long getRandomFlightSchedule();

	List<FlightScheduleDTO> getFlightsAbleToCancel();

  
}
