package sandbox;
/**
 * This class is a data class that hold the last action and the ability of the agent to take that action.
 * @author Caleb Chan
 *
 */
public class ActionHistory {
	private MovementAction lastAction;
	private boolean ableToTake;
	
	public ActionHistory(MovementAction lastAction, boolean ableToTake) {
		this.lastAction = lastAction;
		this.ableToTake = ableToTake;
	}

	public MovementAction getLastAction() {
		return lastAction;
	}

	public boolean isAbleToTake() {
		return ableToTake;
	}
	
}
