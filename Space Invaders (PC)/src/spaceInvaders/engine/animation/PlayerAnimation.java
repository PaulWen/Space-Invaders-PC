package spaceInvaders.engine.animation;

import java.awt.Image;

import spaceInvaders.Game;
import spaceInvaders.graphic.*;
import spaceInvaders.window.GamePanel;

public class PlayerAnimation implements Runnable {
	
	//Datenfelder
	
	PlayerManager playerManager; 	//PlayerManager, um auf die Liste mit allen Spielern zugreifen zu können	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public PlayerAnimation() {
		//Datenfelder Initialisieren
		playerManager = new PlayerManager(); //PlayerManager, um auf die Liste mit allen Spielern zugreifen zu können
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
				
				//jeden Spieler/Schiff animieren
				for (int playerIndex = 1; playerManager.getPlayerList().size() - playerIndex >= 0; playerIndex++) {
					Player player = playerManager.getPlayerList().get(playerManager.getPlayerList().size() - playerIndex);	
					
					//solange Spieler nicht Tot
					if (player.getEntity().getLives() > 0) {
						// die Position des Schiffes wenn nötig verändern
						if(player.getInputHandler().down && !player.getInputHandler().up) {
							moveDown(duration, player.getEntity());
						} else if(player.getInputHandler().up && !player.getInputHandler().down) {
							moveUp(duration, player.getEntity());
						} 
						if(player.getInputHandler().right && !player.getInputHandler().left) {
							moveRight(duration, player.getEntity());
						} else if(player.getInputHandler().left && !player.getInputHandler().right) {
							moveLeft(duration, player.getEntity());
						}
						//schießen
						if (player.getInputHandler().shoot && System.currentTimeMillis() - player.getEntity().getLastShoot() >= 1000 / player.getEntity().getShootPerSecond()) {
							player.getEntity().setLastShoot(System.currentTimeMillis());	// damit z.B. nur einmal in der Sekunde geschossen werden kann
							shoot(player.getEntity().getX() + player.getEntity().getSprite().getWidth(null) / 2 - player.getEntity().getShootSprite().getWidth(null) / 2, player.getEntity().getY() - player.getEntity().getShootSprite().getHeight(null), player.getEntity().getShootSprite(), player.getEntity().getShootForce());
						}
					} else {
						playerManager.removePlayer(player); //Spieler entfernen wenn Tot
					}
				}
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e + " Collision");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e + " PlayerAnimation");
			}
			
			
			
			
		}
	}
	
	
	/**
	 * Methode zum Berechnen wie weit sich das Schiff nach Rechts bewegen muss
	 * @param duration => wie viel Zeit von der letzten Positionsangabe bis jetzt verstrichen ist, um so die richtige Positions verschiebung angeben zu können (Pixel/Hunderstel)
	 * @param ship => um x und y-Koordinate übergeben zu können
	 */
	public void moveRight(long duration, ShipEntity ship) {
		double movment = duration * ship.getGeschwindigkeitX() / 1000;
		//Prüfen, dass man sich nicht außerhalb des Spielfeldes bewegt
		if (ship.getX() + movment < GamePanel.spielfeld.getWidth() - ship.getSprite().getWidth(null)) {
			ship.setX(ship.getX() + movment);
		} else {
			ship.setX(GamePanel.spielfeld.getWidth() - ship.getSprite().getWidth(null));
		}
	}
	
	/**
	 * Methode zum Berechnen wie weit sich das Schiff nach Links bewegen muss
	 * @param duration => wie viel Zeit von der letzten Positionsangabe bis jetzt verstrichen ist, um so die richtige Positions verschiebung angeben zu können (Pixel/Hunderstel)
	 * @param ship => um x und y-Koordinate übergeben zu können
	 */
	public void moveLeft(long duration, ShipEntity ship) {
		double movment = duration * ship.getGeschwindigkeitX() / 1000;
		//Prüfen, dass man sich nicht außerhalb des Spielfeldes bewegt
		if (ship.getX() - movment > 0) {
			ship.setX(ship.getX() - movment);
		} else {
			ship.setX(0);
		}
	}
	
	/**
	 * Methode zum Berechnen wie weit sich das Schiff nach Unten bewegen muss
	 * @param duration => wie viel Zeit von der letzten Positionsangabe bis jetzt verstrichen ist, um so die richtige Positions verschiebung angeben zu können (Pixel/Hunderstel)
	 * @param ship => um x und y-Koordinate übergeben zu können
	 */
	public void moveDown(long duration, ShipEntity ship) {
		double movment = duration * ship.getGeschwindigkeitY() / 1000;
		//Prüfen, dass man sich nicht außerhalb des Spielfeldes bewegt
		if (ship.getY() + movment < GamePanel.spielfeld.getHeight() - ship.getSprite().getHeight(null)) {
			ship.setY(ship.getY() + movment);
		} else {
			ship.setY(GamePanel.spielfeld.getHeight() - ship.getSprite().getHeight(null));
		} 
	}
	
	/**
	 * Methode zum Berechnen wie weit sich das Schiff nach Oben bewegen muss
	 * @param duration => wie viel Zeit von der letzten Positionsangabe bis jetzt verstrichen ist, um so die richtige Positions verschiebung angeben zu können (Pixel/Hunderstel)
	 * @param ship => um x und y-Koordinate übergeben zu können
	 */
	public void moveUp(long duration, ShipEntity ship) {
		double movment = duration * ship.getGeschwindigkeitY() / 1000;
		//Prüfen, dass man sich nicht außerhalb des Spielfeldes bewegt
		if (ship.getY() - movment > 0) {
			ship.setY(ship.getY() - movment);
		} else {
			ship.setY(0);
		} 
	}
	
	/**
	 * Methode um einen Schuss zu erstellen
	 * @param x => x-Koordinate, von wo er starten soll
	 * @param y => y-Koordinate, von wo er starten soll
	 * @param shootSprite => Bild, welches den Schuss darstellt
	 * @param shootForce => wie viele Leben der Schuss abziehen soll, wenn er trifft (Stärke des Schusses)
	 */
	public void shoot(int x, int y, Image shootSprite, int shootForce) {
		new ShootEntity(shootSprite, x, y, 0, 300, true, shootForce, 1);
	}
}
