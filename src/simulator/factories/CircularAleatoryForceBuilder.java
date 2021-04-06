package simulator.factories;

import org.json.JSONObject;

import simulator.model.CircularAleatoryForce;
import simulator.model.ForceLaws;


public class CircularAleatoryForceBuilder extends Builder<ForceLaws> {

	public CircularAleatoryForceBuilder() {
		super("cfa","Circular aleatory force");
	}
	protected CircularAleatoryForce createTheInstance(JSONObject data) {
		
		Long _s = data.has("s")? data.getLong("s"): System.currentTimeMillis();
		return new CircularAleatoryForce(_s);
	}

	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("s","the seed");
		return data;
	}

}
