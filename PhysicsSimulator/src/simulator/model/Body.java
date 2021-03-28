package simulator.model;

import org.json.JSONObject;
import simulator.misc.Vector2D;

public class Body {
	
	protected String id;
	protected Vector2D v;
	protected Vector2D f;
	protected Vector2D p;
	protected double m;
	
	public Body(String id,Vector2D p,Vector2D v, double m) {
		this.id = id;
		this.v = v;
		this.p = p;
		this.m = m;
		f = new Vector2D();
	}
	void move(double t){
		Vector2D a = new Vector2D();
		
		if(m!= 0) a = f.scale(1.0/m);
		
		p = p.plus(v.scale(t)); //p =  p + v*t
		p = p.plus(a.scale(0.5 * t * t)); //p = p + v*t + 1/2 *a *t*t ;
		
		v.plus(a.scale(t)); // v = v + a*t
		
	}
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		
		if (getClass() != obj.getClass()) return false;
		
		Body other = (Body) obj;
		
		if (id == null) {
			if (other.id != null) return false;
		}
		else if (!id.equals(other.id)) return false;
		
		return true;
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
		this.f  = f.plus(f);
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
