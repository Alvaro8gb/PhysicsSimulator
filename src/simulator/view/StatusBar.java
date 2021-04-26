package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	// ...
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		
	this.setLayout( new FlowLayout( FlowLayout.LEFT ));
	this.setBorder( BorderFactory.createBevelBorder( 1 ));
	this.setBackground(new Color(215, 216, 216));
	
	
	JToolBar toolBar = new JToolBar();
	
	_currTime = new JLabel();
	addLabel(_currTime,toolBar);
	_numOfBodies = new JLabel();
	addLabel(_numOfBodies,toolBar);
	_currLaws = new JLabel();
	addLabel(_currLaws,toolBar);

	toolBar.setFloatable(false);
	
	add(toolBar);
	
	
	}
	
	private void addLabel(JLabel label,JToolBar toolBar) {
	
		toolBar.add(label);
		toolBar.addSeparator(new Dimension(30,30));
		
	}
// TODO complete the code to build the tool bar
// other private/protected methods
// ...
// SimulatorObserver methods
// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_currTime.setText("Time : " + Double.toString(time));
		_numOfBodies.setText("Bodies : "+ bodies.size());
		_currLaws.setText("Laws : " + fLawsDesc);
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		_currLaws.setText("Laws : " + fLawsDesc);
		_currTime.setText("Time : " + Double.toString(time));
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		_currTime.setText("Time : " + Double.toString(time));
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		_currLaws.setText("Laws : " + fLawsDesc);
		
	}

	  public static void main(String args[]) {

		  JFrame j = new JFrame();
		  j.add( new StatusBar(new Controller(new PhysicsSimulator(20,new NoForce()),null,null)));
		  j.setVisible(true);
		  j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  j.pack();
	   }
}
