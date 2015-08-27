
package agent.lfo;

import java.util.ArrayList;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.MovementAction;
import sandbox.sensor.Sensor;

public class ZigZagExpert extends DirtBasedAgent{

	private boolean moveUp;
	private boolean moveRight;
	
    public ZigZagExpert (Creature c, Environment environment){
        super(c, environment);
        
        moveUp = false;
        moveRight = false;
    }
    
    @Override
    public MovementAction testAction(Creature c) {
        Sensor s = c.getSensor();
        ArrayList<Direction> wallDir = new ArrayList<Direction>();
        for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			int dist = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
			if (value == Environment.WALL && dist == Environment.CLOSE){
				wallDir.add(d);
			}
		}
        
        if (!moveUp && !moveRight){
        	if (wallDir.contains(Direction.WEST)){
        		if (wallDir.contains(Direction.SOUTH)){
        			if (wallDir.contains(Direction.EAST)){
        				moveUp = true;
        				return MovementAction.MOVE_UP;
        			}else{
        				moveUp = true;
        				moveRight = true;
        				return MovementAction.MOVE_RIGHT;
        			}
        		}else{
        			moveRight = true;
        			return MovementAction.MOVE_DOWN;
        		}
        	}else{
        		return MovementAction.MOVE_LEFT;
        	}
        }else if (moveUp && !moveRight){
        	if (wallDir.contains(Direction.EAST)){
        		if (wallDir.contains(Direction.SOUTH)){
        			if (wallDir.contains(Direction.WEST)){
        				moveUp = false;
        				return MovementAction.MOVE_UP;
        			}else{
        				moveUp = true;
        				moveRight = false;
        				return MovementAction.MOVE_LEFT;
        			}
        		}else{
        			moveRight = false;
        			return MovementAction.MOVE_DOWN;
        		}
        	}else{
        		return MovementAction.MOVE_RIGHT;
        	}
        }else if (!moveUp && moveRight){
        	if (wallDir.contains(Direction.WEST)){
        		if (wallDir.contains(Direction.NORTH)){
        			if (wallDir.contains(Direction.EAST)){
        				moveUp = false;
        				return MovementAction.MOVE_DOWN;
        			}else{
        				moveUp = false;
        				moveRight = true;
        				return MovementAction.MOVE_RIGHT;
        			}
        		}else{
        			moveRight = true;
        			return MovementAction.MOVE_UP;
        		}
        	}else{
        		return MovementAction.MOVE_LEFT;
        	}
        }else{
        	if (wallDir.contains(Direction.EAST)){
        		if (wallDir.contains(Direction.NORTH)){
        			if (wallDir.contains(Direction.WEST)){
        				moveUp = true;
        				return MovementAction.MOVE_DOWN;
        			}else{
        				moveUp = false;
        				moveRight = false;
        				return MovementAction.MOVE_LEFT;
        			}
        		}else{
        			moveRight = false;
        			return MovementAction.MOVE_UP;
        		}
        	}else{
        		return MovementAction.MOVE_RIGHT;
        	}
        }
    }
}
