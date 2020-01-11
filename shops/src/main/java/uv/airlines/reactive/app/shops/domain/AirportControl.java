package uv.airlines.reactive.app.shops.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="airportControl")
public class AirportControl {
	@Id
	private String id;
	
	private String identification;
	
	private LocalDateTime securityGateDate;
	
	private LocalDateTime boardingDate;
	
	private String gate;
	
	private String airportDestiny;
	
	private Long boardingPassId; 
	
	public LocalDateTime getSecurityGateDate() {
		return securityGateDate;
	}

	public void setSecurityGateDate(LocalDateTime securityGateDate) {
		this.securityGateDate = securityGateDate;
	}

	public Long getBoardingPassId() {
		return boardingPassId;
	}

	public void setBoardingPassId(Long boardingPassId) {
		this.boardingPassId = boardingPassId;
	}

	public String getIdentification() {
		return identification;
	}
	
	public void setIdentification(String identification) {
		this.identification = identification;
	}
	
	public LocalDateTime getSecurityDateDate() {
		return securityGateDate;
	}
	
	public void setSecurityDateDate(LocalDateTime securityDateDate) {
		this.securityGateDate = securityDateDate;
	}
	
	public LocalDateTime getBoardingDate() {
		return boardingDate;
	}
	
	public void setBoardingDate(LocalDateTime boardingDate) {
		this.boardingDate = boardingDate;
	}
	
	public String getGate() {
		return gate;
	}
	
	public void setGate(String gate) {
		this.gate = gate;
	}
	
	public String getAirportDestiny() {
		return airportDestiny;
	}
	
	public void setAirportDestiny(String airportDestiny) {
		this.airportDestiny = airportDestiny;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AirportControl() {
		super();
	}

	public AirportControl(String id, String identification, LocalDateTime securityGateDate, LocalDateTime boardingDate,
			String gate, String airportDestiny, Long boardingPassId) {
		super();
		this.id = id;
		this.identification = identification;
		this.securityGateDate = securityGateDate;
		this.boardingDate = boardingDate;
		this.gate = gate;
		this.airportDestiny = airportDestiny;
		this.boardingPassId = boardingPassId;
	}	
	
}
