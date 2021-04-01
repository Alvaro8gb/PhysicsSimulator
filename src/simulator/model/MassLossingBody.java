package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body {

	private double lossFactor;
	private double lossFrequency;
	
	public MassLossingBody(String id, Vector2D v, Vector2D p, double m,double lossFactor ,double lossFrequency) {
		super(id, v, p, m);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
	}
	
}
