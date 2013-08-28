package me.test;

import java.io.DataInputStream;
import java.net.DatagramPacket;

import me.engine.location.Location;
import me.engine.packet.Packet;
import me.engine.server.Server;

public class TestServer extends Server{

	public TestServer(int port) {
		super(port);
	}

	@Override
	public void handlePacket(int id, DataInputStream inputStream,
			DatagramPacket receivePacket) {
		if(id == 0){
			System.out.println("player joined!");
		}
	}
	
	public static void main(String[] args){
		TestServer server = new TestServer(9876);
		TestClient client = new TestClient("localhost",9876);

		client.sendPacket(new Packet(server,0,"test"));
	//	server.sendPacket(new Packet(server,0,"test"));
		
	}

	@Override
	public void initWorld() {
//this.getWorld().setPlayer(new Player(this,this.getStartLocation().getX(),this.getStartLocation().getY()));
		
		
		{
			
		


			
			
			
			
			for(int i = 0;i<50;i++)
				if(i%2==0)
			this.getWorld().addEntity(new Wall(32*i,50,2));
			for(int i = 0;i<50;i++)
				if(!(i%2==0))
			this.getWorld().addEntity(new Wall(32*i,50,3));
			
			for(int i = 0;i<20;i++)
				this.getWorld().addEntity(new Wall(-32,50-32*i,4));	
			for(int i = 0;i<20;i++)
				this.getWorld().addEntity(new Wall(32*50,50-32*i,4));
			
			
			for(int i=0;i<10;i++)
			this.getWorld().addEntity(new Wall(32*i+64,50-96,4));
		//	this.getWorld().addEntity(new Enemy(this,32*10+64,50-96));
			
			for(int i=0;i<10;i++)
		   this.getWorld().addEntity(new Wall(32*i+64*10,50-96,4));
		//	this.getWorld().addEntity(new Enemy(this,32*10+64*10,50-96));
				

			
		//	this.getWorld().addEntity(new Enemy(this,50,0));
			
			
		}
		
	}

	@Override
	public Location getStartLocation() {
		return new Location(10,10);
	}



}
