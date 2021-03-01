package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {

	public T createInstance(JSONObject info) {
		
		/*
		si la información suministrada por info es
		correcta, entonces crea un objeto de tipo T (i.e., una instancia de una subclase de T).
		En otro caso devuelve null para indicar que este constructor es incapaz de reconocer
		ese formato. En caso de que reconozca el campo type pero haya un error en alguno
		de los valores suministrados por la sección data, el método lanza una excepcion
		IllegalArgumentException.
		*/
		
		return null;
	}
	
	public JSONObject getBuilderInfo() {
		
		/*
		 * devuelve un objeto JSON que sirve de plantilla	para el correspondiente constructor, i.e., un valor válido para el parámetro de
	createInstance (ver getInfo() de Factory<T>).
		 */
		
		return null;
	}
	
}
