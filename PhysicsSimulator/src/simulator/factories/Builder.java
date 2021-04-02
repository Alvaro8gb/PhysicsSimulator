package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	protected String typetag;
	protected String desc;
	public Builder(String typetag, String desc){
		this.typetag = typetag;
		this.desc = desc;
	}
	public T createInstance(JSONObject info) {
		
		/*
		si la información suministrada por info es
		correcta, entonces crea un objeto de tipo T (i.e., una instancia de una subclase de T).
		En otro caso devuelve null para indicar que este constructor es incapaz de reconocer
		ese formato. En caso de que reconozca el campo type pero haya un error en alguno
		de los valores suministrados por la sección data, el método lanza una excepcion
		IllegalArgumentException.
		*/
		T b = null;
		if(typetag != null && typetag.equals(info.getString("type"))){
			b = createTheInstance(info.getJSONObject("data"));
			return b;
		}
		return null;
	}
	protected abstract T createTheInstance(JSONObject jsonObject);
	public JSONObject getBuilderInfo() {
		
		/*
		 * devuelve un objeto JSON que sirve de plantilla	para el correspondiente constructor, i.e., un valor válido para el parámetro de
	createInstance (ver getInfo() de Factory<T>).
		 */
		JSONObject info = new JSONObject();
		info.put("type",typetag);
		info.put("data",createData());
		info.put("des",desc);
		return info;
	}
	protected JSONObject createData() {
		return new JSONObject();
	}
	
}
