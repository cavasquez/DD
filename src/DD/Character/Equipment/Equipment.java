package DD.Character.Equipment;

import java.io.Serializable;


public abstract class Equipment implements Serializable {
	private static final long serialVersionUID = -1775933955920698814L;
	String name;
	int weight;
	
	abstract public String getName();
	abstract public int getWeight();
	
	
}
