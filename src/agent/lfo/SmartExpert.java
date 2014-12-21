package agent.lfo;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.Obstacle;
import sandbox.sensor.Sensor;

public abstract class SmartExpert extends DirtBasedAgent{

	private boolean dirtFound;
	private Direction dirtDirection;
	
	protected boolean hitSomething;
	
	public SmartExpert(int size, Creature c) {
		super(size, c);
		this.dirtFound = false;
		this.hitSomething = false;
	}
	
	protected MovementAction nextSmartDirection(Creature c){
		hitSomething = false;
		Sensor s = c.getSensor();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			if (Obstacle.DIRT.getId() == value){
				if (dirtFound && dirtDirection != null && !dirtDirection.equals(d)){
					continue;
				}else if (!dirtFound){
					dirtFound = true;
					dirtDirection = d;
				}
				int dist = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
				if (dist <= 1){
					dirtFound = false;
					dirtDirection = null;
				}
				MovementAction a = calculateMovement(c.getDir(), d);
				if (a != null){
					return a;
				}
			}
			if ((int)s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue() == -1){
				hitSomething = true;
			}
		}
		return null;
	}

}
