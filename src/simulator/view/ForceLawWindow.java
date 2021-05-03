package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

import simulator.control.Controller;

public class ForceLawWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private List<JSONObject> options;
	
	ForceLawWindow(Controller ctrl){
		this._ctrl = ctrl;	
		initGIU();
	}
	private void initGIU() {
		setLayout(new BorderLayout());
		setVisible(true);
		
		setTitle("Force Laws Selection");

		JLabel help = new JLabel("<html><p>Select a force law and provide values for the parametes in the <em>Value column</em> (default values are used for parametes with no value).</p></html>");
		add(help,BorderLayout.NORTH);
		 
	
		JPanel mid = new JPanel(new BorderLayout());
		 
			JPanel middown = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			 	
			JLabel info = new JLabel("Force Laws: ");
			middown.add(info);
			
			JComboBox<String> list = new JComboBox<String>();
			options = _ctrl.getForceLawsInfo();
			for(JSONObject x : options)  list.addItem(x.getString("desc"));
			middown.add(list);
		
		mid.add(middown,BorderLayout.SOUTH);
		
		ParametersTable table = new ParametersTable( _ctrl.getForceLawsInfo().get(0));
		
		mid.add(table,BorderLayout.NORTH);
		
		add(mid,BorderLayout.CENTER);
		
		
		JPanel end = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		
		JButton buttonCancel =  createButton("Cancel");
		buttonCancel.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { dispose(); }});
		end.add(buttonCancel);
		
		JButton buttonOK =  createButton("OK");
		buttonOK.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { setLaw( list.getSelectedItem().toString()); }});
		end.add(buttonOK);
		add(end,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setBackground(Color.WHITE);
		button.setMargin(null);
		return button;
	}
	private void setLaw(String info) {
		

		for(JSONObject x : options) if( info.equals(x.getString("desc"))) _ctrl.setForceLaws(x);
		
		dispose();
		
	}
}
