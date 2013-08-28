package me.engine.main;

public abstract class GameTicks implements Runnable{

	public GameTicks(Engine e) {
	}
	
	
	public abstract void serverTick();
	public abstract void clientTick();
}
