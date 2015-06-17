package spaceInvaders.engine.controle;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener {

	//Datenfelder
	
	public boolean up, down, left, right, shoot; // um anzugeben, welche Taste grade gedrückt wird
	public int keyUp, keyDown, keyLeft, keyRight, keyShoot; // gibt die Taste an, welche für die jeweilige Bewegung genutz werden/zum schießen
	
	
	
	//Konstruktor
	
	/** 
	 * Konstruktor
	 * @param keyUp, keyDown, keyLeft, keyRight, shoot => gibt die Taste an, welche für die jeweilige Bewegung genutz werden/zum schießen
	 */
	public InputHandler(int keyUp, int keyDown, int keyLeft, int keyRight, int keyShoot) {
		//Datenfelder Initialisieren
		this.keyUp = keyUp;
		this.keyDown = keyDown;
		this.keyLeft = keyLeft;
		this.keyRight = keyRight;
		this.keyShoot = keyShoot;
		
		 up = false;
		 down = false;
		 left = false;
		 right = false;
		 shoot = false;
	}
	
	
	
	//Methoden
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == keyLeft) {
			left = true;
		}
		if (key == keyRight) {
			right = true;
		}
		if (key == keyUp) {
			up = true;
		}
		if (key == keyDown) {
			down = true;
		}
		if (key == keyShoot) {
			shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == keyLeft) {
			left = false;
		}
		if (key == keyRight) {
			right = false;
		}
		if (key == keyUp) {
			up = false;
		}
		if (key == keyDown) {
			down = false;
		}
		if (key == keyShoot) {
			shoot = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}	
}
