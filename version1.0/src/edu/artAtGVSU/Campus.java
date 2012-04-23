package edu.artAtGVSU;

import java.util.ArrayList;

public class Campus {
	String campusName;
	String campusID;
	ArrayList<Building> buildings;
	
	public Campus(String cName, String cID){
		campusName = cName;
		campusID = cID;
		buildings = null;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public void setCampusID(String campusID) {
		this.campusID = campusID;
	}

	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

	public String getCampusName() {
		return campusName;
	}

	public String getCampusID() {
		return campusID;
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}
}
