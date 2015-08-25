package agent.backtracking;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.MovementAction;
import sandbox.creature.StateBasedCreature;

public class ActionBasedAgent extends StateBasedAgent{

	public ActionBasedAgent(int size, Creature c){
		super(size, c);			
	}
	
	private boolean isTurnRightState;
	
	@Override
	public MovementAction testAction(Creature c){
		MovementAction action = null;
		
		boolean tch = (boolean)c.getSensor().getSense(StateBasedAgentSenseConfig.TOUCH).getValue();
		double snr = (double)c.getSensor().getSense(StateBasedAgentSenseConfig.SONAR).getValue();
		
//		if (tch){
//			action = MovementAction.MOVE_DOWN;
//		}else if (!tch && snr < 2){
//			action = MovementAction.REVERSE; // TODO: Add Reverse
//		}else if (!tch && snr >= 3){
//			action = MovementAction.MOVE_UP;
//		}else if (!tch && snr >= 2 && snr < 3 && isTurnRightState){
//			action = MovementAction.TURN_RIGHT;
//			isTurnRightState = !isTurnRightState;
//		}else if (!tch && snr >= 2 && snr < 3 && !isTurnRightState){
//			action = MovementAction.TURN_LEFT;
//			isTurnRightState = !isTurnRightState;
//		}
		return action;
	}

	@Override
	protected Creature createCreature() {
		return new StateBasedCreature(2, 2, Direction.NORTH);
	}

	@Override
	protected void resetInternalState() {
		isTurnRightState = false;
	}
}
