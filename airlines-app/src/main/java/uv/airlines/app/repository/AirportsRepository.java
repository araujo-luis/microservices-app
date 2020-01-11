package uv.airlines.app.repository;

import uv.airlines.app.domain.Airports;
import uv.airlines.app.service.dto.AirportsDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Airports entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AirportsRepository extends JpaRepository<Airports, Long> {
	
	@Query(value = "SELECT id FROM airports ORDER BY RAND() LIMIT 1", nativeQuery=true)
	String getRandomAirport();

}
