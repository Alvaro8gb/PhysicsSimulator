package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;


public class MassEqualStatesBuilder extends Builder<StateComparator> {

	public MassEqualStatesBuilder() {
		super("masseq", "the mass comparator");
	}
	protected MassEqualStates createTheInstance(JSONObject data) {
		return new MassEqualStates();
	}
	protected JSONObject createData() {
		return new JSONObject();
	}

}
