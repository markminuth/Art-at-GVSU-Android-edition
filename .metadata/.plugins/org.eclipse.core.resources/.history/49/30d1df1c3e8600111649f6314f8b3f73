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
	String imageURLMedium;
	String imageURLLarge;
	String iconImageURL;
	String locName;
	String locNotes;
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
		imageURLLarge = null;
		imageURLMedium = null;
		iconImageURL = null;
		locName = null;
		medium = null;
		geoLoc = null;
		stopID = null;
	}
	
	public ArtWork(String aName, String aImageURL, String aDescription, String aIdentifier, String aID){
		artID = aID;
		artTitle = aName;
		description = aDescription;
		idno = aIdentifier;
		iconImageURL = aImageURL;
		workDate = null;
		historicalContext= null;
		imageURLLarge = null;
		imageURLMedium = null;
		artistID = null;
		artistName = null;
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
		imageURLLarge = null;
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
	 * All information needed for individaul art piece outside of a tour
	 */
	public ArtWork(String aID, String aArtistName, String aTitle, String aDescription, String aIdNo, String aWorkDate, String aHistContext, String aImageURLMedium, String aImageURLLarge, String aLocName, String aMedium, GeoPoint aGeoLoc){
		artID = aID;
		artistName = aArtistName;
		artTitle = aTitle;
		description = aDescription;
		idno = aIdNo;
		workDate = aWorkDate;
		historicalContext= aHistContext;
		imageURLMedium = aImageURLMedium;
		imageURLLarge = aImageURLLarge;
		locName = aLocName;
		medium = aMedium;
		geoLoc = aGeoLoc;
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

	public String getImageURLLarge() {
		return imageURLLarge;
	}

	public void setImageURLLarge(String imageURLLarge) {
		this.imageURLLarge = imageURLLarge;
	}
	
	public String getImageURLMedium() {
		return imageURLMedium;
	}

	public void setImageURLMedium(String imageURLMedium) {
		this.imageURLMedium = imageURLMedium;
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
	
	public String getIconURL() {
		return iconImageURL;
	}

	public void setIconURL(String iconURL) {
		this.iconImageURL = iconURL;
	}
}
