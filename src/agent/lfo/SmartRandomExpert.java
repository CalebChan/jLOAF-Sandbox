package agent.lfo;

import java.util.ArrayList;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.MovementAction;
import sandbox.sensor.Sensor;

public class SmartRandomExpert extends SmartExpert{

	public SmartRandomExpert(Creature c, Environment environment) {
		super(c, environment);
	}

	@Override
	public MovementAction testAction(Creature c) {
		Direction action = this.checkForDirt(c);
		if (action != null){
			return Direction.convertDirToAct(action);
		}
		Sensor s = c.getSensor();
		ArrayList<Direction> possibleDir = new ArrayList<Direction>();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			int dist = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
			
			if (value == Environment.WALL && dist == Environment.FAR){
				possibleDir.add(d);
			}
		}
		int value = r.nextInt(possibleDir.size());
		return Direction.convertDirToAct(possibleDir.get(value));
	}

}
