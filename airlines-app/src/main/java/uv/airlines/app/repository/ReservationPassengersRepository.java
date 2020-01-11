package uv.airlines.app.repository;

import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.dto.BoardingPassDTO;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for the ReservationPassengers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReservationPassengersRepository extends JpaRepository<ReservationPassengers, Long> {	
	
	//Q4
	public List<ReservationPassengers> findByReservation_idAndSeatNumberEquals(Long reservationId, String seatNumber);
	
	//Q5 Part 1 (Part 1 in FlightScheduleRepository)
	public List<ReservationPassengers> findByReservation_flightSchedule_takeoffDateGreaterThanAndReservation_flightSchedule_takeoffDateLessThanAndReservation_statusEqualsAndReservation_idEqualsAndSeatNumberIsNotNull(LocalDateTime today, LocalDateTime plusSevenDays, Boolean status, Long reservationId);
	
	//Q6
	@Query("SELECT new uv.airlines.app.service.dto.PassengersPriorityDTO(P.id, P.name, P.lastname,  COUNT(P)) "+ 
			"FROM ReservationPassengers RP " + 
			"INNER JOIN RP.passenger P " + 
			"where RP.priority = 1 " + 
			"GROUP BY P.id " + 
			"HAVING COUNT(P) > ?1")
	public List<PassengersPriorityDTO> getPassengerPriority(long priority);

	
	//Q7
	@Query("SELECT new uv.airlines.app.service.dto.ProfitFlightsDTO(A.munipality, A.continent, A.id, SUM(RP.flightRate)) "+ 
			"FROM ReservationPassengers RP " + 
			"INNER JOIN RP.reservation R " + 
			"INNER JOIN R.flightSchedule FS " +  
			"INNER JOIN FS.airportArrival A " +
			"WHERE R.reservationDate BETWEEN :start AND :end " + 
			"GROUP BY FS.airportArrival " +
			"ORDER BY SUM(RP.flightRate) DESC")
	public List<ProfitFlightsDTO> findTop10ProfitsFlights(Pageable pageable, @Param("start") Date startDate, @Param("end") Date  endDate);
	
	//Q8
	@Query("SELECT new uv.airlines.app.service.dto.MonthlyProfitsDTO(YEAR(R.reservationDate), MONTH(R.reservationDate), sum(RP.flightRate)) " + 
			"FROM ReservationPassengers RP " +
			"INNER JOIN RP.reservation R " + 
			"GROUP BY YEAR(R.reservationDate), MONTH(R.reservationDate) " +
			"ORDER BY YEAR(R.reservationDate), MONTH(R.reservationDate) DESC")
	public List<MonthlyProfitsDTO> getMonthlyProfits(Pageable pageable);


	public List<ReservationPassengers> findByReservation_id(Long reservationId);


	public List<ReservationPassengers> findByReservation(Long reservationId);
	
	@Query("SELECT new uv.airlines.app.service.dto.BoardingPassDTO(RP.id, RP.passenger.id, FS.airportArrival.id) " +
			"FROM ReservationPassengers RP " + 
			"INNER JOIN RP.reservation R " +  
			"INNER JOIN R.flightSchedule FS " + 
			"WHERE RP.id = ?1")
	public BoardingPassDTO findBoardingPass(Long id);

}
