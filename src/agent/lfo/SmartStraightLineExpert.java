package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

@Deprecated
public class SmartStraightLineExpert extends SmartExpert{
	
	private Random r;
	private boolean hitWall;
	
	public SmartStraightLineExpert(int size, Creature c) {
		super(size, c);
		r = new Random(0);
		hitWall = false;
	}

	@Override
	public MovementAction testAction(Creature c) {
		MovementAction action = this.nextSmartDirection(c);
		if (action != null){
			return action;
		}
		if (hitSomething){
			hitWall = true;
			return MovementAction.STAND;
		}else if (hitWall){
			hitWall = false;
			int value = r.nextInt(Direction.values().length);
			return calculateMovement(c.getDir(), Direction.values()[value]);
		}
		return MovementAction.MOVE_UP;
	}

}
