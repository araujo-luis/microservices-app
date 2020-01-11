package uv.airlines.app.web.rest;

import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.FlightScheduleDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FlightScheduleResource {

    private static final String ENTITY_NAME = "testAppFlightSchedule";

    // @Value("${jhipster.clientApp.name}")
    private String applicationName = "airlinesApp";

    private final FlightScheduleService flightScheduleService;

    public FlightScheduleResource(FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @PostMapping("/flight-schedules")
    public ResponseEntity<FlightScheduleDTO> createFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO)
            throws URISyntaxException {

        if (flightScheduleDTO.getId() != null) {
            throw new BadRequestAlertException("A new flightSchedule cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        FlightScheduleDTO result = flightScheduleService.save(flightScheduleDTO);
        return ResponseEntity
                .created(new URI("/api/flight-schedules/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @PutMapping("/flight-schedules")
    public ResponseEntity<FlightScheduleDTO> updateFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO)
            throws URISyntaxException {
        if (flightScheduleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FlightScheduleDTO result = flightScheduleService.save(flightScheduleDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                flightScheduleDTO.getId().toString())).body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY','ROLE_AIRLINE')")
    @GetMapping("/flight-schedules")
    public List<FlightScheduleDTO> getFlights(@RequestParam Map<String,String> allParams) {
        return flightScheduleService.findFlights(allParams);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY','ROLE_AIRLINE')")
    @GetMapping("/flight-schedules/pending/{idAgency}")
    public List<FlightScheduleDTO> getPendingFlights(@PathVariable Long idAgency) {
        return flightScheduleService.findPendingFlights(LocalDateTime.now(), idAgency);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY','ROLE_AIRLINE')")
    @GetMapping("/flight-schedules/able-to-cancel")
    public List<FlightScheduleDTO> getFlightsAbleToCancel() {
        return flightScheduleService.getFlightsAbleToCancel();
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY','ROLE_AIRLINE')")
    @GetMapping("/flight-schedules/flown/{idAgency}")
    public List<FlightScheduleDTO> getFlownFlights(@PathVariable Long idAgency) {
        return flightScheduleService.findFlownFlights(LocalDateTime.now(), idAgency);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY','ROLE_AIRLINE')")
    @GetMapping("/flight-schedules/{id}")
    public ResponseEntity<FlightScheduleDTO> getFlightSchedule(@PathVariable Long id) {
        Optional<FlightScheduleDTO> flightScheduleDTO = flightScheduleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flightScheduleDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @DeleteMapping("/flight-schedules/{id}")
    public ResponseEntity<Void> deleteFlightSchedule(@PathVariable Long id) {
        flightScheduleService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
