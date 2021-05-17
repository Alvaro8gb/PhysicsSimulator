package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

public class ForceLawWindow extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private List<JSONObject> _options;
	private JComboBox<String> list;
	private ParametersTable table;
	private JSONObject _law;
	
	public ForceLawWindow( List<JSONObject> options){
		_options = options;
		initGIU();
	}
	private void initGIU() {
		setLayout(new BorderLayout());
		setTitle("Force Laws Selection");
		setSize(700,350);
		setModal(true); 
		setLocationRelativeTo(this.getParent());
		

		JLabel help = new JLabel("<html><p>Select a force law and provide values for the parametes in the <em>Value column</em> (default values are used for parametes with no value).</p></html>");
		add(help,BorderLayout.NORTH);
		 
		table = new ParametersTable(_options.get(0));
		add(table,BorderLayout.CENTER);
		
		JPanel end = new JPanel();
		end.setLayout(new BoxLayout(end,BoxLayout.PAGE_AXIS));
	
			JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
		 	
			JLabel info = new JLabel("Force Laws: ");
			optionsPanel.add(info);
			
			list = new JComboBox<String>();
			for(JSONObject x : _options)  list.addItem(x.getString("desc"));

			list.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent e) { modifyTable(); }});
			optionsPanel.add(list);
			
			end.add(optionsPanel);
			
			JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
			
			JButton buttonCancel =  createButton("Cancel");
			buttonCancel.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { setVisible(false); }});
			buttonsPanel.add(buttonCancel);
			
			JButton buttonOK =  createButton("OK");
			buttonOK.addActionListener(new ActionListener(){  @Override public void actionPerformed(ActionEvent arg0) { setLaw( list.getSelectedItem().toString()); }});
			buttonsPanel.add(buttonOK);
			
			end.add(buttonsPanel);
			
	    add(end,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public JSONObject getSelectedLaw() {
		return _law;
	}
	private JButton createButton(String name) {
		JButton button = new JButton(name);
		button.setBackground(Color.WHITE);
		button.setMargin(null);
		return button;
	}
	private void modifyTable() {
		
		 for(JSONObject x : _options) if(list.getSelectedItem().equals(x.getString("desc"))) table.setLawInfo(x);
	}
	private JSONObject parseFLawsDesc(String info ) {
		JSONObject data = new JSONObject();
		for(JSONObject x : _options) {
					
					if( info.equals(x.getString("desc"))) {
						data.put("type", x.getString("type"));
						data.put("data", createData());
						data.put("desc", x.getString("desc"));
						return data;
						
					}
		}
		return data;
	}
	private void setLaw(String info) {
			_law = parseFLawsDesc(info);
			dispose();
		
	}
	private JSONObject createData() {
		return table.createData();
	}

}
