package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Builder<T>{

	protected String _type;
	protected String _desc;

	public Builder(String _type,String _desc) {
		this._type =_type;
		this._desc = _desc;
	}
	public T createInstance(JSONObject info) {
		
		T b = null;
		
		if(_type != null && _type.equals(info.getString("type"))) {
			
			try {
			b = createTheInstance(info.getJSONObject("data"));
			}catch(JSONException je) {
				throw new IllegalArgumentException("Fail to parse data"+ je.getMessage(),je);
			}
		}
		
		return b;
	}
	
	protected abstract T createTheInstance(JSONObject data);
	
	public JSONObject getBuilderInfo() {
		
		JSONObject info = new JSONObject();
		
		info.put("type",_type);
		info.put("data",createData());
		info.put("desc",_desc);
		
		return info;
	}
	protected abstract JSONObject createData();
	


}
