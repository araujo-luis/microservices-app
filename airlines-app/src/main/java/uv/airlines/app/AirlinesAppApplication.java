package uv.airlines.app;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import uv.airlines.app.domain.Airports;
import uv.airlines.app.service.AgenciesService;
import uv.airlines.app.service.AircraftsService;
import uv.airlines.app.service.AirportsService;
import uv.airlines.app.service.FlightScheduleService;
import uv.airlines.app.service.PassengerService;
import uv.airlines.app.service.ReservationPassengersService;
import uv.airlines.app.service.ReservationsService;
import uv.airlines.app.service.dto.AgenciesDTO;
import uv.airlines.app.service.dto.AircraftsDTO;
import uv.airlines.app.service.dto.AirportsDTO;
import uv.airlines.app.service.dto.FlightScheduleDTO;
import uv.airlines.app.service.dto.PassengerDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;
import uv.airlines.app.service.dto.ReservationsDTO;

@SpringBootApplication
public class AirlinesAppApplication implements CommandLineRunner {

	@Autowired
	private AirportsService airportsService;
	@Autowired
	private AircraftsService aircraftsService;

	@Autowired
	private FlightScheduleService flightScheduleService;

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private AgenciesService agenciesService;

	@Autowired
	private ReservationsService reservationsService;
	
	@Autowired
	private ReservationPassengersService reservationPassengerService;

	public static void main(String[] args) {
		SpringApplication.run(AirlinesAppApplication.class, args);
	}
	
	private final CsvMapper mapper = new CsvMapper();
    public  <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(clazz).withHeader();
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
    
    public <T> List<T> loadObjectList(Class<T> type, String fileName) {
    	try {
    		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
    		CsvMapper mapper = new CsvMapper();
    		File file = new ClassPathResource(fileName).getFile();
    		MappingIterator<T> readValues = 
    				mapper.reader(type).with(bootstrapSchema).readValues(file);
    		
    		return readValues.readAll();
    		} catch (Exception e) {
    			System.out.println(e.toString());
    			return null;
    		}
    	}
   
	@Override
	public void run(String... args) throws Exception {
		
		
		//airportsService.saveAll(loadObjectList(AirportsDTO.class, "airports.csv"));

		 //generateAircraft(20);
		 //generateScheduleFlight(20);
		 //generatePassenger(20);
		 //generateAgencies(10);
		 //generateReservation(10);
		 //generateReservationPassenger(100);

	}

	

	public void generateAirport(int quantity) {
		Faker faker = new Faker();


		for (int i = 0; i < 20; i++) {

			AirportsDTO airportsDTO = new AirportsDTO();
			airportsDTO.setContinent(faker.address().countryCode());
			airportsDTO.setCoordinates(faker.address().longitude() + ", " + faker.address().latitude());
			airportsDTO.setElevation(faker.number().randomDigit());
			airportsDTO.setGpscode(faker.bothify("??##"));
			airportsDTO.setCountry(faker.address().countryCode());
			airportsDTO.setRegion(faker.address().zipCode());
			airportsDTO.setLocalcode(faker.bothify("??##"));
			airportsDTO.setMunipality(faker.address().cityName());
			airportsDTO.setName(faker.beer().name());
			airportsDTO.setType(faker.options().option("plane", "heli"));
			airportsDTO.setId(faker.bothify("??##"));
			airportsService.save(airportsDTO);

		}

	}

	public void generateAircraft(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			AircraftsDTO aircrafts = new AircraftsDTO();
			aircrafts.setNumber(faker.bothify("##??"));
			aircrafts.setCapacity(Integer.parseInt(faker.options().option("60", "80", "100")));
			aircraftsService.save(aircrafts);
		}

	}

	public void generateScheduleFlight(int quantity) {

		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			Integer randomNumber = (int) Math.round(Math.random() * ( 90 - 1 ));
			Integer randomHour = (int) Math.round(Math.random() * ( 10 - 1 ));
			FlightScheduleDTO flightScheduleDTO = new FlightScheduleDTO();
			flightScheduleDTO.setAircraftId(Long.valueOf(faker.number().numberBetween(2, 23)));
			flightScheduleDTO
					.setAirportArrivalId(airportsService.getRandomAirport());
			flightScheduleDTO
					.setAirportTakeoffId(airportsService.getRandomAirport());
			flightScheduleDTO.setFlightRate(Float.valueOf(faker.commerce().price()));
			Date date = faker.date().future(randomNumber, TimeUnit.DAYS);
			flightScheduleDTO.setTakeoffDate(date);
			Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(Calendar.HOUR_OF_DAY, randomHour);
			flightScheduleDTO.setArrivalDate(calendar.getTime());
			flightScheduleService.save(flightScheduleDTO);
		}
	}

	public void generatePassenger(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			PassengerDTO passengerDTO = new PassengerDTO();
			passengerDTO.setId(faker.idNumber().ssnValid());
			passengerDTO.setName(faker.name().firstName());
			passengerDTO.setLastname(faker.name().lastName());
			passengerService.save(passengerDTO);
		}
	}

	public void generateAgencies(int quantity) {
		Faker faker = new Faker();

		for (int i = 0; i < quantity; i++) {
			AgenciesDTO agenciesDTO = new AgenciesDTO();
			agenciesDTO.setName(faker.company().name());
			agenciesService.save(agenciesDTO);
		}

	}

	public void generateReservation(int quantity) {
		Faker faker = new Faker();
		for (int i = 0; i < quantity; i++) {
			ReservationsDTO reservationsDTO = new ReservationsDTO();
			Integer randomNumber = (int) Math.round(Math.random() * ( 90 - 1 ));
			reservationsDTO.setReservationDate(faker.date().past(randomNumber, TimeUnit.DAYS));
			reservationsDTO.setAgenciesId(agenciesService.getRandomAngency());
			reservationsDTO.setFlightScheduleId(flightScheduleService.getRandomFlightSchedule());
			reservationsDTO.setStatus(true);
			reservationsService.save(reservationsDTO);
		}
		}
	
	private void generateReservationPassenger(int quantity) {
		Faker faker = new Faker();
		for (int i = 0; i < quantity; i++) {
			Integer luggagesNumber = (int) Math.round(Math.random() * ( 2 - 1 ));
			Double priceRandom =(double) Math.round(Math.random() * ( 300 - 33 ));
			ReservationPassengersDTO  reservationPassengerDTO = new ReservationPassengersDTO();
			reservationPassengerDTO.setLuggagesQuanity(luggagesNumber);
			reservationPassengerDTO.setPaid(true);
			reservationPassengerDTO.setPassengerId(passengerService.getRandomPassenger());
			reservationPassengerDTO.setFlightRate(priceRandom);
			reservationPassengerDTO.setPriority(faker.bool().bool());
			reservationPassengerDTO.setSeatNumber(null);
			reservationPassengerDTO.setReservation(reservationsService.getRandomReservation());
			reservationPassengerService.save(reservationPassengerDTO);
		}
	
	
	}
	}


