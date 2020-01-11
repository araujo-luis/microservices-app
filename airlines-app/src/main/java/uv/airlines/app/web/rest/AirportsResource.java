package uv.airlines.app.web.rest;

import uv.airlines.app.service.AirportsService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.AirportsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.Airports}.
 */
@RestController
@RequestMapping("/api")
public class AirportsResource {

    private final Logger log = LoggerFactory.getLogger(AirportsResource.class);

    private static final String ENTITY_NAME = "testAppAirports";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final AirportsService airportsService;

    public AirportsResource(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRPORT')")
    @PostMapping("/airports")
    public ResponseEntity<AirportsDTO> createAirports(@Valid @RequestBody AirportsDTO airportsDTO)
            throws URISyntaxException {
        log.debug("REST request to save Airports : {}", airportsDTO);
        if (airportsDTO.getId() != null) {
            throw new BadRequestAlertException("A new airports cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AirportsDTO result = airportsService.save(airportsDTO);
        return ResponseEntity
                .created(new URI("/api/airports/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRPORT')")
    @PutMapping("/airports")
    public ResponseEntity<AirportsDTO> updateAirports(@Valid @RequestBody AirportsDTO airportsDTO)
            throws URISyntaxException {
        log.debug("REST request to update Airports : {}", airportsDTO);
        if (airportsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AirportsDTO result = airportsService.save(airportsDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, airportsDTO.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRPORT')")
    @GetMapping("/airports")
    public List<AirportsDTO> getAllAirports() {
        log.debug("REST request to get all Airports");
        return airportsService.findAll();
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRPORT')")
    @GetMapping("/airports/{id}")
    public ResponseEntity<AirportsDTO> getAirports(@PathVariable Long id) {
        log.debug("REST request to get Airports : {}", id);
        Optional<AirportsDTO> airportsDTO = airportsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(airportsDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRPORT')")
    @DeleteMapping("/airports/{id}")
    public ResponseEntity<Void> deleteAirports(@PathVariable Long id) {
        log.debug("REST request to delete Airports : {}", id);
        airportsService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
