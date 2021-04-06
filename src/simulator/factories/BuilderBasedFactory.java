package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{

	private List<Builder<T>> _builders;
	private List<JSONObject> _factoryElements;

	public BuilderBasedFactory(List<Builder<T>> builders) {
		
		_builders = new ArrayList<>(builders);
		_factoryElements = new ArrayList<JSONObject>();
		
		for (Builder<T> bb : _builders) _factoryElements.add(bb.getBuilderInfo());
	}

	public T createInstance(JSONObject info) throws IllegalArgumentException {
		
		if (info != null) {
			for (Builder<T> bb : _builders) {
				T o = bb.createInstance(info);
				if (o != null) return o;
			}
		}
		
		throw new IllegalArgumentException("Invalid value for createInstance: " + info);
	}

	public List<JSONObject> getInfo() {
		return _factoryElements;
	}

}
