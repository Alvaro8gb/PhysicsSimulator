package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator> {

	public MassEqualStatesBuilder() {
		super("masseq", "Mass Equal State");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected StateComparator createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		return new MassEqualStates();
	}

	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		return data;
	}

}
