package me.engine.location;

public class Location {
   int x;
   int y;
   
   public Location(int a,int b){
	   x = a;
	   y = b;
   }
   
   public void setLocation(int a,int b){
	   x = a;
	   y = b;
   }
   
   public int getX(){
	   return x;
   }
   
   public int getY(){
	   return y;
   }
   
   
   public static int getDistanceX(Location a,Location b){
	   int x=a.getX()-b.getX();
	   
	   if(x >= 0)
		   return x;
	   else
		   return -x;
	   
	   
   }
   
   public static int getDistanceY(Location a,Location b){
	   int y=a.getY()-b.getY();
	   
	   if(y >= 0)
		   return y;
	   else
		   return -y;
	   
	   
   }
}
