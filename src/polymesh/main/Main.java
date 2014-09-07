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

import polymesh.framework.TransformationManager;

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
	private static final int WIDTH = 766;
	private static final int HEIGHT = 600;
	public static final int BIG_PANEL_WIDTH = 550;
	public static final int BIG_PANEL_HEIGHT = 550;
	public static final int SMALL_PANEL_WIDTH = 180;
	public static final int SMALL_PANEL_HEIGHT = 180;
	
	private static Polymesh porygon;
	public static State state;
	
	private JPanel princPanel;
	private JPanel topSPanel;
	private JPanel midSPanel;
	private JPanel botSPanel;
	private BufferedImage doubleBuffer;
	private boolean keyPressed;

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
		setSize(WIDTH, HEIGHT);
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

		doubleBuffer = new BufferedImage(BIG_PANEL_WIDTH, BIG_PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		initializePorygon();
		start();
	}	
	
	private void initializePorygon() {
		porygon = new Polymesh();
		Point p1 = new Point(-50, -50, 20);
		Point p2 = new Point(-50, 50, 20);
		Point p3 = new Point(50, 50, 20);
		Point p4 = new Point(50, -50, 20);
		Point p5 = new Point(-50, -50, 120);
		Point p6 = new Point(-50, 50, 120);
		Point p7 = new Point(50, 50, 120);
		Point p8 = new Point(50, -50, 120);
		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p2, p3);
		Edge e3 = new Edge(p3, p4);
		Edge e4 = new Edge(p4, p1);
		Edge e5 = new Edge(p5, p6);
		Edge e6 = new Edge(p6, p7);
		Edge e7 = new Edge(p7, p8);
		Edge e8 = new Edge(p8, p5);
		Edge e9 = new Edge(p1, p5);
		Edge e10 = new Edge(p2, p6);
		Edge e11 = new Edge(p3, p7);
		Edge e12 = new Edge(p4, p8);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addCorner(p3);
		porygon.addCorner(p4);
		porygon.addCorner(p5);
		porygon.addCorner(p6);
		porygon.addCorner(p7);
		porygon.addCorner(p8);
		porygon.addEdge(e1);
		porygon.addEdge(e2);
		porygon.addEdge(e3);
		porygon.addEdge(e4);
		porygon.addEdge(e5);
		porygon.addEdge(e6);
		porygon.addEdge(e7);
		porygon.addEdge(e8);
		porygon.addEdge(e9);
		porygon.addEdge(e10);
		porygon.addEdge(e11);
		porygon.addEdge(e12);
		porygon.setCenter(new Point(0, 0, 70));
	}
	
	private void start() {
		state = State.Drawing;
		keyPressed = false;
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
		dbg.fillRect(0, 0, BIG_PANEL_WIDTH, BIG_PANEL_HEIGHT);
		porygon.drawPerspective(dbg);
		princPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void paintFrontal(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, BIG_PANEL_WIDTH, BIG_PANEL_HEIGHT);
		porygon.drawFrontal(dbg);
		topSPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void paintSide(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, SMALL_PANEL_WIDTH, SMALL_PANEL_HEIGHT);
		porygon.drawSide(dbg);
		midSPanel.getGraphics().drawImage(doubleBuffer, 0, 0, this);
	}
	
	private void paintTop(Graphics dbg) {
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, SMALL_PANEL_WIDTH, SMALL_PANEL_HEIGHT);
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
		if (!keyPressed) {
			keyPressed = true;
		switch (ke.getKeyCode()) {
		//Rotates Porygon clockwise in z
		case KeyEvent.VK_1:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.zAxis, -Math.PI/18), false);
			break;
		//Rotates Porygon counter clockwise in z
		case KeyEvent.VK_2:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.zAxis, Math.PI/18), false);
			break;
		//Rotates Porygon counter clockwise in x
		case KeyEvent.VK_3:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.xAxis, -Math.PI/18), false);
			break;
		//Rotates Porygon clockwise in x 
		case KeyEvent.VK_4:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.xAxis, Math.PI/18), false);
			break;
		//Rotates Porygon counter clockwise in y
		case KeyEvent.VK_5:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.yAxis, -Math.PI/18), false);
			break;
		//Rotates Porygon clockwise in y
		case KeyEvent.VK_6:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.yAxis, Math.PI/18), false);
			break;
		//Moves Porygon y+ 
		case KeyEvent.VK_W:
			porygon.updateTransformation(TransformationManager.translation3D(0, 1, 0), true);
			break;
		//Moves Porygon y- 
		case KeyEvent.VK_S:
			porygon.updateTransformation(TransformationManager.translation3D(0, -1, 0), true);
			break;
		//Moves Porygon x-
		case KeyEvent.VK_A:
			porygon.updateTransformation(TransformationManager.translation3D(-1, 0, 0), true);
			break;
		//Moves Porygon x+ 
		case KeyEvent.VK_D:
			porygon.updateTransformation(TransformationManager.translation3D(1, 0, 0), true);
			break;
		//Moves Porygon z+
		case KeyEvent.VK_Z:
			porygon.updateTransformation(TransformationManager.translation3D(0, 0, 1), true);
			break;
		//Moves Porygon z-
		case KeyEvent.VK_C:
			porygon.updateTransformation(TransformationManager.translation3D(0, 0, -1), true);
			break;
		//Enlarges Porygon 
		case KeyEvent.VK_Q:
			porygon.updateTransformation(TransformationManager.scaling3D(1.1, 1.1, 1.1), false);
			break;
		//Shrinks Porygon 
		case KeyEvent.VK_E:
			porygon.updateTransformation(TransformationManager.scaling3D(0.9, 0.9, 0.9), false);
			break;
		//Enlarges Porygon x 
		case KeyEvent.VK_T:
			porygon.updateTransformation(TransformationManager.scaling3D(1.1, 1, 1), false);
			break;
		//Shrinks Porygon x
		case KeyEvent.VK_Y:
			porygon.updateTransformation(TransformationManager.scaling3D(0.9, 1, 1), false);
			break;
		//Enlarges Porygon y
		case KeyEvent.VK_G:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 1.1, 1), false);
			break;
		//Shrinks Porygon y
		case KeyEvent.VK_H:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 0.9, 1), false);
			break;
		//Enlarges Porygon z
		case KeyEvent.VK_B:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 1, 1.1), false);
			break;
		//Shrinks Porygon z
		case KeyEvent.VK_N:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 1, 0.9), false);
			break;
		}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent key) {
		keyPressed = false;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
	}
	
	public static Polymesh getPorygon() {
		return porygon;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		run();
	}
}
