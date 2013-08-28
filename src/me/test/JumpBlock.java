package me.test;

import java.awt.Image;
import java.io.IOException;


import me.engine.effects.Particle;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.SheetLoader;

public class JumpBlock extends Entity implements Runnable{
	
	String text;
	boolean inuse=false;
	int inusetime = 0;
	public JumpBlock(int a,int b){
	  super(a,b);
	   size = new Size(loc,32,32);
	  name = "JumpBlock";
	  SheetLoader loader;
	try {
		loader = new SheetLoader(this.getClass().getResource("s_tiles.png"),8,8,16,16);
		  this.getPictureLoader().setOwnSize(this.getSize().getWidth(),this.getSize().getWidth());
		  this.getPictureLoader().ImportFromSheet("jump1", loader, 1, 0);
		  this.getPictureLoader().ImportFromSheet("jump2", loader, 2, 0);
		  this.getPictureLoader().ImportFromSheet("jump3", loader, 3, 0);
	} catch (IOException e) {
		e.printStackTrace();
	}

	  
	}
	
	public void setUse(boolean b)
	{
		if(b == true && inuse != true)
		{
			inusetime=15;
			new Thread(this).start();
		}
		inuse = b;
	}
	
	
	public boolean inUse(){
	return inuse;
	}
	
	
	
	
	
	
	 @Override
	    public Image getImage(){
		 if(!inuse)
	    	return loader.getImage("jump1");
		 else{
			 if(inusetime > 10)
				 	return loader.getImage("jump2");	
			 else  if(inusetime > 5)
				 	return loader.getImage("jump3");	
			 else
				 	return loader.getImage("jump2");	
		 }
		   
	    }
	 
	 public boolean canJumpOn(){
		 return true;
	 }
	 
	 public boolean doesDamage(){
		 return false;
	 }
	 
	 @Override
		public void collision(Engine engine, Entity e,boolean b) {
		 
    	  	if(!this.inUse()  && e.getLocation().getY()<this.getLocation().getY() && b)
  		  {

    	  		 e.setJump(true);
                 e.setJumpTime(-15);
    	  		 e.setCanJump(true);
    	  		 e.setCanJumpTime(0);
    	  		this.setUse(true);
                 engine.playSound(this.getLocation().getX(),this.getLocation().getY(),"spring");
  		  }
		}

	@Override
	public void run() {

		while(inusetime >= 0){
			inusetime--;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
		}
		
		inusetime = 0;
		inuse = false;
	}
	 

		
	

}