package me.test;

import java.awt.Image;
import java.io.IOException;


import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.SheetLoader;

public class Player extends Entity{
	
	String text;
	Engine engine;
	public Player(Engine en,int a,int b){
	  super(a,b);
	   size = new Size(loc,32,32);
	  text = "right";
	  name = "Player";
	  engine = en;
	  SheetLoader loader;
	try {
		loader = new SheetLoader(this.getClass().getResource("players.png"),8,8,16,16);
		  this.getPictureLoader().setOwnSize(this.getSize().getWidth(),this.getSize().getWidth());
		  this.getPictureLoader().ImportFromSheet("left", loader, 1, 0);
		  this.getPictureLoader().ImportFromSheet("right", loader, 2, 0);
	} catch (IOException e) {
		e.printStackTrace();
	}

	  
	}
	
	
	 @Override
	    public Image getImage(){
	    	return loader.getImage(text);
	    }
	 



		public void setNext(boolean left, boolean right, int i) {
			if(i == -1 && right){
				text = "right";
				
			}
			else if(i == -2 && left)
				text = "left";
			else if(i == 1)
				text = "left";
			else if(i == 2)
				text = "right";
		}
		
	
		public boolean hasGravity() {
			return true;
		}

}
