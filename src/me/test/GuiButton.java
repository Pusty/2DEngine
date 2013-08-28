package me.test;

import java.awt.Image;
import java.io.IOException;

import me.engine.gui.GuiPart;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.render.SheetLoader;

public class GuiButton extends GuiPart implements Runnable{

	boolean on = false;
	
	public void setOn(boolean b){
		on = b;
	}
	
	public boolean getOn(){
		return on;
	}
	
	
	public GuiButton(int x, int y) {
		super(x, y);

	}

	
	public Image getImage(){
		if(on == false)
		return this.getIMGLoader().getImage("button");
		else
			return this.getIMGLoader().getImage("button2");	
	}
	
	@Override
	public void onClick(Engine engine) {
		System.out.println("clicked!");
	}


	@Override
	public void initTextures() {
		this.setSize(new Size(this.getLocation(),128,128/4));
		  SheetLoader loader;
		try {
			
			loader = new SheetLoader(this.getClass().getResource("button.png"),4,1,128,128/4);
			  this.getIMGLoader().setOwnSize(128,128/4);
			  this.getIMGLoader().ImportFromSheet("button", loader, 0, 0);
			  this.getIMGLoader().ImportFromSheet("button2", loader, 1,0);
			  this.getIMGLoader().ImportFromSheet("button3", loader, 2,0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	public void start(){
		new Thread(this).start();
	}
	@Override
	public void run() {
		on = true;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		on = false;
	}
}
