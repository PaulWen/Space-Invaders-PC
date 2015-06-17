package spaceInvaders.graphic;

import java.awt.Image;

public class ShipEntity extends Entity{

	//Datenfeld
	
	private int shootForce; // wie viel Leben ein Schuss abzieht
	private int shootPerSecond;	// wie viele Sch�sse maximal in der Sekunde abgefeuert werden k�nnen
	private long lastShoot;	//um zuverhindern das z.B. mehr als ein Schuss pro Sekunde abgefeuert werden kann
	
	//zum erstellen, des Bildes f�r einen Schuss
	private static SpriteManager spriteManager;
	private Image shootSprite;
	
	
	
	//Konstuktor
	
	/**
	 * Konstrukor
	 * @param imageSorce => Bild f�r auf dem Spielfeld (Sprite)
	 * @param x => Startpunkt auf der x-Achse
	 * @param y => Startpunkt auf der y-Achse
	 * @param geschwindigkeitX	=> mit welcher Geschwindigkeit es sich auf der x-Achse bewegt
	 * @param geschwindigkeitY => mit welcher Geschwindigkeit es sich auf der y-Achse bewegt
	 * @param lives => wie viele Leben es haben soll
	 * @param shootPerSecond => wie viele Sch�sse das Schiff pro Sekunde abfeuern kann
	 */
	public ShipEntity(String imageSorce, int x, int y, double geschwindigkeitX, double geschwindigkeitY, int lives, String shootSorce, int shootForce, int shootPerSecond) {
		super(imageSorce, x, y, geschwindigkeitX, geschwindigkeitY, lives);
		
		//Entity der Entsprechenden Liste hinzuf�gen
		EntityManager entityManager = new EntityManager();
		entityManager.getEntityShipList().add(this);	
		
		//Datenfelder Inizialisieren
		spriteManager = new SpriteManager();
		shootSprite = spriteManager.sprite(shootSorce);
		
		this.shootForce = shootForce;
		this.shootPerSecond = shootPerSecond;
		lastShoot = - 1;
	}

	
	
	//Getter und Setter
	
	public int getShootPerSecond() {
		return shootPerSecond;
	}

	public void setShootPerSecond(int shootPerSecond) {
		this.shootPerSecond = shootPerSecond;
	}
	
	public long getLastShoot() {
		return lastShoot;
	}
	
	public void setLastShoot(long lastShoot) {
		this.lastShoot = lastShoot;
	}
	
	public int getShootForce() {
		return shootForce;
	}
	
	public void setShootForce(int shootForce) {
		this.shootForce = shootForce;
	}

	public Image getShootSprite() {
		return shootSprite;
	}
	
	//Methode
	
	public static void resetSpriteManager() {
		spriteManager = null;
	}
}
