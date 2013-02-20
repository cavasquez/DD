package DD.MapTool;
import java.io.Serializable;
import java.util.Comparator;

public class OPSComparator implements Comparator<Objects>, Serializable{
	

		public int compare(Objects o1, Objects o2) {
			if(o1.priority < o2.priority){
				return 1;
			}
			if(o1.priority > o2.priority)
				return -1;
			else
				return 0;
		}
}
