package graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Hero {

	BufferedImage sprite;
	String name;
	int locX;
	int locY;
	int moveSpeed = 5;
	boolean pickedUp = false;;
	int width = 100;
	int height = 200;
	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Hero(String name, String imageLocation, int x, int y){
		this.name = name;
		locX = x;
		locY = y;
		sprite = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		URL url = getClass().getResource(imageLocation);
		try {
			BufferedImage original = ImageIO.read(url);
			Graphics2D g = (Graphics2D) sprite.getGraphics();
			int w = original.getWidth();
			int h = original.getHeight();
			g.drawImage(original, 0, 0, width, height, 0 , 0, w, h, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getLocX() {
		return locX;
	}

	public int getLocY() {
		return locY;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public String getName() {
		return name;
	}

	public void moveLeft() {
		this.locX-=moveSpeed;
		
	}
	public void moveRight() {
		this.locX+=moveSpeed;
		
	}
	public void moveUp() {
		this.locY-=moveSpeed;
		
	}
	public void moveDown() {
		this.locY+=moveSpeed;
		
	}

}
