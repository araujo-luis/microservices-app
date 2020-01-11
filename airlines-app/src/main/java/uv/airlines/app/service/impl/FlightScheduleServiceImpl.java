package uv.airlines.app.service.impl;

import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.domain.FlightSchedule;
import uv.airlines.app.repository.FlightScheduleRepository;
import uv.airlines.app.service.dto.FlightScheduleDTO;
import uv.airlines.app.service.mapper.FlightScheduleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightScheduleServiceImpl implements FlightScheduleService {

        private final Logger log = LoggerFactory.getLogger(FlightScheduleServiceImpl.class);

        private final FlightScheduleRepository flightScheduleRepository;

        private final FlightScheduleMapper flightScheduleMapper;

        public FlightScheduleServiceImpl(FlightScheduleRepository flightScheduleRepository,
                        FlightScheduleMapper flightScheduleMapper) {
                this.flightScheduleRepository = flightScheduleRepository;
                this.flightScheduleMapper = flightScheduleMapper;
        }

        @Override
        public FlightScheduleDTO save(FlightScheduleDTO flightScheduleDTO) {
                FlightSchedule flightSchedule = flightScheduleMapper.toEntity(flightScheduleDTO);
                flightSchedule = flightScheduleRepository.save(flightSchedule);
                return flightScheduleMapper.toDto(flightSchedule);
        }

       
        @Override
        @Transactional(readOnly = true)
        public List<FlightScheduleDTO> findAll() {
                return flightScheduleRepository.findAll().stream().map(flightScheduleMapper::toDto)
                                .collect(Collectors.toCollection(LinkedList::new));
        }

        @Override
        @Transactional(readOnly = true)
        public Optional<FlightScheduleDTO> findOne(Long id) {
                return flightScheduleRepository.findById(id).map(flightScheduleMapper::toDto);
        }

  
        @Override
        public void delete(Long id) {
                flightScheduleRepository.deleteById(id);
        }

        @Override
        public List<FlightScheduleDTO> findFlights(Map<String, String> allParams) {
        	//String airportTakeoff, String airportArrival, Long takeoffDate, Long takeoffDate2
        	LocalDateTime takeoffLocalDate;
        	LocalDateTime takeoffLocalDate2;
        	String airportTakeoff = allParams.get("airportTakeoff");
        	String airportArrival = allParams.get("airportArrival");
        	String takeoffDate = allParams.get("takeoffDate");
        	

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime takeoffLocal = LocalDateTime.parse(takeoffDate+ " 02:00", formatter);
            
            
        	Integer passengers = Integer.parseInt(allParams.get("passengers"));
        	
        	if(allParams.containsKey("optional")) {
        		LocalDateTime.of(2019,5,28,0,0,0).toInstant(ZoneOffset.UTC).toEpochMilli();
        		System.out.println("OPTIONAL");
        		takeoffLocalDate = takeoffLocal.minusDays(3);
        		takeoffLocalDate2 = takeoffLocal.plusDays(3);
        		
        	}else {
        		takeoffLocalDate = takeoffLocal;
        		takeoffLocalDate2 = takeoffLocal.plusDays(1);
        	}
        	
            System.out.println(takeoffLocalDate);
            System.out.println(takeoffLocalDate2);
                
            return flightScheduleRepository.findFlightsAvailable(airportTakeoff, airportArrival, takeoffLocalDate, takeoffLocalDate2, passengers)
                                .stream().map(flightScheduleMapper::toDto)
                                .collect(Collectors.toCollection(LinkedList::new));
        }

		@Override
		public List<FlightScheduleDTO> findPendingFlights(LocalDateTime today, Long idAgency) {
			// TODO Auto-generated method stub
			return flightScheduleRepository.findByArrivalDateGreaterThanAndReservations_agencies_idEquals(LocalDateTime.now(), idAgency).stream()
					.map(flightScheduleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		}

		@Override
		public List<FlightScheduleDTO> findFlownFlights(LocalDateTime today, Long idAgency) {
			return flightScheduleRepository.findByArrivalDateLessThanAndReservations_agencies_idEquals(LocalDateTime.now(), idAgency).stream()
					.map(flightScheduleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		}

		@Override
		public Long getRandomFlightSchedule() {
			return flightScheduleRepository.getRandomFlightSchedule();
		}

		@Override
		public List<FlightScheduleDTO> getFlightsAbleToCancel() {
			LocalDateTime today = LocalDateTime.now();
			LocalDateTime plusSevenDays = today.plusDays(7);
			return flightScheduleRepository.findByTakeoffDateGreaterThanAndTakeoffDateLessThanAndReservations_statusEquals(today, plusSevenDays, true).stream()
					.map(flightScheduleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		}

	

}
