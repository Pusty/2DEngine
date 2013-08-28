package me.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

import me.engine.client.Client;
import me.engine.location.Location;

public class TestClient extends Client{

	Location loc;
	public TestClient(String ip, int port) {
		super(ip, port);
		loc = new Location(0,0);
	}

	@Override
	public void handlePacket(int id, DataInputStream inputStream,
			DatagramPacket receivePacket) {

		if(id == 1){
			try {
				int x = Integer.parseInt(inputStream.readUTF().split("#")[0]);
				int y = Integer.parseInt(inputStream.readUTF().split("#")[1]);
				loc = new Location(x,y);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initWorld() {
         this.setWorld(new TestWorld());
		
	}

	public Location getStartLocation() {
		return loc;
	}
	
	

}
