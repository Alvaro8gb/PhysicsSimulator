package simulator.control;

import org.json.JSONObject;

public class MassEqualStates extends BasicComparator{

	public boolean equal(JSONObject s1, JSONObject s2) {
		
		if (!super.equal(s1, s2)) return false;
		
		for (int i = 0; i < bodiesA.length(); i++) {
			if(bodiesA.getJSONObject(i).getDouble("m") != bodiesB.getJSONObject(i).getDouble("m")) return false; 
			if(!bodiesA.getJSONObject(i).getString("id").equals(bodiesB.getJSONObject(i).getString("id")) ) return false; 
		}
		
		return true;
	}
	
}
