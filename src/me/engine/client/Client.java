package me.engine.client;



import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import me.engine.location.Location;
import me.engine.packet.Packet;
import me.engine.server.ServerSideClient;
import me.engine.side.Side;
import me.engine.world.World;

public abstract class Client extends Side implements Runnable{
	DatagramSocket clientSocket;
	int port;
    InetAddress serverIp;
    World world;
	public Client(String ip,int port){
		  try {
			  initWorld();
			  clientSocket = new DatagramSocket();
			  serverIp =InetAddress.getByName(ip);
			  this.port = port;
               new Thread(this).start();
			
		      }
		      catch(Exception e) {
					e.printStackTrace();
		      }
	}
	
	public World getWorld(){
		return world;
	}
	
	public void setWorld(World w){
		world = w;
	}
	
	public abstract void initWorld();
	


	@Override
	public void run() {
        System.out.println("Client started!");
		while(true){
			tryToGetData();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	public void tryToGetData(){
        try {
        	 byte[] receiveData = new byte[1024];
        	   DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
               clientSocket.receive(receivePacket);
                    
               DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(receivePacket.getData()));
 
               
               handlePacket(inputStream.readInt(),inputStream,receivePacket);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	public abstract void handlePacket(int id, DataInputStream inputStream, DatagramPacket receivePacket);
	      

	      
	      
	      
	

	@Override
	public String getSide() {
		return "Client";
	}

	
	//To the Server
	@Override
	public void sendPacket(Packet p) {
		   try {	
		  DatagramPacket sendPacket = new DatagramPacket(p.getData(), p.getData().length, serverIp, port);  
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract Location getStartLocation();


}
