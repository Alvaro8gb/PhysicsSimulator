package simulator.factories;

import org.json.JSONObject;

import simulator.model.NoForce;

public class NoForceBuilder extends Builder<NoForce>{

	public NoForceBuilder() {
		super("nf", "sin fuerza");
	}
	public NoForce createTheInstance(JSONObject data) throws IllegalArgumentException {
		return new NoForce();
	}
	public JSONObject createData() {
		return new JSONObject();
	}
}
