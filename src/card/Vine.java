package card;

import data.WineToken;

/**
 * One of four decks that are used in game play. Vine cards are planted in players' 
 * fields and when harvested yield red and/or white grape tokens. Some vines require
 * a trellis and/or irrigation structures in order to be planted. The rules of the 
 * game differentiate between grape tokens and wine tokens, but for the purpose of 
 * programming, they are functionally the same, so I am using the same class for both.
 * @author leahj
 *
 */
public class Vine implements Card, Comparable<Card>{
	/**
	 * Name of this vine
	 */
	private String variety;
	/**
	 * Array that holds either 1 or 2 grape tokens of either Red or White color
	 * depending on what this vine produces.
	 */
	private WineToken[] grapeTokens;
	//private int value;
	//private String[] colors = new String[2];
	//private final String[] colorSet = {"Red", "White"};
	/**
	 * Name set for varieties of vines
	 */
	private final String[] varietySet = {"Pinot", "Malvasia","Sangiovese", "Syrah", 
			"Trebbiano","Merlot", "Sauvignon Blanc","Cabernet Sauvignon","Chardonnay"};
	private String structures = "";//structures
	//private final String[] prerequisiteSet = {"Trellis", "Irrigation"};
	
	/**
	 * int val determines the variety and the grape tokens that are assigned. Pinot
	 * is the only variety that has two grape tokens, a red and white
	 * 
	 * @param val
	 */
	public Vine(int val) {
		variety = varietySet[val];
		if(val > 0 ) {
			grapeTokens = new WineToken[1];
		}else {
			grapeTokens = new WineToken[2];
		}
		switch(val) {
		case 0 : //Pinot
			grapeTokens[0] = new WineToken("Red", 1);
			grapeTokens[1] = new WineToken("White", 1);
			break;
		case 1 ://Malvasia
			grapeTokens[0] = new WineToken("White", 1);
			break;
		case 2://Sangiovese
			grapeTokens[0] = new WineToken("Red", 1);
			structures= "Trellis";
			break;
		case 3: //Syrah
			grapeTokens[0] = new WineToken("Red", 2);
			break;
		case 4: //Trebbiano
			grapeTokens[0] = new WineToken("White", 1);
			break;
		case 5: //Merlot
			grapeTokens[0] = new WineToken("Red", 3);
			structures = "Irrigation";
			break;
		case 6: //Sauvignon Blanc
			grapeTokens[0] = new WineToken("White", 3);
			structures = "Irrigation";
			break;
		case 7://Cabernet Sauvignon
			grapeTokens[0] = new WineToken("Red", 4);
			structures = "Trellis, Irrigation";
			break;
		case 8://Chardonnay
			grapeTokens[0] = new WineToken("White", 4);
			structures = "Trellis, Irrigation";
			break;
		}
	}

	public String getVariety() {
		return variety;
	}
	
	public int getValue() {
		return grapeTokens[0].getValue();
	}
	
	/**
	 * Used to sort the grape tokens during harvesting
	 * @return
	 */
	public boolean hasRed() {
		for(int i = 0; i < grapeTokens.length; i++) {
			if(grapeTokens[i].getColor().equals("Red")) {
				return true;
			}
		}
		return false;
	}
	
	public String getPrerequisites() {
		return structures;
	}
	/**
	 * Display vine information to console
	 */
	public String toString() {
		//               Summer |
		String answer = "Vine   | " + variety + " , " + grapeTokens[0].getColor() + " : "+ grapeTokens[0].getValue()  ;
		if(grapeTokens.length > 1) {
			answer += " , " + grapeTokens[1].getColor() + " : " + grapeTokens[1].getValue();
		}
			if(structures != "") {
				answer += " Structures needed: " + structures; 
			}
		return answer;
	}

	
	
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}
}
