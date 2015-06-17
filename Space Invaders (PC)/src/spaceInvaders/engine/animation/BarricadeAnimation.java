package spaceInvaders.engine.animation;


import spaceInvaders.Game;
import spaceInvaders.graphic.*;

public class BarricadeAnimation implements Runnable{
	
	//Datenfelder
	
	private EntityManager entityManager;	//EntityManager, um Schüsse hinzufügen zu können
	
	
	
	
	//Konstruktor
		
	/**
	 * Konstruktor
	 */
	public BarricadeAnimation() {
		//Datenfelder Initialisieren
		entityManager = new EntityManager(); //EntityManager, um auf die Listen aller Entitys zugreifen zu können
	}
		
	

	
	//Methoden
		
	@Override
	public void run() {
		// solange das Spiel gespielt wird
		while (Game.running) {

			
			
			try {
			
				//jede Barrikarde animieren
				for (int barricadeEntityIndex = 1; entityManager.getEntityBariakardenList().size() - barricadeEntityIndex >= 0; barricadeEntityIndex++) {
					BarricadeEntity barricade = entityManager.getEntityBariakardenList().get(entityManager.getEntityBariakardenList().size() - barricadeEntityIndex);
					
					//richtiges Image zum jeweiligen Lebens Status
					if (barricade.getLives() <= 60 && barricade.getLives() > 30 && barricade.getBrocken() != 1 ) {
						barricade.setBrocken(barricade.getBrocken() + 1);
						barricade.setImage("barricadeBroken1.png");
					} else if (barricade.getLives() <= 30 && barricade.getLives() > 0 && barricade.getBrocken() != 2 ) {
						barricade.setBrocken(barricade.getBrocken() + 1);
						barricade.setImage("barricadeBroken2.png");
					} else if (barricade.getLives() <= 0 && barricade.getBrocken() != 3 ) {
						barricade.setBrocken(barricade.getBrocken() + 1);
						entityManager.getEntityBariakardenList().remove(barricade);	//Barrikarde entfernen
					}
				}
				
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e + " BarricadeAnimation");
			}
			
			try{
			Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println(e + " BarricadeAnimation");
			}
			
			
			
			
		}
	}
}
