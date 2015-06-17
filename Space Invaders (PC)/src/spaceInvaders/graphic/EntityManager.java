package spaceInvaders.graphic;


import java.util.ArrayList;


public class EntityManager {

	//Datenfelder
	
	private static ArrayList<ShipEntity> entityShipList;	//Liste welche alle Spieler-Schiffe enthält, welche sich garde auf dem Spielfeld befinden
	private static ArrayList<ShootEntity> entityShootList;	//Liste welche alle Schüsse enthält, welche sich garde auf dem Spielfeld befinden
	private static ArrayList<BarricadeEntity> entityBariakardenList;	//Liste welche alle Barrikaden enthält, welche sich garde auf dem Spielfeld befinden
	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public EntityManager() {
		//Datenfelder Initialisieren
		if (entityShipList == null) {	//nur wenn noch nicht Initialisiert
			entityShipList = new ArrayList<ShipEntity>();	//Liste welche alle Spieler-Schiffe enthält, welche sich garde auf dem Spielfeld befinden
			entityShootList = new ArrayList<ShootEntity>();	//Liste welche alle Schüsse enthält, welche sich garde auf dem Spielfeld befinden
			entityBariakardenList = new ArrayList<BarricadeEntity>();	//Liste welche alle Barrikaden enthält, welche sich garde auf dem Spielfeld befinden
		}
		
	}	
	
	
	
	//Getter und Setter
	
	public ArrayList<ShipEntity> getEntityShipList() {
		return entityShipList;
	}



	public ArrayList<ShootEntity> getEntityShootList() {
		return entityShootList;
	}



	public ArrayList<BarricadeEntity> getEntityBariakardenList() {
		return entityBariakardenList;
	}
	
	//Methoden
	
	public static void resetLists() {
		entityShipList = null;
		entityShootList = null;
		entityBariakardenList = null;
	}
}
