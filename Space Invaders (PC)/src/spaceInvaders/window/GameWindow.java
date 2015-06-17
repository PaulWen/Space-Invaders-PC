package spaceInvaders.window;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import spaceInvaders.Game;
import spaceInvaders.window.gameBar.GameBar;


public class GameWindow extends JFrame implements WindowListener {
	
	//Datenfelder
	
	private Dimension dimensionBar;	//ein Objekt der Klasse Dimension enthählt sowohl die höhe als auch die Breite (x, y) für ein Fenster, Panel, etc.
	private Dimension dimensionSpielfeld;
	//Panels
	private GamePanel spielfeld;	//Panel, welches das Spielfeld darstellt
	private GameBar bar; // Panel, welches Informationen, wie Punkte und Leben enthält
	
	
	//Konstruktor
	
	/**
	 * @param title => Name des Fensters
	 * @param breite => Breite des Spielfeldes
	 * @param hoehe => Höhe des Spielfeldes
	 */
	public GameWindow(String title, int breite, int hoehe) {
		super(title); // neuses Fenster erstellen, dafür wird der Konstruktor der Klasse JFrame aufgrufen (hier die Klasse erbt ja von JFrame)
		
		// Datenfelder Initialisieren

			//Panels erstellen und deren Größe Festlegen
		spielfeld = new GamePanel();
		dimensionSpielfeld = new Dimension(breite, hoehe);
		spielfeld.setMaximumSize(dimensionSpielfeld);
		spielfeld.setMinimumSize(dimensionSpielfeld);
		spielfeld.setPreferredSize(dimensionSpielfeld);
		
		bar = new GameBar();
		dimensionBar = new Dimension(0, 25);
		bar.setMaximumSize(dimensionBar);
		bar.setMinimumSize(dimensionBar);
		bar.setPreferredSize(dimensionBar);
		
		//Componenten hinzufügen
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
//		add(bar, BorderLayout.NORTH);		//die Lebenzanzeige etc. hinzufügen
		add(spielfeld, BorderLayout.SOUTH);		//das Spielfeld dem Fnster hinzufügen
		
		
		//Eigenschaften des Fensters festlegen
		pack(); // damit sich die größe des JFrames der des Layouts anpasst = der Größe aller Panels.
		setLocationRelativeTo(null); // das das Fenster mittig auf dem Bildschrim geöffnet wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // damit das Programm beendet wird, wenn das Fenster geschlossen wird.
		addWindowListener(this);
		setResizable(false);
		setVisible(true); // Das Fenster anzeigen
		
		//Animations- und andere Threads Starten
		spielfeld.start();
//		bar.start();
	}

	
	//Methoden


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
//		Game.running = false;
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
