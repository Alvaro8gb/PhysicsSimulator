package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	private final static  String _currTimeTex = "Time : ";
	private final static String _currLawsTex = "Laws : " ;
	private final static String _numOfBodiesTex = "Bodies : ";
	private static final Color _BACK_COLOR = new Color(208, 213, 214);
	
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		
	setLayout( new FlowLayout( FlowLayout.LEFT ));
	setBorder( BorderFactory.createBevelBorder( 1 ));
	setBackground(_BACK_COLOR);
	
    
	JSeparator s = new JSeparator(SwingConstants.VERTICAL);
	s.setBackground(Color.black);
	
	JToolBar toolBar = new JToolBar();
	
	toolBar.setFloatable(false);
	
	toolBar.setBackground(new Color(215, 216, 216));
	
	_currTime = new JLabel(_currTimeTex);
	addLabel(_currTime,toolBar);
	add(s);
	
	_numOfBodies = new JLabel(_numOfBodiesTex);
	addLabel(_numOfBodies,toolBar);
	add(s);
	
	_currLaws = new JLabel(_currLawsTex);
	addLabel(_currLaws,toolBar);

	
	add(toolBar);

	}
	
	private void addLabel(JLabel label,JToolBar toolBar) {
	
	toolBar.add(label);
	toolBar.addSeparator(new Dimension(40,label.getWidth()));
		
	}

	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_currTime.setText(_currTimeTex + Double.toString(time));
		_numOfBodies.setText(_numOfBodiesTex + bodies.size());
		_currLaws.setText(_currLawsTex + fLawsDesc);
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_currLaws.setText(_currLawsTex + fLawsDesc);
		_currTime.setText(_currTimeTex + Double.toString(time));
		_numOfBodies.setText(_numOfBodiesTex + bodies.size());
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_numOfBodies.setText(_numOfBodiesTex + bodies.size());
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_currTime.setText(_currTimeTex + Double.toString(time));
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		_currLaws.setText(_currLawsTex + fLawsDesc);
		
		
	}

}
