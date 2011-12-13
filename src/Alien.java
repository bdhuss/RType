import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Alien {
	//global variables
	private String alien = "images//alien.png";
	private int x, y;
	private boolean visible;
	private Image image;
	
	//constructor
	public Alien(int x, int y) {
		ImageIcon ii = new ImageIcon(getClass().getResource(alien));
		image = ii.getImage();
		visible = true;
		this.x = x;
		this.y = y;
	}
	
	public void move() {
		if (x < 0) {
			x = 400;
		}
		x -= 1;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}

}
