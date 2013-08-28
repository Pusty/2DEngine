package me.engine.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler  implements KeyListener{

	public boolean left;
	public boolean down;
	public boolean right;
	public boolean up;
	public boolean screen;
	
	
    public KeyHandler()
    {    
    
    }

	public void keyPressed(KeyEvent e){

		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
					up = true;		
	          break;
		case KeyEvent.VK_DOWN:			
			down = true;
	          break;
		case KeyEvent.VK_LEFT:
				left = true;
	          break;
		case KeyEvent.VK_RIGHT:
					right = true;
			
	          break;
			case 65:
			left = true;
			break;
			case 87:
			up = true;
			break;
			case 68:
			right = true;
			break;
			case 83:
			down = true;
			break;
			case KeyEvent.VK_F1:
			screen = true;
			try {
				Thread.sleep(120);
			} catch (InterruptedException e1) {
				// TODO Automatisch generierter Erfassungsblock
				e1.printStackTrace();
			}
			break;
			case 75:
			System.exit(0);
			break;
		}
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case 37:
			case 65:
			left = false;
			break;
			case 38:
			case 87:
			up = false;
			break;
			case 39:
			case 68:
			right = false;
			break;
			case 40:
			case 83:
			down = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e){
	
	}
}
