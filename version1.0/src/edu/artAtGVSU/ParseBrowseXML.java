package edu.artAtGVSU;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseBrowseXML {

	static ArrayList<Campus> campuses = new ArrayList<Campus>();
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
	
	public static ArrayList<Campus> campusNamesDataRequest(){
		//Add tour number to the URL
		String url = "http://gvsuartgallery.org/service.php/iteminfo/ItemInfo/rest?method=get&type=ca_storage_locations&item_ids[0]=1&bundles[0]=ca_storage_locations.children.location_id&options[ca_storage_locations.children.location_id][returnAsArray]=1&bundles[1]=ca_storage_locations.children.preferred_labels.name&options[ca_storage_locations.children.preferred_labels.name][returnAsArray]=1";
		InputStream in = makeConnection(url);
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(in);
				
			Element docElement = doc.getDocumentElement();
			
			String key = "";
			String name = "";
			NodeList campusKeys = docElement.getElementsByTagName("ca_storage_locations.children.location_id");
			Element cKeys = (Element) campusKeys.item(0);
			NodeList cKeysList = cKeys.getChildNodes();
			int keys = cKeysList.getLength();	
			
			NodeList campusNames = docElement.getElementsByTagName("ca_storage_locations.children.preferred_labels.name");
			Element cNames = (Element) campusNames.item(0);
			NodeList cNamesList = cNames.getChildNodes();
			int names = cNamesList.getLength();	
			
			//minus 3 for on loan, print and drawing cabinet and gvsu storage
			for(int i = 0; i < (keys - 3); i++){
				key = cKeysList.item(i).getTextContent();
				name = cNamesList.item(i).getTextContent();
				Campus c = new Campus(name, key);
				campuses.add(c);
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
		
		return campuses;
	}
	
	public static void buildingNamesDataRequest(String id){
		String url = "http://gvsuartgallery.org/service.php/iteminfo/ItemInfo/rest?method=get&type=ca_storage_locations&item_ids[0]=%d&bundles[0]=ca_storage_locations.children.location_id&options[ca_storage_locations.children.location_id][returnAsArray]=1&bundles[1]=ca_storage_locations.children.preferred_labels.name&options[ca_storage_locations.children.preferred_labels.name][returnAsArray]=1";
		url = url.replace("%d", id);
		InputStream in = makeConnection(url);
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document doc = db.parse(in);
			
Element docElement = doc.getDocumentElement();
			
			String key = "";
			String name = "";
			NodeList campusKeys = docElement.getElementsByTagName("ca_storage_locations.children.location_id");
			Element cKeys = (Element) campusKeys.item(0);
			NodeList cKeysList = cKeys.getChildNodes();
			int keys = cKeysList.getLength();	
			
			NodeList campusNames = docElement.getElementsByTagName("ca_storage_locations.children.preferred_labels.name");
			Element cNames = (Element) campusNames.item(0);
			NodeList cNamesList = cNames.getChildNodes();
			int names = cNamesList.getLength();	
			
			for(int i = 0; i < keys; i++){
				key = cKeysList.item(i).getTextContent();
				name = cNamesList.item(i).getTextContent();
				Campus c = new Campus(name, key);
				campuses.add(c);
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
	}
}
