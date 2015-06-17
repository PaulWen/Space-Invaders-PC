package spaceInvaders.graphic;


import java.awt.Image;


public class Entity {
	
	//Datenfelder
	
	//wo sich das Entity auf dem Spielfeld grade befindet
	private double x;
	private double y;
	//Das Sprite stellt das Objekt auf dem Spielfeld da, also das Bild
	private static SpriteManager spriteManager;
	private Image sprite;
	// Geschwindigkeit in Pixel/Sekunde
	private double geschwindigkeitX;
	private double geschwindigkeitY;
	//Leben angabe
	private int lives;
	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 * @param imageName => Anzeige-Bild des Objektes (Dateiname)
	 * @param x => wo es sich auf der x-Achse am Anfang befinden soll
	 * @param y => wo es sich auf der y-Achse am Anfang befinden soll
	 * @param geschwindigkeitX, geschwindigkeitY => Geschwindigkeit in Pixel/Sekunde auf der jewiligen Achse
	 */
	public Entity(String imageName, int x, int y, double geschwindigkeitX, double geschwindigkeitY, int lives) {
		//Datenfelder Initialisieren
		spriteManager = new SpriteManager();
		sprite = spriteManager.sprite(imageName);
		this.x = x;
		this.y = y;
		this.geschwindigkeitX = geschwindigkeitX;
		this.geschwindigkeitY = geschwindigkeitY;
		this.lives = lives;
	}
	
	/**
	 * Konstruktor für einen Schuss
	 * @param imageName => Anzeige-Bild des Objektes (Dateiname)
	 * @param x => wo es sich auf der x-Achse am Anfang befinden soll
	 * @param y => wo es sich auf der y-Achse am Anfang befinden soll
	 * @param geschwindigkeitX, geschwindigkeitY => Geschwindigkeit in Pixel/Sekunde auf der jewiligen Achse
	 */
	public Entity(Image sprite, int x, int y, double geschwindigkeitX, double geschwindigkeitY, int lives) {
		//Datenfelder Initialisieren
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.geschwindigkeitX = geschwindigkeitX;
		this.geschwindigkeitY = geschwindigkeitY;
		this.lives = lives;
	}
	
	
	
	//Getter und Setter
	
	public Image getSprite() {
		return sprite;
	}
	
	public int getX() {
		return (int)x;
	}
	
	public int getY() {
		return (int)y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getGeschwindigkeitX() {
		return geschwindigkeitX;
	}

	public void setGeschwindigkeitX(double geschwindigkeitX) {
		this.geschwindigkeitX = geschwindigkeitX;
	}

	public double getGeschwindigkeitY() {
		return geschwindigkeitY;
	}
	
	public void setGeschwindigkeitY(double geschwindigkeitY) {
		this.geschwindigkeitY = geschwindigkeitY;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}

	public void setImage(String imageName) {
		sprite = spriteManager.sprite(imageName);
	}
	
	//methoden
	
	public static void resetSpriteManager() {
		spriteManager = null;
	}
}
