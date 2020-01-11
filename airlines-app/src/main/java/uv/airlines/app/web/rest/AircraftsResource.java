package uv.airlines.app.web.rest;

import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.AircraftsDTO;

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
 * REST controller for managing {@link uv.airlines.app.domain.Aircrafts}.
 */
@RestController
@RequestMapping("/api")
public class AircraftsResource {

    private final Logger log = LoggerFactory.getLogger(AircraftsResource.class);

    private static final String ENTITY_NAME = "testAppAircrafts";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final AircraftsService aircraftsService;

    public AircraftsResource(AircraftsService aircraftsService) {
        this.aircraftsService = aircraftsService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @PostMapping("/aircrafts")
    public ResponseEntity<AircraftsDTO> createAircrafts(@Valid @RequestBody AircraftsDTO aircraftsDTO)
            throws URISyntaxException {
        log.debug("REST request to save Aircrafts : {}", aircraftsDTO);
        if (aircraftsDTO.getId() != null) {
            throw new BadRequestAlertException("A new aircrafts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AircraftsDTO result = aircraftsService.save(aircraftsDTO);
        return ResponseEntity
                .created(new URI("/api/aircrafts/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @PutMapping("/aircrafts")
    public ResponseEntity<AircraftsDTO> updateAircrafts(@Valid @RequestBody AircraftsDTO aircraftsDTO)
            throws URISyntaxException {
        log.debug("REST request to update Aircrafts : {}", aircraftsDTO);
        if (aircraftsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AircraftsDTO result = aircraftsService.save(aircraftsDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                aircraftsDTO.getId().toString())).body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @GetMapping("/aircrafts")
    public List<AircraftsDTO> getAllAircrafts() {
        log.debug("REST request to get all Aircrafts");
        return aircraftsService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @GetMapping("/aircrafts/{id}")
    public ResponseEntity<AircraftsDTO> getAircrafts(@PathVariable Long id) {
        log.debug("REST request to get Aircrafts : {}", id);
        Optional<AircraftsDTO> aircraftsDTO = aircraftsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aircraftsDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @DeleteMapping("/aircrafts/{id}")
    public ResponseEntity<Void> deleteAircrafts(@PathVariable Long id) {
        log.debug("REST request to delete Aircrafts : {}", id);
        aircraftsService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
