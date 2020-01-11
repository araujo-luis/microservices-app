package uv.airlines.reactive.app.shops.service;

import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uv.airlines.reactive.app.shops.domain.AirportControl;

public interface AirportControlService {

	Mono<AirportControl> save(AirportControl airportControl );

	Flux<AirportControl > findAll();

	Mono<AirportControl> findOne(String id);

	Mono<ResponseEntity<Void>>  delete(String id);
	
	Mono<AirportControl> saveBoardingDate(AirportControl airportControl );	

}
