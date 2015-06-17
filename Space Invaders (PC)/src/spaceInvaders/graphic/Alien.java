package spaceInvaders.graphic;


public class Alien {
	
	//Datenfelder
	
	private int type;	// vom welchem Typ das Alien ist
	private int line;	// in welcher Reihe es sich grade befindet (wenn 1 dann ist er der forderste Alien und darf Schieﬂen)
	private int row;	// in welcher Spalte es sich befindet
	private boolean canShoot;	// ob es schieﬂen kann (alien kann nur schieﬂen wenn in der ersten Reihe oder in der Zweiten und ein Alien von Typ 2 vor ihm ist)
	private ShipEntity entity;	//Das Spiel Objekt, welches auf dem Spielfeld den Spieler repr‰sentiert
	private double movement; // wie viel es sich noch bewegen muss.
	
	//Konstruktor
	
	
	public Alien(int type, int x, int y, int line, int row) {
		//Datenfelder Initialisieren
		movement = 0;
		if (type == 1) {	//wenn Alien vom Typ 1
			this.type = 1;
			entity = new ShipEntity("alien1.png", x, y, 20, 1, 3, "shootAlien.png", 0, 1);
			this.line = line;
			this.row = row;
			canShoot = false;
		} else if (type == 2) {	//wenn Alien vom Typ 2
			this.type = 2;
			entity = new ShipEntity("alien2.png", x, y, 20, 1, 2, "shootAlien.png", 1, 1);
			this.line = line;
			this.row = row;
			canShoot = true;
		} else if (type == 3) {	//wenn Alien vom Typ 3
			this.type = 3;
			entity = new ShipEntity("alien3.png", x, y, 20, 1, 1, "shootAlienHard.png", 2, 1);
			this.line = line;
			this.row = row;
			canShoot = true;
		}
		
		//Alien der Entsprechenden Liste hinzuf¸gen
		AlienManager alienManager = new AlienManager();
		alienManager.getAlienList().add(this);
		
		if (alienManager.getAlienList().get(alienManager.getAlienList().size() - 1) != null) {
			
		}
	}
	
	
	
	//Getter
		
	public double getMovement() {
		return movement;
	}
	
	public void setMovement(double movement) {
		this.movement = movement;
	}
	
	
	public ShipEntity getEntity () {
		return entity;
	}
	
	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public int getType() {
		return type;
	}

	public boolean getCanShoot() {
		return canShoot;
	}
}
