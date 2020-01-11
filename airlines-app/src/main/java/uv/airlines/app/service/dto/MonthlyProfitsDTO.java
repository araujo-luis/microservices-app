package uv.airlines.app.service.dto;

public class MonthlyProfitsDTO {
	
	private int year;
	private int  month;
	private double profits;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public double getProfits() {
		return profits;
	}
	public void setProfits(double profits) {
		this.profits = profits;
	}
	public MonthlyProfitsDTO(int year, int month, double profits) {
		super();
		this.year = year;
		this.month = month;
		this.profits = profits;
	}
	public MonthlyProfitsDTO() {
		super();
	}
	

}
