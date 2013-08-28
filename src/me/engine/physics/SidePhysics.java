package me.engine.physics;


import me.engine.main.Engine;
import me.engine.object.Entity;

public class SidePhysics extends Physics{

	public SidePhysics(Engine e) {
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
		}
		

		
	
		if(e.getCanJumpTime() == -1 && isGroundCollision(e,e.getLocation().getX(),e.getLocation().getY()+5)){
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
        		  l.collision(engine, e, true);
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

	public boolean isGroundCollision(Entity e, int x,int y) {

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
        		  l.collision(engine, e, false);
        		  
        	  	if(e.getLocation().getY() < l.getLocation().getY())
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

		if(canMove(player,x,y+6) && !player.isJumping()){
			   moveTo(player,x,y+6);
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
			
			if(isGroundCollision(player,player.getLocation().getX(),player.getLocation().getY()+5)  && player.isJumping())
				player.setJump(false);
			}
			
			

		
		
	}


}
