package me.engine.world;

import java.util.ArrayList;
import java.util.List;

import me.engine.client.Client;
import me.engine.effects.Particle;
import me.engine.location.Location;
import me.engine.object.Entity;
import me.engine.side.Side;

public abstract class World {
   public Entity[] entitys;
   public Particle[] particle;
   public Entity player;

   public World(){
	   entitys = new Entity[100];
	   particle = new Particle[100];
   }
   

   public void addEntity(Entity e){

	   
	   for(int i=0;i<entitys.length;i++){
		   if(entitys[i] == null){
			   entitys[i] = e;
		   return;}  
	   }
   }
   
   public void setPlayer(Entity p){
	   player = p;
   }
   
   public Entity getPlayer(){
	   return player;
   }
   
   public List<Entity> getEntityList(){
	   List<Entity> list = new ArrayList<Entity>();
	   for(Entity d:entitys)
		   if(d != null)
	          list.add(d);
	   
	   return list;
   }
   
   
   public Entity[] getEntityArray(){
	   return entitys;
   }
   
   
   
   public List<Particle> getParticlList(){
	   List<Particle> list = new ArrayList<Particle>();
	   for(Particle d:particle)
		   if(d != null)
	          list.add(d);
	   
	   return list;
   }
   
   
   public Particle[] getParticleArray(){
	   return particle;
   }
   
   public void addParticle(Particle pa){

	   
	   for(int i=0;i<particle.length;i++){
		   if(particle[i] == null){
			   particle[i] = pa;
		   return;}  
	   }
   }
public void removeParticle(Particle pa) {
if(particle != null)
	for(int i=0;i<particle.length;i++){
		if(particle[i] != null && particle[i] == pa)
			particle[i] = null;
	}
}
public void removeEntity(Entity l) {
	if(entitys != null)
		for(int i=0;i<entitys.length;i++){
			if(entitys[i] != null && entitys[i] == l)
				entitys[i] = null;
		}
}








  
   
}
