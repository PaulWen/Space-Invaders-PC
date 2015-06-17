package spaceInvaders.graphic;


import java.util.ArrayList;


public class AlienManager {

	//Datenfelder
	
	private EntityManager entityManager;	//EntityManager, um Sch�sse hinzuf�gen zu k�nnen
	
	private static ArrayList<Alien> alienList;

	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public AlienManager() {
		//Datenfelder Initialisieren
		if (alienList == null) {	//nur wenn noch nicht Initialisiert
			alienList = new ArrayList<Alien>();
		}
		
		entityManager = new EntityManager(); //EntityManager, um auf die Listen aller Entitys zugreifen zu k�nnen
	}
	
	
	
	
	//Getter
	
	public ArrayList<Alien> getAlienList() {
		return alienList;
	}
	
	
	
	
	//Methode 
	
	public void removeAlien(Alien alien) {
		entityManager.getEntityShipList().remove(alien.getEntity());
		alienList.remove(alien);
	}
	
	/**
	 * Methode, welche Information �ber die angefragte Reihe gibt.
	 */
	public void alienLineInformations(int line) {
		
	}
	
	public static void resetAlienList() {
		alienList = null;
	}
}
