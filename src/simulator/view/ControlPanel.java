package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver,ActionListener {


	private static final long serialVersionUID = 1L;

	private Controller _ctrl;
	private boolean _stopped;
	private JToolBar toolBar;
	private JButton fileSelect,lawsSelect,run,stop,exit;
	private JFileChooser fileChososer;
	private JPanel stepsPanel,deltaTimePanel;
	private final double defaultSteps= 10.0;
	
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	private void initGUI() {
	
		
		//Barra de botones
		toolBar = new JToolBar();
		toolBar.setOpaque(false);
		add(toolBar);
		
		//Boton de selector de archivos
		fileSelect = createControlButton("open.png","Load a file");	
		fileSelect.setActionCommand("Select your file"); 

		fileChososer = new JFileChooser();
		
		toolBar.add(fileSelect);
		toolBar.addSeparator();
		
		//Boton de selector de leyes
		lawsSelect = createControlButton("physics.png","Select a law");	
		toolBar.add(lawsSelect);
		toolBar.addSeparator();
		
		//Boton de run/play
		run = createControlButton("run.png","Run simulation");	
		toolBar.add(run);
		toolBar.addSeparator();
		
		//Boton de stop
		stop = createControlButton("stop.png","Stop simulation");	
		toolBar.add(stop);
		
		//Boton de exit
		exit = createControlButton("exit.png","Abort simulation");
		toolBar.add(exit);
		toolBar.addSeparator();
		
		//Selector numero de pasos
	    stepsPanel = new JPanel(); 
	    stepsPanel.setToolTipText("Change number of steps");
		JLabel stepsLabel = new JLabel("Steps: ");
		SpinnerNumberModel stepsModel = new SpinnerNumberModel(defaultSteps,0.0,100000000000.0,1.0);
		JSpinner steps = new JSpinner(stepsModel);
		steps.setPreferredSize(new Dimension(80,30));
		 
	    JSpinner.NumberEditor editor = (JSpinner.NumberEditor) steps.getEditor();
	    DecimalFormat format = editor.getFormat();
	    format.setMinimumFractionDigits(3);
	     
	    stepsPanel.add(stepsLabel);
		stepsPanel.add(steps);
		
		toolBar.add(stepsPanel);
		toolBar.addSeparator();
		 
		//Selector del tiempo entre pasos
		deltaTimePanel = new JPanel();
		stepsPanel.setToolTipText("Change delta-time");
		JLabel deltaTimeText = new JLabel("Delta-Time: ");
		JTextField deltaTimeBox = new JTextField("2500.0    ");
		deltaTimeBox.setAlignmentY(CENTER_ALIGNMENT);
		deltaTimeBox.setBounds(150,40,100,30); 
		deltaTimePanel.add(deltaTimeText);
		deltaTimePanel.add(deltaTimeBox);
		
		toolBar.add(deltaTimePanel);
		

		enableToolBar(true);
		setVisible(true);
	}
	private void run_sim(int n) {
		
		if ( n>0 && !_stopped ) {
			
		try {
		//_ctrl.run(1);
			
		} catch (Exception e) {
		// TODO show the error in a dialog box
		// TODO enable all buttons
		_stopped = true;
		}
			SwingUtilities.invokeLater( new Runnable() { 
				public void run() {
			   run_sim(n-1); } });
		}else {
			_stopped = true;
			// TODO enable all buttons
		}
		// SimulatorObserver methods
		// ...
	}
	private JButton createControlButton(String iconName,String toolTipMessage) {
		JButton controlButton = new JButton();
		
		controlButton.setIcon(new ImageIcon("resources/icons/"+iconName)); 
		controlButton.setToolTipText(toolTipMessage);
		controlButton.addActionListener(this); 
		
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
	
	@Override
	public void actionPerformed(ActionEvent event) {
		 if(event.getSource() == fileSelect){
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
		 else if( event.getSource() == exit) {
			 int n = JOptionPane.showConfirmDialog(null, "You really want to exit PhysicsSimulator?", "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(n == 0) System.exit(0);
		 }
		 
	}
	 private void enableToolBar(boolean enable) {
		 	fileSelect.setEnabled(enable);
		 	lawsSelect.setEnabled(enable);
	        run.setEnabled(enable);
	        exit.setEnabled(enable);
	    }
	
	  public static void main(String args[]) {

		  JFrame j = new JFrame();
		  j.add( new ControlPanel(new Controller(new PhysicsSimulator(20,new NoForce()),null,null)));
		  j.setVisible(true);
		  j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   }
	  

}
