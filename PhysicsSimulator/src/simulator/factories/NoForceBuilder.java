package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		super("nf", "without power");
	}
	public NoForce createTheInstance(JSONObject data) throws IllegalArgumentException {
		return new NoForce();
	}
	public JSONObject createData() {
		return new JSONObject();
	}
}
