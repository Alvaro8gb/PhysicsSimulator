package simulator.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

import simulator.control.Controller;

public class ForceLawWindow extends JFrame{
	private Controller _ctrl;
	private List<JSONObject> options;
	ForceLawWindow(Controller ctrl){
		this._ctrl = ctrl;
		//cuadro dialogo
		JFrame j = new JFrame();
		j.setLayout(new BorderLayout());
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setTitle("Force Laws Selection");
		initGIU(j);
		j.pack();
	}
	private void initGIU(JFrame j) {
		//mensaje arriba
		 JLabel help = new JLabel("<html><p>Select a force law and provide values for the parametes in the <b>Value column</b> (default values are used for parametes with no value).</p></html>");
		 j.add(help,BorderLayout.NORTH);
		 //MEDIO
		 JPanel mid = new JPanel();
		 mid.setLayout(new BorderLayout());
		 mid.setVisible(true);
		 //parte alta del medio
		 //parte baja del medio
		 JPanel middown = new JPanel();
		 middown.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		 	JLabel info = new JLabel("Force Laws: ");
			JComboBox list = new JComboBox<String>();
			options = _ctrl.getForceLawsInfo();
			//aparezcan las force law en la ComboBox
			for(JSONObject x : options) {
				list.addItem(x.getString("desc"));
			}
			//eleccion fuerza
			
			/*list.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) { 
				if(list.getSelectedItem().equals("Newton’s law of universal gravitation")) _ctrl.setForceLaws(info);
				else if(list.getSelectedItem().equals("No force"))_ctrl.setForceLaws(info);
				else if(list.getSelectedItem().equals("Moving towards a fixed point"))_ctrl.setForceLaws(info);
				else if(list.getSelectedItem().equals("Circular aleatory force"))_ctrl.setForceLaws(info);
			}
			});*/
			middown.add(info);
			middown.add(list);
		mid.add(middown,BorderLayout.SOUTH);
		j.add(mid,BorderLayout.CENTER);
		//FINAL
		JPanel end = new JPanel();
		end.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		JButton buttonCancel = new JButton("Cancel");
		JButton buttonOK = new JButton("OK");
		end.add(buttonCancel);
		end.add(buttonOK);
		j.add(end,BorderLayout.SOUTH);
	}
}
