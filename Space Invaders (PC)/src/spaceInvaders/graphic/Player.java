package spaceInvaders.graphic;


import spaceInvaders.engine.controle.InputHandler;


public class Player {
	
	//Datenfelder
	
	private String name;	// Name des Spielers
	private InputHandler input;	// ein InputHandler, welcher weiß mit welchen Tasten der Spieler spielt
	private ShipEntity entity;	//Das Spiel Objekt, welches auf dem Spielfeld den Spieler repräsentiert
	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 * @param name => Name des Spielers
	 * @param input => welche Tasten erbenutzt, KeyListener
	 * @param entity => stellt praktisch die Spielfigur da
	 */
	public Player(String name, InputHandler input, ShipEntity entity) {		
		//Datenfelder Initialisieren
		this.name = name;
		this.input = input;
		this.entity = entity;
		
		//Spieler der Entsprechenden Liste hinzufügen
		PlayerManager playerManager = new PlayerManager();
		playerManager.getPlayerList().add(this);
	}
	
	
	
	//Getter
	
	public String getName() {
		return name;
	}
	
	public InputHandler getInputHandler() {
		return input;
	}
	
	public ShipEntity getEntity () {
		return entity;
	}
}
