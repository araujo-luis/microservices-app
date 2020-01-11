package uv.airlines.app.repository;

import uv.airlines.app.domain.Aircrafts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Aircrafts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AircraftsRepository extends JpaRepository<Aircrafts, Long> {

}
