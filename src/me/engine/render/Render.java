package me.engine.render;


import javax.swing.JFrame;

import me.engine.main.Engine;


public class Render{
     
	   
	public void setSize( Engine engine, int x, int y){
		 JFrame frame = new JFrame();
	    frame.setName(engine.getName());
            
			frame.setSize(x, y);
			engine.initStartImages();
			frame.add(engine);
			engine.init();
			frame.setIconImage(engine.getIconImage());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.setVisible(true);
			
			 }
		
	}

	

