package spaceInvaders.window.gameBar;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import spaceInvaders.engine.animation.Render;
import spaceInvaders.graphic.EntityManager;
import spaceInvaders.graphic.Player;
import spaceInvaders.graphic.PlayerManager;
import spaceInvaders.graphic.SpriteManager;

public class GameBar extends JPanel{
	
	//Datenfelder
	
	private Render render; //Thread zum Rendern des Spielfeldes(Aktuallisieren)
	
	private EntityManager entityManager;	//EntityManager, um auf die Listen aller Entitys zugreifen zu können
	private PlayerManager playerManager;	//PlayerManager, um auf die Liste mit allen Spielern zugreifen zu können
	private static SpriteManager spriteManager; //Das SpriteManager, um das Hintergrund bild zu bekommen 
	
	//Lables
	private JLabel leben;	//Lebensanzeige
	private JLabel punkte; //Punkteanzeige
	
	//Konstruktor
	
	/**
	 * @param ship => Objekt eines Schiffes, welches dieser Spieler Steuert
	 * @param player => der Spieler (ein Objekt von Spieler)
	 */
	public GameBar() {		
		//Panel erzeugen
		super(); // JPanel erzeugen
		this.setBackground(Color.GRAY);	//Hintergrundfarbe festlegen
		
		
		//Datenfelder Initialisieren
		render = new Render(this); //Thread zum Rendern des Spielfeldes(Aktuallisieren)
		
		entityManager = new EntityManager(); //EntityManager, um auf die Listen aller Entitys zugreifen zu können
		playerManager = new PlayerManager(); //PlayerManager, um auf die Liste mit allen Spielern zugreifen zu können
		spriteManager = new SpriteManager(); //Das SpriteManager, um das Hintergrund bild zu bekommen 
		
		leben = new JLabel("Punkte: 256");
		punkte = new JLabel("  Name: Paul ");
		
		
		//Eigenschaften vom Panel/ Anordnung der Komponenten
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(leben, BorderLayout.EAST);
		add(punkte, BorderLayout.WEST);
	}
	
	public void start() {		
		//Threads zum Animieren Starten
		new Thread(render).start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);	
		
		g.dispose();
	}
}
