package data;
/**
 * A structure consists of a name and a cost associated with obtaining it. They affect the 
 * game in different ways, but for now I'm leaving it up to the implementation to handle.
 * @author leahj
 * @see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 *
 */
public class Structure {
	/**
	 * Name of this structure
	 */
	private String name;
	/**
	 * Cost of this structure in gold
	 */
	private int cost;
	/**
	 * Nameset that an int can choose from in the constructor
	 */
	private String[] nameSet = {"Trellis", "Yoke", "Irrigation", "Windmill", 
			"Cottage", "TastingRoom", "Medium Cellar", "Large Cellar"};
	/**
	 * int val chooses the name from nameset and sets the appropriate cost
	 * @param val
	 */
	public Structure(int val) {
		switch(val) {
			case 0:
				name = "Trellis";
				cost = 2;
				break;
			case 1 :
				name = "Yoke";
				cost = 2;
				break;
			case 2 :
				name = "Irrigation";
				cost = 3;
				break;
			case 3 :
				name = "Windmill";
				cost = 5;
				break;
			case 4 :
				name = "Cottage";
				cost = 4;
				break;
			case 5:
				name = "Tasting Room" ;
				cost = 6;
				break;
			case 6 :
				name = "Medium Cellar";
				cost = 4;
				break;
			case 7 :
				name =  "Large Cellar";
				cost = 6;
				break;
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
}
