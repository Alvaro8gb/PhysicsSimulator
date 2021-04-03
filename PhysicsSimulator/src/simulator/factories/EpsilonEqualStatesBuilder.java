package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;


public class EpsilonEqualStatesBuilder extends Builder<StateComparator>{

	public EpsilonEqualStatesBuilder() {
		super("epseq", "Espsilon-equal states comparator");
		
	}
	public EpsilonEqualStates createTheInstance(JSONObject data){
		
		double eps = data.has("eps")? data.getDouble("eps"): 0.0 ;
		
		return new EpsilonEqualStates(eps);
	}

	public JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("eps", "the epsilon");
		
		return data;
	}

}
