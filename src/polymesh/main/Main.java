package polymesh.main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Main JFrame of the application
 */
public class Main extends JFrame implements KeyListener, ActionListener {
		
	enum State {
		Waiting, Drawing
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
	private JPanel princPanel;
	private JPanel topSPanel;
	private JPanel midSPanel;
	private JPanel botSPanel;
	private BufferedImage doubleBuffer;
	private Polymesh porygon;
	private State state;

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
		
		princPanel = new JPanel();
		princPanel.setBounds(10, 10, 550, 550);
		getContentPane().add(princPanel);
		princPanel.setBackground(Color.BLACK);

		topSPanel = new JPanel();
		topSPanel.setBounds(570, 10, 180, 180);
		getContentPane().add(topSPanel);
		topSPanel.setBackground(Color.BLACK);
		topSPanel.setOpaque(true);

		midSPanel = new JPanel();
		midSPanel.setBounds(570, 195, 180, 180);
		getContentPane().add(midSPanel);
		midSPanel.setBackground(Color.BLACK);

		botSPanel = new JPanel();
		botSPanel.setBounds(570, 380, 180, 180);
		getContentPane().add(botSPanel);
		botSPanel.setBackground(Color.BLACK);

		doubleBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		initializePorygon();
		start();
	}	
	
	private void initializePorygon() {
		porygon = new Polymesh();
		Point p1 = new Point(150, 500, 1);
		Point p2 = new Point(200, 200, 1);
		Edge e1 = new Edge(p1, p2);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addEdge(e1);
		
	}
	
	private void start() {
		state = State.Drawing;
		Timer timer = new Timer(1000/60, this);
		timer.start();
	}

	private void paint() {
		Graphics dbg = doubleBuffer.getGraphics();
		paintPerspective(dbg);
		paintFrontal(dbg);
		paintSide(dbg);
		paintTop(dbg);
		state = State.Waiting;
	}
	
	private void paintPerspective(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);
		porygon.drawPerspective(dbg);
		princPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void paintFrontal(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);
		porygon.drawFrontal(dbg);
		topSPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void paintSide(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);
		porygon.drawSide(dbg);
		midSPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void paintTop(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);
		porygon.drawTop(dbg);
		botSPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void run() {
		if (state == State.Drawing) {
			paint();
		}
	}
	
	@Override
	public void update(Graphics g) {}

	
	@Override
	public void paint(Graphics g) {}

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
		run();
	}
}
