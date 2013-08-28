package me.engine.physics;

import me.engine.location.Location;
import me.engine.main.Engine;
import me.engine.object.Entity;

public abstract  class Physics {
	protected Engine engine;
	public Physics(Engine e){
		engine = e;
	}
	
	public  abstract void usePhysics(Entity e,int x, int y);
	
	public abstract boolean canMove(Entity e,int x,int y);

	public abstract void useGravity(Entity player, int x, int y);


	
	
}
