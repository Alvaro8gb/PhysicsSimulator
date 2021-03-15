package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;


public class MassEqualStatesBuilder extends Builder<MassEqualStates> {

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
