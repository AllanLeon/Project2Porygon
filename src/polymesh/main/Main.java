package polymesh.main;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main JFrame of the application
 */
public class Main extends JFrame implements KeyListener, ActionListener {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private BufferedImage doubleBuffer;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Main();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public Main() {
		initialize();
	}
	
	/**
	 * Initialize and set up the basic components of the frame.
	 */
	private void initialize() {
		setTitle("Porygon 3D");
		setSize(766, 600);
		setVisible(true);
		setResizable(false);
		setFocusable(true);
		addKeyListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel princPanel = new JPanel();
		princPanel.setBounds(10, 10, 550, 550);
		getContentPane().add(princPanel);
		princPanel.setBackground(Color.BLACK);

		JPanel topSPanel = new JPanel();
		topSPanel.setBounds(570, 10, 180, 180);
		getContentPane().add(topSPanel);
		topSPanel.setBackground(Color.BLACK);
		topSPanel.setOpaque(true);

		JPanel midSPanel = new JPanel();
		midSPanel.setBounds(570, 195, 180, 180);
		getContentPane().add(midSPanel);
		midSPanel.setBackground(Color.BLACK);

		JPanel botSPanel = new JPanel();
		botSPanel.setBounds(570, 380, 180, 180);
		getContentPane().add(botSPanel);
		botSPanel.setBackground(Color.BLACK);

		doubleBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		//Rotates Porygon clockwise in z
		case KeyEvent.VK_UP:
			break;
		//Rotates Porygon counter clockwise in z
		case KeyEvent.VK_DOWN:
			break;
		//Rotates Porygon counter clockwise in x
		case KeyEvent.VK_LEFT:
			break;
		//Rotates Porygon clockwise in x 
		case KeyEvent.VK_RIGHT:
			break;
		//Moves Porygon y+ 
		case KeyEvent.VK_W:
			break;
		//Moves Porygon y- 
		case KeyEvent.VK_S:
			break;
		//Moves Porygon x-
		case KeyEvent.VK_A:
			break;
		//Moves Porygon x+ 
		case KeyEvent.VK_D:
			break;
		//Enlarges Porygon 
		case KeyEvent.VK_Q:
			break;
		//Shrinks Porygon 
		case KeyEvent.VK_E:
			break;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_UP:
			break;
		case KeyEvent.VK_DOWN:
			break;
		case KeyEvent.VK_LEFT:
			break;
		case KeyEvent.VK_RIGHT:
			break;
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_A:
			break;
		case KeyEvent.VK_D:
			break;
		case KeyEvent.VK_Q:
			break;
		case KeyEvent.VK_E:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
	}
	
	public static int getWindowWidth() {
		return WIDTH;
	}
	
	public static int getWindowHeight() {
		return HEIGHT;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
