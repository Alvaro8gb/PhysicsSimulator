package simulator.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
		
		private Controller _ctrl;
		private static final int _WIDTH = 700;
		private static final int _HEIGHT = 1000;
		
		public MainWindow(Controller ctrl) {
			super("Physics Simulator");
			_ctrl = ctrl;
			initGUI();
		}
		
		private void initGUI() {
			
		    Dimension t = Toolkit.getDefaultToolkit().getScreenSize();
	        int altura = t.height;
	        int anchura = t.width;
	
	        setBounds(Math.max(anchura/2 - _WIDTH/2 ,0), Math.max(altura/2 - _HEIGHT/2,0) , _WIDTH, altura/2 + altura/3);
	        setPreferredSize(this,_WIDTH, altura/2 + altura/3);
	
	        
			JPanel mainPanel = new JPanel(new BorderLayout());
			setContentPane(mainPanel);
			
			ControlPanel controlPanel = new ControlPanel(_ctrl);
			mainPanel.add(controlPanel, BorderLayout.PAGE_START);
			
			JPanel mid = new JPanel();
			mid.setLayout(new BoxLayout(mid,BoxLayout.Y_AXIS));
			
			BodiesTable bodiestable = new BodiesTable(_ctrl);
			setPreferredSize(bodiestable,_WIDTH,150);
			
			mid.add(bodiestable);
			
			Viewer viewer = new Viewer(_ctrl);
			
			mid.add(viewer);
			
			mainPanel.add(mid,BorderLayout.CENTER);
			
			StatusBar statusBar = new StatusBar(_ctrl);
			mainPanel.add(statusBar, BorderLayout.PAGE_END);
			setPreferredSize(statusBar,_WIDTH,40);
	
			setVisible(true);
			
		}
		
		private void setPreferredSize(Container container,int width,int _height ) {
			container.setPreferredSize(new Dimension(width,_height));
			container.setMaximumSize(new Dimension(width,_height));	
		}
}
