package sandbox.sensor;

import java.util.List;

import agent.backtracking.StateBasedAgentSenseConfig;
import sandbox.ActionHistory;
import sandbox.Creature;
import sandbox.Environment;
import sandbox.MovementAction;

public class StateBasedSensor extends Sensor {

	public StateBasedSensor(List<Sense> senses, Creature c) {
		super(senses, c);
	}

	@Override
	public void updateSenses(Environment env) {
		if (senses.size() != StateBasedAgentSenseConfig.SENSOR_COUNT){
			return;
		}
		updateSound(this.senses.get(StateBasedAgentSenseConfig.SOUND), env);
		updateSonar(this.senses.get(StateBasedAgentSenseConfig.SONAR), env);
		updateTouch(this.senses.get(StateBasedAgentSenseConfig.TOUCH), env);
	}
	
//	private Random r = new Random(0);	

	private void updateSound(Sense sound, Environment env){
//		if (c.getSound() < 1 || c.getSound() > 2){
//		c.setSound(1);
//		}
//		if (r.nextGaussian() < 0.1){
//			c.setSound((c.getSound() % 2) + 1);
//		}
	}
	
	private void updateSonar(Sense sonar, Environment env){
		int sonarCount = 0;
		int world[][] = env.getEnvironment();
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
	
	private void updateTouch(Sense touch, Environment env){
		ActionHistory history = c.getLastActionHistory();
		if (history == null){
			return;
		}
		if (history.getLastAction().equals(MovementAction.MOVE_UP) || history.getLastAction().equals(MovementAction.MOVE_DOWN)){
			touch.setValue(!history.isAbleToTake());
		}
	}

}
