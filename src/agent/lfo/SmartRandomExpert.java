package agent.lfo;

import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;

@Deprecated
public class SmartRandomExpert extends SmartExpert{

	private Random r;
	
	public SmartRandomExpert(int size, Creature c) {
		super(size, c);
		r = new Random();		
	}

	@Override
	public MovementAction testAction(Creature c) {
		MovementAction action = this.nextSmartDirection(c);
		if (action != null){
			return action;
		}
		int value = r.nextInt(Direction.values().length);
		return calculateMovement(c.getDir(), Direction.values()[value]);
	}

}
