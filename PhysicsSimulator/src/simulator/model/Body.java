package simulator.model;

import org.json.JSONObject;
import simulator.misc.Vector2D;

public class Body {
	
	protected String id;
	protected Vector2D v;
	protected Vector2D f;
	protected Vector2D p;
	protected double m;
	
	public Body(String id,Vector2D v,Vector2D p, double m) {
		this.id = id;
		this.v = v;
		this.p = p;
		this.m = m;
		f = new Vector2D();
	}
	void move(double t){
		Vector2D a;
		
		if(m!= 0) a = f.scale(1/m);
		else a = new Vector2D();
		
		p = p.plus(v.scale(t));
		p = p.plus(a.scale(1/2 * t * t));
		v = v.plus(a.scale(t));
		
	}
	public String getId() {
		return id;
	}
	public Vector2D getVelocity() {
		return v;
	}
	public Vector2D getForce() {
		return f;
	}
	public Vector2D getPosition() {
		return p;
	}
	public double getMass(){
		return m;
	}
	void addForce(Vector2D f) {
		this.f  = this.f.plus(f);
	}
	void resetForce() {
		this.f = new Vector2D();
	}
	public JSONObject getState() {
		JSONObject bo = new JSONObject();
		
		bo.put("id", id);
		bo.put("m", m);
		bo.put("p", p.asJSONArray());
		bo.put("v", v.asJSONArray());
		bo.put("f", f.asJSONArray());
		
		return bo;
	}
	public String toString(){
		return getState().toString();
	}

}
