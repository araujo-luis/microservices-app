package uv.airlines.app.web.rest;

import uv.airlines.app.service.PassengerService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.PassengerDTO;

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

@RestController
@RequestMapping("/api")
public class PassengerResource {

    private static final String ENTITY_NAME = "testAppPassenger";

    private String applicationName = "airlinesApp";

    private final PassengerService passengerService;

    public PassengerResource(PassengerService passengerService) {
        this.passengerService = passengerService;
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PostMapping("/passengers")
    public ResponseEntity<PassengerDTO> createPassenger(@Valid @RequestBody PassengerDTO passengerDTO)
            throws URISyntaxException {
        if (passengerDTO.getId() != null) {
            throw new BadRequestAlertException("A new passenger cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PassengerDTO result = passengerService.save(passengerDTO);
        return ResponseEntity
                .created(new URI("/api/passengers/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PutMapping("/passengers")
    public ResponseEntity<PassengerDTO> updatePassenger(@Valid @RequestBody PassengerDTO passengerDTO)
            throws URISyntaxException {
        if (passengerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PassengerDTO result = passengerService.save(passengerDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                passengerDTO.getId().toString())).body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINES')")
    @GetMapping("/passengers")
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINES')")
    @GetMapping("/passengers/{id}")
    public ResponseEntity<PassengerDTO> getPassenger(@PathVariable String id) {
        Optional<PassengerDTO> passengerDTO = passengerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(passengerDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @DeleteMapping("/passengers/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable String id) {
        passengerService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
