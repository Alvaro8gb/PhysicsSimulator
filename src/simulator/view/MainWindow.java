package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;

public class MainWindow extends JFrame {
		// ...
		Controller _ctrl;
		public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
		}
		
		private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		ControlPanel controlPanel = new ControlPanel(_ctrl);
		
		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		
		StatusBar statusBar = new StatusBar(_ctrl);
		
		mainPanel.add(statusBar, BorderLayout.PAGE_END);
		
		JPanel mid = new JPanel();
		mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
		Viewer viewer = new Viewer(_ctrl);
		viewer.setMaximumSize(new Dimension(3000, 500));
		mid.add(viewer);
		BodiesTable bodiestable = new BodiesTable(_ctrl);
		bodiestable.setMaximumSize(new Dimension(3000, 100));
		mid.add(bodiestable);
		mainPanel.add(mid,BorderLayout.CENTER);
		
		}
		
		public static void main(String args[]) {

			MainWindow j = new MainWindow(new Controller(new PhysicsSimulator(20,new NoForce()),null,null));
			  j.setVisible(true);
			  j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			  j.pack();
		   }
}
