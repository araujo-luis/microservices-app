package uv.airlines.reactive.app.shops.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uv.airlines.reactive.app.shops.domain.AirportControl;
import uv.airlines.reactive.app.shops.service.AirportControlService;


@RestController
@RequestMapping("/api")
public class AirportControlResource {

	@Autowired
	private AirportControlService airportControlService ;
	
	@PostMapping("/airport-control")
	public Mono<AirportControl> saveAirportControl (@RequestBody AirportControl airportControl) {
		return airportControlService.save(airportControl);		
	}
	
	@GetMapping("/airport-control")
	public Flux<AirportControl> findAll(){
		return airportControlService.findAll();
	}
	
	@GetMapping("/airport-control/{id}")
	public Mono<AirportControl> findOne(@PathVariable("id") String id){
		return airportControlService.findOne(id);
	}
	
	@PutMapping("/airport-control")
	public Mono<AirportControl> update(@RequestBody AirportControl airportControl){
		
		return airportControlService.save(airportControl);
	}
	
	@PutMapping("/airport-control/boarding-date")
	public Mono<AirportControl> updateSecurityGate(@RequestBody AirportControl airportControl){
		return airportControlService.saveBoardingDate(airportControl);
	}
	
	@DeleteMapping("/airport-control/{id}")
	public Mono<ResponseEntity<Void>>  delete(@PathVariable("id") String id){
		return airportControlService.delete(id);
	}
	
}
