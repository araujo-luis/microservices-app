package uv.airlines.reactive.app.shops.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uv.airlines.reactive.app.shops.domain.AirportControl;
import uv.airlines.reactive.app.shops.dto.BoardingPassDTO;
import uv.airlines.reactive.app.shops.repository.AirportControlRepository;
import uv.airlines.reactive.app.shops.service.AirportControlService;

@Service
public class AirportControlServiceImpl implements AirportControlService {
	@Autowired
	private AirportControlRepository airportControlRepository;
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	
	@Override
	public Mono<AirportControl> save(AirportControl airportControl) {
		Mono<BoardingPassDTO> response = webClientBuilder.build().get()
				.uri("http://mysql-app/api/reservation-passengers/boarding-pass-data/" + airportControl.getBoardingPassId().toString())
				.retrieve().bodyToMono(BoardingPassDTO.class);
		return response.flatMap(r ->{
			airportControl.setAirportDestiny(r.getAirportArrivalId());
			airportControl.setIdentification(r.getPassengerId());	
			airportControl.setSecurityGateDate(LocalDateTime.now());			
			return airportControlRepository.save(airportControl);
		});
		
	}

	@Override
	public Flux<AirportControl> findAll() {
		return airportControlRepository.findAll();
	}

	@Override
	public Mono<AirportControl> findOne(String id) {
		return airportControlRepository.findById(id);
	}

	@Override
	public Mono<ResponseEntity<Void>> delete(String id) {
		return airportControlRepository.deleteById(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
	}

	@Override
	public Mono<AirportControl> saveBoardingDate(AirportControl airportControl) {
		Mono<AirportControl> ac = airportControlRepository.findByBoardingPassId(airportControl.getBoardingPassId());
		return ac.flatMap(r ->{
			r.setBoardingDate(LocalDateTime.now());
			return airportControlRepository.save(r);
		});
		
	}




}
