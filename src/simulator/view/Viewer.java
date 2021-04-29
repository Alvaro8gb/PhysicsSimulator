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
import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {
	// ...
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	private boolean _showVectors;
	private String helpText;
	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	
	}
	private void initGUI() {
		// TODO add border with title
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
					_scale =  _scale / 1.1;
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
		setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.black, 2),"Viewer",TitledBorder.LEFT, TitledBorder.TOP));
		// use ’gr’ to draw not ’g’ --- it gives nicer results
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		// TODO draw a cross at center
		gr.setColor(Color.RED);
		gr.drawLine(_centerX,_centerY,_centerX + 10,_centerY);
		gr.drawLine(_centerX,_centerY,_centerX - 10,_centerY);
		gr.drawLine(_centerX,_centerY,_centerX,_centerY + 10);
		gr.drawLine(_centerX,_centerY,_centerX,_centerY - 10);
		// TODO draw bodies (with vectors if _showVectors is true)
		for(Body b: _bodies) {
			gr.setColor(Color.BLUE);
			int x = _centerX +(int)(b.getPosition().getX() /_scale);
			int y = _centerY -(int)(b.getPosition().getY() /_scale);
			int w = (int)(5 /_scale);
			int h = (int)(5 / _scale);
			gr.fillOval(x, y,w,h);
			gr.setColor(Color.BLACK);
			gr.drawString(b.getId(),x,y - 8);
			if(_showVectors) {
				int x1 = _centerX +(int)(b.getVelocity().getX() /_scale);
				int y1 = _centerY - (int)(b.getVelocity().getY() /_scale);
				drawLineWithArrow(gr,x,y,x1,y1,5,5,Color.GREEN,Color.GREEN);
				x1 = _centerX +(int)(b.getForce().getX() /_scale);
				y1 = _centerY - (int)(b.getForce().getY() /_scale);
				drawLineWithArrow(gr,x,y,x1,y1,5,5,Color.RED,Color.RED);
			}
		}
		// TODO draw help if _showHelp is true
		if(_showHelp) {
			helpText ="h: toggle help, v:toggle vectors, +:zoom in, -:zoom out, =:fit";
			gr.setColor(Color.RED);
			gr.drawString(helpText,6,30);
			helpText ="Scale ratio:" + _scale;
			gr.setColor(Color.RED);
			gr.drawString(helpText,6,45);
		}
	}
	private void drawArrows() {
		
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
	@Override
	public void onRegister(java.util.List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		autoScale();
		repaint();
	}

	@Override
	public void onReset(java.util.List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		autoScale();
		repaint();
	}

	@Override
	public void onBodyAdded(java.util.List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		autoScale();
		repaint();
	}

	@Override
	public void onAdvance(java.util.List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		repaint();
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
