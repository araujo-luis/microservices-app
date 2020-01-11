package uv.airlines.reactive.app.shops.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uv.airlines.reactive.app.shops.domain.Shops;
import uv.airlines.reactive.app.shops.dto.BoardingPassDTO;
import uv.airlines.reactive.app.shops.repository.ShopsRepository;
import uv.airlines.reactive.app.shops.service.ShopService;
@Service
public class ShopsServiceImpl implements ShopService {
	
	@Autowired
	private ShopsRepository shopsRepository;

	@Autowired
	WebClient.Builder webClientBuilder;	
	
	@Override
	public Mono<Shops> save(Shops shops) {
		Mono<BoardingPassDTO> response = webClientBuilder.build().get()
				.uri("http://mysql-app/api/reservation-passengers/boarding-pass-data/" 
						+ shops.getBoardingPassId().toString())
				.retrieve().bodyToMono(BoardingPassDTO.class);
		
		return response.flatMap(r ->{
				shops.setAirportDestiny(r.getAirportArrivalId());
				shops.setCustomerId(r.getPassengerId());	
				shops.setShopDate(LocalDateTime.now());
				return shopsRepository.save(shops);
			});		
	}
	
	

	@Override
	public Flux<Shops> findAll() {
		return shopsRepository.findAll();
	}

	@Override
	public Mono<Shops> findOne(String id) {
		return shopsRepository.findById(id);
	}

	@Override
	public Mono<ResponseEntity<Void>> delete(String id) {
		return shopsRepository.deleteById(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
	}

	@Override
	public Shops getData(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
