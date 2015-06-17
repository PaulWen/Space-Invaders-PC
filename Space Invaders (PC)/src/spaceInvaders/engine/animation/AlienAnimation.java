package spaceInvaders.engine.animation;

import java.awt.Image;
import java.lang.reflect.Array;
import java.util.ArrayList;

import spaceInvaders.Game;
import spaceInvaders.graphic.*;
import spaceInvaders.window.GamePanel;

public class AlienAnimation implements Runnable{	
	//Datenfelder
	
	private AlienManager alienManager; 	//AlienManager, um auf die Liste mit allen Aliens zugreifen zu können	
	private PlayerManager playerManager; // PlayerManage, um zufragen ob ein Spieler unter einem Alien ist, denn nur dann schießt das Alien
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public AlienAnimation() {
		//Datenfelder Initialisieren
		alienManager = new AlienManager(); //AlienManager, um auf die Liste mit allen Aliens zugreifen zu können
		playerManager = new PlayerManager(); // PlayerManage, um zufragen ob ein Spieler unter einem Alien ist, denn nur dann schießt das Alien
	}
	
	
	
	
	//Methoden
	
	@Override
	public void run() {		
		
		// um die Dauer des Letzen Schleifendurchgangs zu messen und somit die fortbewegung des Schiffes korrekt anzugeben
		long duration = 0; 
		long last =  System.currentTimeMillis();
		
		// solange das Spiel gespielt wird
		while (Game.running) { 
			//bestimmen der bearbeitungsdauer eies Schleifendurchgangs
			duration = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			
			try {
				
				//jedes Alien/Schiff animieren
				for (int alienIndex = 1; alienManager.getAlienList().size() - alienIndex >= 0; alienIndex++) {
					Alien alien = alienManager.getAlienList().get(alienManager.getAlienList().size() - alienIndex);	
//					System.out.println("wir sind noch da!");
					//solange Alien nicht Tot
					if (alien.getEntity().getLives() > 0 && alien.getEntity().getY() < GamePanel.spielfeld.getHeight()) {	// wenn Alien Lebt und sich nicht auf der Y-Achse aus dem Spielfeld bewegt hat
						//wenn Alien Schießen kann/darf
						if (allowedToShoot(alien)) {
							alien.getEntity().setLastShoot(System.currentTimeMillis());	// damit z.B. nur einmal in der Sekunde geschossen werden kann
							shoot(alien.getEntity().getX() + alien.getEntity().getSprite().getWidth(null) / 2 - alien.getEntity().getShootSprite().getWidth(null) / 2, alien.getEntity().getY() + alien.getEntity().getSprite().getHeight(null), alien.getEntity().getShootSprite(), alien.getEntity().getShootForce());
						}
						
						//Aliens bewegen						
						double movment = (duration * alien.getEntity().getGeschwindigkeitX() / 1000) + alien.getMovement();
						//abfragen, ob movment kleiner als ein 1, wenn ja muss man diesen wert auf den nächsten addieren,
						//da immer nur ein Pixel weitergegangen werden kann, und wenn es kleiner ist
						//(bei z.B. einer sehr niedrigen geschwindigkeit) wird gar nicht gegangen
						if ((movment > 1 || movment < -1)) {
							//Prüfen, dass man sich nicht außerhalb des Spielfeldes bewegt
							if (alien.getEntity().getX() + movment < GamePanel.spielfeld.getWidth() - alien.getEntity().getSprite().getWidth(null) && alien.getEntity().getX() + movment > 0) {
								alien.getEntity().setX(alien.getEntity().getX() + movment);	
							} else {
								for (int alienIndex2 = 1; alienManager.getAlienList().size() - alienIndex2 >= 0; alienIndex2++) {
									Alien alien2 = alienManager.getAlienList().get(alienManager.getAlienList().size() - alienIndex2);	
									moveDeeper(alien2);
								}
							}
							alien.setMovement(0);
						} else { // wenn es noch kein voller Pixel ist, dann movement zum noch auszuführenden movment des Schiffs hinzufügen
							alien.setMovement(alien.getMovement() + movment);
						}	
					} else {
						alienManager.removeAlien(alien); //Alien entfernen wenn Tot
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e + " Collision");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e + " AlienAnimation");
			}
		}
	}
	
	/**
	 * Methode, um ein Alien eine Rheie tiefer zu setzen
	 * @param alien => Alien, welches eine Rheie tiefer gesetzt werden soll.
	 */
	public void moveDeeper(Alien alien) {
		alien.getEntity().setY(alien.getEntity().getY() + 45);	//Alien eine Reihe tiefer setzen		
		alien.getEntity().setGeschwindigkeitX(-1 * (alien.getEntity().getGeschwindigkeitX()));	// bewegungsrichtung umdrehen
		
		if (alien.getEntity().getGeschwindigkeitX() < 0) {	
			alien.setMovement(0);
			alien.getEntity().setX(alien.getEntity().getX() - 1); // schiff von der wand etwas wegbewegen
		} else {
			alien.getEntity().setX(alien.getEntity().getX() + 1); // schiff von der wand etwas wegbewegen
			alien.setMovement(0);
		}
	}
	
	/**
	 * Methode, um zu gucken ob ein Alien Schießen darf. Ein Alien darf nicht schießen, wenn er hinter einem Alien Steht, welches nicht vom Typ 1 ist, es seiden er ist in der ersten Reihe.
	 * Außerdem darf ein Alien nur Schießen, wenn es Schießen kann und sich über einem Spieler befindet. 
	 * @param alien
	 * @return
	 */
	public boolean allowedToShoot(Alien alien) {		
		boolean allowedToShoot = false;
		
		//wenn es die Fehigkeit besitzt zu schießen und dies nicht zuschnell hintereinander versucht zu tun
		if (alien.getCanShoot() &&
			System.currentTimeMillis() - alien.getEntity().getLastShoot() >= 1000 / alien.getEntity().getShootPerSecond()
			) {
			
			//wenn alles ok
		} else {
			return false;
		}
		
		
		//wenn es sich über einem Spieler befindet		
		for (int playerIndex = 1; playerManager.getPlayerList().size() - playerIndex >= 0; playerIndex++) {
			Player player = playerManager.getPlayerList().get(playerManager.getPlayerList().size() - playerIndex);	
		
			if (alien.getEntity().getX() <= player.getEntity().getX() + player.getEntity().getSprite().getWidth(null) && 
				alien.getEntity().getX() + alien.getEntity().getSprite().getWidth(null) >= player.getEntity().getX() && 
				alien.getEntity().getY() < player.getEntity().getY()
				) {
				
				//wenn alles ok
				allowedToShoot = true;
				break;
			} else {
				allowedToShoot = false;
			}
		}
		if (!allowedToShoot) {
			return false;
		}
		
		
//		//wenn er in der ersten Reihe ist oder wenn er hinter einem Alien steht, welches nicht vom Typ 1 ist
//		ArrayList<Alien> alienList = alienManager.getAlienList();
//		for (int alienIndex = 1; alienList.size() - alienIndex >= 0; alienIndex++) {
//			Alien alien2 = alienManager.getAlienList().get(alienList.size() - alienIndex);	
//			
//			//ob ein Alien unter dem Abgefragten Alien
//			if (alien.getEntity().getX() == alien2.getEntity().getX()) {
//				allowedToShoot = false;
//				if (alien2.getType() == 1 || alien.getEntity().getY() > alien2.getEntity().getY()) {	// ob das Alien, welches sich unter dem Abgefragten Alien befindet von Typ 1 ist
//					allowedToShoot = true;
//				}
//			}
//		}
		
		
		return allowedToShoot;
	}
	
	/**
	 * Methode um einen Schuss zu erstellen
	 * @param x => x-Koordinate, von wo er starten soll
	 * @param y => y-Koordinate, von wo er starten soll
	 * @param shootSprite => Bild, welches den Schuss darstellt
	 * @param shootForce => wie viele Leben der Schuss abziehen soll, wenn er trifft (Stärke des Schusses)
	 */
	public void shoot(int x, int y, Image shootSprite, int shootForce) {
		new ShootEntity(shootSprite, x, y, 0, 300, false, shootForce, 1);
	}
}
