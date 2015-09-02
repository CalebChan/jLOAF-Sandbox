package agent.lfo.expert;

import java.util.ArrayList;

import agent.lfo.DirtBasedAgentSenseConfig;
import agent.lfo.SmartExpert;
import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.MovementAction;
import sandbox.sensor.Sensor;

public class SmartStraightLineExpert extends SmartExpert{
	
	private Direction currentDirection;
	
	public SmartStraightLineExpert(Creature c, Environment environment) {
		super(c, environment);
		
		currentDirection = null;
	}

	@Override
	public MovementAction testAction(Creature c) {
		Direction action = this.checkForDirt(c);
		if (action != null){
			currentDirection = action;
			return Direction.convertDirToAct(action);
		}
		Sensor s = c.getSensor();
		int value = (int) s.getSense(currentDirection.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
		int dist = (int) s.getSense(currentDirection.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
		
		if (value == Environment.WALL && dist == Environment.CLOSE){
			currentDirection = null;
		}
		
		if (currentDirection != null){
			return Direction.convertDirToAct(currentDirection);
		}
		
		ArrayList<Direction> possibleDir = new ArrayList<Direction>();
		for (Direction d : Direction.values()){
			value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			dist = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
			
			if (value == Environment.WALL && dist == Environment.FAR){
				possibleDir.add(d);
			}
		}
		value = r.nextInt(possibleDir.size());
		currentDirection = possibleDir.get(value);
		return Direction.convertDirToAct(possibleDir.get(value));
		
	}

}
