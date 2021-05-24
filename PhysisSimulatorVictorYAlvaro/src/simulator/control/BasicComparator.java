package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class BasicComparator implements StateComparator{
	
	protected JSONArray bodiesA;
	protected JSONArray bodiesB;

	public boolean equal(JSONObject s1, JSONObject s2) {
		
		if(s1 == null || s2 == null ) return false;
		
		if(s1.getDouble("time") != s2.getDouble("time")) return false;
		
		bodiesA = s1.getJSONArray("bodies");
		bodiesB = s2.getJSONArray("bodies");

		if(bodiesA.length()!=bodiesB.length()) return false;
		
		return true;
	}

}
