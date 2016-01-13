package graphics;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class MyWindow extends JFrame implements KeyListener{
//hi
	static int screenWidth;
	static int screenHeight;
	int width = 700;
	int height = 700;

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenWidth = (int) screenSize.getWidth();
		screenHeight = (int) screenSize.getHeight();
		new MyWindow();
	}

	public MyWindow(){
		setVisible(true);
		setSize(width,height);
		setLocation(screenWidth/2-width/2,screenHeight/2-height/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
	}

	Hero guy = new Hero("guy","/images/heros/hero.png",400,400);
	Hero girl = new Hero("girl","/images/heros/hero2.png",200,200);
	Hero potion = new Hero("potion","/images/heros/potion.png",100,100);

	public void paint(Graphics g){
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) g;
		//		g2.setColor(Color.white);
		//		g2.fillRect(0, 0, width, height);
		//		g2.setColor(Color.blue);
		//		g2.drawOval(50, 50, 150, 150);
		//		g2.drawArc(50, 50, 150, 130,225,90);
		//		g2.drawLine(90, 90, 90, 120);
		//		g2.drawLine(150, 90, 150, 120);
		//		int squareD = 10;
		//		int margin = 2;
		//		int shade = 255;
		//		for (int x = 0; x < width; x+=(squareD)){
		//			if (shade > 0) shade--;
		//			Color temp = new Color(x/3,x/3,shade);
		//			g2.setColor(temp);
		//			g2.drawLine(x, 0, height, x );
		//			g2.drawLine(0, x, x, height );
		//		}
		//			guy = new Hero("guy","/images/heros/hero.png",400,400);
		//			girl = new Hero("girl","/images/heros/hero2.png",200,200);
		g2.drawImage(guy.getSprite(),guy.getLocX(),guy.getLocY(),null);
		if (girl.getLocX()-potion.getLocX()+Math.abs(girl.getLocY()-potion.getLocY())<10 && !potion.pickedUp){
			potion.pickedUp = true;
			girl.moveSpeed+=10;
		}
		if (!potion.pickedUp) g2.drawImage(potion.getSprite(), potion.getLocX(), potion.getLocY(), null);
		g2.drawImage(girl.getSprite(),girl.getLocX(),girl.getLocY(),null);
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case 37: girl.moveLeft(); 
		break;
		case 38: girl.moveUp(); 
		break;
		case 39: girl.moveRight();
		break;
		case 40: girl.moveDown(); 
		break;
		}
		System.out.println(girl.getLocX());
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

	@Override
	public void keyTyped(KeyEvent e) {


	}
}

