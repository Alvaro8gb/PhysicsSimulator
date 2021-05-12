package simulator.view;

import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {
	
	private static final long serialVersionUID = 1L;
	
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _showVectors;
	private final static String  helpText = "h: toggle help, v: toggle vectors, +: zoom-in, -: zoom-out, =: fit";
	private static final Color _BODY_COLOR = Color.BLUE;
	private static final Color _ID_COLOR = Color.BLACK;
	private static final Color _HELP_COLOR = Color.RED;

	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	
	}
	private void initGUI() {
		
		setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY, 2),"Viewer",TitledBorder.LEFT, TitledBorder.TOP));
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		_showVectors = true;

		addKeyListener(new KeyListener(){
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
					case '-':
						_scale = _scale * 1.1;
						repaint();
						break;
					case '+':
						_scale =   Math.max(1000.0, _scale / 1.1);
						repaint();
						break;
					case '=':
						autoScale();
						repaint();
						break;
					case 'h':
						_showHelp = !_showHelp;
						repaint();
					break;
					case 'v':
						_showVectors = !_showVectors;
						repaint();
					break;
					default:
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

		});
		
		
		addMouseListener(new MouseListener() {
			// ...
			@Override
			public void mouseEntered(MouseEvent e) {
			requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			});

	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// use ’gr’ to draw not ’g’ --- it gives nicer results
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		
		// TODO draw a cross at center
		gr.setColor(Color.RED);
		gr.drawLine(_centerX-5,_centerY,_centerX + 5,_centerY);
		gr.drawLine(_centerX,_centerY-5,_centerX ,_centerY + 5);
		
		// draw bodies
		drawBodies(gr);
		
		// TODO draw help if _showHelp is true
		if(_showHelp) showHelp(gr);    
	}
	
	private void showHelp(Graphics2D g) {
		g.setColor(_HELP_COLOR);
		g.drawString(helpText, 10, 25);
		g.drawString("Scaling ratio:  " + _scale, 10, 40);// TODO Auto-generated method stub
			
	}


	private void drawBodies(Graphics2D g) {
		
		for (Body b : _bodies) {
			Vector2D p = b.getPosition();
			int x = _centerX + (int) (p.getX() / _scale);
			int y = _centerY - (int) (p.getY() / _scale);

			if (_showVectors) showVectors(g, x, y, b);
			
			g.setColor(_BODY_COLOR);
			g.fillOval(x - 5, y - 5, 11, 11);

			g.setColor(_ID_COLOR);
			int tw = g.getFontMetrics().stringWidth(b.getId());
			g.drawString(b.getId(), x - tw / 2, y - 10);

		}
		
	}
	
	private void showVectors(Graphics2D g, int x, int y, Body b) {
		
		Vector2D v = b.getVelocity().direction().scale(20);
		int x2 = x + (int) v.getX();
		int y2 = y - (int) v.getY();
		drawLineWithArrow(g, x, y, x2, y2, 3, 3, Color.GREEN, Color.GREEN);

		Vector2D f = b.getForce().direction().scale(20);
		int x1 = x + (int) f.getX();
		int y1 = y - (int) f.getY();
		drawLineWithArrow(g, x, y, x1, y1, 3, 3, Color.RED, Color.RED);
		
	}
	
	private void autoScale() {
		double max = 1.0;
		
		for (Body b : _bodies) {
			Vector2D p = b.getPosition();
			max = Math.max(max, Math.abs(p.getX()));
			max = Math.max(max, Math.abs(p.getY()));
		}
		
		double size = Math.max(1.0, Math.min(getWidth(), getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
		
		}
	
	private void drawLineWithArrow(Graphics g,int x1, int y1,int x2, int y2,int w, int h,Color lineColor, Color arrowColor) {
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - w, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;
		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;
		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };
		g.setColor(lineColor);
		g.drawLine(x1, y1, x2, y2);
		g.setColor(arrowColor);
		g.fillPolygon(xpoints, ypoints, 3);

	}
	
	private void update(List<Body> bodies, boolean scale) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				_bodies = bodies;
				if (scale) {
					autoScale();
				}
				repaint();
			}
		});

	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		update(bodies,true);
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		update(bodies,true);
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		update(bodies,true);
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		update(bodies,false);
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
	
		
	}
	
}
