package me.engine.render;

import java.awt.Graphics;

import me.engine.main.Engine;

public abstract class OtherRender {

	private boolean oninit=true;
	public OtherRender(){
		
	}
	
	public void setInitStart(boolean b){
		oninit = b;
	}
	
	public boolean getInitStart(){
		return oninit;
	}
	
	public abstract void render(Engine e,Graphics g);
}
