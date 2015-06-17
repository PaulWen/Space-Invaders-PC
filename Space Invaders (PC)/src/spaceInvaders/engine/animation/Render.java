package spaceInvaders.engine.animation;


import javax.swing.JComponent;

import spaceInvaders.Game;

public class Render implements Runnable{
	public static boolean rendere = false;
	
	//Datenfelder
	
	private JComponent component;
	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 * @param component => zum angeben, welche Komponente (z.B. JPanel) immer aktuallisiert wrden soll
	 */
	public Render(JComponent component) {
		//Datenfelder Initialisieren
		this.component = component;
	}
	
	
	
	//Methoden
	
	@Override
	public void run() {
		// um die Dauer des Letzen Schleifendurchgangs zu messen und somit die fortbewegung des Schusses korrekt anzugeben
		long duration = 0; 
		long last =  0;
		
		// solange das Spiel gespielt wird
		while (Game.running) {
			//bestimmen der bearbeitungsdauer eies Schleifendurchgangs um die FPS zu bekommen
			duration = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
//TODO FPS COUNTER			System.out.println(1000 / duration + "fps");
			
			rendere = true;
			component.repaint(); // um Spielfeld zu aktuallisieren
			try {
				rendere = false;
				Thread.sleep(16); //Die angabe wie oft die Schleife ausgeführt werden soll (zurzeit 100fps)
			}
			catch (InterruptedException e) {

			}
		}
	}
}
