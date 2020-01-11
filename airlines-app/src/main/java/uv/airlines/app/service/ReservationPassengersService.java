package uv.airlines.app.service;

import uv.airlines.app.domain.ReservationPassengers;
import uv.airlines.app.service.dto.BoardingPassDTO;
import uv.airlines.app.service.dto.MonthlyProfitsDTO;
import uv.airlines.app.service.dto.PassengersPriorityDTO;
import uv.airlines.app.service.dto.ProfitFlightsDTO;
import uv.airlines.app.service.dto.ReservationPassengersDTO;

import java.util.List;
import java.util.Optional;

public interface ReservationPassengersService {

    ReservationPassengersDTO save(ReservationPassengersDTO reservationPassengersDTO);

    List<ReservationPassengersDTO> findAll();
    
    List<PassengersPriorityDTO> findAllPassengersWithPriority();
    
    List<MonthlyProfitsDTO> getMonthlyProfits();
    
    List<ProfitFlightsDTO> getTop10ProfitsFlights();
    
    Optional<ReservationPassengersDTO> findOne(Long id);

    void delete(Long id);

	ReservationPassengers changePassenger(Long reservationId, String passengerOriginal, String passengerChanged);

	ReservationPassengers updateBoardingPasses(Long reservationId, String passengerId);
	
	List<ReservationPassengersDTO> getBoardingPasses(Long reservationId, String seatNumber);

	List<ReservationPassengersDTO> getPassengersAbleToChange(Long reservationId);
	
	BoardingPassDTO findBoardingPassData(Long id);
}
