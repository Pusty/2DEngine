package me.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;

import me.engine.gui.Gui;
import me.engine.listener.KeyHandler;
import me.engine.listener.MouseHandler;
import me.engine.location.Location;
import me.engine.location.Size;
import me.engine.main.Engine;
import me.engine.physics.SidePhysics;
import me.engine.render.OtherRender;
import me.engine.render.PictureLoader;
import me.engine.render.Render;
import me.engine.render.SheetLoader;
import me.engine.world.World;

public class TestEngine extends Engine {

	public int kills = 0;
	int curmission = 0;
	static int nextmission = 0;

	public static void main(String[] args) {

		Render render = new Render();
		render.setSize(new TestEngine(), 100, 100);
	}

	@Override
	public void init() {
		super.init();

		this.hookPhysic(new TestPhysics(this));
		
		
		//Gui gui = new Gui(0,0);
		//gui.addPart("button", new GuiButton(10,10));

	}

	public void preInit() {

	}

	@Override
	public Image getIconImage() {
		return this.getImageLoader().getImage("icon");

	}

	public void addMouse() {
		MouseHandler mousehandler = new MouseHandler(this);
		addMouseListener(mousehandler);
		addMouseMotionListener(mousehandler);
	}

	public void initStartImages() {
		super.initStartImages();
		SheetLoader sheet;
		try {
			sheet = new SheetLoader(this.getClass().getResource("players.png"),
					8, 8, 16, 16);
			this.getImageLoader().ImportFromSheet("icon", sheet, 3, 0);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		BufferedImage screen = new BufferedImage(this.getWidth(),
				this.getHeight(), 4);
		Graphics g3 = screen.getGraphics();

		for (OtherRender render : this.getRenderClass()) {
			if (render.getInitStart())
				render.render(this, g3);
		}

		g.drawImage(screen, 0, 0, this);

	}
	
	
	
	public World getWorldOutOfString(String map){
		TestWorld world = new TestWorld();
		
		
		String[] strings = map.split(",");
		for(int i=0;i<strings.length;i++)
			strings[i] = map.split(",")[i];
		
		

		int yc = 0;

		for(int p=0;p<strings.length;p++){
			for(int d=0;d<strings[p].length();d++){
				 if(strings[p].charAt(d) == ' '){
					   
				   }else if(strings[p].charAt(d) == 'O'){
					   world.addEntity(new Wall(d*32,p*32,4));
				   }else if(strings[p].charAt(d) == 'P'){

					   world.setPlayer(new Player(this, d*32,p*32));
				   }else if(strings[p].charAt(d) == 'I'){
					   world.addEntity(new Wall(d*32,p*32,5));
				   }else if(strings[p].charAt(d) == 'E'){
					   world.addEntity(new Enemy(this,d*32,p*32));
				   }else if(strings[p].charAt(d) == 'J'){
					   world.addEntity(new Jumper(this, d*32,p*32));
				   }else if(strings[p].charAt(d) == 'G'){
					   world.addEntity(new Wall(d*32,p*32,2));
				   }else if(strings[p].charAt(d) == 'F'){
					   world.addEntity(new Wall(d*32,p*32,3));
				   }else if(strings[p].charAt(d) == 'X'){
					   world.addEntity(new Wall(d*32,p*32,6));
				   }else if(strings[p].charAt(d) == 'D'){
					   world.addEntity(new Wall(d*32,p*32,7));
				   }else if(strings[p].charAt(d) == 'C'){
					   world.addEntity(new Wall(d*32,p*32,8));
				   }else if(strings[p].charAt(d) == 'S'){
					   world.addEntity(new JumpBlock(d*32,p*32));
				   }else if(strings[p].charAt(d) == 'W'){
					   world.addEntity(new WoodBlock(this, d*32,p*32));
				   }else if(strings[p].charAt(d) == '0'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,true,0));
				   }else if(strings[p].charAt(d) == '1'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,false,0));
				   }else if(strings[p].charAt(d) == '2'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,true,1));
				   }else if(strings[p].charAt(d) == '3'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,false,1));
				   }else if(strings[p].charAt(d) == '4'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,true,2));
				   }else if(strings[p].charAt(d) == '5'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,false,2));
				   }else if(strings[p].charAt(d) == '6'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,true,3));
				   }else if(strings[p].charAt(d) == '7'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,false,3));
				   }else if(strings[p].charAt(d) == '8'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,true,4));
				   }else if(strings[p].charAt(d) == '9'){
					   world.addEntity(new DoorBlock(this,d*32,p*32,false,4));
				   }

			}
			yc++;
		}

		
		return world;
	}
	
	
	

	public void update(Graphics g) {
		BufferedImage screen = new BufferedImage(this.getWidth(),
				this.getHeight(), 4);
		Graphics g3 = screen.getGraphics();

		for (OtherRender render : this.getRenderClass()) {
			render.render(this, g3);
		}

		g.drawImage(screen, 0, 0, this);

	}

	@Override
	public void addKeys() {
		TestKeyHandler keyhandler = new TestKeyHandler(this);
		addKeyListener(keyhandler);
	}

	@Override
	public void addGameTick() {
		this.addTickClass(new TestTicks(this));
	}

	
	
	public static Gui getStartMenu(int x,int y){
		Gui gui = new Gui(x,y);
		
		
		


		
		



            int i = 0;
            {
			GuiButton button = new GuiButton(x,y+(128/4*4+128/4*i+128*i)){		
				@Override
				public void onClick(Engine engine) {
					if(!this.getOn()){
						engine.playSound(0,0, "click");
						this.start();
                    ((TestEngine)engine).setWorld( ((TestEngine)engine).getNextWorld(0));
						}
				}
				
				
				@Override
				public void initTextures() {
					this.setSize(new Size(this.getLocation(),128*4 ,128/4*4));
					  SheetLoader loader;
					try {
						
						loader = new SheetLoader(this.getClass().getResource("button.png"),4,1,128,128/4);
						  this.getIMGLoader().setOwnSize(128*4,128/4*4);
						  this.getIMGLoader().ImportFromSheet("button", loader, 0, 0);
						  this.getIMGLoader().ImportFromSheet("button2", loader, 1,0);

					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			};
			gui.addPart("b"+i, button);
            }
	
			
			
            i++;
            {
			GuiButton button = new GuiButton(x,y+(128/4*4+128/4*i+128*i)){		
				@Override
				public void onClick(Engine engine) {
					if(!this.getOn()){
						engine.playSound(0,0, "click");
						this.start();
                    ((TestEngine)engine).setWorld( ((TestEngine)engine).getNextWorld(1));
						}
				}
				
				
				@Override
				public void initTextures() {
					this.setSize(new Size(this.getLocation(),128*4 ,128/4*4));
					  SheetLoader loader;
					try {
						
						loader = new SheetLoader(this.getClass().getResource("button.png"),4,1,128,128/4);
						  this.getIMGLoader().setOwnSize(128*4,128/4*4);
						  this.getIMGLoader().ImportFromSheet("button", loader, 0, 0);
						  this.getIMGLoader().ImportFromSheet("button2", loader, 1,0);

					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			};
			gui.addPart("b"+i, button);
            }
            
             i++;
            {
			GuiButton button = new GuiButton(x,y+(128/4*4+128/4*i+128*i)){		
				@Override
				public void onClick(Engine engine) {
					if(!this.getOn()){
						engine.playSound(0,0, "click");
						this.start();
                    ((TestEngine)engine).setWorld( ((TestEngine)engine).getNextWorld(2));
						}
				}
				
				
				@Override
				public void initTextures() {
					this.setSize(new Size(this.getLocation(),128*4 ,128/4*4));
					  SheetLoader loader;
					try {
						
						loader = new SheetLoader(this.getClass().getResource("button.png"),4,1,128,128/4);
						  this.getIMGLoader().setOwnSize(128*4,128/4*4);
						  this.getIMGLoader().ImportFromSheet("button", loader, 0, 0);
						  this.getIMGLoader().ImportFromSheet("button2", loader, 1,0);

					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			};
			gui.addPart("b"+i, button);
            }
	
			
			
            i++;
            {
			GuiButton button = new GuiButton(x,y+(128/4*4+128/4*i+128*i)){		
				@Override
				public void onClick(Engine engine) {
					if(!this.getOn()){
						engine.playSound(0,0, "click");
						this.start();
                    ((TestEngine)engine).setWorld( ((TestEngine)engine).getNextWorld(3));
						}
				}
				
				
				@Override
				public void initTextures() {
					this.setSize(new Size(this.getLocation(),128*4 ,128/4*4));
					  SheetLoader loader;
					try {
						
						loader = new SheetLoader(this.getClass().getResource("button.png"),4,1,128,128/4);
						  this.getIMGLoader().setOwnSize(128*4,128/4*4);
						  this.getIMGLoader().ImportFromSheet("button", loader, 0, 0);
						  this.getIMGLoader().ImportFromSheet("button2", loader, 1,0);

					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			};
			gui.addPart("b"+i, button);
            }

		
		
		
		return gui;
	}
	@Override
	public void initWorld() {

		// this.setWorld(new TestWorld());
		this.setWorld(this.getNextWorld());
	}

	@Override
	public void addRenderClass() {
		this.addRenderClass(new EntityRender());
	}



	@Override
	public void initParticles() {
		SheetLoader sheet;
		try {
			sheet = new SheetLoader(
					this.getClass().getResource("particle.png"), 8, 8, 16, 16);
			this.getParticleLoader().ImportFromSheet("jump-green", sheet, 0, 0);
			this.getParticleLoader().ImportFromSheet("jump-red", sheet, 1, 0);
			this.getParticleLoader().ImportFromSheet("jump-blue", sheet, 4, 0);
			this.getParticleLoader().ImportFromSheet("splash-green", sheet, 2,0);
			this.getParticleLoader().ImportFromSheet("splash-red", sheet, 3, 0);
			this.getParticleLoader().ImportFromSheet("splash-blue", sheet, 5, 0);
			this.getParticleLoader().ImportFromSheet("splash-wood", sheet, 6, 0);
			this.getParticleLoader().ImportFromSheet("splash-door", sheet, 7, 0);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initClient() {
		// TODO Automatisch generierter Methodenstub

	}

	@Override
	public void initServer() {
		// TODO Automatisch generierter Methodenstub

	}

	public World getFirstWorld() {
		TestWorld world = new TestWorld();

		world.setPlayer(new Player(this, this.getStartLocation().getX(), this
				.getStartLocation().getY()));

		{

			for (int i = 0; i < 50; i++)
				if (i % 2 == 0)
					world.addEntity(new Wall(32 * i, 50, 2));
			for (int i = 0; i < 50; i++)
				if (!(i % 2 == 0))
					world.addEntity(new Wall(32 * i, 50, 3));

			for (int i = 0; i < 20; i++)
				world.addEntity(new Wall(-32, 50 - 32 * i, 5));
			for (int i = 0; i < 20; i++)
				world.addEntity(new Wall(32 * 50, 50 - 32 * i, 5));

			for (int i = 0; i < 10; i++)
				world.addEntity(new Wall(32 * i + 64, 50 - 96, 4));
			for (int i = 0; i < 5; i++)
				world.addEntity(new Enemy(this, 32 * 10 + 64, 50 - 96 - i * 32));

			for (int i = 0; i < 10; i++)
				world.addEntity(new Wall(32 * i + 64 * 10, 50 - 96, 4));
			for (int i = 0; i < 5; i++)
				world.addEntity(new Jumper(this, 32 * 10 + 64 * 10,
						50 - 96 - i * 32));

			for (int i = 0; i < 10; i++)
				world.addEntity(new Wall(32 * -2 + 64 + 32 * i, 50 - 96 - 96, 4));
			for (int i = 0; i < 5; i++)
				world.addEntity(new Enemy(this, 32 * -2 + 64 + 32 * i,
						50 - 96 - 96 - (i + 1 * 132)));

			world.addEntity(new Wall(32 * 50 - 32, 50 - 32, 6));
			// this.getWorld().addEntity(new Enemy(this,50,0));

		}
		return world;
	}

	public World getNextWorld(int id){
       curmission = id;
       return getNextWorld();
       
	}
	public World getNextWorld() {
		System.out.println(curmission);
		if (curmission == 0)
			return getFirstWorld();
		else if(curmission == 1){
			return getWorldOutOfString("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,"+
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
                                       "I                                                             I," +
	                                   "I                                                             I," +
	                                   "I                                                             I," +
	                                   "I                                                             I," +
	                                   "I                                                             I," +
	                                   "I                                                             I," +
	                                   "I                                                             I," +
	                                   "I                                      J                      I," +
	                                   "I                                          J                  I," +
	                                   "I                                     CCCCCC    WWWWWWW       I," +
	                                   "I                     S          E  E                         I," +
	                                   "I                CCCCCC         CCCCC           WWWWWWW       I," +
	                                   "I                                                             I," +
					                   "I                                               WWWWWWW       I," +
					                   "I      OOOOOOO       DDDDDDDDDDDD                             I," +
					                   "I             W      CCCCCCCCCCCC                             I," +
					                   "I              W                                              I," +
					                   "I               W                                             I," +
					                   "I                W                                  IIIIIIIIIII," +
					                   "I                                            W                I," +
					                   "I                                                          X  I," +
					                   "I   P      J      OOOOOOOO                  W   W             I," +
					                   "I         OOOO                         W     W     W    CCCCCCI," +
					                   "IOOOOOO                                                       I," +
					                   "IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDI," +
					                   "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,");
		}else if(curmission == 2){
			return getWorldOutOfString(
					"IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,"+
                    "I                                                             I," +
                    "I                  E   J                  E     J             I," +
                    "I      J  E E   J          J      E   J      J     E          I," +
                    "IWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWI," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                         X   I," +
                    "I                                                      OOOO   I," +
                    "I                                                    11       I," +
                    "I                                                   1         I," +
                    "I                                               1111          I," +
                    "I                                                             I," +
                    "I                        0 E             WWWW                 I," +
                    "I                        IIIIOO                               I," +
                    "I                                 WWWW                        I," +
                    "I                                                             I," +
                    "I                           WWWW                              I," +
                    "I     P  S                          WWWW                      I," +
                    "I    CCCCC                                                    I," +
                    "I                                    WWWW                     I," +
	                "I                              WWWW                           I," +
	                "I                      WWWWW                                  I," +
	                "I                                                             I," +
	                "I            S                 S                              I," +
	                "I        CCCCC             CCCCC                              I," +
	                "I                S    S                                       I," +
	                "I                IJJJJI                                       I," +
                    "I                IWWWWI                                       I," +
	                "I                I    I                                       I," +
	                "I                ISSSSI                                       I," +
	                "I                IIIIII                                       I," +
	                "IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDI," +
	                "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,");
}else if(curmission == 3){

	World world = getWorldOutOfString(
		         	"IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,"+
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                                                             I," +
		            "I                          I                                  I," +
		            "I                           IX                                I," +
		            "I                            II2                              I," +
		            "I                              I                              I," +
		            "I                               IIIIIIIO                      I," +
		            "I                                                             I," +
		            "I                             W          W                    I," +
		            "I                 J    11W11                                  I," +
		            "I             1  OOO            W      W                      I," +
		            "I             1                                               I," +
		            "I                                 11111                       I," +
		            "I           C                                                 I," +
		            "I           C                                                 I," +
		            "I              1                                              I," +
		            "I                 W                                           I," +
		            "I                    1                                        I," +
		            "I                       W                                     I," +
		            "I                          1   P                              I," +
		            "I                            CCCCCC         DDDDDD            I," +
		            "I                                          DIIIIIID           I," +
		            "I                                         DII   EIID          I," +
		            "I                                         III    III          I," +
		            "I                                         III    III          I," +
		            "I                                         III0   III          I," +
		            "IDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDIII3333IIIDDDDDDDDDDI," +
		            "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIDDDDIIIIIIIIIIIIII," +
		            "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,");	
	DoorBlock.setChannel(1, true);
	return world;
}
		
		curmission=0;
		return getFirstWorld();
	}
	
	
	/*
			return getWorldOutOfString(
					"IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,"+
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
                    "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
                    "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "I                                                             I," +
	                "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII,");
	 */

	@Override
	public void playSound(int x, int y, String name) {

			this.getSoundLoader().playClip(name);
	}

	@Override
	public void initSounds() {
		this.getSoundLoader().addSound("jump",
				this.getClass().getResource("jump.wav"));
		this.getSoundLoader().addSound("jump2",
				this.getClass().getResource("jump2.wav"));
		this.getSoundLoader().addSound("spring",
				this.getClass().getResource("spring.wav"));
		this.getSoundLoader().addSound("portal",
				this.getClass().getResource("portal.wav"));
		this.getSoundLoader().addSound("disappear",
				this.getClass().getResource("disappear.wav"));	
		this.getSoundLoader().addSound("click",
						this.getClass().getResource("click.wav"));

	}
}
