package simulator.view;


import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {


	private static final long serialVersionUID = 1L;

	private Controller _ctrl;
	private JToolBar toolBar;
	private JButton fileSelect,lawsSelect,run,stop,exit;
	private JFileChooser fileChooser;
	private JTextField deltaTimeBox;
	private JSpinner stepsSpinner;
	private ForceLawWindow forceLawWindow ;
	private static final int _DEFAULT_STEPS = 10000;
	private static final double _DEFAULT_DELTA_TIME = 2500;
	private RunThread runHebra;
	private static final Color _Button_Color = new Color(100, 198, 252);

	private class RunThread extends Thread {

		private int n ;
		
		RunThread (int n){
			this.n= n;
		}
		 public void run() {
	
		  try {
			  int i = 0;
				 while(i < n && !interrupted()) {
					 _ctrl.run(1);
					sleep(50); 
					 i++;
				 }
					 
				} catch (InterruptedException e) {	}
		 }
		
	}

	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		initGUI();
		_ctrl.addObserver(this);
	}
	private void initGUI() {
	
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		setAlignmentY(TOP_ALIGNMENT);
	
		runHebra = new RunThread(0);
		
		//Barra de botones
		toolBar = new JToolBar();
		toolBar.setOpaque(false);
		toolBar.setAlignmentX(LEFT_ALIGNMENT);
		add(toolBar);
		
		//Boton de selector de archivos
		fileSelect = createControlButton("open.png","Load a file","load");	
		fileSelect.setActionCommand("Select your file"); 
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		
		
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
		stop.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { stopAction(); }});
		toolBar.add(stop);

		toolBar.addSeparator();
		
		//Selector numero de pasos
		JPanel stepsPanel = new JPanel(); 
	    stepsPanel.setAlignmentX(CENTER_ALIGNMENT);
	    stepsPanel.setToolTipText("Change number of steps");
		JLabel stepsLabel = new JLabel("Steps: ");
		SpinnerNumberModel stepsModel = new SpinnerNumberModel(_DEFAULT_STEPS,100,1000000000,100);
		stepsSpinner = new JSpinner(stepsModel);
		stepsSpinner.setPreferredSize(new Dimension(80,30));
		stepsSpinner.setMaximumSize(new Dimension(80,30));

	     
	    stepsPanel.add(stepsLabel);
		stepsPanel.add(stepsSpinner);
		
		toolBar.add(stepsPanel);
		toolBar.addSeparator();
		 
		//Selector del tiempo entre pasos
		JPanel deltaTimePanel = new JPanel();
		deltaTimePanel.setAlignmentX(CENTER_ALIGNMENT);
		deltaTimePanel.setToolTipText("Change delta-time");
		JLabel deltaTimeText = new JLabel("Delta-Time: ");
		deltaTimeBox = new JTextField(Double.toString(_DEFAULT_DELTA_TIME));
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
	
		
		forceLawWindow = new ForceLawWindow(_ctrl.getForceLawsInfo());


	}
	private void run_sim(int n) {
		
		
		if ( n>0) {
			try{
				runHebra = new RunThread(n);
				runHebra.start();
			}catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Dialog",JOptionPane.ERROR_MESSAGE);
				runHebra.interrupt();
				enableToolBar(true);
			}
		}
		
	}
	private JButton createControlButton(String iconName,String toolTipMessage,String actionCommand) {
		JButton controlButton = new JButton();
		
		controlButton.setIcon(new ImageIcon("resources/icons/"+iconName)); 
		controlButton.setToolTipText(toolTipMessage); 
		controlButton.setActionCommand(actionCommand);
		controlButton.setBackground(_Button_Color);
		
		return controlButton;	
		
	}
		
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		deltaTimeBox.setText(Double.toString(dt));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		deltaTimeBox.setText(Double.toString(dt));
		
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
		deltaTimeBox.setText(Double.toString(dt));
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
	
		
	}
	private void selectFileAction() {
		 int returnVal = fileChooser.showOpenDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION) {
                 File file = fileChooser.getSelectedFile();
                 _ctrl.reset();
             try {
            	 _ctrl.loadBodies(new FileInputStream(file));
             } catch (Exception ex) {
                 System.err.println("Error");
                 JOptionPane.showMessageDialog(null, "An error occurred while loading  bodies file.","Error", JOptionPane.ERROR_MESSAGE);
             }
         }
	}
	private void selectLawAction() {
		
		try{	
			forceLawWindow.setVisible(true);
			JSONObject law = forceLawWindow.getSelectedLaw();
			if( law != null) _ctrl.setForceLaws(law);
		
		}catch(IllegalArgumentException e ) {
			JOptionPane.showMessageDialog(this.getParent(),e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	private void runAction() {
		
		 try{	
			 _ctrl.setDeltaTime( Double.parseDouble(deltaTimeBox.getText()));
			 enableToolBar(false);
			 stop.setEnabled(true);
			 run_sim((int)stepsSpinner.getValue());
		}catch(Exception e ) {
				JOptionPane.showMessageDialog(this.getParent(),e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
		}
		 
	}
	private void stopAction() {
		runHebra.interrupt();
		enableToolBar(true);
	}
	private void exitAction() {
		 int n = JOptionPane.showConfirmDialog(null, "You really want to exit PhysicsSimulator?", "Confirm exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(n == JOptionPane.YES_OPTION) System.exit(1);
	}
	
	 private void enableToolBar(boolean enable) {
		 	fileSelect.setEnabled(enable);
		 	lawsSelect.setEnabled(enable);
	        run.setEnabled(enable);
	        stepsSpinner.setEnabled(enable);
	        deltaTimeBox.setEnabled(enable);
	        exit.setEnabled(enable);
	        stop.setEnabled(enable);
	 }
	
}
