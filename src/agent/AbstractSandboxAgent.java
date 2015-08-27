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

	public AbstractSandboxAgent(Creature c, Environment environment) {
		state = new ArrayList<AgentState>();
		if (c == null){
			creature = createCreature();
		}else{
			creature = c;
		}
		if (environment != null){
			this.setEnvironment(environment);
		}
	}
	
	public void setEnvironment(Environment environment){
		box = environment;
		box.addCreature(creature);
	}
	
	public Creature getCreature(){
		return creature;
	}

	public abstract MovementAction testAction(Creature c);

	protected abstract Creature createCreature();

	public abstract void runAgent(int iterations);
	
	public abstract void saveTrace(String filename);

}