package me.engine.location;

import java.awt.Rectangle;

public class Size {

	Location loc;
	int sizex;
	int sizey;
	Rectangle rec;
	public Size(Location loc,int sizex,int sizey){
		this.loc = new Location(loc.getX(),loc.getY());
		this.sizex = sizex;
		this.sizey = sizey;
		rec = new Rectangle(loc.getX(),loc.getY(),sizex,sizey);
	}
	
	public Rectangle getRectange(int x,int y){
		return new Rectangle(x,y,sizex,sizey);
	}
	
	public void rezise(Location loc,int sizex,int sizey){
		this.loc = new Location(loc.getX(),loc.getY());
		this.sizex = sizex;
		this.sizey = sizey;
		rec = new Rectangle(loc.getX(),loc.getY(),sizex,sizey);
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public int getHeight(){
		return sizey;
	}
	
	public int getWidth(){
		return sizex;
	}
}
