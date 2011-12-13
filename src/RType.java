import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class RType extends JFrame {
	//global variables
	public final static int WIDTH = 400;
	public final static int HEIGHT = 300;
	
	//constructor
	public RType() {
		add(new Board());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("RType");
		setVisible(true);
	}
	
	//! MAIN METHOD !
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			System.out.println("UIManager.getSystemLookAndFeelClassName() :: FAILED");
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RType();
			}
		});
	}
}
