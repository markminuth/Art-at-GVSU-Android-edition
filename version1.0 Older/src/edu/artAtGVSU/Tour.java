package edu.artAtGVSU;

import java.util.ArrayList;

/*
 * Tour class creates a tour object that holds data and image URLs to pieces of art
 */
public class Tour {
	
	String tourID;
	String tourName;
	String imageMainURL;
	String tourAccess;
	ArrayList<ArtWork> artPieces = new ArrayList<ArtWork>();

	public Tour(){
		tourID = null;
		tourName = null;
		imageMainURL = null;
		tourAccess = null;
		artPieces = null;
	}
	
	public Tour(String tID, String tName, String tImageMainURL, String tAccess){
		tourID = tID;
		tourName = tName;
		imageMainURL = tImageMainURL;
		tourAccess = tAccess;
		artPieces = null;
	}
	
	public Tour(String tID, String tName, String tImageMainURL, String tAccess, ArrayList<ArtWork> aPieces){
		tourID = tID;
		tourName = tName;
		imageMainURL = tImageMainURL;
		tourAccess = tAccess;
		artPieces = aPieces;
	}

	public String getTourID() {
		return tourID;
	}

	public void setTourID(String tourID) {
		this.tourID = tourID;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public String getImageMainURL() {
		return imageMainURL;
	}

	public void setImageMainURL(String imageMainURL) {
		this.imageMainURL = imageMainURL;
	}
	
	public String getTourAccess() {
		return tourAccess;
	}

	public void setTourAccess(String tourAccess) {
		this.tourAccess = tourAccess;
	}

	public ArrayList<ArtWork> getArtPieces() {
		return artPieces;
	}

	public void setArtPieces(ArrayList<ArtWork> artPieces) {
		this.artPieces = artPieces;
	}	
}

