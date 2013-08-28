package me.test;

import me.engine.client.Client;
import me.engine.effects.Particle;
import me.engine.listener.KeyHandler;
import me.engine.location.Location;
import me.engine.main.Engine;
import me.engine.main.GameTicks;
import me.engine.object.Entity;
import me.engine.packet.Packet;
import me.engine.world.World;

public class TestTicks extends GameTicks {

	
	


	
	
	Engine engine;
	public TestTicks(Engine e){
		super(e);
		engine = e;
		
		if(engine.getSide().equalsIgnoreCase("Client")){
			engine.getSideClass().sendPacket(new Packet(engine.getSideClass(),2,""));
		}
		
	}
	@Override
	public void run() {
		

		while(true){
		
			if(engine.getSide().equalsIgnoreCase("None")){
				serverTick();
				clientTick();
			}
			else if(engine.getSide().equalsIgnoreCase("Client")){
				clientTick();
			}
			else if(engine.getSide().equalsIgnoreCase("Server")){
				serverTick();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			
        }
		


	
	}
	@Override
	public void serverTick() {
		if(engine.getWorld() != null && engine.getWorld().getPlayer() != null && engine.getPhysics() != null){

			
			
			engine.getPhysics().usePhysics(engine.getWorld().getPlayer(), engine.getWorld().getPlayer().getLocation().getX(),engine.getWorld().getPlayer().getLocation().getY());
			
			
			if(((KeyHandler)engine.getKeyListeners()[0]).up)
			engine.getPhysics().usePhysics(engine.getWorld().getPlayer(), engine.getWorld().getPlayer().getLocation().getX(),engine.getWorld().getPlayer().getLocation().getY()-5);
			if(((KeyHandler)engine.getKeyListeners()[0]).down)
			engine.getPhysics().usePhysics(engine.getWorld().getPlayer(),engine.getWorld().getPlayer().getLocation().getX(),engine.getWorld().getPlayer().getLocation().getY()+5);
			
			if(((KeyHandler)engine.getKeyListeners()[0]).left)
			engine.getPhysics().usePhysics(engine.getWorld().getPlayer(),engine.getWorld().getPlayer().getLocation().getX()-5,engine.getWorld().getPlayer().getLocation().getY());
			if(((KeyHandler)engine.getKeyListeners()[0]).right)
			engine.getPhysics().usePhysics(engine.getWorld().getPlayer(),engine.getWorld().getPlayer().getLocation().getX()+5,engine.getWorld().getPlayer().getLocation().getY());
			
			//			engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("particle-00"), engine.getWorld().getPlayer().getLocation().getX(), engine.getWorld().getPlayer().getLocation().getY(),60));
		     

			
			
			
			
			engine.getPhysics().useGravity(engine.getWorld().getPlayer(),engine.getWorld().getPlayer().getLocation().getX(),engine.getWorld().getPlayer().getLocation().getY());
			
			
			
			for(Entity e:engine.getWorld().getEntityArray())
			{
				if(e != null && e.canMove())
					e.move();
			}
			
			
	
		}
		
	}
	@Override
	public void clientTick() {
		engine.repaint();	
		
	}

}
