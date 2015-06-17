package spaceInvaders.graphic;

public class BarricadeEntity extends Entity{
	
	//Datenfelder
	
	private int brocken;
	
	
	//Konstruktor
	
	public BarricadeEntity(String imageName, int x, int y, double geschwindigkeitX, double geschwindigkeitY) {
		super(imageName, x, y, geschwindigkeitX, geschwindigkeitY, 90);
		
		//Datenfelder Initialisieren
		brocken = 0;
		
		//Entity der Entsprechenden Liste hinzufügen
		EntityManager entityManager = new EntityManager();
		entityManager.getEntityBariakardenList().add(this);
	}
	
	
	
	//Getter
	
	public int getBrocken() {
		return brocken;
	}
	
	public void setBrocken(int brocken) {
		this.brocken = brocken;
	}

}
