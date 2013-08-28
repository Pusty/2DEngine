package me.test;

import java.awt.Graphics;

import me.engine.effects.Particle;
import me.engine.gui.Gui;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.render.MovingRender;
import me.engine.render.OtherRender;

public class EntityRender extends MovingRender{

	public EntityRender() {
		super();
	}







	@Override
	public void renderMoving(Engine e, Graphics g, int i, int j) {
		

		for(Particle par:e.getWorld().getParticleArray()){
			if(par == null)
				continue;
			g.drawImage(par.getImage(), par.getLocation().getX()-i+e.getWidth()/2, par.getLocation().getY()-j+e.getHeight()/2, null);	
		}
		
		
		for(Entity en:e.getWorld().getEntityArray()){
			if(en == null)
				continue;
			g.drawImage(en.getImage(), en.getLocation().getX()-i+e.getWidth()/2, en.getLocation().getY()-j+e.getHeight()/2, null);	
		}
		

		
		
		for(Entity en:((TestWorld)e.getWorld()).getPlayers()){
			if(en == null)
				continue;
			g.drawImage(en.getImage(), en.getLocation().getX()-i+e.getWidth()/2, en.getLocation().getY()-j+e.getHeight()/2, null);	
		}
		
		
		
		
		//Drawing the player (not an entity!)
		g.drawImage(e.getWorld().getPlayer().getImage(), e.getWorld().getPlayer().getLocation().getX()-i+e.getWidth()/2, e.getWorld().getPlayer().getLocation().getY()-j+e.getHeight()/2, null);
		
		for(Gui gui : e.getGuis().values()){
			gui.render(g);
		}

		
		
	}
	

	
}
