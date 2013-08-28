package me.engine.effects;

import java.awt.Image;

import me.engine.location.Location;
import me.engine.main.Engine;
import me.engine.world.World;

public class Particle implements Runnable{
	
	Image img;
	Engine engine;
	Location loc;
	int lifetime; //1 Tick = 100 ms = 0,1s
	World world;
	
	public Particle(Engine e,World w,Image i,int x,int y,int lt){
		img = i;
		engine = e;
		loc = new Location(x,y);
		lifetime = lt;
		world = w;
		this.start();
	}
	
	public void start(){
		new Thread(this).start();
	}
	
	public void setLocation(int x,int y){
		loc = new Location(x,y);
	}
	
	public void setImage(Image i){
		img = i;
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public Image getImage(){
		return img;
	}
	
	public int getLT(){
		return lifetime;
	}

	@Override
	public void run() {
	   	for(int i=0;i<lifetime;i++){
	   		lifetime--;
	   		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Automatisch generierter Erfassungsblock
				e.printStackTrace();
			}
	   	}
	   	
	   	world.removeParticle(this);
	   	
	}
}
