package uv.airlines.reactive.app.shops.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import uv.airlines.reactive.app.shops.domain.Shops;

public interface ShopsRepository  extends ReactiveMongoRepository<Shops, String>{
	
	String nativeQuery = "db.airportControl.aggregate([ " + 
			"  { '$match': { " + 
			"    '$and': [ " + 
			"      { '$expr': { '$eq': [{ '$year': '$boardingDate' }, 2019] }}, " + 
			"      { 'airportDestiny': 'MAD'} " + 
			"    ] " + 
			"  }}, " + 
			"  { '$group': { " + 
			"    '_id': null, " + 
			"    'average': { " + 
			"      '$avg': { '$subtract': ['$boardingDate', '$securityGateDate'] }" + 
			"    } " + 
			"  }} " + 
			"]);"; // ORDER BY

		//Query query = em.createNativeQuery(nativeQuery, Shops.class);
}
