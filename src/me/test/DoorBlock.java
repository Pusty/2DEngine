package me.test;

import java.awt.Image;
import java.io.IOException;


import me.engine.effects.Particle;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.SheetLoader;

public class DoorBlock extends Entity implements Runnable{
	
	
	//Channel
	static boolean[] CHANNEL;
	{
		CHANNEL = new boolean[500];

	}
	//Channel
	String text;
	
	
	
	boolean inuse=false;
	
	int channel=0;
	
	
	boolean effect = false;
	
	
	boolean button=false;
	
	Engine engine;
	public DoorBlock(Engine en,int a,int b,boolean button,int channel){
	  super(a,b);
	  engine = en;
	  this.button = button;
	  this.channel = channel;
	   size = new Size(loc,32,32);
	  name = "DoorBlock";
	  SheetLoader loader;
	try {
		loader = new SheetLoader(this.getClass().getResource("s_tiles.png"),8,8,16,16);
		  this.getPictureLoader().setOwnSize(this.getSize().getWidth(),this.getSize().getWidth());
		  this.getPictureLoader().ImportFromSheet("on", loader, 3, 1);
		  this.getPictureLoader().ImportFromSheet("button", loader, 4, 1);
		  this.getPictureLoader().ImportFromSheet("off", loader, 2, 1);
	} catch (IOException e) {
		e.printStackTrace();
	}
	

	  if(!button)
		new Thread(this).start();

	  
	}
	
	
	public static void setChannel(int id,boolean b){
		try{
		CHANNEL[id] = b;
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	
	public static boolean getChannel(int id) {
		try{
		return CHANNEL[id];
	}catch(Exception e){
		e.printStackTrace();
		return false;
	}
	}
	
	public void setUse(boolean b)
	{
		if(button)
		CHANNEL[channel] = b;	
		else
		inuse = b;
	}
	
	
	public boolean inUse(){
	return 	inuse;
	}
	
	
	
	
	
	
	
	
	 @Override
	    public Image getImage(){
		 if(button)
			 return loader.getImage("button");
		 else
		 if(!inuse)
	    	return loader.getImage("on");
		 else
			 return loader.getImage("off");
		
		   
	    }
	 
	 public boolean canJumpOn(){
		 if(button)
			 return true;
		 else
		 if(!inuse)
		 return true;
		 else
			 return false;
	 }
	 
	 @Override
	 public boolean hasCollision(){
		 if(button)
			 return true;
			 else
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
		
		
		if(!button)
			
		while(this != null && !this.button){
			try{
			if(CHANNEL[channel] && engine.getPhysics().canMove(this, this.getLocation().getX(), this.getLocation().getY())){
			     inuse = false;
					effect = false;
			}else if(!CHANNEL[channel] && engine.getPhysics().canMove(this, this.getLocation().getX(), this.getLocation().getY()))
			     inuse = true;
			
			
			
			if(!effect){
			engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-door"), this.getLocation().getX(),this.getLocation().getY(),10));
			effect = true;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}catch(Exception e){
				
			}
		}
		else
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			inuse=false;
		}
		

	}
	 

		
	@Override
	public void collision(Engine engine, Entity l,boolean b) {
		if(this.button && !inuse){
			CHANNEL[channel] = CHANNEL[channel]?false:true;
			inuse=true;
			engine.playSound(this.getLocation().getX(), this.getLocation().getY(), "click");
			new Thread(this).start();
		}
	}

}