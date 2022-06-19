package ObjectClasses;

public class Seat {

	private int id;
	private String type;
	private boolean isAvailable;
	private int aircraftid;
	
	public Seat(int id, String type, boolean isAvailable,int aircraftid) {
		super();
		this.id = id;
		this.type = type;
		this.isAvailable = isAvailable;
		this.aircraftid=aircraftid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getAirid() {
		return aircraftid;
	}

	public void setAirid(int aircraftid) {
		this.aircraftid = aircraftid;
	}
	
	
}
