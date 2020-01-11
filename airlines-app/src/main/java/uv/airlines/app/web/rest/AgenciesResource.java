package uv.airlines.app.web.rest;

import uv.airlines.app.service.AgenciesService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.AgenciesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.Agencies}.
 */
@RestController
@RequestMapping("/api")
public class AgenciesResource {

    private static final String ENTITY_NAME = "testAppAgencies";

    private String applicationName = "airlinesApp";

    private final AgenciesService agenciesService;

    public AgenciesResource(AgenciesService agenciesService) {
        this.agenciesService = agenciesService;
    }
    @PreAuthorize("hasAnyRole('ROLE_AGENCY')")
    @PostMapping("/agencies")
    public ResponseEntity<AgenciesDTO> createAgencies(@Valid @RequestBody AgenciesDTO agenciesDTO)
            throws URISyntaxException {
        if (agenciesDTO.getId() != null) {
            throw new BadRequestAlertException("A new agencies cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgenciesDTO result = agenciesService.save(agenciesDTO);
        return ResponseEntity
                .created(new URI("/api/agencies/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_AGENCY')")
    @PutMapping("/agencies")
    public ResponseEntity<AgenciesDTO> updateAgencies(@Valid @RequestBody AgenciesDTO agenciesDTO)
            throws URISyntaxException {
        if (agenciesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AgenciesDTO result = agenciesService.save(agenciesDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, agenciesDTO.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @GetMapping("/agencies")
    public List<AgenciesDTO> getAllAgencies() {
        return agenciesService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_AGENCY')")
    @GetMapping("/agencies/{id}")
    public ResponseEntity<AgenciesDTO> getAgencies(@PathVariable Long id) {
        Optional<AgenciesDTO> agenciesDTO = agenciesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agenciesDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @DeleteMapping("/agencies/{id}")
    public ResponseEntity<Void> deleteAgencies(@PathVariable Long id) {
        agenciesService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
