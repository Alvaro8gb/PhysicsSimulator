package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator{

	public boolean equal(JSONObject s1, JSONObject s2) {
		
		if(s1 == null || s2 == null ) return false;
		
		if(s1.getDouble("time") != s2.getDouble("time")) return false;
		
		JSONArray as1 = s1.getJSONArray("bodies");
		JSONArray as2 = s2.getJSONArray("bodies");

		if(as1.length()!=as2.length()) return false;
		
		for (int i = 0; i < as2.length(); i++) {
			if(as1.getJSONObject(i).getDouble("m") != as2.getJSONObject(i).getDouble("m")) return false; 
			if(!as1.getJSONObject(i).getString("id").equals(as2.getJSONObject(i).getString("id")) ) return false; 
		}
		
		return true;
	}

}
