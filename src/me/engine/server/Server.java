package me.engine.server;



import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import me.engine.client.Client;
import me.engine.location.Location;
import me.engine.packet.Packet;
import me.engine.side.Side;
import me.engine.world.World;




public abstract class Server extends Side implements Runnable{
	DatagramSocket  serverSocket;
	ServerSideClient[] clients;
	World world;
	
	
	
	
	public Server(int port){
		try {
			initWorld();
	        serverSocket = new DatagramSocket (port);
	        clients = new ServerSideClient[25];
	       
	        new Thread(this).start();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public abstract void initWorld();
	
	
	public World getWorld()
	{
		return world;
	}
	
	
	public void setWorld(World w){
		world = w;
	}
	public void tryToGetData(){
        try {
        	 byte[] receiveData = new byte[1024];
        	   DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        	   
               serverSocket.receive(receivePacket);
       
               boolean allreadyingame = false;
               for(ServerSideClient client:clients){
            	   if(client != null && receivePacket.getAddress() == client.getAddress() && receivePacket.getPort() == client.getPort())
            		   allreadyingame = true;
               }
               
               if(allreadyingame == false)
            	   this.addClient(receivePacket);
              
               
               
               
               DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(receivePacket.getData()));
 
               
               handlePacket(inputStream.readInt(),inputStream,receivePacket);
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void addClient(DatagramPacket packet) {
		for(int i=0;i<clients.length;i++){
			if(clients[i] == null){
				clients[i] = new ServerSideClient(packet.getAddress(),packet.getPort());
				return;
			}
		}
		
	}

	public abstract void handlePacket(int id, DataInputStream inputStream, DatagramPacket receivePacket);
	
	
	


	@Override
	public void run() {
        System.out.println("Server started!");
		while(true){
			tryToGetData();
		/*	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}

	@Override
	public String getSide() {
		return "Server";
	}

	
	//To everybody
	@Override
	public void sendPacket(Packet p) {
		for(ServerSideClient c:clients){
			if(c == null)
				continue;
			  DatagramPacket sendPacket =
	                  new DatagramPacket(p.getData(), p.getData().length, c.getAddress(), c.getPort());
	                  try {
						serverSocket.send(sendPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
		}
	}

	
	/*
	 * 
	        while(true)
	        {
	           Socket connectionSocket = serverSocket.accept();
	           BufferedReader inFromClient =
	              new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	           DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	           clientSentence = inFromClient.readLine();
	           System.out.println("Received: " + clientSentence);
	           capitalizedSentence = clientSentence.toUpperCase() + '\n';
	           outToClient.writeBytes(capitalizedSentence);
	        }
	 */
	
	

   
	public abstract Location getStartLocation();

}
