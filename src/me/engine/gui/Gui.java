package me.engine.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.engine.location.Location;
import me.engine.main.Engine;
import me.engine.render.PictureLoader;

public class Gui {

    Location location;
    HashMap<String,GuiPart> parts;
	
	public Gui(int x,int y){
		location = new Location(x,y);
		parts = new HashMap<String,GuiPart>();
	}
	
	public void render(Graphics g){
		for(GuiPart p:parts.values()){
			g.drawImage(p.getImage(), p.getLocation().getX()+location.getX(), p.getLocation().getY()+location.getY(), null);
		}
		
	}
	
	public void addPart(String name,GuiPart part){
		parts.put(name,part);
	}
	
	public GuiPart getPart(String name){
		return parts.get(name);
	}
	
	public Location getLocation(){
		return location;
	}
	
	public HashMap<String,GuiPart> getParts(){
		return parts;
	}
	

}
