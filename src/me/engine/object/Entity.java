package me.engine.object;

import java.awt.Image;

import me.engine.location.Location;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.render.PictureLoader;

public abstract class Entity {
	protected  PictureLoader loader;
    protected String name;
    int id;
    boolean jump;
    boolean canjump;
    int canjumptime;
    int jumptime;
    protected  Location loc;
    
    protected Size size;


   public Entity(int a,int b){
	   loader = new PictureLoader();
	   name = "entity";
	   id = getNextID();
	   jump = false;
	   canjump = true;
	   jumptime = 0;
	   loc = new Location(a,b);
	   size = new Size(new Location(loc.getX(),loc.getY()),5,5);
   }
    
   
   public PictureLoader getPictureLoader(){
	   return loader;
   }
    //Temp
    public static int usedid = 0;
    
    public Image getImage(){
    	return loader.getImage("basic");
    }
    
    public Image getImage(String name){
    	return loader.getImage(name);
    }
    
   
    
    
    public void setLocation(int x,int y){
    	loc = new Location(x,y);
    }
    
    
    public void setSize(Location loc,int sizex,int sizey){
    	this.size = new Size(loc,sizex,sizey);
    }
    

    public Location getLocation(){
    	return loc;
    }
    
    public boolean hasCollision(){
    	return  true;
    }
    
    public Size getSize(){
    	return size;
    }
    
    public void setID(int i){
    	id = i;
    }
    
    public int getID(){
    	return id;
    }
    
    public int getNextID(){
    	int i = usedid;
    	usedid++;
    	return i;
    }
    
    public String getName(){
    	return name;
    }
    
    public void setName(String n){
    	name = n;
    }


	public void setJump(boolean b) {
	jump = b;
	if(jump == true)
		jumptime = 0;
	}
	
	public boolean isJumping(){
		return jump;
	}


	public int getJumpTime() {
	
		return jumptime;
	}
	
	public void setJumpTime(int i) {
		jumptime = i;
	}
	
	public void setCanJump(boolean b){
		canjump = b;
		if(canjump == false)
			setCanJumpTime(0);
	}
	
	public boolean canJump(){
		return canjump;
	}
	
	public void setCanJumpTime(int b){
		canjumptime = b;
		
	}
	
	public int getCanJumpTime(){
		return canjumptime;
	}


	public boolean hasGravity() {
		return false;
	}
	public boolean canMove() {
		return false;
	}


	public void move() {
		//Override!
	}


	public boolean doesDamage() {
		return false;
	}


	public boolean canJumpOn() {
		return false;
	}


	public boolean canDie() {
		return false;
	}


	public void collision(Engine engine, Entity p,boolean b) {
		
	}



    
    
}
