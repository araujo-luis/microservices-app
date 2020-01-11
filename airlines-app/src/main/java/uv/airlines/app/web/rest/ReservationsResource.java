package uv.airlines.app.web.rest;

import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.ReservationsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReservationsResource {

    private static final String ENTITY_NAME = "testAppReservations";

    private String applicationName = "airlinesApp";

    private final ReservationsService reservationsService;

    public ReservationsResource(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PostMapping("/reservations")
    public ResponseEntity<ReservationsDTO> createReservations(@RequestBody ReservationsDTO reservationsDTO)
            throws URISyntaxException {
        if (reservationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReservationsDTO result = reservationsService.save(reservationsDTO);
        return ResponseEntity
                .created(new URI("/api/reservations/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PutMapping("/reservations")
    public ResponseEntity<ReservationsDTO> updateReservations(@RequestBody ReservationsDTO reservationsDTO)
            throws URISyntaxException {
        if (reservationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservationsDTO result = reservationsService.save(reservationsDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                reservationsDTO.getId().toString())).body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PutMapping("/reservations/cancel/{id}")
    public String cancelReservations(@PathVariable Long id) {
        return reservationsService.cancelReservation(id); 
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @GetMapping("/reservations")
    public List<ReservationsDTO> getAllReservations() {
        return reservationsService.findAll();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationsDTO> getReservations(@PathVariable Long id) {
        Optional<ReservationsDTO> reservationsDTO = reservationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationsDTO);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservations(@PathVariable Long id) {
        reservationsService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
