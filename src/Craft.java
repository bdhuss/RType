import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Craft {
	//global variables
	private final int LEFT = KeyEvent.VK_LEFT;
	private final int RIGHT = KeyEvent.VK_RIGHT;
	private final int UP = KeyEvent.VK_UP;
	private final int DOWN = KeyEvent.VK_DOWN;
	private final int SPACE = KeyEvent.VK_SPACE;
	private String craft = "images//craft.png";
	private int x, y, dx, dy, width, height;
	private Image image;
	private ArrayList<Missile> missiles;
	private boolean visible;

	//constructor
	public Craft() {
		ImageIcon ii = new ImageIcon(getClass().getResource(craft));
		image = ii.getImage();
		x = 40;
		y = 60;
		width = image.getWidth(null);
		height = image.getHeight(null);
		missiles = new ArrayList<Missile>();
		visible = true;
	}

	public void move() {
		if (x + width >= RType.WIDTH) {
			x = (RType.WIDTH - width) - 1;
		}
		else if (y + height + 25 >= RType.HEIGHT) {
			y = (RType.HEIGHT - height - 25) - 1;
		}
		else {
			x = Math.abs(x + dx);
			y = Math.abs(y + dy);
		}
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
	
	public ArrayList<Missile> getMissiles() {
		return missiles;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void keyPressed(KeyEvent ke) {
		int key = ke.getKeyCode();

		if (key == SPACE) {
			fire();
		}
		if (key == LEFT) {
			dx = -1;
		}
		if (key == RIGHT) {
			dx = 1;
		}
		if (key == UP) {
			dy = -1;
		}
		if (key == DOWN) {
			dy = 1;
		}
	}

	public void keyReleased(KeyEvent ke) {
		int key = ke.getKeyCode();

		if (key == LEFT || key == RIGHT) {
			dx = 0;
		}
		if (key == UP || key == DOWN) {
			dy = 0;
		}
	}
	
	public void fire() {
		missiles.add(new Missile(x + width, y + height/2));
	}
}
