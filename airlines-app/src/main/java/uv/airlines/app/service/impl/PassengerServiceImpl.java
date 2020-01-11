package uv.airlines.app.service.impl;

import uv.airlines.app.service.PassengerService;
import uv.airlines.app.domain.Passenger;
import uv.airlines.app.repository.PassengerRepository;
import uv.airlines.app.service.dto.PassengerDTO;
import uv.airlines.app.service.mapper.PassengerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Passenger}.
 */
@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final Logger log = LoggerFactory.getLogger(PassengerServiceImpl.class);

    private final PassengerRepository passengerRepository;

    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public PassengerDTO save(PassengerDTO passengerDTO) {
        Passenger passenger = passengerMapper.toEntity(passengerDTO);
        passenger = passengerRepository.save(passenger);
        return passengerMapper.toDto(passenger);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PassengerDTO> findAll() {
        return passengerRepository.findAll().stream().map(passengerMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    }

   
    @Override
    @Transactional(readOnly = true)
    public Optional<PassengerDTO> findOne(String id) {
        return passengerRepository.findById(id).map(passengerMapper::toDto);
    }

    @Override
    public void delete(String id) {
        passengerRepository.deleteById(id);
    }

	@Override
	public String getRandomPassenger() {
		return passengerRepository.getRandomPassenger();
	}
}
