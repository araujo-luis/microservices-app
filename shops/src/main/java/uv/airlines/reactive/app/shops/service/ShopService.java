package uv.airlines.reactive.app.shops.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uv.airlines.reactive.app.shops.domain.Shops;

@Service
public interface ShopService {

	Mono<Shops> save(Shops shops);

	Flux<Shops > findAll();

	Mono<Shops> findOne(String id);

	Mono<ResponseEntity<Void>>  delete(String id);
	
	Shops getData(String id);
}
