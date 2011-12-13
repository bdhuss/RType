import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Missile {
	//global variables
	private final int MISSILE_SPEED = 2;
	private String missile = "images//missile.png";
	private int x, y;
	private Image image;
	private boolean visible;
	
	//constructor
	public Missile(int x, int y) {
		ImageIcon ii = new ImageIcon(getClass().getResource(missile));
		image = ii.getImage();
		visible = true;
		this.x = x;
		this.y = y;
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
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}
	
	public void move() {
		x += MISSILE_SPEED;
		if (x > RType.WIDTH) {
			visible = false;
		}
	}
	
	
}
