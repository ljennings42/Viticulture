
public class Card_Order implements Card, Comparable<Card>{
	private int victoryPoints;
	private int residualGold;
	private int[] values;
	private String[] colors;
	private final String[] colorSet = {"Red", "White", "Blush", "Sparkling"};
	
	public Card_Order(int val) {
		colors = new String[3];
		values = new int[3];
		if(val < 5) {
			victoryPoints = 2;
			residualGold = 1;
		}else if(val >= 5 && val < 13) {
			victoryPoints = 3;
			residualGold = 1;
		}else if(val >= 13 && val < 23) {
			victoryPoints = 4;
			residualGold = 1;
		}else if(val >= 23 && val < 30) {
			victoryPoints = 5;
			residualGold = 2;
		}else {
			victoryPoints = 6;
			residualGold = 2;
		}
		
		switch(val) {
		//VP: 2
		case 0 :
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 1;
			values[1] = 3;
		case 1 :
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 2;
			values[1] = 2;
		case 2:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 3;
			values[1] = 1;
		case 3:
			colors[0] = colorSet[1];
			values[0] = 5;
		case 4:
			colors[0] = colorSet[2];
			values[0] = 4;
		//VP: 3
		case 5:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 4;
			values[1] = 2;
		case 6:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 3;
			values[1] = 3;
		case 7:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 2;
			values[1] = 4;
		case 8:
			colors[0] = colorSet[0];
			colors[1] = colorSet[0];
			values[0] = 4;
			values[1] = 3;
		case 9:
			colors[0] = colorSet[1];
			colors[1] = colorSet[1];
			values[0] = 4;
			values[1] = 3;
		case 10:
			colors[0] = colorSet[0];
			values[0] = 6;
		case 11:
			colors[0] = colorSet[1];
			values[0] = 6;
		case 12:
			colors[0] = colorSet[3];
			values[0] = 6;
		//VP: 4
		case 13:
			colors[0] = colorSet[3];
			values[0] = 7;
		case 14:
			colors[0] = colorSet[3];
			values[0] = 8;
		case 15:
			colors[0] = colorSet[1];
			values[0] = 6;
		case 17:
			colors[0] = colorSet[1];
			colors[1] = colorSet[1];
			values[0] = 7;
			values[0] = 6;
		case 18:
			colors[0] = colorSet[0];
			colors[1] = colorSet[0];
			colors[2] = colorSet[0];
			values[0] = 4;
			values[1] = 3;
			values[2] = 2;
		case 19:
			colors[0] = colorSet[0];
			colors[1] = colorSet[0];
			colors[2] = colorSet[0];
			values[0] = 4;
			values[1] = 3;
			values[2] = 2;
		case 20:
			colors[0] = colorSet[0];
			colors[1] = colorSet[0];
			values[0] = 3;
			values[1] = 5;
		case 21:
			colors[0] = colorSet[0];
			colors[1] = colorSet[0];
			values[0] = 5;
			values[1] = 3;
		case 22:
			colors[0] = colorSet[0];
			colors[1] = colorSet[2];
			values[0] = 4;
			values[1] = 4;
		case 23:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 4;
			values[1] = 4;
		//VP: 5
		case 24:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			values[0] = 6;
			values[1] = 6;
		case 25:
			colors[0] = colorSet[0];
			colors[1] = colorSet[1];
			colors[2] = colorSet[2];
			values[0] = 2;
			values[1] = 2;
			values[2] = 5;
		case 26:
			colors[0] = colorSet[1];
			colors[1] = colorSet[2];
			values[0] = 3;
			values[1] = 7;
		case 27:
			colors[0] = colorSet[0];
			colors[1] = colorSet[2];
			values[0] = 3;
			values[1] = 7;
		case 28:
			colors[0] = colorSet[0];
			colors[1] = colorSet[0];
			values[0] = 7;
			values[1] = 6;
		case 29:
			colors[0] = colorSet[1];
			colors[1] = colorSet[1];
			values[0] = 7;
			values[1] = 6;
		case 30:
			colors[0] = colorSet[3];
			values[0] = 9;	
		//VP: 6
		case 31:
			colors[0] = colorSet[2];
			colors[1] = colorSet[2];
			values[0] = 7;
			values[1] = 6;
		case 32:
			colors[0] = colorSet[1];
			colors[1] = colorSet[3];
			values[0] = 3;
			values[1] = 7;
		case 33:
			colors[0] = colorSet[1];
			colors[1] = colorSet[3];
			values[0] = 2;
			values[1] = 8;
		case 34:
			colors[0] = colorSet[1];
			colors[1] = colorSet[3];
			values[0] = 4;
			values[1] = 7;
		case 35:
			colors[0] = colorSet[1];
			colors[1] = colorSet[3];
			values[0] = 3;
			values[1] = 8;
		}
	}

	public String toString() {
		return "Order | " + victoryPoints;
	}
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}

}
