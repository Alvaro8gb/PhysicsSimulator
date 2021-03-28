package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;


public class MassEqualStatesBuilder extends Builder<StateComparator> {

	public MassEqualStatesBuilder() {
		super("masseq", "esto es un comparador de masas");
	}
	public MassEqualStates createTheInstance(JSONObject data) {
		return new MassEqualStates();
	}
	public JSONObject createData() {
		return new JSONObject();
	}

}
