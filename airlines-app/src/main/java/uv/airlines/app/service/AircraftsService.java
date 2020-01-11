package uv.airlines.app.service;

import uv.airlines.app.service.dto.AircraftsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Aircrafts}.
 */
public interface AircraftsService {

    /**
     * Save a aircrafts.
     *
     * @param aircraftsDTO the entity to save.
     * @return the persisted entity.
     */
    AircraftsDTO save(AircraftsDTO aircraftsDTO);

    /**
     * Get all the aircrafts.
     *
     * @return the list of entities.
     */
    List<AircraftsDTO> findAll();

    /**
     * Get the "id" aircrafts.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AircraftsDTO> findOne(Long id);

    /**
     * Delete the "id" aircrafts.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
