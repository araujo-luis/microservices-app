package uv.airlines.app.service.dto;

public class ProfitFlightsDTO {

	private String munipality;
	
	private String continent;
	
	private String airport_arrival;
	
	private double payment;
	
	public ProfitFlightsDTO(String munipality, String continent, String airport_arrival, double payment) {
		super();
		this.munipality = munipality;
		this.continent = continent;
		this.airport_arrival = airport_arrival;
		this.payment = payment;
	}
	
	public ProfitFlightsDTO() {
		super();
	}
	
	public String getMunipality() {
		return munipality;
	}
	
	public void setMunipality(String munipality) {
		this.munipality = munipality;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public void setContinent(String continent) {
		this.continent = continent;
	}
	
	public String getAirport_arrival() {
		return airport_arrival;
	}
	
	public void setAirport_arrival(String airport_arrival) {
		this.airport_arrival = airport_arrival;
	}
	
	public double getPayment() {
		return payment;
	}
	
	public void setPayment(double payment) {
		this.payment = payment;
	}
	
	
}

