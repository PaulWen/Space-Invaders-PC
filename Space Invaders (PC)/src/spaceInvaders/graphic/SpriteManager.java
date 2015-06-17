package spaceInvaders.graphic;


import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;


public class SpriteManager {
	
	//Datenfelder
	
	private static HashMap<String, Image> spriteList;
	
	
	
	
	//Konstruktor
	
	/**
	 * Konstruktor
	 */
	public SpriteManager() {	
		//Datenfelder Initialisieren
		if (spriteList == null) {	//nur wenn noch nicht Initialisiert
			spriteList = new HashMap<String, Image>();
		}
	}
	
	
	
	
	//Getter
	
	public  HashMap<String, Image> getSpriteList() {
		return spriteList;
	}
	
	
	
	
	//Methoden
	
	/**
	 * Methode um ein Bild zu bekommen. Die Methode verwaltet dabei die Bilder und guckt das keine zwei Objekte des selben Bildes erstellt werden.
	 * @param imageName => Dateiname des Bildes
	 * @return => das gewünschte Bild
	 */
	public Image sprite(String imageName) {
		if (spriteList.containsKey(imageName)) {
			return spriteList.get(imageName);
		} else {
			spriteList.put(imageName, new ImageIcon("images/" + imageName).getImage());	
		}
		
		return spriteList.get(imageName);
	}
	
	public static void resetSpriteList() {
		spriteList = null;
	}
	
	
}
