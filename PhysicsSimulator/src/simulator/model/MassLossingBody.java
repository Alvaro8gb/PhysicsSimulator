package simulator.model;

import simulator.misc.Vector2D;

public class MassLossingBody extends Body{

	private double lossFactorPercent;
	private double lossFrequency;
	private double timerCounter ;
	
	public MassLossingBody(String id, Vector2D v, Vector2D p, double m,double lossFactor,double lossFrequency) {
		super(id, v, p, m);
		
		if(lossFactor > 1 || lossFactor < 0) throw new IllegalArgumentException("The lossFactor must be a number between 0-1");
		
		this.lossFrequency = lossFrequency;
		lossFactorPercent = 1-lossFactor;
		timerCounter = 0.0;
	}
	
	public void move(double t) {
		super.move(t);
		timerCounter+=t;
		if(timerCounter >= lossFrequency) {
			m *= lossFactorPercent;
			timerCounter = 0.0;
		}
	
	}
	
}
