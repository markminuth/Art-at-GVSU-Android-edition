package edu.artAtGVSU;

public class ArtWorkObjectSetUp{
	
	private static ArtWork artWorkToOpen;
	
	public ArtWorkObjectSetUp(ArtWork a) {
		artWorkToOpen = a;
	}
	
	public static ArtWork getArtWork(){
		return artWorkToOpen;
	}
}
