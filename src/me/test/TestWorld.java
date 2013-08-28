package me.test;

import me.engine.location.Location;
import me.engine.object.Entity;
import me.engine.world.World;

public class TestWorld extends World {
	
	EntityPlayerMP[] players;
	public TestWorld(){
		super();
		   entitys = new Entity[1000];
		   players = new EntityPlayerMP[25];
	}
	

	
	 public void addPlayer(EntityPlayerMP e){

		   
		   for(int i=0;i<players.length;i++){
			   if(players[i] == null){
				   players[i] = e;
			   return;}  
		   }
	   }
	 
	 public EntityPlayerMP[] getPlayers()
	 {
		 return players;
	 }


	 
	 
	   

}
