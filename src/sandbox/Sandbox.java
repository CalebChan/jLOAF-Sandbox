package sandbox;
import java.util.ArrayList;
import java.util.List;

public class Sandbox {

	private List<Creature> creatureList;
	
	private int world[][];
	
	public Sandbox(int size){
		this.world = new int[size][size];
		this.creatureList = new ArrayList<Creature>();
		
		initWorld();
	}
	
	protected void initWorld(){
		for (int i = 0; i < this.world.length; i++){
			this.world[0][i] = Obstacle.WALL.getId();
			this.world[i][0] = Obstacle.WALL.getId();
			this.world[this.world.length - 1][i] = Obstacle.WALL.getId();
			this.world[i][this.world.length - 1] = Obstacle.WALL.getId();
		}
	}
	
	/**
	 * Adds the creature to the sandbox and returns the id of the creature.
	 * @param creature The creature to be added
	 * @return Returns the id of the creature
	 */
	public int addCreature(Creature creature){
		this.creatureList.add(creature);
		return this.creatureList.size() - 1;
	}
	
	public void setWorld(int world[][]){
		this.world = world;
	}
	
	public void init(){
		for (int i = 0; i < this.creatureList.size(); i++){
			this.creatureList.get(i).getSensor().updateSenses(this);
		}
	}
	
	public int[][] getWorld(){
		return this.world;
	}
	
	public List<Creature> getCreature(){
		return this.creatureList;
	}
	
	public void takeAction(int index, MovementAction action){
		boolean bump = false;
		switch(action){
		case MOVE_UP:
			bump = move(index, Direction.NORTH);
			break;
		case MOVE_DOWN:
			bump = move(index, Direction.SOUTH);
			break;
		case TURN_LEFT:
			turnLeft(index);
			break;
		case TURN_RIGHT:
			turnRight(index);
			break;
		case MOVE_LEFT:
			bump = move(index, Direction.WEST);
			break;
		case MOVE_RIGHT:
			bump = move(index, Direction.EAST);
			break;
		case REMOVE_OBSTACLE:
			action = MovementAction.STAND;
			break;
		case STAND:
			break;
		default:
			action = MovementAction.STAND;
			bump = false;
		}
		removeObstacle(index);
		
		Creature c = this.creatureList.get(index);
		c.addAction(new ActionHistory(action, !bump));
		c.getSensor().updateSenses(this);
	}
	
	private void removeObstacle(int index){
		Creature c = this.creatureList.get(index);
		int x = c.getX();
		int y = c.getY();
		
		clearSpace(x, y);
	}
	
	private void clearSpace(int x, int y){
		Obstacle o = Obstacle.idToEnum(this.world[x][y]);
		if(!o.isClippable()){
			this.world[x][y] = Obstacle.NOTHING.getId();
		}
	}
	
	private boolean move(int index, Direction dir){
		Creature c = this.creatureList.get(index);
		int oldX = c.getX();
		int oldY = c.getY();
		switch(dir){
		case NORTH:
			oldX--;
			break;
		case SOUTH:
			oldX++;
			break;
		case EAST:
			oldY++;
			break;
		case WEST:
			oldY--;
			break;
		}
		if (oldX < 0 || oldY < 0){
			return true;
		}else if(oldX >= this.world.length || oldY >= this.world[0].length){
			return true;
		}
		Obstacle o = Obstacle.idToEnum(this.world[oldX][oldY]);
		if(o.isClippable()){
			return true;
		}
		c.setX(oldX);
		c.setY(oldY);
		return false;
	}
	
	private void turnLeft(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.getNextDirection(MovementAction.TURN_LEFT, c.getDir()));
	}
	
	private void turnRight(int index){
		Creature c = this.creatureList.get(index);
		c.setDir(Direction.getNextDirection(MovementAction.TURN_RIGHT, c.getDir()));
	}
}
