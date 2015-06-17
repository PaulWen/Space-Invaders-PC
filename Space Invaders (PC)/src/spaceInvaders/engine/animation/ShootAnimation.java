package spaceInvaders.engine.animation;


import spaceInvaders.Game;
import spaceInvaders.graphic.*;

public class ShootAnimation implements Runnable{

	//Datenfelder
	
	private EntityManager entityManager;	//EntityManager, um Schüsse hinzufügen zu können

	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public ShootAnimation() {
		//Datenfelder Initialisieren
		entityManager = new EntityManager(); //EntityManager, um auf die Listen aller Entitys zugreifen zu können
	}

	
	
	
	//Methoden
	
	@Override
	public void run() {
		// um die Dauer des Letzen Schleifendurchgangs zu messen und somit die fortbewegung des Schusses korrekt anzugeben
		long duration = 0; 
		long last =  0;
		
		// solange das Spiel gespielt wird
		while (Game.running) {
			//bestimmen der bearbeitungsdauer eies Schleifendurchgangs
			duration = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			
			
			try {
				
				//jeden Schuss animieren
				for (int shootEntityIndex = 1; entityManager.getEntityShootList().size() - shootEntityIndex >= 0; shootEntityIndex++) {
					ShootEntity shoot = entityManager.getEntityShootList().get(entityManager.getEntityShootList().size() - shootEntityIndex);
						
					if(shoot == null) System.out.println("null");	//TODO shoot ist null warum verstehe ich aber nicht
					
					//Bewegen
					double movment = duration * shoot.getGeschwindigkeitY() / 1000;
					
					//Prüfen, dass man sich nicht außerhalb des Spielfeldes bewegt und der Schuss nicht schon "Tot" ist und Fragen ob von einem Alien oder Spieler
					if (shoot.getSpielerSchuss() && shoot.getY() - movment > 0 && shoot.getLives() > 0) {	//Spieler Schuss
						shoot.setY(shoot.getY() - movment);
					} else if(!shoot.getSpielerSchuss() && shoot.getY() + movment > 0 && shoot.getLives() > 0) {	//Alien Schuss
						shoot.setY(shoot.getY() + movment);
					} else {	//Toter Schuss
						entityManager.getEntityShootList().remove(shoot);	//Shuss entfernen
					}
				}
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e + " Collision");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e + " ShootAnimation");
			}
		}
	}
}
