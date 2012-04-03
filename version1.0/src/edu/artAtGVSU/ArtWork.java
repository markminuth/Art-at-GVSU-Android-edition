package edu.artAtGVSU;

import com.google.android.maps.GeoPoint;

/*
 * Class for a piece of artWork and its detials
 */
public class ArtWork {
	
	String artID;
	String artistID;
	String artistName;
	String artTitle;
	String description;
	String idno;
	String workDate;
	String historicalContext;
	String imageURL;
	String iconImageURL;
	String locName;
	String medium;
	GeoPoint geoLoc;
	String stopID;
	
	public ArtWork(){
		artID = null;
		artistID = null;
		artistName = null;
		artTitle = null;
		description = null;
		idno = null;
		workDate = null;
		historicalContext= null;
		imageURL = null;
		iconImageURL = null;
		locName = null;
		medium = null;
		geoLoc = null;
		stopID = null;
	}
	
	public ArtWork(String aName, String aImageURL){
		artID = null;
		artistID = null;
		artistName = null;
		artTitle = aName;
		description = null;
		idno = null;
		workDate = null;
		historicalContext= null;
		imageURL = null;
		iconImageURL = aImageURL;
		locName = null;
		medium = null;
		geoLoc = null;
		stopID = null;
	}
	/*
	 * Constructor used for a piece of art shown by the tours menu
	 */
	public ArtWork(GeoPoint aGeoLoc, String aName, String aID, String aStopID){
		artID = aID;
		geoLoc = aGeoLoc;
		artTitle = aName;
		stopID = aStopID;
		iconImageURL = null;
		imageURL = null;
		artistID = null;
		artistName = null;
		description = null;
		idno = null;
		workDate = null;
		historicalContext= null;
		locName = null;
		medium = null;
	}
	
	/*
	 * Constructor used for the image URL of a piece of art in the tours menu
	 * TODO may have to remove some parameters depending on what the XML returns
	 */
	public ArtWork(String aName, String aID, String aStopID, String aIconImageURL){
		artID = aID;
		artTitle = aName;
		stopID = aStopID;
		iconImageURL = aIconImageURL;
		imageURL = null;
		artistID = null;
		artistName = null;
		description = null;
		idno = null;
		workDate = null;
		historicalContext= null;
		locName = null;
		geoLoc = null;
		medium = null;
	}
	
	
	public ArtWork(String aID, String aArtistID, String aArtistName, String aTitle, String aDescription, String aIdNo, String aWorkDate, String aHistContext, String aImageURL, String aLocName, String aMedium, GeoPoint aGeoLoc, String aStopID){
		artID = aID;
		artistID = aArtistID;
		artistName = aArtistName;
		artTitle = aTitle;
		description = aDescription;
		idno = aIdNo;
		workDate = aWorkDate;
		historicalContext= aHistContext;
		imageURL = aImageURL;
		locName = aLocName;
		medium = aMedium;
		geoLoc = aGeoLoc;
		stopID = aStopID;
	}

	public String getArtID() {
		return artID;
	}

	public void setArtID(String artID) {
		this.artID = artID;
	}

	public String getArtistID() {
		return artistID;
	}

	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getHistoricalContext() {
		return historicalContext;
	}

	public void setHistoricalContext(String historicalContext) {
		this.historicalContext = historicalContext;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public GeoPoint getGeoLoc() {
		return geoLoc;
	}

	public void setGeoLoc(GeoPoint geoLoc) {
		this.geoLoc = geoLoc;
	}

	public String getStopID() {
		return stopID;
	}

	public void setStopID(String stopID) {
		this.stopID = stopID;
	}
}
