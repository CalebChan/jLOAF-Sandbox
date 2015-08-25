package agent;

import java.util.ArrayList;
import java.util.List;

import sandbox.Creature;
import sandbox.Environment;
import sandbox.MovementAction;

public abstract class AbstractSandboxAgent {

	protected List<AgentState> state;
	protected Environment box;
	protected int id;
	protected Creature creature;

	public AbstractSandboxAgent(int size, Creature c) {
		state = new ArrayList<AgentState>();
		box = new Environment(size, size);
		if (c == null){
			creature = createCreature();
		}else{
			creature = c;
		}
		box.addCreature(creature);
	}

	public abstract MovementAction testAction(Creature c);

	protected abstract Creature createCreature();

	public abstract void runAgent(int iterations);
	
	public abstract void saveTrace(String filename);

}