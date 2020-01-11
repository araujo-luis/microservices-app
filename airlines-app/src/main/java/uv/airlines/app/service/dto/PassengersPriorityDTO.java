package uv.airlines.app.service.dto;

import java.io.Serializable;

public class PassengersPriorityDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;
	
	private String lastname;
	
	private long times_priority;
	
	public PassengersPriorityDTO(String id, String name, String lastname, long times_priority) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.times_priority = times_priority;
	}
	
	public PassengersPriorityDTO() {
		super();
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public long getTimes_priority() {
		return times_priority;
	}
	
	public void setTimes_priority(long times_priority) {
		this.times_priority = times_priority;
	}
	
	

}
