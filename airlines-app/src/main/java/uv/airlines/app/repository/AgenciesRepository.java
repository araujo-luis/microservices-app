package uv.airlines.app.repository;

import uv.airlines.app.domain.Agencies;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the Agencies entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenciesRepository extends JpaRepository<Agencies, Long> {
	@Query(value = "SELECT id FROM agencies ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Long getRandomAgency();

}
