package simulator.control;

import org.json.JSONObject;

public class NotEqualStatesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	NotEqualStatesException(int step ,JSONObject act,JSONObject exp){
		super("States diferents on step : " + step + "\n"+ "Actual: " + act + "\n"+ "Expected: " + exp+ "\n");		
	}
	
}
