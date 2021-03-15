package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;


public class EpsilonEqualStatesBuilder extends Builder<EpsilonEqualStates>{

	public EpsilonEqualStatesBuilder() {
		super("epseq", "esto es el comaparador modulo epsilon");
		
	}
	public EpsilonEqualStates createTheInstance(JSONObject data){
		
		double eps = data.getDouble("eps");
		
		return new EpsilonEqualStates(eps);
	}

	public JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("eps", "the epsilon");
		
		return data;
	}

}
