package me.test;

import java.awt.event.KeyEvent;

import me.engine.effects.Particle;
import me.engine.listener.KeyHandler;
import me.engine.main.Engine;

public class TestKeyHandler extends KeyHandler implements Runnable{
	Engine engine;

	
	boolean[] canpress = new boolean[2];
	public TestKeyHandler(Engine e)
    {    
        engine = e;

    }

	public void keyPressed(KeyEvent e){

		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
					up = true;		
	          break;
		case KeyEvent.VK_SPACE:
			up = true;		
 break;
		case KeyEvent.VK_LEFT:
				left = true;
				((Player)engine.getWorld().getPlayer()).setNext(left, right,1);
	          break;
		case KeyEvent.VK_RIGHT:
					right = true;
					((Player)engine.getWorld().getPlayer()).setNext(left, right,2);
	          break;
			case 65:
			left = true;
			((Player)engine.getWorld().getPlayer()).setNext(left, right,1);
			break;

			case 68:
			right = true;
			((Player)engine.getWorld().getPlayer()).setNext(left, right,2);
			break;
			case KeyEvent.VK_F1:
			screen = true;
			try {
				Thread.sleep(120);
			} catch (InterruptedException e1) {
	
				e1.printStackTrace();
			}
			break;
			case 75:
			System.exit(0);
			break;
			case KeyEvent.VK_ESCAPE:
		
				if(canpress[0] == false){
					if(!engine.getGuis().containsKey("menu"))  {
				engine.getGuis().put("menu",TestEngine.getStartMenu(10,10));
		
					}else{
				engine.getGuis().remove("menu");
			
					}
				start(0);
				}
			break;
			case KeyEvent.VK_M:
				if(canpress[1] == false){
				if(((TestPhysics)engine.getPhysics()).debug){
		 			  engine.getWorld().getPlayer().setLocation(engine.getStartLocation().getX(), engine.getStartLocation().getY());
		 			 engine.getWorld().getPlayer().setJump(false);
		 			 engine.getWorld().getPlayer().setJumpTime(0);
		 			 engine.getWorld().getPlayer().setCanJump(true);
		 			 engine.getWorld().getPlayer().setCanJumpTime(0); 
				}else
				engine.setWorld(((TestEngine)engine).getNextWorld());
				
				start(1);
				}
				break;
		}
	}
	
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case 37:
			case 65:
			left = false;
			((Player)engine.getWorld().getPlayer()).setNext(left, right,-1);
			break;
			case 38:
			up = false;
			break;
			case 39:
			case 68:
			right = false;
			((Player)engine.getWorld().getPlayer()).setNext(left, right,-2);
			break;
			case KeyEvent.VK_SPACE:
				up = false;	
				break;
		}
	}

	public void keyTyped(KeyEvent e){
	
	}

	
	public void start(int id){
		canpress[id]=true;
		new Thread(this).start();
	}
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		canpress[0] = false;
		canpress[1] = false;
	}
	
	
	
}
