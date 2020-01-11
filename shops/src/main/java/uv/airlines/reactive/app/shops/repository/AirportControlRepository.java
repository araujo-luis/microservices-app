package uv.airlines.reactive.app.shops.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;
import uv.airlines.reactive.app.shops.domain.AirportControl;

public interface AirportControlRepository extends ReactiveMongoRepository<AirportControl, String> {

	Mono<AirportControl> findByBoardingPassId(Long boardingPassId);
	
}
