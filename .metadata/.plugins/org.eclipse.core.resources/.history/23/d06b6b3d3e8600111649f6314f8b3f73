package edu.artAtGVSU;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.android.maps.GeoPoint;

public class ParseArtWorkXML {
	static ArrayList<ArtWork> identifierSearchedArt;
	static ArrayList<String> artistNameSearchedArt;
	static Tour artWorkToTour;
	
	/*
	 * Make Connection with URL parameter (URL string can be concatenated with conditions when
	 * passed into the method) returns the inputStream from the response.   
	 */
	private static InputStream makeConnection(String url){
		URL urlSend = null;
		InputStream in = null;
		try{
			urlSend = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlSend.openConnection();
			in = connection.getInputStream();
		}catch(MalformedURLException e){
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	
	/*
	 * Sets up the point for the geo Location by parsing the XML String and putting the lat and lon
	 * int values into the GeoPoint object. Returns the GeoPoint created.
	 */
	private static GeoPoint createPoint(String geoLocation){
		String loc = geoLocation;
		int startTrim = loc.indexOf("[") + 1;
		String gLoc = loc.substring(startTrim);
		String lat = gLoc.substring(0 ,gLoc.indexOf(","));
		String lon = gLoc.substring(gLoc.indexOf(",") + 1, gLoc.indexOf("]"));
		double latitude = Double.valueOf(lat.trim()).doubleValue();
		double longitude = Double.valueOf(lon.trim()).doubleValue();

		int iLat = (int) (latitude * 1E6);
		int iLon = (int) (longitude * 1E6);
		GeoPoint p = new GeoPoint(iLat, iLon);
		return p;
	}
	
	/*
	 * Makes a connection with the URL that requests the artwork icon for the tours gallery.
	 * Then parses the XML and adds the icon image to the artwork object and returns the piece of artwork
	 * tourArtPos set to -1 if art piece is not being called for the tour
	 */
	public static ArtWork artIconRequest(String aID, int tourArtPos){
		String url = "http://gvsuartgallery.org/service.php/iteminfo/ItemInfo/rest?method=get&type=ca_objects&item_ids[0]=%d&bundles[0]=ca_objects.object_id&bundles[1]=ca_objects.access&bundles[2]=ca_object_representations.media.icon&options[ca_object_representations.media.icon][returnURL]=1";
		url = url.replace("%d", aID);
		InputStream in = makeConnection(url);
		ArtWork artPiece = null;
		artWorkToTour = getTour();	
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(in);	
			
			Element docElement = doc.getDocumentElement();
			Node getNode = docElement.getFirstChild();
			Element getE = (Element) getNode.getFirstChild();
			NodeList artIconInfo = getE.getChildNodes();
			
			String access = artIconInfo.item(1).getTextContent();
			String iconURL = artIconInfo.item(2).getTextContent();
			
			if(tourArtPos != -1 && Integer.parseInt(access) == 1){
				artPiece = artWorkToTour.artPieces.get(tourArtPos);
				artPiece.setIconURL(iconURL);
			}
			
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return artPiece;
	}
	
	/*
	 * Get information for the art piece by the art ID
	 * Concatenates the URL call with the art ID and makes the web call and parses the 
	 * response XML adding the information to the artWork object
	 */
	public static ArtWork artWorkRequestID(String aID){
		String url = "http://gvsuartgallery.org/service.php/iteminfo/ItemInfo/rest?method=get&type=ca_objects&item_ids[0]=%d&bundles[1]=ca_objects.object_id&bundles[2]=ca_object_labels.name&bundles[3]=ca_objects.work_description&bundles[4]=ca_objects.idno&bundles[5]=ca_objects.work_date&bundles[6]=ca_objects.historical_context&bundles[7]=ca_object_representations.media.medium&bundles[8]=ca_storage_locations.preferred_labels.name&bundles[9]=ca_entities.preferred_labels.displayname&bundles[10]=ca_objects.work_medium&bundles[11]=ca_object_representations.media.large&bundles[12]=ca_entities.entity_id&bundles[13]=ca_storage_locations.georeference&bundles[14]=storage_location_notes&bundles[15]=ca_objects.access&options[ca_object_representations.media.medium][returnURL]=1&options[ca_object_representations.media.large][returnURL]=1";
		url = url.replace("%d", aID);
		InputStream in = makeConnection(url);
		ArtWork artPiece = null;
		artWorkToTour = getTour();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(in);
			
			Element docElement = doc.getDocumentElement();
			Node getNode = docElement.getFirstChild();
			Element getE = (Element) getNode.getFirstChild();
			NodeList artDetails = getE.getChildNodes();
			
			String artName = null;
			String description = null;
			String idNo = null;
			String date = null;
			String histContext = null;
			String mediumImage = null;
			String largeImage = null;
			String locName = null;
			String artistName = null;
			String medium = null;
			String locNotes = null;
			GeoPoint geoP = null;
			String access = null;
			
			artName = artDetails.item(1).getTextContent();
			description = artDetails.item(2).getTextContent();
			idNo = artDetails.item(3).getTextContent();
			date = artDetails.item(4).getTextContent();
			histContext = artDetails.item(5).getTextContent();
			mediumImage = artDetails.item(6).getTextContent();
			locName = artDetails.item(7).getTextContent();
			artistName = artDetails.item(8).getTextContent();
			medium = artDetails.item(9).getTextContent();
			largeImage = artDetails.item(10).getTextContent();
			geoP = createPoint(artDetails.item(12).getTextContent());
			locNotes = artDetails.item(13).getTextContent();
			access = artDetails.item(14).getTextContent();
			
			if(Integer.parseInt(access) == 1){
					artPiece = new ArtWork(aID, artistName, artName, description, idNo, date, histContext, mediumImage, largeImage, locName, medium, geoP);
			}
		
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return artPiece;
	}
	
	/*
	 * Search based on the idNo
	 * Concatenates URL call with the idNo of the art work and makes web call. Gets the XML from 
	 * the call and parses the correct information into a list of artworks.
	 */
	public static ArrayList<ArtWork> artWorkRequestIdentifier(String identifier){
		identifierSearchedArt = new ArrayList<ArtWork>();
		String url = "http://gvsuartgallery.org/service.php/search/Search/rest?method=queryRest&type=ca_objects&query=idno:%@*&additional_bundles[work_description]&additional_bundles[access]&additional_bundles[ca_object_representations.media.icon][returnURL]=1";
		url = url.replace("%@", identifier);
		InputStream in = makeConnection(url);
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(in);
			
			Element docElement = doc.getDocumentElement();
			NodeList artWorkObjects = docElement.getElementsByTagName("ca_objects");
			
			for(int i = 0; i < artWorkObjects.getLength(); i++){
				Element artPiece = (Element) artWorkObjects.item(i);
				NodeList artDetails = artPiece.getChildNodes();
				String artID = artPiece.getAttribute("object_id");
				String artName = artDetails.item(0).getTextContent();
				String artIdentifier = artDetails.item(1).getTextContent();
				String artDescription = artDetails.item(2).getTextContent();
				String access = artDetails.item(3).getTextContent();
				String artImageURL = artDetails.item(4).getTextContent();
				//Only add artwork if access is allowed
				if(Integer.parseInt(access) == 1){
					ArtWork a = new ArtWork(artName, artImageURL, artDescription, artIdentifier, artID);
					identifierSearchedArt.add(a);
				}
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return identifierSearchedArt;
	}
	
	/*
	 * 
	 */
	public static ArrayList<String> artWorkRequestArtistName(String artistName){
		artistNameSearchedArt = new ArrayList<String>();
		String url = "http://gvsuartgallery.org/service.php/search/Search/rest?method=queryRest&type=ca_entities&query=ca_entity_labels.displayname:%@*&additional_bundles=";
		url = url.replace("%@", artistName);
		InputStream in = makeConnection(url);
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(in);
			
			Element docElement = doc.getDocumentElement();
			NodeList artistNames = docElement.getElementsByTagName("ca_entities");
			
			for(int i = 0; i < artistNames.getLength(); i++){
				Element artist = (Element) artistNames.item(i);
				NodeList name = artist.getChildNodes();
				String aName = name.item(0).getTextContent();
				artistNameSearchedArt.add(aName);
			}
			
		}catch(Exception e){
			
		}
		
		return artistNameSearchedArt;
	}

	public static Tour getTour() {
		return artWorkToTour;
	}

	public static void setTour(Tour artWorkToTour) {
		ParseArtWorkXML.artWorkToTour = artWorkToTour;
	}

}
