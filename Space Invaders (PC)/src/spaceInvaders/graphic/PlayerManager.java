package spaceInvaders.graphic;


import java.util.ArrayList;


public class PlayerManager {

	//Datenfelder

	private EntityManager entityManager;	//EntityManager, um Sch�sse hinzuf�gen zu k�nnen
	
	private static ArrayList<Player> playerList;
		
	
	
	
		
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public PlayerManager() {
		//Datenfelder Initialisieren
		if (playerList == null) {	//nur wenn noch nicht Initialisiert
			playerList = new ArrayList<Player>();
		}
		
		
		entityManager = new EntityManager(); //EntityManager, um auf die Listen aller Entitys zugreifen zu k�nnen
	}
	
	
	
	
	//Getter
	
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	
	
	
	//Methode 
	
	public void removePlayer(Player player) {
		entityManager.getEntityShipList().remove(player.getEntity());
		playerList.remove(player);
	}
	
	public static void resetPlayerList() {
		playerList = null;
	}
}

