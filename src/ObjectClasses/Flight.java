package ObjectClasses;
import java.util.ArrayList;

public class Flight {
	
	private int id;
	private int price;
	private String departure;
	private String arrival;
	private Airport departureAirport;
	private Airport arrivalAirport;
	private Aircraft aircraft;
	private Airline airline;
	
	public Flight(int id, int price, String departure, String arrival, Airport departureAirport, Airport arrivalAirport, Aircraft aircraft, Airline airline) {
		super();
		this.id = id;
		this.price = price;
		this.departure = departure;
		this.arrival = arrival;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.aircraft = aircraft;
		this.airline = airline;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(Airport arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	
}
