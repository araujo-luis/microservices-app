package uv.airlines.app.service.impl;

import uv.airlines.app.service.ReservationPassengersService;
import uv.airlines.app.domain.Passenger;
import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.domain.Reservations;
import uv.airlines.app.repository.PassengerRepository;
import uv.airlines.app.repository.ReservationPassengersRepository;
import uv.airlines.app.repository.ReservationsRepository;
import uv.airlines.app.service.dto.BoardingPassDTO;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;
import uv.airlines.app.service.mapper.ReservationPassengersMapper;
import uv.airlines.app.web.rest.errors.BadRequestAlertException;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ReservationPassengers}.
 */
@Service
@Transactional
public class ReservationPassengersServiceImpl implements ReservationPassengersService {

    private final Logger log = LoggerFactory.getLogger(ReservationPassengersServiceImpl.class);

    private final ReservationPassengersRepository reservationPassengersRepository;
    
    private final PassengerRepository passengerRepository;
    
    private final ReservationsRepository reservationRepository;

    private final ReservationPassengersMapper reservationPassengersMapper;

    public ReservationPassengersServiceImpl(ReservationPassengersRepository reservationPassengersRepository,
            ReservationPassengersMapper reservationPassengersMapper, PassengerRepository passengerRepository,
            ReservationsRepository reservationRepository) {
        this.reservationPassengersRepository = reservationPassengersRepository;
        this.reservationPassengersMapper = reservationPassengersMapper;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationPassengersDTO save(ReservationPassengersDTO reservationPassengersDTO) {
        ReservationPassengers reservationPassengers = reservationPassengersMapper.toEntity(reservationPassengersDTO);
        reservationPassengers = reservationPassengersRepository.save(reservationPassengers);
        return reservationPassengersMapper.toDto(reservationPassengers);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationPassengersDTO> findAll() {
        return reservationPassengersRepository.findAll().stream().map(reservationPassengersMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservationPassengersDTO> findOne(Long id) {
        return reservationPassengersRepository.findById(id).map(reservationPassengersMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        reservationPassengersRepository.deleteById(id);
    }

	@Override
	public List<PassengersPriorityDTO> findAllPassengersWithPriority() {
		return reservationPassengersRepository.getPassengerPriority(2);
	}

	@Override
	public List<ProfitFlightsDTO> getTop10ProfitsFlights() {
		Pageable top10 = PageRequest.of(0, 10);
		Date now = new Date();
		Date monthAgo = new DateTime().minusMonths(1).toDate();
		return reservationPassengersRepository.findTop10ProfitsFlights(top10, monthAgo, now);
	}

	@Override
	public List<MonthlyProfitsDTO> getMonthlyProfits() {
		Pageable months = PageRequest.of(0,6);
		return reservationPassengersRepository.getMonthlyProfits(months);
	}

	@Override
	public ReservationPassengers changePassenger(Long reservationId, String passengerOriginal,
			String passengerChanged) {
		List<ReservationPassengers> RP = reservationPassengersRepository.findByReservation_id(reservationId);
		if(RP.size() == 0) {
			throw new BadRequestAlertException("NO HAY RESERVAS DISPONIBLES", "ReservationPassenger", "idnull"); 
		}
		
		
		Optional<ReservationPassengers> passenger = RP.stream().filter(x -> x.getPassenger().getId().equals(passengerOriginal)).findFirst();
		
		if(!passenger.isPresent()) {
			throw new BadRequestAlertException("NO EXISTE PASAJERO", "ReservationPassenger", "idnull");
		}
		
		Optional<Passenger> passengerId =	passengerRepository.findById(passengerChanged);
		 
		if(!passengerId.isPresent()) {
			throw new BadRequestAlertException("NO EXISTE PASAJERO NUEVO", "ReservationPassenger", "idnull");
		}
		
		ReservationPassengersDTO rDto = passenger.map(reservationPassengersMapper::toDto).get();
		rDto.setPassengerId(passengerChanged);
		passenger.get().setPassenger(passengerId.get());
		
		return reservationPassengersRepository.save(reservationPassengersMapper.toEntity(rDto));
	}

	@Override
	public ReservationPassengers updateBoardingPasses(Long reservationId, String passengerId) {
		List<ReservationPassengers> reservations = reservationPassengersRepository.findByReservation_id(reservationId);
		
		if(reservations.isEmpty()) {
			throw new BadRequestAlertException("Reservation in ", "Reservations", " Reservation does not exists in Reservations Passenger");
		}
		
		Optional<ReservationPassengers> p = reservations.stream().filter(x-> x.getPassenger().getId().equals(passengerId)).findFirst();
				
		if(!p.isPresent()) {
			throw new BadRequestAlertException("Passenger in ", "Reservations", " Passenger does not exists in Reservations Passengers");
		}

		if(p.get().getStatus()) {
			throw new BadRequestAlertException("Passenger in ", "Reservations", " Boarding pass has already been generated");
		}
		
		LocalDateTime takeoffDate =  p.get().getReservation().getFlightSchedule().getTakeoffDate();
		Long hoursDifference = Duration.between(takeoffDate , LocalDateTime.now()).toHours();
		if(!(hoursDifference >= -24 && hoursDifference < 0 )) {
			throw new BadRequestAlertException("Passenger in ", "Reservations", " Boarding pass must be generated in 24 hours range");
		}
		
		p.get().setStatus(true);
		return reservationPassengersRepository.save(p.get());
		
	}
	
	@Override
	public List<ReservationPassengersDTO> getBoardingPasses(Long reservationId, String seatNumber) {		
		return reservationPassengersRepository.findByReservation_idAndSeatNumberEquals(reservationId, null).stream().map(reservationPassengersMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public List<ReservationPassengersDTO> getPassengersAbleToChange(Long reservationId) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime plusSevenDays = today.plusDays(7);
		return reservationPassengersRepository.findByReservation_flightSchedule_takeoffDateGreaterThanAndReservation_flightSchedule_takeoffDateLessThanAndReservation_statusEqualsAndReservation_idEqualsAndSeatNumberIsNotNull(today, plusSevenDays, true, reservationId).stream().map(reservationPassengersMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public BoardingPassDTO findBoardingPassData(Long id) {
		return reservationPassengersRepository.findBoardingPass(id);
	}

}
