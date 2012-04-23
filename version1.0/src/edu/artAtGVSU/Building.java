package edu.artAtGVSU;

import java.util.ArrayList;

public class Building {
	String buildingID;
	String buildingName;
	ArrayList<Floor> floors = new ArrayList<Floor>();
	
	public Building(String bName, String bID){
		buildingName = bName;
		buildingID = bID;
		floors = null;
	}

	public String getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(String buildingID) {
		this.buildingID = buildingID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public ArrayList<Floor> getFloors() {
		return floors;
	}

	public void setFloors(ArrayList<Floor> floors) {
		this.floors = floors;
	}	
}
