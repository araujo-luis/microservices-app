package uv.airlines.app.service.impl;

import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.domain.Reservations;
import uv.airlines.app.repository.ReservationsRepository;
import uv.airlines.app.service.dto.ReservationsDTO;
import uv.airlines.app.service.mapper.ReservationsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationsServiceImpl implements ReservationsService {

    private final Logger log = LoggerFactory.getLogger(ReservationsServiceImpl.class);

    private final ReservationsRepository reservationsRepository;

    private final ReservationsMapper reservationsMapper;

    public ReservationsServiceImpl(ReservationsRepository reservationsRepository,
            ReservationsMapper reservationsMapper) {
        this.reservationsRepository = reservationsRepository;
        this.reservationsMapper = reservationsMapper;
    }

    @Override
    public ReservationsDTO save(ReservationsDTO reservationsDTO) {
        Reservations reservations = reservationsMapper.toEntity(reservationsDTO);
        reservations = reservationsRepository.save(reservations);
        return reservationsMapper.toDto(reservations);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationsDTO> findAll() {
        return reservationsRepository.findAll().stream().map(reservationsMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservationsDTO> findOne(Long id) {
        return reservationsRepository.findById(id).map(reservationsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        reservationsRepository.deleteById(id);
    }

	@Override
	public Long getRandomReservation() {
		return reservationsRepository.getRandomReservation();
	}

	@Override
	public String cancelReservation(Long id) {
		Optional<Reservations> reservation = reservationsRepository.findById(id);
		if(!reservation.isPresent()) {
			return "Reservation doesn't exists";
		}
		
		LocalDateTime reservationDate =  reservation.get().getFlightSchedule().getTakeoffDate();
		Long daysDifference = Duration.between(reservationDate, LocalDateTime.now()).toDays();
		
		if (!reservation.get().getStatus()) {
			return "Reservation has already been canceled";
		}
		
		if(daysDifference  >= -7 && daysDifference  < 0 ) {
			reservation.get().setStatus(false);
			reservationsRepository.save(reservation.get());
			return "Reservation canceled";
		}
		
		return "Reservations could not be canceled";
	}
}
