package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ForceLawWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Controller _ctrl;
	private List<JSONObject> options;
	private JComboBox<String> list;
	private ParametersTable table;
	private String fLawsDesc;
	
	public ForceLawWindow(Controller ctrl,String fLawsDesc){
		this._ctrl = ctrl;
		this.fLawsDesc = fLawsDesc;
		initGIU();
	}
	private void initGIU() {
		setLayout(new BorderLayout());
		setTitle("Force Laws Selection");
		setSize(650,350);
		setLocation(630,0);
		
		options = _ctrl.getForceLawsInfo();

		JLabel help = new JLabel("<html><p>Select a force law and provide values for the parametes in the <em>Value column</em> (default values are used for parametes with no value).</p></html>");
		add(help,BorderLayout.NORTH);
		 
		table = new ParametersTable(options.get(0));
		add(table,BorderLayout.CENTER);
		
		JPanel end = new JPanel();
		end.setLayout(new BoxLayout(end,BoxLayout.PAGE_AXIS));
	
			JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		 	
			JLabel info = new JLabel("Force Laws: ");
			optionsPanel.add(info);
			
			list = new JComboBox<String>();
			for(JSONObject x : options)  list.addItem(x.getString("desc"));
			setComboBox(fLawsDesc);
			
			list.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent e) { modifyTable(); }});
			optionsPanel.add(list);
			
			end.add(optionsPanel);
			
			JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			
			JButton buttonCancel =  createButton("Cancel");
			buttonCancel.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { dispose(); }});
			buttonsPanel.add(buttonCancel);
			
			JButton buttonOK =  createButton("OK");
			buttonOK.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { setLaw( list.getSelectedItem().toString()); }});
			buttonsPanel.add(buttonOK);
			
			end.add(buttonsPanel);
			
	    add(end,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		//pack();
	}
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setBackground(Color.WHITE);
		button.setMargin(null);
		return button;
	}
	private void modifyTable() {
		
		 for(JSONObject x : options) if(list.getSelectedItem().equals(x.getString("desc"))) table.setObj(x);
	}
	private JSONObject parseFLawsDesc(String info ) {
		JSONObject data = new JSONObject();
		for(JSONObject x : options) {
					
					if( info.equals(x.getString("desc"))) {
						data.put("type", x.getString("type"));
						data.put("data", createData());
						data.put("desc", x.getString("desc"));
						return data;
						
					}
		}
		return data;
	}
	private void setComboBox(String fLawsDesc) {
		int i = 0;
		System.out.println(fLawsDesc);
		for(JSONObject x : options)  {
			if( fLawsDesc.equals(x.getString("desc"))) list.setSelectedIndex(i);
			else i++;
		}
		
	}
	private void setLaw(String info) {
		
		//JOptionPane.showMessageDialog(this,info,"Ley", JOptionPane.WARNING_MESSAGE);
		
		try {
			_ctrl.setForceLaws(parseFLawsDesc(info));
			dispose();
		}catch(Exception e ) {
			JOptionPane.showMessageDialog(this,e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
		}
		
	}
	private JSONObject createData() {
		return table.createData();
	}

}
