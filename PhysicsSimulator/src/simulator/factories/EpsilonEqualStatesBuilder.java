package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	public EpsilonEqualStatesBuilder() {
		super("epseq", "Epsilon Equal State");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected StateComparator createTheInstance(JSONObject jsonObject) {
		double eps= jsonObject.has("eps")? jsonObject.getDouble("eps"):0.0;
		return new EpsilonEqualStates(eps);
	}

	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();		
		data.put("eps", "EES epsilon");
		return null;
	}

}
