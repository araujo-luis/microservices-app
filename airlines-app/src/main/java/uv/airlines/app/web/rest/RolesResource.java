package uv.airlines.app.web.rest;

import uv.airlines.app.service.RolesService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.RolesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link uv.airlines.app.domain.Roles}.
 */
@RestController
@RequestMapping("/api")
public class RolesResource {

    private final Logger log = LoggerFactory.getLogger(RolesResource.class);

    private static final String ENTITY_NAME = "testAppRoles";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RolesService rolesService;

    public RolesResource(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/roles")
    public ResponseEntity<RolesDTO> createRoles(@Valid @RequestBody RolesDTO rolesDTO) throws URISyntaxException {
        log.debug("REST request to save Roles : {}", rolesDTO);
        if (rolesDTO.getId() != null) {
            throw new BadRequestAlertException("A new roles cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RolesDTO result = rolesService.save(rolesDTO);
        return ResponseEntity
                .created(new URI("/api/roles/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/roles")
    public ResponseEntity<RolesDTO> updateRoles(@Valid @RequestBody RolesDTO rolesDTO) throws URISyntaxException {
        log.debug("REST request to update Roles : {}", rolesDTO);
        if (rolesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RolesDTO result = rolesService.save(rolesDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rolesDTO.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/roles")
    public List<RolesDTO> getAllRoles(@RequestParam(required = false) String filter) {
        if ("User-is-null".equals(filter)) {
            log.debug("REST request to get all Roless where User is null");
            return rolesService.findAllWhereUserIsNull();
        }
        log.debug("REST request to get all Roles");
        return rolesService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/roles/{id}")
    public ResponseEntity<RolesDTO> getRoles(@PathVariable String id) {
        log.debug("REST request to get Roles : {}", id);
        Optional<RolesDTO> rolesDTO = rolesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rolesDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<Void> deleteRoles(@PathVariable String id) {
        log.debug("REST request to delete Roles : {}", id);
        rolesService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
