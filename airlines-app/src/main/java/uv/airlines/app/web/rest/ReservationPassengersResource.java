package uv.airlines.app.web.rest;

import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.ReservationPassengersService;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;
import uv.airlines.app.service.dto.BoardingPassDTO;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;

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
public class ReservationPassengersResource {

    private static final String ENTITY_NAME = "testAppReservationPassengers";

    private String applicationName = "airlinesApp";

    private final ReservationPassengersService reservationPassengersService;

    public ReservationPassengersResource(ReservationPassengersService reservationPassengersService) {
        this.reservationPassengersService = reservationPassengersService;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PostMapping("/reservation-passengers")
    public ResponseEntity<ReservationPassengersDTO> createReservationPassengers(
            @Valid @RequestBody ReservationPassengersDTO reservationPassengersDTO) throws URISyntaxException {
        if (reservationPassengersDTO.getId() != null) {
            throw new BadRequestAlertException("A new reservationPassengers cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        ReservationPassengersDTO result = reservationPassengersService.save(reservationPassengersDTO);
        return ResponseEntity
                .created(new URI("/api/reservation-passengers/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PutMapping("/reservation-passengers")
    public ResponseEntity<ReservationPassengersDTO> updateReservationPassengers(
            @Valid @RequestBody ReservationPassengersDTO reservationPassengersDTO) throws URISyntaxException {
        if (reservationPassengersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReservationPassengersDTO result = reservationPassengersService.save(reservationPassengersDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
                reservationPassengersDTO.getId().toString())).body(result);
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY')")
    @PutMapping("/reservation-passengers/reservation/{reservationId}/change/{passengerOriginal}/{passengerChanged}")
    public ReservationPassengers changePassenger(@PathVariable Long reservationId, @PathVariable String passengerOriginal, 
    												@PathVariable String passengerChanged) {
        return reservationPassengersService.changePassenger(reservationId, passengerOriginal, passengerChanged);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINE')")
    @PutMapping("/reservation-passengers/boarding-pass/{reservationId}/{passengerId}")
    public ReservationPassengers updateBoardingPasses(@PathVariable Long reservationId, @PathVariable String passengerId) {
        return reservationPassengersService.updateBoardingPasses(reservationId, passengerId);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINE', 'ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers")
    public List<ReservationPassengersDTO> getAllReservationPassengers() {
        return reservationPassengersService.findAll();
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers/able-to-change/{reservationId}")
    public List<ReservationPassengersDTO> getPassengersAbleToChange(@PathVariable Long reservationId) {
        return reservationPassengersService.getPassengersAbleToChange(reservationId);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers/boarding-pass/{reservationId}")
    public List<ReservationPassengersDTO> getAllBoardingPasses(@PathVariable Long reservationId) {
        return reservationPassengersService.getBoardingPasses(reservationId, null);
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers/{id}")
    public ResponseEntity<ReservationPassengersDTO> getReservationPassengers(@PathVariable Long id) {
        Optional<ReservationPassengersDTO> reservationPassengersDTO = reservationPassengersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reservationPassengersDTO);
    }
    
    
    @GetMapping("/reservation-passengers/boarding-pass-data/{id}")
    public BoardingPassDTO getBoardingPassData(@PathVariable Long id) {
        BoardingPassDTO boardingPassDTO = reservationPassengersService.findBoardingPassData(id);
        return boardingPassDTO;
    }   
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers/boarding-priority")
    public List<PassengersPriorityDTO> getPriorityPassengers() {
       
        return reservationPassengersService.findAllPassengersWithPriority();
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers/top-destinations")
    public List<ProfitFlightsDTO> getTop10Destinations() {
       
        return reservationPassengersService.getTop10ProfitsFlights();
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AIRLINE')")
    @GetMapping("/reservation-passengers/monthly-profits")
    public List<MonthlyProfitsDTO> getMonthlyProfits() {
        return reservationPassengersService.getMonthlyProfits();
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_AGENCY', 'ROLE_AIRLINE')")
    @DeleteMapping("/reservation-passengers/{id}")
    public ResponseEntity<Void> deleteReservationPassengers(@PathVariable Long id) {
        reservationPassengersService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
