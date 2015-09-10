package agent.lfo.expert;

import java.awt.Point;
import java.util.ArrayList;

import agent.lfo.DirtBasedAgentSenseConfig;
import agent.lfo.SmartExpert;
import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.MovementAction;
import sandbox.sensor.Sensor;

public class SmartExplorerExpert extends SmartExpert {
	
	private ArrayList<Point> openSpaces;
	private ArrayList<Point> closeSpaces;
	
	private Point currentPoint;
	
	public SmartExplorerExpert(Creature c, Environment environment) {
		super(c, environment);
		this.openSpaces = new ArrayList<Point>();
		this.closeSpaces = new ArrayList<Point>();
		currentPoint = new Point(0, 0);
	}
	
	@Override
	public MovementAction testAction(Creature c) {
		currentPoint = new Point(this.creature.getX(), this.creature.getY());
		
		openSpaces.remove(currentPoint);
		closeSpaces.add(currentPoint);
		
		Sensor s = c.getSensor();
		ArrayList<Direction> possibleDir = new ArrayList<Direction>();
		for (Direction d : Direction.values()){
			int value = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.TYPE_SUFFIX).getValue();
			int dist = (int) s.getSense(d.name() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue();
			
			if (value == Environment.WALL && dist == Environment.FAR){
				possibleDir.add(d);
			}
		}
		Point newPoint;
		for (Direction d : possibleDir){
			switch(Direction.convertDirToAct(d)){
			case MOVE_UP:
				newPoint = new Point(c.getX(), c.getY() - 1);
				break;
			case MOVE_DOWN:
				newPoint = new Point(c.getX(), c.getY() + 1);
				break;
			case MOVE_LEFT:
				newPoint = new Point(c.getX() - 1, c.getY());
				break;
			case MOVE_RIGHT:
				newPoint = new Point(c.getX() + 1, c.getY());
				break;
			case STAND:
			default:	
				newPoint = new Point(c.getX(), c.getY());
				break;
			}
			if (!openSpaces.contains(newPoint) && !closeSpaces.contains(newPoint)){
				openSpaces.add(newPoint);
			}
		}
		Direction action = this.checkForDirt(c);
		if (action != null){
			switch(Direction.convertDirToAct(action)){
			case MOVE_UP:
				currentPoint = new Point(c.getX(), c.getY() - 1);
				break;
			case MOVE_DOWN:
				currentPoint = new Point(c.getX(), c.getY() + 1);
				break;
			case MOVE_LEFT:
				currentPoint = new Point(c.getX() - 1, c.getY());
				break;
			case MOVE_RIGHT:
				currentPoint = new Point(c.getX() + 1, c.getY());
				break;
			case STAND:
			default:	
				currentPoint = new Point(c.getX(), c.getY());
				break;
			}
			
			return Direction.convertDirToAct(action);
		}
		
		// Suppose to select closest but discrete distances used so just pick first point
		Point closest = null;
		if (!openSpaces.isEmpty()){
			closest = openSpaces.get(0);
		}

		if (closest != null){
			ArrayList<Direction> candidates = new ArrayList<Direction>();
			ArrayList<Double> distance = new ArrayList<Double>();
			ArrayList<Point> newPositions = new ArrayList<Point>();
			Point p;
			for (Direction d : possibleDir){
				switch(Direction.convertDirToAct(d)){
				case MOVE_UP:
					p = new Point(c.getX(), c.getY() - 1);
					break;
				case MOVE_DOWN:
					p = new Point(c.getX(), c.getY() + 1);
					break;
				case MOVE_LEFT:
					p = new Point(c.getX() - 1, c.getY());
					break;
				case MOVE_RIGHT:
					p = new Point(c.getX() + 1, c.getY());
					break;
				case STAND:
				default:	
					p = new Point(c.getX(), c.getY());
					break;
				}
				candidates.add(d);
				distance.add(p.distance(closest));
				newPositions.add(p);
			}
			// Sort lowest distance to highest
			boolean change = false;
			do{
				change = false;
				for (int i = 0; i < candidates.size() - 1; i++){
					if (distance.get(i) > distance.get(i + 1)){
						Direction tmpDir = candidates.get(i);
						Double tmpDist = distance.get(i);
						Point tmpPos = newPositions.get(i);
						
						candidates.set(i, candidates.get(i + 1));
						distance.set(i, distance.get(i + 1));
						newPositions.set(i, newPositions.get(i + 1));
						
						candidates.set(i + 1, tmpDir);
						distance.set(i + 1, tmpDist);
						newPositions.set(i + 1, tmpPos);
						
						change = true;
					}
				}
			}while(change);
			
			if (!candidates.isEmpty()){
				int selected = 0;
				while(selected < candidates.size() - 1 && r.nextInt(4) == 0){
					selected++;
				}
				currentPoint = newPositions.get(selected);
				return Direction.convertDirToAct(candidates.get(selected));
			}
		}
		
		return MovementAction.STAND;
	}

}
