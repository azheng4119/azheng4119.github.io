package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MyWindow extends JFrame implements KeyListener{
	int width = 1000;
	int height = 800;
	ArrayList<Hero> heros = new ArrayList<Hero>();
	Hero guy = new Hero("guy","/images/heros/hero.png",400,400);
	Hero girl = new Hero("girl","/images/heros/hero2.png",200,200);
	Hero potion = new Hero("potion","/images/heros/potion.png",100,100);
	Graphics2D g2;
	Hero selected = girl;

	public static void main(String[] args) {
		new MyWindow();

	}

	public MyWindow(){
		applySettings();//for JFrame-related methods
		setVisible(true);//makes the frame visible
		reset();//starts a game
	}

	private void reset() {
		
	}


	private void applySettings() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 1000;
		int height = 800;
		setSize(width,height);
		setLocation((int)(screenSize.getWidth()-width)/2, (int)(screenSize.getHeight()-height)/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		addKeyListener(this);

	}


	public void paint(Graphics g){
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) g;
		super.paintComponents(g);
		heros.add(guy);
		heros.add(girl);
		for (Hero h: heros){
			g2.setFont(new Font("default",16, 16));
			g2.drawImage(h.getSprite(),h.getLocX(),h.getLocY(),null);
			if (selected == h) g2.setFont(new Font("default", Font.BOLD, 16));
			g2.drawString(h.getHealth()+"",h.getLocX()+h.width/2,h.getLocY()-10);

		}
		if (girl.getLocX()-potion.getLocX()+Math.abs(girl.getLocY()-potion.getLocY())<10 && !potion.pickedUp){
			potion.pickedUp = true;
			girl.moveSpeed+=10;
		}
		if (!potion.pickedUp) g2.drawImage(potion.getSprite(), potion.getLocX(), potion.getLocY(), null);
		g.drawImage(image, 0, 0, null);
	}
	int x = 1;
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case 32: x++; selected = heros.get(x%2);
		break;
		case 37: selected.moveLeft(); 
		break;
		case 38: selected.moveUp(); 
		break;
		case 39: selected.moveRight();
		break;
		case 40: selected.moveDown(); 
		break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {


	}

	@Override
	public void keyTyped(KeyEvent e) {


	}
}


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