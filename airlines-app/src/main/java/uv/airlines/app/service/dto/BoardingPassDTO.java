package uv.airlines.app.service.dto;

public class BoardingPassDTO {
	
	private Long id;
	
	private String passengerId;
	
	private String airportArrivalId;

	public BoardingPassDTO(Long id, String passengerId, String airportArrivalId) {
		super();
		this.id = id;
		this.passengerId = passengerId;
		this.airportArrivalId = airportArrivalId;
	}

	public BoardingPassDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getAirportArrivalId() {
		return airportArrivalId;
	}

	public void setAirportArrivalId(String airportArrivalId) {
		this.airportArrivalId = airportArrivalId;
	}
	
	

}
