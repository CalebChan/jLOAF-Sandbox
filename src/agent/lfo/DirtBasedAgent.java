package agent.lfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.MovementAction;
import sandbox.creature.DirtBasedCreature;
import agent.AbstractSandboxAgent;
import agent.AgentState;

public abstract class DirtBasedAgent extends AbstractSandboxAgent {
	
	public DirtBasedAgent(Creature c, Environment environment) {
		super(c, environment);
	}

	@Override
	protected Creature createCreature() {
		return new DirtBasedCreature(2, 2, Direction.NORTH);
	}

	@Override
	public void runAgent(int iterations) {
		for (int i = 0; i < iterations; i++){
			MovementAction action = testAction(creature);
			state.add(new DirtBasedAgentState(action, creature.getSensor()));
			box.makeMove(action, creature);
		}
	}

	@Override
	public void saveTrace(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for  (AgentState s : state){
				if (s instanceof DirtBasedAgentState){
					DirtBasedAgentState b = (DirtBasedAgentState)s;
					int dist [] = b.getDistances();
					int type[] = b.getTypes();
					String output = "";
					for (Direction d : Direction.values()){
						output += dist[d.ordinal()] + "|" + type[d.ordinal()] + "|";
					}
					output += s.getAction().name();
					writer.write(output + "\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
