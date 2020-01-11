package uv.airlines.reactive.app.shops.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shops")
public class Shops {
	@Id
	private String id;
	
	private String customerId;
	
	private LocalDateTime shopDate;

	private String airportDestiny;
	
	@Override
	public String toString() {
		return "Shops [id=" + id + ", customerId=" + customerId + ", shopDate=" + shopDate + ", airportDestiny="
				+ airportDestiny + ", shopId=" + shopId + ", totalSpent=" + totalSpent + ", boardingPassId="
				+ boardingPassId + "]";
	}

	private String shopId;
	
	private float totalSpent;
	
	private Long boardingPassId;
	
	public Long getBoardingPassId() {
		return boardingPassId;
	}

	public void setBoardingPassId(Long boardingPassId) {
		this.boardingPassId = boardingPassId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getShopDate() {
		return shopDate;
	}

	public void setShopDate(LocalDateTime shopDate) {
		this.shopDate = shopDate;
	}

	public String getAirportDestiny() {
		return airportDestiny;
	}

	public void setAirportDestiny(String airportDestiny) {
		this.airportDestiny = airportDestiny;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public float getTotalSpent() {
		return totalSpent;
	}

	public void setTotalSpent(float totalSpent) {
		this.totalSpent = totalSpent;
	}

	

	public Shops(String id, String customerId, LocalDateTime shopDate, String airportDestiny, String shopId,
			float totalSpent, Long boardingPassId) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.shopDate = shopDate;
		this.airportDestiny = airportDestiny;
		this.shopId = shopId;
		this.totalSpent = totalSpent;
		this.boardingPassId = boardingPassId;
	}

	public Shops() {
		super();
	}
	
}
