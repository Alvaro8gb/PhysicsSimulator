package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ForceLawWindow extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private List<JSONObject> _options;
	private JComboBox<String> list;
	private static final int _WIDTH = 650;
	private static final int _HEIGHT = 350;
	private ParametersTable table;
	private JSONObject _law;

	
	public ForceLawWindow( List<JSONObject> options){
		_options = options;
		initGIU();
	}
	private void initGIU() {
		setLayout(new BorderLayout());
		setTitle("Force Laws Selection");
		setSize(_WIDTH,_HEIGHT);
		setModal(true); 
		setLocationRelativeTo(this.getParent());
		

	     try {
            BufferedImage img = ImageIO.read(new File("resources/icons/physics.png"));
            setIconImage(img);
	      } catch (IOException e) {
	           System.out.println("Error to put window icon image   " + e.getMessage());
	      }
	        
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
	private void setLaw(String info) {

		try{
			_law = parseFLawsDesc(info);
			dispose();
		}catch(JSONException  e ) {
			JOptionPane.showMessageDialog(this.getParent(),e.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
		}
			
	}
	private JSONObject parseFLawsDesc(String info ) {
		JSONObject data = new JSONObject();
		for(JSONObject x : _options) {
					
					if( info.equals(x.getString("desc"))) {
						data.put("type", x.getString("type"));
						data.put("data", createData());
						return data;			
					}
		}
		return data;
	}
	private JSONObject createData() {

		JSONObject obj = new JSONObject();
		for(int i = 0; i < table.getRowCount();i++) {
			if(table.getValueAt(i, 1) != null) obj.put( (String)table.getValueAt(i, 0) , parseObject( table.getValueAt(i, 1)) );
		}
		
		return obj;
	}
	private Object parseObject(Object value) {
		
		String cad = value.toString();
		
		if(cad.equals("")) return null;
		else {
			if(cad.charAt(0) == '[') return new JSONArray(cad);
			else return value;
		}
	}


}
