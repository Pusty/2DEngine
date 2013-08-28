package me.engine.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import me.engine.side.Side;

public class Packet {

	public DataOutputStream outstream;
	public ByteArrayOutputStream byteoutstream;
	public Side packetSide;
	public Packet(Side side,int id,String... s){
		try {
		byteoutstream = new ByteArrayOutputStream(8);
		outstream = new DataOutputStream(new ByteArrayOutputStream(8));
	
	  outstream.writeInt(id);
	  for(String d:s){
		  if(d != null)
		  outstream.writeUTF(d);
	  }

		
		side = packetSide;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Packet(Side side,int id,String s){
		try {
		byteoutstream = new ByteArrayOutputStream(8);
		outstream = new DataOutputStream(new ByteArrayOutputStream(8));
	
	  outstream.writeInt(id);

		  outstream.writeUTF(s);
	  

		
		side = packetSide;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void send(){
		packetSide.sendPacket(this);
	}
	
	public DataOutputStream getStream(){
		return outstream;
	}
	
	public byte[] getData(){
		return byteoutstream.toByteArray();
	}
}
