package me.test;

import java.awt.Image;
import java.io.IOException;


import me.engine.effects.Particle;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.SheetLoader;

public class WoodBlock extends Entity implements Runnable{
	
	String text;
	boolean inuse=false;
	int inusetime = 0;
	boolean rinuse=false;
	Engine engine;
	public WoodBlock(Engine en,int a,int b){
	  super(a,b);
	  engine = en;
	   size = new Size(loc,32,32);
	  name = "WoodBlock";
	  SheetLoader loader;
	try {
		loader = new SheetLoader(this.getClass().getResource("s_tiles.png"),8,8,16,16);
		  this.getPictureLoader().setOwnSize(this.getSize().getWidth(),this.getSize().getWidth());
		  this.getPictureLoader().ImportFromSheet("cloud1", loader, 1, 1);
		  this.getPictureLoader().ImportFromSheet("cloud2", loader, 2, 1);
	} catch (IOException e) {
		e.printStackTrace();
	}

	  
	}
	
	public void setUse(boolean b)
	{
		
		if(b == true && inuse != true)
		{

			new Thread(this).start();
			rinuse = true;
		}else
		inuse = b;
		rinuse = b;
	}
	
	
	public boolean inUse(){
	return 	rinuse;
	}
	
	
	
	
	@Override
	public void collision(Engine engine, Entity l,boolean b) {
		  if(!this.inUse()){
			  this.setUse(true);
				engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-wood"), this.getLocation().getX(),this.getLocation().getY(),30));
               engine.playSound(this.getLocation().getX(),this.getLocation().getY(),"disappear");
		  }
	}
	
	
	 @Override
	    public Image getImage(){
		 if(!inuse)
	    	return loader.getImage("cloud1");
		 else{


		    	return loader.getImage("cloud2");			
		 }
		   
	    }
	 
	 public boolean canJumpOn(){
		 if(!inuse)
		 return true;
		 else
			 return false;
	 }
	 
	 @Override
	 public boolean hasCollision(){
		 if(!inuse)
		 return true;
		 else
			 return false;
	 }
	 
	 public boolean doesDamage(){
		 return false;
	 }

	@Override
	public void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} 
		inusetime=50;
		inuse = true;
		rinuse = true;
		if(engine.getPhysics().canMove(this, this.getLocation().getX(), this.getLocation().getY()))
			inusetime--;
		
		while(inusetime >= 0){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(engine.getPhysics().canMove(this, this.getLocation().getX(), this.getLocation().getY()))
			inusetime--;

		}
		
		inusetime = 0;
		inuse = false;
		rinuse = false;
	}
	 

		
	

}