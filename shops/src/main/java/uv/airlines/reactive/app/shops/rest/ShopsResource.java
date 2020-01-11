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
import uv.airlines.reactive.app.shops.domain.Shops;
import uv.airlines.reactive.app.shops.service.ShopService;

@RestController
@RequestMapping("/api")
public class ShopsResource {
	
	@Autowired
	private ShopService shopsService;
	
	@PostMapping("/shops")
	public Mono<Shops> saveAirportControl (@RequestBody Shops shops) {
		return shopsService.save(shops);		
	}
	
	@PutMapping("/shops")
	public Mono<Shops> updateAirportControl (@RequestBody Shops shops) {
		return shopsService.save(shops);		
	}
	
	@GetMapping("/shops")
	public Flux<Shops> findAll(){
		return shopsService.findAll();
	}
	
	@GetMapping("/shops/{id}")
	public Mono<Shops> findOne(@PathVariable("id") String id){
		return shopsService.findOne(id);
	}
	
	@DeleteMapping("/shops/{id}")
	public Mono<ResponseEntity<Void>>  delete(@PathVariable("id") String id){
		return shopsService.delete(id);
	}

}
