package me.engine.render;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;


public class PictureLoader {
	HashMap<String, Image> list = new HashMap<String, Image>();
    public boolean useOwnSize=false;
    public int ownSizeX=0;
    public int ownSizeY=0;
	
	public PictureLoader() {
		list.clear();
	}
	
	public void setOwnSize(int x,int y){
		useOwnSize=true;
		ownSizeX=x;
		ownSizeY=y;
	}

	
	
	
	public void ImportFromSheet(String name,SheetLoader loader,int x,int y){
		Image img = loader.getImage(x, y);
		int sizex=(int)(img.getWidth(null));
		int sizey=(int)(img.getHeight(null));
		if(useOwnSize)
		{
	     sizex=ownSizeX;
	     sizey=ownSizeY;
		}
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}
	
	public void ImportFromSheet(String name,SheetLoader loader,int x,int y,boolean b){
		Image img = loader.getImage(x, y);

		list.put(name, img);
	}
	
	public void ImportFromSheet(String name,SheetLoader loader,int x,int y,int sizex,int sizey){
		Image img = loader.getImage(x, y);
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}
	
	
	
	public void addImage(String name, String pfad) {
		Image img = new ImageIcon(pfad).getImage();
		int sizex=(int)(img.getWidth(null));
		int sizey=(int)(img.getHeight(null));
		if(useOwnSize)
		{
	     sizex=ownSizeX;
	     sizey=ownSizeY;
		}
		Image img2 = resizeImage(img,img.getWidth(null)*sizex,img.getHeight(null)*sizey);
		list.put(name, img2);
	}
	
	public void addImage(String name, URL pfad) {
		Image img = new ImageIcon(pfad).getImage();
		
		
		//BufferedImage img2 = resizeImage(img,img.getWidth(null),img.getHeight(null));
		int sizex=(int)(img.getWidth(null));
		int sizey=(int)(img.getHeight(null));
		if(useOwnSize)
		{
	     sizex=ownSizeX;
	     sizey=ownSizeY;
		}
		Image img2 = resizeImage(img,img.getWidth(null)*sizex,img.getHeight(null)*sizey);
		
		list.put(name, (Image)img2);
	}

	public void addImage(String name, Image img) {
		list.put(name, img);
	}

	public Image getImage(String name) {
		if (list.containsKey(name))
			return list.get(name);
		else
			return null;
	}
	

	public Image removeImage(String name) {
		if (list.containsKey(name))
			return list.remove(name);
		else
			return null;
	}

	public HashMap<String, Image> getList() {
		return list;
	}

	public void setList(HashMap<String, Image> list) {
		this.list = list;
	}
	
	
	/*public void reinit(double sizex,double sizey){
		if(sizex < 1 || sizey < 1)
		return;
		for(String n:this.list.keySet())
		{
			Image img =  this.list.get(n);
			Image img2 = resizeImage(img,(int)(img.getWidth(null)*sizex),(int)(img.getHeight(null)*sizey));
			list.put(n,img2);
		}
	}*/
	
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, 3);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.SrcOver);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        try{
        image.flush();
        }catch(Exception e){
        	
        }
        return bufferedImage;
    }

	public void addRezizedImage(String name, Image img, int sizex, int sizey) {
		Image img2 = resizeImage(img,sizex,sizey);
		list.put(name, img2);
	}

}
