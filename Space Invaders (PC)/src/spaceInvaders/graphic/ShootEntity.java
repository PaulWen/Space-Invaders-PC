package spaceInvaders.graphic;


import java.awt.Image;

public class ShootEntity extends Entity{

	//Datenfleder
	
	private final boolean spielerSchuss; //true wenn das ein Schuss von einem Spieler, false wenn alien
	private int shootForce; // wie viel Leben ein Schuss abzieht
	
	
	
	//Konstruktor
	
	public ShootEntity(Image sprite, int x, int y, double geschwindigkeitX, double geschwindigkeitY, boolean spielerSchuss, int shotForce, int lives) {
		super(sprite, x, y, geschwindigkeitX, geschwindigkeitY, lives);
		
		//Datenfelder Initialisieren
		this.spielerSchuss = spielerSchuss;
		this.shootForce = shotForce;
		
		//Entity der Entsprechenden Liste hinzufügen
		EntityManager entityManager = new EntityManager();
		entityManager.getEntityShootList().add(this);
	}
	
	
	
	//Getter
	
	public boolean getSpielerSchuss() {
		return spielerSchuss;
	}
	
	public int getShootForce() {
		return shootForce;
	}
}
