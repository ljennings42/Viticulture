
public class Structure {
	private String name;
	private String[] nameSet = {"Trellis", "Yoke", "Irrigation", "Windmill", 
			"Cottage", "TastingRoom", "Cellars"};
	
	public Structure(int id) {
		name = nameSet[id];
	}
}
