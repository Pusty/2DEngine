package me.engine.main;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.engine.client.Client;
import me.engine.gui.Gui;
import me.engine.location.Location;
import me.engine.physics.Physics;
import me.engine.render.OtherRender;
import me.engine.render.PictureLoader;
import me.engine.server.Server;
import me.engine.side.Side;
import me.engine.sound.SoundLoader;
import me.engine.world.World;



public abstract class Engine extends Applet{
    
	String name = "new product";
	PictureLoader imgs;
	PictureLoader parloader;
	SoundLoader soundloader;
	private List<GameTicks> tickclasses;
	private List<OtherRender> renderclasses;
	private HashMap<String,Gui> guis;
	  World world;
	 Physics physic;
	 Client client;
	 Server server;

	public abstract Image getIconImage();
	
	

	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
public void preInit(){
	
}
	
@ Override
	public void init(){
		super.init();
		tickclasses = new ArrayList<GameTicks>();
		renderclasses = new ArrayList<OtherRender>();
		guis = new HashMap<String,Gui>();
		soundloader = new SoundLoader(this);
		
		preInit();
		addMouse();
		addKeys();
		initParticles();
		initSounds();
		addRenderClass();
		initWorld();
		addGameTick();
		for(GameTicks ticks:tickclasses){
			try{
				Thread t = new Thread(ticks);
				t.start();
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
		}
	}
	
	public void setWorld(World w){
		world = w;
	}
	public World getWorld(){
		return world;
	}
	public abstract void  initWorld();

	public abstract void addGameTick();
	
	public abstract void initClient();
	
	public abstract void initServer();

	public abstract void addRenderClass();
	
	public abstract void addMouse();
	
	public abstract void addKeys();
	
	public abstract void initParticles();
	
	public abstract void initSounds();
	
	public void initStartImages(){
		imgs = new PictureLoader();
		parloader = new PictureLoader();
		parloader.setOwnSize(32, 32);
	}
	
	
	public void playSound(int x,int y,String name){
		this.getSoundLoader().playClip(name);
	}
	
	


	
	public void paint(Graphics g) {


	}

	public void update(Graphics g) {


	}
	
	public void hookPhysic(Physics phy){
		physic = phy;
	}
	
	public Physics getPhysics(){
		return physic;
	}
	
	
   
	
	public void addTickClass(GameTicks tickclass){
		tickclasses.add(tickclass);
	}
	
	
	public void addRenderClass(OtherRender renderclass){
		renderclasses.add(renderclass);
	}
	public List<GameTicks> getTickClass(){
		return tickclasses;
	}
	
	
	public HashMap<String,Gui> getGuis(){
		return guis;
	}
	
	
	public List<OtherRender> getRenderClass(){
		return renderclasses;
	}
	
	public PictureLoader getImageLoader(){
		return imgs;
	}
	
	public SoundLoader getSoundLoader(){
		return soundloader;
	}




	public  Location getStartLocation(){
		return new Location(10,10);
	}



	public PictureLoader getParticleLoader() {
		return parloader;
	}
	
	public Client getClient() {
		return client;
	}
	
	public Server getServer(){
		return server;
	}
	
	
	public String getSide(){
		return "None";
	}
	
	
	public Side getSideClass(){
		return null;
	}
	
	
	public void setClient(Client c) {
		 client = c;
	}
	
	public void setServer(Server s){
		server = s;
	}
	
	
}
