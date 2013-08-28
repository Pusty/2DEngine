package me.engine.render;

import java.awt.Graphics;

import me.engine.main.Engine;

public abstract class MovingRender extends OtherRender{

	@Override
	public void render(Engine e, Graphics g) {
		try{
         renderMoving(e,g,e.getWorld().getPlayer().getLocation().getX()-e.getStartLocation().getX(),e.getWorld().getPlayer().getLocation().getY()-e.getStartLocation().getY());
		}catch(Exception ed) {
			ed.printStackTrace();
		}
	}

	public abstract void renderMoving(Engine e,Graphics g, int i, int j);
	
	
	

   
	
	
}
