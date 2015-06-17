package spaceInvaders.engine.animation;

import java.awt.Rectangle;

import spaceInvaders.Game;
import spaceInvaders.graphic.*;

public class Collision implements Runnable {

	// Datenfelder

	private EntityManager entityManager; // EntityManager,auf Listen mit Entitys
											// zugriff zu haben
	private AlienManager alienManager; // AlienManager, um auf AlienEntitys
										// zugriff zu haben
	private PlayerManager playerManager; // PlayerManager, um auf
											// PlayerEntitys/Schiffe zugriff zu
											// haben

	// Konstruktor

	/**
	 * Konstruktor
	 */
	public Collision() {
		// Datenfelder Initialisieren
		entityManager = new EntityManager(); // EntityManager, um auf die Listen
												// aller Entitys zugreifen zu
												// können
		alienManager = new AlienManager();
		playerManager = new PlayerManager();
	}

	// Methoden

	@Override
	public void run() {
		// solange das Spiel gespielt wird
		while (Game.running) {	
			
			try {
					
				//Jeden Schuss übrprüfen
				for (int shootEntityIndex = 1; entityManager.getEntityShootList().size() - shootEntityIndex >= 0; shootEntityIndex++) {
					ShootEntity shoot = entityManager.getEntityShootList().get(entityManager.getEntityShootList().size() - shootEntityIndex);				
					
					boolean getroffen = false; // um zugucken ob vielleicht schon eine Kollision mit diesem Schuss gefunden wurde
					
					//Frageb ob ein Schuss mit einer Barrikarde kollidiert ist
					for (int barricadeEntityIndex = 1; entityManager.getEntityBariakardenList().size() - barricadeEntityIndex >= 0; barricadeEntityIndex++) {
						BarricadeEntity barricade = entityManager.getEntityBariakardenList().get(entityManager.getEntityBariakardenList().size() - barricadeEntityIndex);	
						
						if (collision(shoot, barricade)) {
							//Barrikarde getroffen
							System.out.println("Kollision");
							shoot.setLives(shoot.getLives() - 1); //Schuss ein Leben abziehen
							barricade.setLives(barricade.getLives() - shoot.getShootForce());	//Entity ein Leben abziehen
							System.out.println(barricade.getLives());
							getroffen = true;
							break;
						}
					}
					if(shoot.getSpielerSchuss()) {	//wenn Spieler-Schuss
						if (!getroffen) {
							//Fragen ob ein Schuss mit einem Alien kollidiert ist
							for (int alienIndex = 1; alienManager.getAlienList().size() - alienIndex >= 0; alienIndex++) {
								Alien alien = alienManager.getAlienList().get(alienManager.getAlienList().size() - alienIndex);	
								
								if (collision(shoot, alien.getEntity())) {
									//Alien-Schiff getroffen
									shoot.setLives(shoot.getLives() - 1); //Schuss ein Leben abziehen
									alien.getEntity().setLives(alien.getEntity().getLives() - shoot.getShootForce());	//Entity ein Leben abziehen
									System.out.println("collision");
									break;
								}
							}
						}
						//Fragen ob ein Alien-Schuss mit einem Spieler-Schuss kollidiert ist
						for (int shootEntityIndex2 = 1; entityManager.getEntityShootList().size() - shootEntityIndex2 >= 0; shootEntityIndex2++) {
							ShootEntity shoot2 = entityManager.getEntityShootList().get(entityManager.getEntityShootList().size() - shootEntityIndex2);
							
							if (!shoot2.getSpielerSchuss()) {
								if (collision(shoot, shoot2)) {
									//Alien-Schuss getroffen
									shoot.setLives(shoot.getLives() - 1); //Schuss ein Leben abziehen
									shoot2.setLives(shoot2.getLives() - shoot.getShootForce()); //Schuss2 ein Leben abziehen
									break;
								}
							}
						}
					} else {	//wenn Alien-Schuss
						if (!getroffen) {
							//Fragen ob ein Schuss mit einem Spieler kollidiert ist
							for (int playerIndex = 1; playerManager.getPlayerList().size() - playerIndex >= 0; playerIndex++) {
								Player player = playerManager.getPlayerList().get(playerManager.getPlayerList().size() - playerIndex);	
							
								if (collision(shoot, player.getEntity())) {
									//Spieler-Schiff getroffen
									shoot.setLives(shoot.getLives() - 1); //Schuss ein Leben abziehen
									player.getEntity().setLives(player.getEntity().getLives() - shoot.getShootForce());	//Entity ein Leben abziehen
									System.out.println(player.getEntity().getLives());
									break;
								}
							}
								
							//Fragen ob ein Alien-Schuss mit einem Spieler-Schuss kollidiert ist
							for (int shootEntityIndex2 = 1; entityManager.getEntityShootList().size() - shootEntityIndex2 >= 0; shootEntityIndex2++) {
								ShootEntity shoot2 = entityManager.getEntityShootList().get(entityManager.getEntityShootList().size() - shootEntityIndex2);
								
								if (shoot2.getSpielerSchuss()) {
									if (collision(shoot, shoot2)) {
										//Spieler-Schuss getroffen
										shoot.setLives(shoot.getLives() - 1); //Schuss ein Leben abziehen	
										shoot2.setLives(shoot2.getLives() - shoot.getShootForce()); //Schuss2 ein Leben abziehen
										break;
									}
								}
							}
						}
					}
				}
				
			} catch (Exception e/*NullPointerExceptionIndexOutOfBoundsException e*/) {
				System.out.println(e + " Collision");
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e + " Collision");
			}
		}
	}

	/**
	 * Methode zum abfragen ob zwei Entitys kollidieren
	 * 
	 * @param entity1
	 *            => Entity
	 * @param entity2
	 *            => Entity
	 * @return => true, wenn sie kollidieren
	 */
	public boolean collision(Entity entity1, Entity entity2) {
		// Entitys in Rechtecke packen
		Rectangle r1 = new Rectangle(entity1.getX(), entity1.getY(), entity1.getSprite().getWidth(null), entity1.getSprite().getHeight(null));
		Rectangle r2 = new Rectangle(entity2.getX(), entity2.getY(), entity2.getSprite().getWidth(null), entity2.getSprite().getHeight(null));

		// abfragen um sie kollidieren
		return r1.intersects(r2);
	}
}