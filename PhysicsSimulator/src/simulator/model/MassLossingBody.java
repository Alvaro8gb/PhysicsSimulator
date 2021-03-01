package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body{

	
	private double lossFactor;
	private double lossFrequency;
	private double c ;
	
	public MassLossingBody(String id, Vector2D v, Vector2D p, double m) {
		super(id, v, p, m);
		c = 0.0;
	}
	
	public void move(double t) {
		super.move(t);
		c+=t;
		if(c >= lossFrequency) {
			m = m*(1-lossFactor);
			c = 0.0;
		}
	}
	
}
