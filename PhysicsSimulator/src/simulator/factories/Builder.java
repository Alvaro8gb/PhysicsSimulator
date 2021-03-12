package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T>{

	protected String _type;
	protected String _desc;

	public T createInstance(JSONObject info) {
		
		T b = null;
		
		if(_type != null && _type.equals(info.getString("type"))) {
			b = createTheInstance(info.getJSONObject("data"));
		}
		
		return b;
	}
	public abstract T createTheInstance(JSONObject data) throws IllegalArgumentException;
	
	
	public JSONObject getBuilderInfo() {
		
		JSONObject info = new JSONObject();
		
		info.put("type",_type);
		info.put("type",createData());
		info.put("desc",_desc);
		
		return info;
	}
	public abstract JSONObject createData();
	


}
