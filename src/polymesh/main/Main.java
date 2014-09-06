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
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	public static final int BIG_PANEL_WIDTH = 550;
	public static final int BIG_PANEL_HEIGHT = 550;
	public static final int SMALL_PANEL_WIDTH = 180;
	public static final int SMALL_PANEL_HEIGHT = 180;
	
	private JPanel princPanel;
	private JPanel topSPanel;
	private JPanel midSPanel;
	private JPanel botSPanel;
	private BufferedImage doubleBuffer;
	private Polymesh porygon;
	private boolean keyPressed;
	public static State state;

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

		doubleBuffer = new BufferedImage(BIG_PANEL_WIDTH, BIG_PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
		initializePorygon();
		start();
	}	
	
	private void initializePorygon() {
		/*Point p1 = new Point(150, 500, 5);
		Point p2 = new Point(200, 200, 10);
		Edge e1 = new Edge(p1, p2);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addEdge(e1);
		porygon = new Polymesh();
		Point p1 = new Point(-50, -50, 50);
		Point p2 = new Point(-50, 50, 50);
		Point p3 = new Point(50, 50, 50);
		Point p4 = new Point(50, -50, 50);
		Point p5 = new Point(-50, -50, 150);
		Point p6 = new Point(-50, 50, 150);
		Point p7 = new Point(50, 50, 150);
		Point p8 = new Point(50, -50, 150);
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
		Polygon pol1 = new Polygon();
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
<<<<<<< HEAD
		porygon.addEdge(e12);*/
		porygon = new Polymesh();
		porygon.setCenter(new Point(0, 60, 45));
		toastLeftLeg();
		toastRightLeg();
		toastBody();		
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
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.zAxis, -Math.PI/18));
			break;
		//Rotates Porygon counter clockwise in z
		case KeyEvent.VK_2:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.zAxis, Math.PI/18));
			break;
		//Rotates Porygon counter clockwise in x
		case KeyEvent.VK_3:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.xAxis, -Math.PI/18));
			break;
		//Rotates Porygon clockwise in x 
		case KeyEvent.VK_4:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.xAxis, Math.PI/18));
			break;
		//Rotates Porygon counter clockwise in y
		case KeyEvent.VK_5:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.yAxis, -Math.PI/18));
			break;
		//Rotates Porygon clockwise in y
		case KeyEvent.VK_6:
			porygon.updateTransformation(TransformationManager.rotation3D(TransformationManager.yAxis, Math.PI/18));
			break;
		//Moves Porygon y+ 
		case KeyEvent.VK_W:
			porygon.updateTransformation(TransformationManager.translation3D(0, 1, 0));
			break;
		//Moves Porygon y- 
		case KeyEvent.VK_S:
			porygon.updateTransformation(TransformationManager.translation3D(0, -1, 0));
			break;
		//Moves Porygon x-
		case KeyEvent.VK_A:
			porygon.updateTransformation(TransformationManager.translation3D(-1, 0, 0));
			break;
		//Moves Porygon x+ 
		case KeyEvent.VK_D:
			porygon.updateTransformation(TransformationManager.translation3D(1, 0, 0));
			break;
		//Moves Porygon z+
		case KeyEvent.VK_Z:
			porygon.updateTransformation(TransformationManager.translation3D(0, 0, 1));
			break;
		//Moves Porygon z-
		case KeyEvent.VK_C:
			porygon.updateTransformation(TransformationManager.translation3D(0, 0, -1));
			break;
		//Enlarges Porygon 
		case KeyEvent.VK_Q:
			porygon.updateTransformation(TransformationManager.scaling3D(1.1, 1.1, 1.1));
			break;
		//Shrinks Porygon 
		case KeyEvent.VK_E:
			porygon.updateTransformation(TransformationManager.scaling3D(0.9, 0.9, 0.9));
			break;
		//Enlarges Porygon x 
		case KeyEvent.VK_T:
			porygon.updateTransformation(TransformationManager.scaling3D(1.1, 1, 1));
			break;
		//Shrinks Porygon x
		case KeyEvent.VK_Y:
			porygon.updateTransformation(TransformationManager.scaling3D(0.9, 1, 1));
			break;
		//Enlarges Porygon y
		case KeyEvent.VK_G:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 1.1, 1));
			break;
		//Shrinks Porygon y
		case KeyEvent.VK_H:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 0.9, 1));
			break;
		//Enlarges Porygon z
		case KeyEvent.VK_B:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 1, 1.1));
			break;
		//Shrinks Porygon z
		case KeyEvent.VK_N:
			porygon.updateTransformation(TransformationManager.scaling3D(1, 1, 0.9));
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
	
	private void toastLeftLeg() {
		//Front rectangle
		Polygon pol1 = new Polygon();
		Point p1 = new Point(50, 15, 20);
		Point p2 = new Point(50, 10, 20);
		Point p3 = new Point(95, 10, 20);
		Point p4 = new Point(95, 15, 20);
		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p2, p3);
		Edge e3 = new Edge(p3, p4);
		Edge e4 = new Edge(p1, p4);
		pol1.addEdge(e1);
		pol1.addEdge(e2);
		pol1.addEdge(e3);
		pol1.addEdge(e4);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addCorner(p3);
		porygon.addCorner(p4);
		porygon.addEdge(e1);
		porygon.addEdge(e2);
		porygon.addEdge(e3);
		porygon.addEdge(e4);
		porygon.addPolygon(pol1);
		//left rectangle
		Polygon pol2 = new Polygon();
		Point p5 = new Point(95, 52, 65);
		Point p6 = new Point(95, 35, 110);
		Point p7 = new Point(95, 10, 110);
		Edge e5 = new Edge(p4, p5);
		Edge e6 = new Edge(p5, p6);
		Edge e7 = new Edge(p6, p7);
		Edge e8 = new Edge(p3, p7);
		pol2.addEdge(e2);
		pol2.addEdge(e5);
		pol2.addEdge(e6);
		pol2.addEdge(e7);
		pol2.addEdge(e8);
		porygon.addCorner(p5);
		porygon.addCorner(p6);
		porygon.addCorner(p7);
		porygon.addEdge(e5);
		porygon.addEdge(e6);
		porygon.addEdge(e7);
		porygon.addEdge(e8);
		porygon.addPolygon(pol2);
		//back rectangle
		Polygon pol3 = new Polygon();
		Point p8 = new Point(50, 10, 110);
		Point p9 = new Point(50, 50, 110);
		Edge e9 = new Edge(p7, p8);
		Edge e10 = new Edge(p8, p9);
		Edge e11 = new Edge(p6, p9);
		pol3.addEdge(e9);
		pol3.addEdge(e10);
		pol3.addEdge(e11);
		pol3.addEdge(e7);
		porygon.addCorner(p8);
		porygon.addCorner(p9);
		porygon.addEdge(e9);
		porygon.addEdge(e10);
		porygon.addEdge(e11);
		porygon.addPolygon(pol3);
		//right rectangle
		Polygon pol4 = new Polygon();
		Point p10 = new Point(50, 65, 65);
		Edge e12 = new Edge(p9, p10);
		Edge e13 = new Edge(p10, p1);
		Edge e14 = new Edge(p2, p8);
		pol4.addEdge(e10);
		pol4.addEdge(e12);
		pol4.addEdge(e13);
		pol4.addEdge(e4);
		pol4.addEdge(e14);
		porygon.addCorner(p10);
		porygon.addEdge(e12);
		porygon.addEdge(e13);
		porygon.addEdge(e14);
		porygon.addPolygon(pol4);
		//top front
		Polygon pol5 = new Polygon();
		Edge e15 = new Edge(p5, p10);
		pol5.addEdge(e3);
		pol5.addEdge(e4);
		pol5.addEdge(e10);
		pol5.addEdge(e5);
		porygon.addEdge(e15);
		porygon.addPolygon(pol4);
		//back front
		Polygon pol6 = new Polygon();
		pol6.addEdge(e15);
		pol6.addEdge(e6);
		pol6.addEdge(e12);
		pol6.addEdge(e11);
		porygon.addPolygon(pol6);
		//bottom
		Polygon pol7 = new Polygon();
		pol7.addEdge(e15);
		pol7.addEdge(e6);
		pol7.addEdge(e12);
		pol7.addEdge(e11);
		porygon.addPolygon(pol7);
	}
	
	private void toastRightLeg() {
		//Front rectangle
		Polygon pol8 = new Polygon();
		Point p1 = new Point(-50, 15, 20);
		Point p2 = new Point(-50, 10, 20);
		Point p3 = new Point(-95, 10, 20);
		Point p4 = new Point(-95, 15, 20);
		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p2, p3);
		Edge e3 = new Edge(p3, p4);
		Edge e4 = new Edge(p1, p4);
		pol8.addEdge(e1);
		pol8.addEdge(e2);
		pol8.addEdge(e3);
		pol8.addEdge(e4);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addCorner(p3);
		porygon.addCorner(p4);
		porygon.addEdge(e1);
		porygon.addEdge(e2);
		porygon.addEdge(e3);
		porygon.addEdge(e4);
		porygon.addPolygon(pol8);
		//left rectangle
		Polygon pol9 = new Polygon();
		Point p5 = new Point(-95, 52, 65);
		Point p6 = new Point(-95, 35, 110);
		Point p7 = new Point(-95, 10, 110);
		Edge e5 = new Edge(p4, p5);
		Edge e6 = new Edge(p5, p6);
		Edge e7 = new Edge(p6, p7);
		Edge e8 = new Edge(p3, p7);
		pol9.addEdge(e2);
		pol9.addEdge(e5);
		pol9.addEdge(e6);
		pol9.addEdge(e7);
		pol9.addEdge(e8);
		porygon.addCorner(p5);
		porygon.addCorner(p6);
		porygon.addCorner(p7);
		porygon.addEdge(e5);
		porygon.addEdge(e6);
		porygon.addEdge(e7);
		porygon.addEdge(e8);
		porygon.addPolygon(pol9);
		//back rectangle
		Polygon pol10 = new Polygon();
		Point p8 = new Point(-50, 10, 110);
		Point p9 = new Point(-50, 50, 110);
		Edge e9 = new Edge(p7, p8);
		Edge e10 = new Edge(p8, p9);
		Edge e11 = new Edge(p6, p9);
		pol10.addEdge(e9);
		pol10.addEdge(e10);
		pol10.addEdge(e11);
		pol10.addEdge(e7);
		porygon.addCorner(p8);
		porygon.addCorner(p9);
		porygon.addEdge(e9);
		porygon.addEdge(e10);
		porygon.addEdge(e11);
		porygon.addPolygon(pol10);
		//right rectangle
		Polygon pol11 = new Polygon();
		Point p10 = new Point(-50, 65, 65);
		Edge e12 = new Edge(p9, p10);
		Edge e13 = new Edge(p10, p1);
		Edge e14 = new Edge(p2, p8);
		pol11.addEdge(e10);
		pol11.addEdge(e12);
		pol11.addEdge(e13);
		pol11.addEdge(e4);
		pol11.addEdge(e14);
		porygon.addCorner(p10);
		porygon.addEdge(e12);
		porygon.addEdge(e13);
		porygon.addEdge(e14);
		porygon.addPolygon(pol11);
		//top front
		Polygon pol12 = new Polygon();
		Edge e15 = new Edge(p5, p10);
		pol12.addEdge(e3);
		pol12.addEdge(e4);
		pol12.addEdge(e10);
		pol12.addEdge(e5);
		porygon.addEdge(e15);
		porygon.addPolygon(pol11);
		//back front
		Polygon pol13 = new Polygon();
		pol13.addEdge(e15);
		pol13.addEdge(e6);
		pol13.addEdge(e12);
		pol13.addEdge(e11);
		porygon.addPolygon(pol13);
		//bottom
		Polygon pol14 = new Polygon();
		pol14.addEdge(e15);
		pol14.addEdge(e6);
		pol14.addEdge(e12);
		pol14.addEdge(e11);
		porygon.addPolygon(pol14);
	}
	
	private void toastBody() {
		//bottom
		Polygon pol15 = new Polygon();
		Point p1 = new Point(-35, 10, 40);
		Point p2 = new Point(35, 10, 40);
		Point p3 = new Point(35, 10, 90);
		Point p4 = new Point(-35, 10, 90);
		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p2, p3);
		Edge e3 = new Edge(p3, p4);
		Edge e4 = new Edge(p1, p4);
		pol15.addEdge(e1);
		pol15.addEdge(e2);
		pol15.addEdge(e3);
		pol15.addEdge(e4);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addCorner(p3);
		porygon.addCorner(p4);
		porygon.addEdge(e1);
		porygon.addEdge(e2);
		porygon.addEdge(e3);
		porygon.addEdge(e4);
		porygon.addPolygon(pol15);
		//back
		Polygon pol16 = new Polygon();
		Point p5 = new Point(-40, 40, 110);
		Point p6 = new Point(40, 40, 110);
		Edge e5 = new Edge(p4, p5);
		Edge e6 = new Edge(p5, p6);
		Edge e7 = new Edge(p6, p3);
		pol16.addEdge(e3);
		pol16.addEdge(e5);
		pol16.addEdge(e6);
		pol16.addEdge(e7);
		porygon.addCorner(p5);
		porygon.addCorner(p6);
		porygon.addEdge(e5);
		porygon.addEdge(e6);
		porygon.addEdge(e7);
		porygon.addPolygon(pol16);
		//middle
		Polygon pol17 = new Polygon();
		Point p7 = new Point(50, 50, 20);
		Point p8 = new Point(-50, 50, 20);
		Edge e8 = new Edge(p6, p7);
		Edge e9 = new Edge(p7, p8);
		Edge e10 = new Edge(p8, p5);
		pol17.addEdge(e8);
		pol17.addEdge(e9);
		pol17.addEdge(e10);
		pol17.addEdge(e6);
		porygon.addCorner(p7);
		porygon.addCorner(p8);
		porygon.addEdge(e8);
		porygon.addEdge(e9);
		porygon.addEdge(e10);
		porygon.addPolygon(pol17);
		//right
		Polygon pol18 = new Polygon();
		Edge e11 = new Edge(p1, p8);
		pol18.addEdge(e10);
		pol18.addEdge(e11);
		pol18.addEdge(e4);
		pol18.addEdge(e5);
		porygon.addEdge(e11);
		porygon.addPolygon(pol18);
		//left
		Polygon pol19 = new Polygon();
		Edge e12 = new Edge(p2, p7);
		pol19.addEdge(e2);
		pol19.addEdge(e7);
		pol19.addEdge(e8);
		pol19.addEdge(e12);
		porygon.addEdge(e12);
		porygon.addPolygon(pol19);
		//front
		Polygon pol20 = new Polygon();
		pol20.addEdge(e1);
		pol20.addEdge(e12);
		pol20.addEdge(e9);
		pol20.addEdge(e11);
		porygon.addPolygon(pol20);
		//right triangle
		Polygon pol21 = new Polygon();
		Point p9 = new Point(-30, 75, 45);
		Point p10 = new Point(-10, 100, 40);
		Edge e13 = new Edge(p8, p10);
		Edge e14 = new Edge(p8, p9);
		Edge e15 = new Edge(p9, p10);
		pol21.addEdge(e13);
		pol21.addEdge(e14);
		pol21.addEdge(e15);
		porygon.addCorner(p9);
		porygon.addCorner(p10);
		porygon.addEdge(e13);
		porygon.addEdge(e14);
		porygon.addEdge(e15);
		porygon.addPolygon(pol21);
		//top
		Polygon pol22 = new Polygon();
		Point p11 = new Point(-10, 80, 90);
		Point p12 = new Point(10, 80, 90);
		Point p13 = new Point(10, 100, 40);
		Edge e16 = new Edge(p10, p11);
		Edge e17 = new Edge(p11, p12);
		Edge e18 = new Edge(p12, p13);
		Edge e19 = new Edge(p10, p13);
		pol22.addEdge(e16);
		pol22.addEdge(e17);
		pol22.addEdge(e18);
		pol22.addEdge(e19);
		porygon.addCorner(p11);
		porygon.addCorner(p12);
		porygon.addCorner(p13);
		porygon.addEdge(e16);
		porygon.addEdge(e17);
		porygon.addEdge(e18);
		porygon.addEdge(e19);
		porygon.addPolygon(pol22);
		//right top
		Polygon pol23 = new Polygon();
		Edge e20 = new Edge(p11, p5);
		pol23.addEdge(e13);
		pol23.addEdge(e16);
		pol23.addEdge(e20);
		pol23.addEdge(e10);
		porygon.addEdge(e20);
		porygon.addPolygon(pol23);
		//back top
		Polygon pol24 = new Polygon();
		Edge e21 = new Edge(p12, p6);
		pol24.addEdge(e6);
		pol24.addEdge(e20);
		pol24.addEdge(e17);
		pol24.addEdge(e21);
		porygon.addEdge(e21);
		porygon.addPolygon(pol24);
		//left top
		Polygon pol25 = new Polygon();
		Edge e22 = new Edge(p7, p13);
		pol25.addEdge(e22);
		pol25.addEdge(e18);
		pol25.addEdge(e21);
		pol25.addEdge(e8);
		porygon.addEdge(e22);
		porygon.addPolygon(pol25);
		//front top
		Polygon pol26 = new Polygon();
		pol26.addEdge(e13);
		pol26.addEdge(e19);
		pol26.addEdge(e22);
		pol26.addEdge(e9);
		porygon.addPolygon(pol26);
		//left triangle
		Polygon pol27 = new Polygon();
		Point p14 = new Point(30, 75, 45);
		Edge e23 = new Edge(p7, p14);
		Edge e24 = new Edge(p13, p14);
		pol27.addEdge(e22);
		pol27.addEdge(e23);
		pol27.addEdge(e24);
		porygon.addCorner(p14);
		porygon.addEdge(e23);
		porygon.addEdge(e24);
		porygon.addPolygon(pol27);	
	}

	private void toastTail() {
		//bottom
		Polygon pol15 = new Polygon();
		Point p1 = new Point(-35, 10, 40);
		Point p2 = new Point(35, 10, 40);
		Point p3 = new Point(35, 10, 90);
		Point p4 = new Point(-35, 10, 90);
		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p2, p3);
		Edge e3 = new Edge(p3, p4);
		Edge e4 = new Edge(p1, p4);
		pol15.addEdge(e1);
		pol15.addEdge(e2);
		pol15.addEdge(e3);
		pol15.addEdge(e4);
		porygon.addCorner(p1);
		porygon.addCorner(p2);
		porygon.addCorner(p3);
		porygon.addCorner(p4);
		porygon.addEdge(e1);
		porygon.addEdge(e2);
		porygon.addEdge(e3);
		porygon.addEdge(e4);
		porygon.addPolygon(pol15);
		//back
		Polygon pol16 = new Polygon();
		Point p5 = new Point(-40, 40, 110);
		Point p6 = new Point(40, 40, 110);
		Edge e5 = new Edge(p4, p5);
		Edge e6 = new Edge(p5, p6);
		Edge e7 = new Edge(p6, p3);
		pol16.addEdge(e3);
		pol16.addEdge(e5);
		pol16.addEdge(e6);
		pol16.addEdge(e7);
		porygon.addCorner(p5);
		porygon.addCorner(p6);
		porygon.addEdge(e5);
		porygon.addEdge(e6);
		porygon.addEdge(e7);
		porygon.addPolygon(pol16);
		//middle
		Polygon pol17 = new Polygon();
		Point p7 = new Point(50, 50, 20);
		Point p8 = new Point(-50, 50, 20);
		Edge e8 = new Edge(p6, p7);
		Edge e9 = new Edge(p7, p8);
		Edge e10 = new Edge(p8, p5);
		pol17.addEdge(e8);
		pol17.addEdge(e9);
		pol17.addEdge(e10);
		pol17.addEdge(e6);
		porygon.addCorner(p7);
		porygon.addCorner(p8);
		porygon.addEdge(e8);
		porygon.addEdge(e9);
		porygon.addEdge(e10);
		porygon.addPolygon(pol17);
		//right
		Polygon pol18 = new Polygon();
		Edge e11 = new Edge(p1, p8);
		pol18.addEdge(e10);
		pol18.addEdge(e11);
		pol18.addEdge(e4);
		pol18.addEdge(e5);
		porygon.addEdge(e11);
		porygon.addPolygon(pol18);
		//left
		Polygon pol19 = new Polygon();
		Edge e12 = new Edge(p2, p7);
		pol19.addEdge(e2);
		pol19.addEdge(e7);
		pol19.addEdge(e8);
		pol19.addEdge(e12);
		porygon.addEdge(e12);
		porygon.addPolygon(pol19);
		//front
		Polygon pol20 = new Polygon();
		pol20.addEdge(e1);
		pol20.addEdge(e12);
		pol20.addEdge(e9);
		pol20.addEdge(e11);
		porygon.addPolygon(pol20);
		//right triangle
		Polygon pol21 = new Polygon();
		Point p9 = new Point(-30, 75, 45);
		Point p10 = new Point(-10, 100, 40);
		Edge e13 = new Edge(p8, p10);
		Edge e14 = new Edge(p8, p9);
		Edge e15 = new Edge(p9, p10);
		pol21.addEdge(e13);
		pol21.addEdge(e14);
		pol21.addEdge(e15);
		porygon.addCorner(p9);
		porygon.addCorner(p10);
		porygon.addEdge(e13);
		porygon.addEdge(e14);
		porygon.addEdge(e15);
		porygon.addPolygon(pol21);
		//top
		Polygon pol22 = new Polygon();
		Point p11 = new Point(-10, 80, 90);
		Point p12 = new Point(10, 80, 90);
		Point p13 = new Point(10, 100, 40);
		Edge e16 = new Edge(p10, p11);
		Edge e17 = new Edge(p11, p12);
		Edge e18 = new Edge(p12, p13);
		Edge e19 = new Edge(p10, p13);
		pol22.addEdge(e16);
		pol22.addEdge(e17);
		pol22.addEdge(e18);
		pol22.addEdge(e19);
		porygon.addCorner(p11);
		porygon.addCorner(p12);
		porygon.addCorner(p13);
		porygon.addEdge(e16);
		porygon.addEdge(e17);
		porygon.addEdge(e18);
		porygon.addEdge(e19);
		porygon.addPolygon(pol22);
		//right top
		Polygon pol23 = new Polygon();
		Edge e20 = new Edge(p11, p5);
		pol23.addEdge(e13);
		pol23.addEdge(e16);
		pol23.addEdge(e20);
		pol23.addEdge(e10);
		porygon.addEdge(e20);
		porygon.addPolygon(pol23);
		//back top
		Polygon pol24 = new Polygon();
		Edge e21 = new Edge(p12, p6);
		pol24.addEdge(e6);
		pol24.addEdge(e20);
		pol24.addEdge(e17);
		pol24.addEdge(e21);
		porygon.addEdge(e21);
		porygon.addPolygon(pol24);
		//left top
		Polygon pol25 = new Polygon();
		Edge e22 = new Edge(p7, p13);
		pol25.addEdge(e22);
		pol25.addEdge(e18);
		pol25.addEdge(e21);
		pol25.addEdge(e8);
		porygon.addEdge(e22);
		porygon.addPolygon(pol25);
		//front top
		Polygon pol26 = new Polygon();
		pol26.addEdge(e13);
		pol26.addEdge(e19);
		pol26.addEdge(e22);
		pol26.addEdge(e9);
		porygon.addPolygon(pol26);
		//left triangle
		Polygon pol27 = new Polygon();
		Point p14 = new Point(30, 75, 45);
		Edge e23 = new Edge(p7, p14);
		Edge e24 = new Edge(p13, p14);
		pol27.addEdge(e22);
		pol27.addEdge(e23);
		pol27.addEdge(e24);
		porygon.addCorner(p14);
		porygon.addEdge(e23);
		porygon.addEdge(e24);
		porygon.addPolygon(pol27);	
	}	
}
