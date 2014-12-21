package sandbox.sensor;

import java.util.List;

import agent.backtracking.StateBasedAgentSenseConfig;

import sandbox.ActionHistory;
import sandbox.Creature;
import sandbox.MovementAction;
import sandbox.Sandbox;

public class StateBasedSensor extends Sensor {

	public StateBasedSensor(List<Sense> senses, Creature c) {
		super(senses, c);
	}

	@Override
	public void updateSenses(Sandbox sandbox) {
		if (senses.size() != StateBasedAgentSenseConfig.SENSOR_COUNT){
			return;
		}
		updateSound(this.senses.get(StateBasedAgentSenseConfig.SOUND), sandbox);
		updateSonar(this.senses.get(StateBasedAgentSenseConfig.SONAR), sandbox);
		updateTouch(this.senses.get(StateBasedAgentSenseConfig.TOUCH), sandbox);
	}
	
//	private Random r = new Random(0);	

	private void updateSound(Sense sound, Sandbox box){
//		if (c.getSound() < 1 || c.getSound() > 2){
//		c.setSound(1);
//		}
//		if (r.nextGaussian() < 0.1){
//			c.setSound((c.getSound() % 2) + 1);
//		}
	}
	
	private void updateSonar(Sense sonar, Sandbox box){
		int sonarCount = 0;
		int world[][] = box.getWorld();
		int oldX = c.getX();
		int oldY = c.getY();
		switch(c.getDir()){
		case NORTH:
			for (int i = oldX; i >= 0; i--){
				if (world[i][oldY] != 0){
					break;
				}
				sonarCount++;
			}
			break;
		case SOUTH:
			for (int i = oldX; i < world.length; i++){
				if (world[i][oldY] != 0){
					break;
				}
				sonarCount++;
			}
			break;
		case EAST:
			for (int i = oldY; i < world[0].length; i++){
				if (world[oldX][i] != 0){
					break;
				}
				sonarCount++;
			}
			break;
		case WEST:
			for (int i = oldY; i >= 0; i--){
				if (world[oldX][i] != 0){
					break;
				}
				sonarCount++;
			}
			break;
		}
		sonar.setValue(sonarCount / 2.0);
	}
	
	private void updateTouch(Sense touch, Sandbox box){
		ActionHistory history = c.getLastActionHistory();
		if (history == null){
			return;
		}
		if (history.getLastAction().equals(MovementAction.MOVE_UP) || history.getLastAction().equals(MovementAction.MOVE_DOWN)){
			touch.setValue(!history.isAbleToTake());
		}
	}

}
