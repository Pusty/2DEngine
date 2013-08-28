package me.engine.gui;

import java.awt.Image;
import java.awt.Rectangle;

import me.engine.location.Location;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.render.PictureLoader;

public abstract class GuiPart {
	PictureLoader imgloader;
	Location location;
	Size size;
	
	public GuiPart(int x,int y){
		imgloader = new PictureLoader();
		location = new Location(x,y);
		initTextures();
	}
	
	
	public abstract void initTextures();
	
	public void setSize(Size s){
		size = new Size(s.getLocation(),s.getWidth(),s.getHeight());
	}
	
	public Image getImage(){
		return imgloader.getImage("main");
	}
	
	public PictureLoader getIMGLoader(){
		return imgloader;
	}
	
	public Location getLocation(){
		return location;
	}

	public Rectangle getRectangle() {
		return size.getRectange(location.getX(), location.getY());
	}

	public void onClick(Engine engine) {
		
	}
	

}
