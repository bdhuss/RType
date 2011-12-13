import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;


@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	//global variables
	private Timer timer;
	private Craft craft;
	private ArrayList<Alien> aliens;
	private boolean ingame;
	private int[][] pos = { 
	        {2380, 29}, {2500, 59}, {1380, 89},
	        {780, 109}, {580, 139}, {680, 239}, 
	        {790, 259}, {760, 50},  {790, 150},
	        {980, 209}, {560, 45},  {510, 70},
	        {930, 159}, {590, 80},  {530, 60},
	        {940, 59},  {990, 30},  {920, 200},
	        {900, 259}, {660, 50},  {540, 90},
	        {810, 220}, {860, 20},  {740, 180},
	        {820, 128}, {490, 170}, {700, 30}};
	
	//constructor
	public Board() {
		addKeyListener(new RTypeAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		ingame = true;
		
		craft = new Craft();
		
		initAliens();
		
		timer = new Timer(5, this);
		timer.start();
	}
	
	public void addNotify() {
		super.addNotify();
	}
	
	public void initAliens() {
		aliens = new ArrayList<Alien>();
		
		for (int x=0; x<pos.length; x++) {
			aliens.add(new Alien(pos[x][0], pos[x][1]));
		}
	}
	
	//paint override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (ingame) {
			Graphics2D g2d = (Graphics2D) g;
			
			//if the craft has not been destroyed
			if (craft.isVisible()) {
				//draw the craft
				g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
				
				//draw the missiles
				ArrayList<Missile> missiles = craft.getMissiles();
				for (int x=0; x<missiles.size(); x++) {
					Missile m = (Missile) missiles.get(x);
					g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
				}
				
				//draw the aliens
				for (int y=0; y<aliens.size(); y++) {
					Alien a = (Alien) aliens.get(y);
					if (a.isVisible()) {
						g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
					}
				}
				
				//draw a string notifying how many aliens are remaining
				g2d.setColor(Color.WHITE);
				g2d.drawString("Aliens left: "+aliens.size(), 5, 15);
			}
		}
		//game over
		else {
			String msg = "Game Over";
			Font font = new Font("Arial", Font.BOLD, 14);
			FontMetrics metr = this.getFontMetrics(font);
			
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString(msg, (RType.WIDTH - metr.stringWidth(msg)) / 2, RType.HEIGHT / 2);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	
	//action listener (IMPLEMENTED)
	public void actionPerformed(ActionEvent ae) {
		if (aliens.size() == 0) {
			ingame = false;
		}
		
		//update missiles
		ArrayList<Missile> missiles = craft.getMissiles();
		for (int x=0; x<missiles.size(); x++) {
			Missile m = (Missile) missiles.get(x);
			if (m.isVisible()) {
				m.move();
			}else {
				missiles.remove(x);
			}
		}
		
		//update aliens
		for (int y=0; y<aliens.size(); y++) {
			Alien a = (Alien) aliens.get(y);
			if (a.isVisible()) {
				a.move();
			}else {
				aliens.remove(y);
			}
		}
		
		craft.move();
		checkCollisions();
		repaint();
	}
	
	public void checkCollisions() {
		Rectangle craftBounds = craft.getBounds();
		//alien collides with users craft
		for (int i=0; i<aliens.size(); i++) {
			Alien a = (Alien) aliens.get(i);
			Rectangle alienBounds = a.getBounds();
			
			if (craftBounds.intersects(alienBounds)) {
				craft.setVisible(false);
				a.setVisible(false);
				ingame = false;
			}
		}
		
		ArrayList<Missile> missiles = craft.getMissiles();
		//missile collides with alien
		for (int y=0; y<missiles.size(); y++) {
			Missile m = (Missile) missiles.get(y);
			Rectangle missileBounds = m.getBounds();
			
			for (int z=0; z<aliens.size(); z++) {
				Alien a = (Alien) aliens.get(z);
				Rectangle alienBounds = a.getBounds();
				
				if (missileBounds.intersects(alienBounds)) {
					m.setVisible(false);
					a.setVisible(false);
				}
			}
		}
	}
	
	//custom KeyAdapter
	private class RTypeAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			craft.keyPressed(ke);
		}
		
		public void keyReleased(KeyEvent ke) {
			craft.keyReleased(ke);
		}
	}
}
