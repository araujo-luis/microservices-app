package uv.airlines.app.service;

import uv.airlines.app.service.dto.AirportsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Airports}.
 */
public interface AirportsService {

    AirportsDTO save(AirportsDTO airportsDTO);
    
    void saveAll(List<AirportsDTO> airportsDTO);

    List<AirportsDTO> findAll();
    
    Optional<AirportsDTO> findOne(Long id);

    void delete(Long id);

	String getRandomAirport();
}
