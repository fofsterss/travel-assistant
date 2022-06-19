package ObjectClasses;
import java.util.ArrayList;

public class Aircraft {

	private int aircraftid;
	private String manufacturer;
	private String aircraftmodel;
	private int totalseats;
	private int emptyseats;
	private ArrayList<Seat> seats;
	
	public Aircraft(int aircraftid, String manufacturer, String aircraftmodel, int totalseats, int emptyseats, ArrayList<Seat> seats) {
		super();
		this.aircraftid = aircraftid;
		this.manufacturer = manufacturer;
		this.aircraftmodel = aircraftmodel;
		this.totalseats = totalseats;
		this.emptyseats = emptyseats;
		this.seats = seats;
	}

	public int getAircraftid() {
		return aircraftid;
	}

	public void setAircraftid(int aircraftid) {
		this.aircraftid = aircraftid;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getAircraftmodel() {
		return aircraftmodel;
	}

	public void setAircraftmodel(String aircraftmodel) {
		this.aircraftmodel = aircraftmodel;
	}

	public int getTotalseats() {
		return totalseats;
	}

	public void setTotalseats(int totalseats) {
		this.totalseats = totalseats;
	}

	public int getEmptyseats() {
		return emptyseats;
	}

	public void setEmptyseats(int emptyseats) {
		this.emptyseats = emptyseats;
	}
	
	public ArrayList<Seat> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}
}
