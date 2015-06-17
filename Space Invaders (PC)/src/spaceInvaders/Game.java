package spaceInvaders;


import java.awt.event.KeyEvent;

import spaceInvaders.engine.controle.InputHandler;
import spaceInvaders.graphic.*;
import spaceInvaders.window.GamePanel;
import spaceInvaders.window.GameWindow;


public class Game {

	//Datenfelder
	
	public static boolean running; // ob Spiel zur Zeit Läuf
	
	//Konstruktor
	public Game() {
		reset();
		starteSpaceinvders();
	}
	
	
	public static void main(String[] args) {
		new Game();
	}
	
	
	
	//Methoden
	
	public void starteSpaceinvders() {
		running = true; // ob Spielfeld animiert werden muss
		
		//Spieler erzeugen und dem Manager übergeben		
		ShipEntity ship = new ShipEntity("ship1.png", 280, 650, 300, 300, 3, "shootPlayer.png", 15, 7);	//Schiff für den Spieler, welches auf dem Panel abgebildet wird
		new Player("Spieler1", new InputHandler(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE), ship); // Spieler welcher das Schiff steuert mit KeyListener, welcher die  Spiel Tasten des Spielers "überwacht"
		
		ShipEntity ship2 = new ShipEntity("ship2.png", 680, 650, 300, 300, 3, "shootPlayer.png", 1, 7);	//Schiff für den Spieler, welches auf dem Panel abgebildet wird
		new Player("Spieler2", new InputHandler(KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD0), ship2); // Spieler welcher das Schiff steuert mit KeyListener, welcher die  Spiel Tasten des Spielers "überwacht"
		
		//Barrikarden erzeugen
		new BarricadeEntity("barricade.png", 50, 500, 0, 0);
		new BarricadeEntity("barricade.png", 250, 500, 0, 0);
		new BarricadeEntity("barricade.png", 450, 500, 0, 0);
		new BarricadeEntity("barricade.png", 650, 500, 0, 0);
		new BarricadeEntity("barricade.png", 850, 500, 0, 0);	
		
//		new Alien(2, 145 + 50 * 1, 50 + 45 * 1, 1, 1);
//		new Alien(2, 145 + 50 * 1, 50 + 45 * 2, 1, 1);
//		new Alien(2, 145 + 50 * 1, 50 + 45 * 3, 1, 1);
		
		
		//LEVEL1 +START+
		for (int i = 1; i <= 12; i++) {
			new Alien(1, 145 + 50 * i, 50 + 45 * 3, 1, i);
		}
		for (int i = 1; i <= 12; i++) {
			new Alien(1, 145 + 50 * i, 50 + 45 * 2, 1, i);
		}
		for (int i = 1; i <= 12; i++) {
			new Alien(2, 145 + 50 * i, 50 + 45 * 1, 2, i);
		}
		for (int i = 1; i <= 12; i++) {
			new Alien(3, 145 + 50 * i, 50 + 45 * 0, 3, i);
		}
		//LEVEL1 +ENDE+
		
		
		//GameWindow öffnen
		new GameWindow("Space Invaders", 1000, 800);		
	}
	
	public void reset() {
		AlienManager.resetAlienList();
		Entity.resetSpriteManager();
		EntityManager.resetLists();
		PlayerManager.resetPlayerList();
		ShipEntity.resetSpriteManager();
		SpriteManager.resetSpriteList();
		GamePanel.resetSpielfeld();
	}	

}
