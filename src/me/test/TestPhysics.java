package me.test;

import me.engine.effects.Particle;
import me.engine.location.Location;
import me.engine.main.Engine;
import me.engine.object.Entity;
import me.engine.physics.SidePhysics;

public class TestPhysics extends SidePhysics{

	
	boolean debug = false;
	
	
	public TestPhysics(Engine e) {
		super(e);
	
	}

	@Override
	public void usePhysics(Entity e, int x,int y) {
		
		
		if(e == null)
		{
			System.out.println("null");
			return;
		}
		
		
	
	
		
		
		if(y<e.getLocation().getY() && canMove(e,x,y) && !e.isJumping() && e.canJump() && e.getCanJumpTime() != -1){
			e.setJump(true);
			
			e.setCanJump(false);
        if(e instanceof Player)
	       engine.playSound(x,y,"jump");
        else
 	       engine.playSound(x,y,"jump2");
			if(e instanceof Enemy)
			engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("jump-red"), x, y,30));
			else if(e instanceof Jumper)
				engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("jump-blue"), x, y,30));
			else
			engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("jump-green"), x, y,30));
		}
		

		
	
		if(e.getCanJumpTime() == -1 && isGroundCollision(e,e.getLocation().getX(),e.getLocation().getY()+5,false)){
			e.setCanJumpTime(0);
		}else 	if(e.getCanJumpTime() == -1 && isGroundCollision(e,x,y  +5,true)){
			e.setCanJumpTime(0);
		}
		
		
		if(y<e.getLocation().getY())
			return;
		
		
		if(canMove(e,x,y))
		   moveTo(e,x,y);

	}

	@Override
	public boolean canMove(Entity e, int x,int y) {

		if(engine.getWorld() != null&&engine.getWorld().getEntityArray()!=null)
		{
	
		

          for(Entity l:engine.getWorld().getEntityArray())
          {
        	  if(l == null)
        		  continue;
        	  if(l == e)
        		  continue;     
        	  if(!l.hasCollision())
        		  continue;
      
 
    		  
        	  if(e.getSize().getRectange(x,y).intersects(l.getSize().getRectange(l.getLocation().getX(),l.getLocation().getY())))
        	  {
        
           		  if(l instanceof Player && e instanceof Enemy)
            		  System.out.println("collision!");

        	  	  if(e instanceof Player){
        	  		  if(l.doesDamage() && !l.canDie()){
        	  			  if(debug){
               			  e.setLocation(engine.getStartLocation().getX(), engine.getStartLocation().getY());
     	                 e.setJump(false);
    	                 e.setJumpTime(0);
    	                 e.setCanJump(true);
    	                 e.setCanJumpTime(0); 
        	  			  }else
        	  				newTry();
               			  
      					engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-green"), x, y,30));
        	  		  }
        	  		  else if(l.doesDamage() && (l.canDie()?!(e.getLocation().getY()<l.getLocation().getY()-e.getSize().getHeight()+5):true ) ){
        	  			  if(debug){
                   			  e.setLocation(engine.getStartLocation().getX(), engine.getStartLocation().getY());
         	                 e.setJump(false);
        	                 e.setJumpTime(0);
        	                 e.setCanJump(true);
        	                 e.setCanJumpTime(0); 
            	  			  }else
            	  				newTry();
    					engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-green"), x, y,30));
    					
    					
    					//Debugg
    					System.out.println(((TestEngine)engine).kills);
    					//System.exit(0);
            		  }  else if(l.doesDamage() && l.canDie() && e.getLocation().getY()<l.getLocation().getY()-e.getSize().getHeight()+5){
            			  engine.getWorld().removeEntity(l);
            			 ((TestEngine)engine).kills++;
            				if(l instanceof Enemy)
            					engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-red"), l.getLocation().getX(), l.getLocation().getY(),30));
            					else if(l instanceof Jumper)
            						engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-blue"), l.getLocation().getX(), l.getLocation().getY(),30));
            					else
            					engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-green"), l.getLocation().getX(), l.getLocation().getY(),30));
            		  }
            	  }else{
         	  		  if(l.doesDamage() && !l.canDie()){
               			  engine.getWorld().removeEntity(e);
               			  
               			  
      					engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-green"), x, y,30));
        	  		  } 
            	  }
        	  	  
        	  	  l.collision(engine,e,true);
        		  return false;
        	  }
          }
          return true;
		}else if(engine.getWorld() != null&&engine.getWorld().getEntityArray()==null){

			return true;
		}

		return false;
	}
	

	public void moveTo(Entity e, int x,int y) {
 
       e.setLocation(x,y);
	}
	
	public void newTry(){
		((TestEngine)engine).setWorld(((TestEngine)engine).getNextWorld());
	}
	
	

	public boolean isGroundCollision(Entity e, int x,int y,boolean b) {

		if(engine.getWorld() != null&&engine.getWorld().getEntityArray()!=null)
		{
	

          for(Entity l:engine.getWorld().getEntityArray())
          {
        	  if(l == null)
        		  continue;
        	  if(l == e)
        		  continue;
        	  if(!l.hasCollision())
        		  continue;
        	  
 	  
        	  if(e.getSize().getRectange(x,y).intersects(l.getSize().getRectange(l.getLocation().getX(),l.getLocation().getY())))
        	  {
        		  if(l.doesDamage() && !l.canDie() && e instanceof Player){
    	  			  if(debug){
               			  e.setLocation(engine.getStartLocation().getX(), engine.getStartLocation().getY());
     	                 e.setJump(false);
    	                 e.setJumpTime(0);
    	                 e.setCanJump(true);
    	                 e.setCanJumpTime(0); 
        	  			  }else
        	  				newTry();
    					engine.getWorld().addParticle(new Particle(engine, engine.getWorld(), engine.getParticleLoader().getImage("splash-green"), x, y,30));
    	  		  }else if(l.doesDamage() && !l.canDie()){
    	  			  engine.getWorld().removeEntity(e);
    	  		  }
        		  
          		  l.collision(engine,e,false);
        		  
          	  	if(e.getLocation().getY() < l.getLocation().getY() && b == false)
          		  return true;
          	  	else if(l.canJumpOn() && b == true)
          	  		return true;
        	  }
     
        		
      
        	  	
          }
          return false;
		}else if(engine.getWorld() != null&&engine.getWorld().getEntityArray()==null){

			return false;
		}

		return false;
	}
	

	@Override
	public void useGravity(Entity player, int x, int y) {

		if(canMove(player,x,y+6)  && !player.isJumping()){
			   moveTo(player,x,y+6);
		}else if(canMove(player,x,y+1)  && !player.isJumping()){
			   moveTo(player,x,y+1);
		}
		
		

			
			
			if(!player.canJump() && player.getCanJumpTime() < 12)
			{
				player.setCanJumpTime(player.getCanJumpTime()+1);
				if(player.getCanJumpTime() >= 12){
					player.setCanJumpTime(-1);
					player.setCanJump(true);
				}
			}
			
			
			if(player.isJumping() && player.getJumpTime() < 10){
				player.setJumpTime(player.getJumpTime()+1);
			    if(canMove(player,x,y-6))
			    	moveTo(player, x, y-6);
			    
			}else if(player.isJumping() && player.getJumpTime() >= 10)
			{
				player.setJumpTime(0);
				player.setJump(false);
			}else{
			
			if(isGroundCollision(player,player.getLocation().getX(),player.getLocation().getY()+5,false)  && player.isJumping())
				player.setJump(false);
      else if(isGroundCollision(player,x,y+5,true)  && player.isJumping())
	player.setJump(false);
			}
			
			

		
		
	}


}
