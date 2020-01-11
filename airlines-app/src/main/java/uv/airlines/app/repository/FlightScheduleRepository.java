package uv.airlines.app.repository;

import uv.airlines.app.domain.FlightSchedule;
import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.dto.FlightScheduleDTO;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the FlightSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Long> {
	
	//public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThanAndAircraft_seatsTakenLessThanAircraft_capacity(
			//String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);
	
	//Q1 and Q2
	@Query("SELECT FS FROM FlightSchedule FS " + 
			"INNER JOIN FS.airportTakeoff A " +  
			"INNER JOIN FS.aircraft AIR " +  
			"WHERE FS.takeoffDate >= :takeoffDate and  FS.takeoffDate < :takeoffDate2 AND FS.airportTakeoff.id=:airportTakeoff AND FS.airportArrival.id=:airportArrival " + 
			"AND (AIR.capacity - AIR.seatsTaken) >= :passengers " +
			"ORDER BY FS.flightRate")
	public List<FlightSchedule> findFlightsAvailable( @Param("airportTakeoff") String airportTakeoff, 
														@Param("airportArrival") String airportArrival, 
														@Param("takeoffDate") LocalDateTime takeoffDate,
														@Param("takeoffDate2") LocalDateTime takeoffDate2, 
														@Param("passengers") Integer passengers);
	
	//Q3 Part1
	public List<FlightSchedule> findByArrivalDateGreaterThanAndReservations_agencies_idEquals(LocalDateTime today, Long idAgency);
	
	//Q3 Part2
	public List<FlightSchedule> findByArrivalDateLessThanAndReservations_agencies_idEquals(LocalDateTime today, Long idAgency);
	
		
	//Q5 part1
	public List<FlightSchedule> findByTakeoffDateGreaterThanAndTakeoffDateLessThanAndReservations_statusEquals(LocalDateTime today, LocalDateTime plusSevenDays, Boolean status);
	
	//public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThan(
			//String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);
	//public List<FlightSchedule> findByAirportTakeoff_idAndAirportArrival_idAndTakeoffDateGreaterThanEqualAndTakeoffDateLessThanOrderByFlightRateAsc(
			//String airportTakeoff, String airportArrival, LocalDateTime arrivalDate, LocalDateTime arrivalDate2);

	@Query(value= "SELECT id FROM flight_schedule ORDER BY RAND() LIMIT 1" , nativeQuery = true)
	public Long getRandomFlightSchedule();
	

	
}
