package uv.airlines.app.service;

import uv.airlines.app.service.dto.RolesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link uv.airlines.app.domain.Roles}.
 */
public interface RolesService {

    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save.
     * @return the persisted entity.
     */
    RolesDTO save(RolesDTO rolesDTO);

    /**
     * Get all the roles.
     *
     * @return the list of entities.
     */
    List<RolesDTO> findAll();

    /**
     * Get all the RolesDTO where User is {@code null}.
     *
     * @return the list of entities.
     */
    List<RolesDTO> findAllWhereUserIsNull();

    /**
     * Get the "id" roles.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RolesDTO> findOne(String id);

    /**
     * Delete the "id" roles.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
