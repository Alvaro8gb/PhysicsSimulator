package simulator.view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.factories.Builder;
import simulator.factories.BuilderBasedFactory;
import simulator.factories.CircularAleatoryForceBuilder;
import simulator.factories.MovingTowardsFixedPointBuilder;
import simulator.factories.NewtonUniversalGravitationBuilder;
import simulator.factories.NoForceBuilder;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {


	private static final long serialVersionUID = 1L;

	private Controller _ctrl;
	private boolean _stopped;
	private JToolBar toolBar;
	private JButton fileSelect,lawsSelect,run,stop,exit;
	private JFileChooser fileChososer;
	private JTextField deltaTimeBox;
	private JSpinner stepsSpinner;
	private JPanel stepsPanel,deltaTimePanel;
	private final int defaultSteps = 10000;
	private final double defaultDeltaTime = 2500;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	private void initGUI() {
	
		
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		setAlignmentY(TOP_ALIGNMENT);

		//Barra de botones
		toolBar = new JToolBar();
		toolBar.setOpaque(false);
		toolBar.setAlignmentX(LEFT_ALIGNMENT);
		add(toolBar);
		
		//Boton de selector de archivos
		fileSelect = createControlButton("open.png","Load a file","load");	
		fileSelect.setActionCommand("Select your file"); 
		fileChososer = new JFileChooser();
		
		fileSelect.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { selectFileAction(); }});

		
		toolBar.add(fileSelect);
		toolBar.addSeparator();
		
		//Boton de selector de leyes
		lawsSelect = createControlButton("physics.png","Select a law","lawSelect");	
		lawsSelect.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { selectLawAction(); }});
		toolBar.add(lawsSelect);
		
		
		
		toolBar.addSeparator();
		
		//Boton de run/play
		run = createControlButton("run.png","Run simulation","run");	
		run.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { runAction(); }});
		toolBar.add(run);
		
		//Boton de stop
		stop = createControlButton("stop.png","Stop simulation","stop");	
		stop.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { _stopped = true; }});
		toolBar.add(stop);

		
	
		//Selector numero de pasos
	    stepsPanel = new JPanel(); 
	    stepsPanel.setAlignmentX(CENTER_ALIGNMENT);
	    stepsPanel.setToolTipText("Change number of steps");
		JLabel stepsLabel = new JLabel("Steps: ");
		SpinnerNumberModel stepsModel = new SpinnerNumberModel(defaultSteps,0,1000000000,1);
		stepsSpinner = new JSpinner(stepsModel);
		stepsSpinner.setPreferredSize(new Dimension(80,30));
		stepsSpinner.setMaximumSize(new Dimension(80,30));

	     
	    stepsPanel.add(stepsLabel);
		stepsPanel.add(stepsSpinner);
		
		toolBar.add(stepsPanel);
		toolBar.addSeparator();
		 
		//Selector del tiempo entre pasos
		deltaTimePanel = new JPanel();
		deltaTimePanel.setAlignmentX(CENTER_ALIGNMENT);
		deltaTimePanel.setToolTipText("Change delta-time");
		JLabel deltaTimeText = new JLabel("Delta-Time: ");
		deltaTimeBox = new JTextField(Double.toString(defaultDeltaTime));
		deltaTimeBox.setAlignmentY(CENTER_ALIGNMENT);
		deltaTimeBox.setPreferredSize(new Dimension(80,30));
		deltaTimeBox.setMaximumSize(new Dimension(80,30));
		deltaTimePanel.add(deltaTimeText);
		deltaTimePanel.add(deltaTimeBox);
		
		toolBar.add(deltaTimePanel);
		toolBar.addSeparator();
		
		toolBar.add(Box.createHorizontalGlue());
		
		
		//Boton de exit
		exit = createControlButton("exit.png","Abort simulation","exit");
		exit.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { exitAction(); }});

		toolBar.add(exit);
	

		setVisible(true);

	}
	private void run_sim(int n) {
		
		if ( n>0 && !_stopped ) {
			
			try {
			_ctrl.run(1);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",JOptionPane.ERROR_MESSAGE);
				_stopped = true;
				enableToolBar(true);
			}
		
			SwingUtilities.invokeLater( new Runnable() { 
				public void run() { 
					run_sim(n-1); 
				} 
		    });
			
			
		}else {
			_stopped = true;
			enableToolBar(true);
		}


	}
	private JButton createControlButton(String iconName,String toolTipMessage,String actionCommand) {
		JButton controlButton = new JButton();
		
		controlButton.setIcon(new ImageIcon("resources/icons/"+iconName)); 
		controlButton.setToolTipText(toolTipMessage);
		//controlButton.addActionListener(this); 
		controlButton.setActionCommand(actionCommand);
		
		return controlButton;
		
	}
		
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	private void selectFileAction() {
		 int returnVal = fileChososer.showOpenDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
                 File file = fileChososer.getSelectedFile();
                 _ctrl.reset();
             try {
            	 _ctrl.loadBodies(new FileInputStream(file));
             } catch (Exception ex) {
                 System.err.println("Error");
                 JOptionPane.showMessageDialog(new JFrame(), "An error occurred while loading  bodies file.","Error", JOptionPane.ERROR_MESSAGE);
             }
         }
	}
	private void selectLawAction() {
		new ForceLawWindow(_ctrl);
	}
	private void runAction() {
		 _stopped = false;
		 enableToolBar(false);
		 stop.setEnabled(true); //Todos desactivados menos Stop
		 
		 _ctrl.setDeltaTime( Double.parseDouble(deltaTimeBox.getText()));
		 
		 run_sim((int)stepsSpinner.getValue());
	}
	private void exitAction() {
		 int n = JOptionPane.showConfirmDialog(null, "You really want to exit PhysicsSimulator?", "Confirm exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(n == 0) System.exit(0);
	}
	
	 private void enableToolBar(boolean enable) {
		 	fileSelect.setEnabled(enable);
		 	lawsSelect.setEnabled(enable);
	        run.setEnabled(enable);
	        exit.setEnabled(enable);
	        stop.setEnabled(enable);
	    }
	
}
