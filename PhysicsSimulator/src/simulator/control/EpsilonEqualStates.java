package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;

public class EpsilonEqualStates extends BasicComparator {

	private double eps;

	public EpsilonEqualStates(double eps) {
		this.eps = eps;
	}
	public boolean equal(JSONObject s1, JSONObject s2) {
		
		if (!super.equal(s1, s2)) return false;

		if(!equalBodies(bodiesA,bodiesB)) return false;
		
		return true;
	}
	private boolean equalBodies(JSONArray a1,JSONArray a2) {
		
		if(a1.length()!=a2.length()) return false;
		
		for (int i = 0; i < a1.length(); i++) {
			
			if(!a1.getJSONObject(i).getString("id").equals(a2.getJSONObject(i).getString("id"))) return false; 
		
			
			if(Math.abs(a1.getJSONObject(i).getDouble("m") - a2.getJSONObject(i).getDouble("m")) > eps) return false; 
			
			
			if(!equalVector(a1.getJSONObject(i).getJSONArray("p"),a2.getJSONObject(i).getJSONArray("p"))) return false;
			if(!equalVector(a1.getJSONObject(i).getJSONArray("f"),a2.getJSONObject(i).getJSONArray("f"))) return false;	
			if(!equalVector(a1.getJSONObject(i).getJSONArray("v"),a2.getJSONObject(i).getJSONArray("v"))) return false;
		}
		
		return true;
	}
	private boolean equalVector(JSONArray a1,JSONArray a2) {
		
		Vector2D v1 = new Vector2D(a1.getDouble(0),a1.getDouble(1));
		Vector2D v2 = new Vector2D(a2.getDouble(0),a2.getDouble(1));

		if(v1.distanceTo(v2) > eps) return false;
		return true;
	}

}




