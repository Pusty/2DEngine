package me.engine.server;

import java.net.InetAddress;

public class ServerSideClient {
	int port;
	InetAddress ip;
 public ServerSideClient(InetAddress address,int port){
	 this.port = port;
	 this.ip = address;
 }


public InetAddress getAddress() {
	return ip;
}

public int getPort() {
	return port;
}
}
