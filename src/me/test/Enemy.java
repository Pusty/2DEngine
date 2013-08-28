package me.test;

import java.awt.Image;
import java.io.IOException;

import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.SheetLoader;

public class Enemy extends Entity{
	
	String text;
	Engine engine;
	boolean side;
	
	public Enemy(Engine en,int a,int b){
	  super(a,b);
	   size = new Size(loc,32,32);
	  text = "right";
	  name = "Enemy";
	  engine = en;
	  side = false;
	  SheetLoader loader;
	try {
		loader = new SheetLoader(this.getClass().getResource("players.png"),8,8,16,16);
		  this.getPictureLoader().setOwnSize(this.getSize().getWidth(),this.getSize().getWidth());
		  this.getPictureLoader().ImportFromSheet("left", loader, 1, 1);
		  this.getPictureLoader().ImportFromSheet("right", loader, 2, 1);
	} catch (IOException e) {
		e.printStackTrace();
	}

	  
	}
	
	
	 @Override
	    public Image getImage(){
	    	return loader.getImage(text);
	    }
	 



		public void setNext(int i) {
			if(i == 2)
				text = "right";
			else if(i == 1)
				text = "left";
		}
		
		
		
		@Override
		public boolean hasGravity(){
			return true;
		}
		
		@Override
		public boolean canMove(){
			return true;
		}
		
		@Override
		public boolean doesDamage(){
			return true;
		}
		
		
		public boolean canDie() {
			return true;
		}
		
		@Override
	   public void move(){
			   if(!engine.getPhysics().canMove(this, this.getLocation().getX(), this.getLocation().getY()+5)){
		   if(side == true){
			   
		
			
			   
			   if(!engine.getPhysics().canMove(this, this.getLocation().getX()+5, this.getLocation().getY()+5))
		  engine.getPhysics().usePhysics(this, this.getLocation().getX()+5, this.getLocation().getY());
			   else
			   {
					  side = false;
					  setNext(1);}   
			   
		   
		   
		  if( !engine.getPhysics().canMove(this, this.getLocation().getX()+5, this.getLocation().getY())){
			  side = false;
		  setNext(1);}
		  
		  
		   }else{

			   
		  
			   if(!engine.getPhysics().canMove(this, this.getLocation().getX()-5, this.getLocation().getY()+5))
	      engine.getPhysics().usePhysics(this, this.getLocation().getX()-5, this.getLocation().getY());
				  else{
						
					  side = true;
					  setNext(2);}   
			   
			   
			   
		  if( !engine.getPhysics().canMove(this, this.getLocation().getX()-5, this.getLocation().getY())){
			  side = true;
		  setNext(2);}
		  
		   
		   }}
			 engine.getPhysics().useGravity(this, getLocation().getX(), getLocation().getY());
		   
		   
	   }
		
		
		
	

}
