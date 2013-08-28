package me.engine.listener;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import me.engine.gui.Gui;
import me.engine.gui.GuiPart;
import me.engine.main.Engine;



public class MouseHandler  implements  MouseListener,MouseMotionListener {

	

	Engine engine;
	public MouseHandler(Engine e){
		engine = e;
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		for(Gui g:engine.getGuis().values()){
			for(GuiPart p:g.getParts().values())
			{
				int x = event.getX();
				int y = event.getY();
				if(new Rectangle(x,y,1,1).intersects(p.getRectangle()))
					p.onClick(engine);
				
			}
		}
	}
    

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent event) {
		
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		
		
	}
	@Override
	public void mouseMoved(MouseEvent event) {

	}

}
