package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.sensor.Sensor;

public abstract class SmartExpert extends DirtBasedAgent{
	
	protected Random r;
	protected static final int DEFAULT_RANDOM_SEED = 0;
	
	public SmartExpert(Creature c, Environment environment) {
		super(c, environment);
		
		r = new Random(DEFAULT_RANDOM_SEED);
	}
	
	/**
	 * This method will check for dirt. If dirt is found it will return an action. If no dirt found it will return null.
	 * @param c
	 * @return
	 */
	protected Direction checkForDirt(Creature c){
		Sensor s = c.getSensor();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			if (value == Environment.DIRT){
				return d;
			}
		}
		return null;
	}

}
