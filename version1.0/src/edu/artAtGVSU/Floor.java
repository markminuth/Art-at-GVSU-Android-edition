package edu.artAtGVSU;

public class Floor {
	String floorID;
	String floorName;
	
	public Floor(String fID, String fName){
		floorID = fID;
		floorName = fName;
	}

	public String getFloorID() {
		return floorID;
	}

	public void setFloorID(String floorID) {
		this.floorID = floorID;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
}
