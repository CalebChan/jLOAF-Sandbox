package sandbox;

public enum Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST,
	;
	public static Direction getPreviousDirection(MovementAction action, Direction currentDirection){
		switch(action){
		case MOVE_DOWN:
		case REMOVE_OBSTACLE:
		case STAND:
		case MOVE_UP:
			return currentDirection;
		case MOVE_LEFT:
		case TURN_LEFT:
			return Direction.values()[(currentDirection.ordinal() + 1) % Direction.values().length];
		case MOVE_RIGHT:
		case TURN_RIGHT:
			int i = ((currentDirection.ordinal() - 1) < 0)? (currentDirection.ordinal() - 1) + Direction.values().length :(currentDirection.ordinal() - 1);
			return Direction.values()[i];
		case REVERSE:
			throw new UnsupportedOperationException();
		}
		return null;
	}
	
	public static Direction getNextDirection(MovementAction action, Direction currentDirection){
		switch(action){
		case MOVE_DOWN:
		case REMOVE_OBSTACLE:
		case STAND:
		case MOVE_UP:
		case REVERSE:
			return getPreviousDirection(action, currentDirection);
		case MOVE_LEFT:
		case TURN_LEFT:
			return getPreviousDirection(MovementAction.TURN_RIGHT, currentDirection);
		case MOVE_RIGHT:
		case TURN_RIGHT:
			return getPreviousDirection(MovementAction.TURN_LEFT, currentDirection);
		}
		return null;
	}
}
