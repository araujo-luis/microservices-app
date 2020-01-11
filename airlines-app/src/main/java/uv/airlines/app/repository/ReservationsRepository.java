package uv.airlines.app.repository;

import uv.airlines.app.domain.Reservations;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Reservations entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
	@Query(value = "SELECT id FROM reservations ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Long getRandomReservation();

}
