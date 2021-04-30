package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;

public class MainWindow extends JFrame {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
		Controller _ctrl;
		private final int width = 500;
		public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
		}
		
		private void initGUI() {
			
			/*
	    Dimension t = Toolkit.getDefaultToolkit().getScreenSize();
        int altura = t.height;
        int anchura = t.width;

       //  setSize(anchura/2, altura/2);
       // setBounds(0,anchura/4,0,anchura/4);
        
        */
        
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		ControlPanel controlPanel = new ControlPanel(_ctrl);
		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		
		//JPanel mid = new JPanel(new GridLayout(2,1,5,5));
		JPanel mid = new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		BodiesTable bodiestable = new BodiesTable(_ctrl);
		bodiestable.setPreferredSize(new Dimension(width, 150));
		bodiestable.setMaximumSize(new Dimension(2000, 100));
		mid.add(bodiestable);
		
		mainPanel.add(mid,BorderLayout.CENTER);
		Viewer viewer = new Viewer(_ctrl);
		viewer.setPreferredSize(new Dimension(width, 400));
		viewer.setMaximumSize(new Dimension(3000, 500));
		mid.add(viewer);
		
		
		StatusBar statusBar = new StatusBar(_ctrl);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);
		statusBar.setPreferredSize(new Dimension(width,40));
		
		setVisible(true);
		//setLocationRelativeTo(null);
		pack();
		
		}
		
		

}
