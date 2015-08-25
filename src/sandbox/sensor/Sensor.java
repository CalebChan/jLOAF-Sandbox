package sandbox.sensor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sandbox.Creature;
import sandbox.Environment;

public abstract class Sensor {
	
	protected Map<String, Sense> senses;
	
	protected Creature c;
	
	public Sensor(List<Sense> senses, Creature c){
		this.senses = new HashMap<String, Sense>();
		for (Sense s : senses){
			this.senses.put(s.getName(), s);
		}
		this.c = c;
	}
	
	public Sense getSense(String senseName){
		return senses.get(senseName);
	}
	
	public Set<String> getSenseKeys(){
		return senses.keySet();
	}
	
	public abstract void updateSenses(Environment environment);

}
