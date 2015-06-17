package spaceInvaders.window;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import spaceInvaders.engine.animation.AlienAnimation;
import spaceInvaders.engine.animation.BarricadeAnimation;
import spaceInvaders.engine.animation.Collision;
import spaceInvaders.engine.animation.PlayerAnimation;
import spaceInvaders.engine.animation.Render;
import spaceInvaders.engine.animation.ShootAnimation;
import spaceInvaders.graphic.BarricadeEntity;
import spaceInvaders.graphic.EntityManager;
import spaceInvaders.graphic.Player;
import spaceInvaders.graphic.PlayerManager;
import spaceInvaders.graphic.ShipEntity;
import spaceInvaders.graphic.ShootEntity;
import spaceInvaders.graphic.SpriteManager;


public class GamePanel extends JPanel{
	
	//Datenfelder
	
	private PlayerAnimation animatShip; // Thread zum Animieren des Schiffes
	private ShootAnimation animateShoot; //Thread zum Animieren des Schusses
	private BarricadeAnimation animateBarricarde; //Thread zum Animieren der Barrikarden
	private AlienAnimation animateAlien; //Thread zum Animieren der Aliens
	private Collision collision; //Thread zum agfragen von Kollisionen
	private Render render; //Thread zum Rendern des Spielfeldes(Aktuallisieren)
	
	private EntityManager entityManager;	//EntityManager, um auf die Listen aller Entitys zugreifen zu können
	private PlayerManager playerManager;	//PlayerManager, um auf die Liste mit allen Spielern zugreifen zu können
	private SpriteManager spriteManager; //Das SpriteManager, um das Hintergrund bild zu bekommen 
	
	public static GamePanel spielfeld;
	
	private Image background;
	
	//Konstruktor
	
	/**
	 * @param ship => Objekt eines Schiffes, welches dieser Spieler Steuert
	 * @param player => der Spieler (ein Objekt von Spieler)
	 */
	public GamePanel() {		
		//Panel erzeugen und Eigenschaften bestimmen
		super(); // JPanel erzeugen
		this.setBackground(Color.BLACK);
		
		//Datenfelder Initialisieren
		animatShip = new PlayerAnimation(); // Thread zum Animieren des Schiffes
		animateShoot = new ShootAnimation(); // Thread zum Animieren des Schusses
		animateBarricarde = new BarricadeAnimation(); //Thread zum Animieren der Barrikarden
		animateAlien = new AlienAnimation(); //Thread zum Animieren der Aliens
		collision = new Collision(); //Thread zum agfragen  von Kollisionen
		render = new Render(this); //Thread zum Rendern des Spielfeldes(Aktuallisieren)
		
		entityManager = new EntityManager(); //EntityManager, um auf die Listen aller Entitys zugreifen zu können
		playerManager = new PlayerManager(); //PlayerManager, um auf die Liste mit allen Spielern zugreifen zu können
		spriteManager = new SpriteManager(); //Das SpriteManager, um das Hintergrund bild zu bekommen 
		
		background = spriteManager.sprite("background.png");	//Hintergrund Bild dem sprite Manager mitteilen
		
		spielfeld = this; // um z.B. die größe einfach ohne ein Ojekt erstellen zu müssen zu erfahren
	}
	
	
	//Getter
	
//	public int getPanelWidth() {
//		return this.getWidth();
//	}
//	
//	public int getPanelHeight() {
//		return this.getHeight();
//	}
	
	
	//Methoden
	
	/**
	 * Methode, welche alle Animations Threads etc. startet
	 */
	public void start() {
		//KeyListener hinzufügen
		this.setFocusable(true); // damit die KeyListener nur dann reagieren, wenn man sich auch garde wirklich in diesem Panel befindet
		
		try {
			for (int playerIndex = 1; playerManager.getPlayerList().size() - playerIndex >= 0; playerIndex++) {
				Player player = playerManager.getPlayerList().get(playerManager.getPlayerList().size() - playerIndex);	
				
				this.addKeyListener(player.getInputHandler());	// KeyListener dem Panel hinzufügen
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e + " GamePanel");
		}
		
		//Threads zum Animieren Starten
		new Thread(render).start();
		new Thread(animatShip).start();
		new Thread(animateShoot).start();
		new Thread(animateBarricarde).start();
		new Thread(animateAlien).start();
		new Thread(collision).start();
	}
	
	
	/**
	 * Methode, welche das Panel, bzw. die Entitys in das Panel an die richtige Position zeichnet
	 */
	public void paint(Graphics g) {
		super.paint(g);
		int fps = 0;
		//Background
		g.drawImage(background, 0, 0, this);
		
		//FPS-Anzeige
//		g.setFont(new Font("Verdan", 20, 50));
//		g.setColor(Color.yellow);
//		g.drawString(String.valueOf(fps), 20, 50);
		
		try {
			
			//durch alle Entitys iterieren und sie auf dem Bildschirm ausgeben
			for (int barricadeEntityIndex = 1; entityManager.getEntityBariakardenList().size() - barricadeEntityIndex >= 0; barricadeEntityIndex++) {
				BarricadeEntity entity = entityManager.getEntityBariakardenList().get(entityManager.getEntityBariakardenList().size() - barricadeEntityIndex);
				
				g.drawImage(entity.getSprite(), entity.getX(), entity.getY(), this);	//Entity zeichnen
			}	
			for (int shipEntityIndex = 1; entityManager.getEntityShipList().size() - shipEntityIndex >= 0; shipEntityIndex++) {
				ShipEntity entity = entityManager.getEntityShipList().get(entityManager.getEntityShipList().size() - shipEntityIndex);
				
				g.drawImage(entity.getSprite(), entity.getX(), entity.getY(), this);	//Entity zeichnen
			}
			for (int shootEntityIndex = 1; entityManager.getEntityShootList().size() - shootEntityIndex >= 0; shootEntityIndex++) {
				ShootEntity entity = entityManager.getEntityShootList().get(entityManager.getEntityShootList().size() - shootEntityIndex);
				
				g.drawImage(entity.getSprite(), entity.getX(), entity.getY(), this);	//Entity zeichnen
			}
		
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e + " GamePanel");
		}
	
		g.dispose();
	}
	
	public static void resetSpielfeld() {
		spielfeld = null;
	}
}
