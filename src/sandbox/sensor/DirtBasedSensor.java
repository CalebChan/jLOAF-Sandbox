package sandbox.sensor;

import java.util.List;

import agent.lfo.DirtBasedAgentSenseConfig;
import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;

public class DirtBasedSensor extends Sensor{

	public DirtBasedSensor(List<Sense> senses, Creature c) {
		super(senses, c);
	}

	@Override
	public void updateSenses(Environment environment) {
		if (this.senses.size() != DirtBasedAgentSenseConfig.SENSOR_COUNT){
			return;
		}
		int[][] world = environment.getEnvironment();
		int oldX = c.getX();
		int oldY = c.getY();
		for (Direction d : Direction.values()){
			int count = 1;
			int obstacle = Environment.WALL;
			switch(d){
			case NORTH:
				
				for (int i = 1; oldY - i >= 0; i++){
					if (world[oldX][oldY - i] != 0){
						obstacle = world[oldX][oldY - i];
						break;
					}
					count++;
				}
				if (oldY - count < 0){
					obstacle = Environment.WALL;
				}
				break;
			case SOUTH:
				for (int i = 1; oldY + i < world.length; i++){
					if (world[oldX][oldY + i] != 0){
						obstacle = world[oldX][oldY + i];
						break;
					}
					count++;
				}
				if (oldY + count >= world.length){
					obstacle = Environment.WALL;
				}
				break;
			case EAST:
				for (int i = 1; oldX + i < world[0].length; i++){
					if (world[oldX + i][oldY] != 0){
						obstacle = world[oldX + i][oldY];
						break;
					}
					count++;
				}
				if (oldX + count >= world[0].length){
					obstacle = Environment.WALL;
				}
				break;
			case WEST:
				for (int i = 1; oldX - i >= 0; i++){
					if (world[oldX - i][oldY] != 0){
						obstacle = world[oldX - i][oldY];
						break;
					}
					count++;
				}
				if (oldX - count < 0){
					obstacle = Environment.WALL;
				}
				break;
			}
			this.senses.get(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).setValue(obstacle); 
			// Convert to double for later casting from object to double
			this.senses.get(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).setValue(((count == 1) ? Environment.CLOSE : Environment.FAR));
		}
	}

}
