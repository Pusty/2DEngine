package me.test;

import java.awt.Image;
import java.io.IOException;


import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.SheetLoader;

public class Wall extends Entity{
	
	String text;
	int type;
	public Wall(int a,int b,int type){
	  super(a,b);
	   size = new Size(loc,32,32);
	  name = "Wall";
	  this.type = type;
	  SheetLoader loader;
	try {
		loader = new SheetLoader(this.getClass().getResource("tiles.png"),8,8,16,16);
		  this.getPictureLoader().setOwnSize(this.getSize().getWidth(),this.getSize().getWidth());
		  this.getPictureLoader().ImportFromSheet("main", loader, type, 0);
	} catch (IOException e) {
		e.printStackTrace();
	}

	  
	}
	
	
	
	
	 
	 @Override
		public void collision(Engine engine, Entity v,boolean b) {
		 
			if(type==6)
			  {
				  boolean entitythere = false;
				  for(Entity q:engine.getWorld().getEntityList()){
					  if(entitythere)
						  continue;
					  if(q != null && (q.doesDamage()&&q.canDie()))
						  entitythere = true;
				  }
				  
				  if(!entitythere){
			 	       engine.playSound(this.getLocation().getX(),this.getLocation().getY(),"portal");
					 ((TestEngine)engine).curmission++;
					 ((TestEngine)engine).setWorld(((TestEngine)engine).getNextWorld());
				  }
				  
				  
				  
				  
			  }
		}
	

	
	
	 @Override
	    public Image getImage(){
	    	return loader.getImage("main");
	    }
	 
	 public boolean canJumpOn(){
		 if(type == 4)
			 return true;
		 if(type == 8)
			 return true;
		 return false;
	 }
	 
	 public boolean doesDamage(){
		 if(type == 7)
			 return true;
		 return false;
	 }
	 

		
	

}