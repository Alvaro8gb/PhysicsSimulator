package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		super("nf", "No Force");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return new NoForce();
	}

	@Override
	protected JSONObject createData() {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		return data;
	}

}
