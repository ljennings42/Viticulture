
public class Card_Grape implements Card, Comparable<Card>{
	private String variety;
	private int value;
	private String[] colors = new String[2];
	private String[] prerequisites = new String[2];//structures
	private final String[] colorSet = {"Red", "White", "Blush", "Sparkling"};
	private final String[] varietySet = {"Sangiovese", "Malvasia","Pinot", "Syrah", 
			"Trebbiano","Merlot", "Sauvignon Blanc","Cabernet Sauvignon","Chardonnay"};
	private final String[] prerequisiteSet = {"Trellis", "Irrigation"};
	
	
	public Card_Grape(int val) {
		variety = varietySet[val];
		switch(val) {
		//Sangiovese
		case 0 :
			value = 1;
			colors[0] = colorSet[0];
		//Malvasia
		case 1 :
			value = 1;
			colors[0] = colorSet[1];
		//Pinot
		case 2:
			value = 2;
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			prerequisites[0] = prerequisiteSet[0];
		//Syrah
		case 3:
			value = 2;
			colors[0] = colorSet[0];
			prerequisites[0] = prerequisiteSet[0];
		//Trebbiano
		case 4:
			value = 2;
			colors[0] = colorSet[1];
			prerequisites[0] = prerequisiteSet[0];
		//Merlot
		case 5:
			value = 3;
			colors[0] = colorSet[0];
			prerequisites[0] = prerequisiteSet[1];
		
		//Sauvignon Blanc
		case 6:
			value = 3;
			colors[0] = colorSet[1];
			prerequisites[0] = prerequisiteSet[1];
		//Cabernet Sauvignon
		case 7:
			value = 4;
			colors[0] = colorSet[0];
			prerequisites[0] = prerequisiteSet[0];
			prerequisites[1] = prerequisiteSet[1];
		
		//Chardonnay
		case 8:
			value = 4;
			colors[0] = colorSet[1];
			prerequisites[0] = prerequisiteSet[0];
			prerequisites[1] = prerequisiteSet[1];
		}
	}

	public String getVariety() {
		return variety;
	}
	
	public String[] getColors() {
		return colors;
	}
	
	public String[] getPrerequisites() {
		return prerequisites;
	}

	public int getValue() {
		return value;
	}
	public String toString() {
		return "Grape | " + variety ;
	}

	
	
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}
}
