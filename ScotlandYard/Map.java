//1018x809
//for displaying the MAP.jpg

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Map {
	private int mapX = 0;
	private int mapY = 0;
	private int mapW = 1018;
	private int mapH = 809;
	Image img = null;
	try () {img = ImageIO.read(new File("map.jpg")}
	catch{IOException e) {}
	
	public Dimension getSize()
	{
		return new Dimension(mapW,mapH);
		
	}
	
	public void paint(Graphics g) {
	       g.drawImage(img, mapX, mapY, null);
	     }
	
}
