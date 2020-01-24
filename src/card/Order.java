package card;

import data.WineToken;

/**
 * Wine Order Card is one of four types of cards that are used during gameplay.
 * These cards are basically missions that have 2 components: goal and the reward.
 * The goal consists of 1-3 wine tokens that each have a color and value. The reward
 * consists of a quantity of victory points and residual income that are awarded
 * on using the card when possessing said combination of wine tokens.
 * @author leahj
 *@see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Order implements Card, Comparable<Card>{
	/**
	 * Victory Points associated with this card. They are awarded to a player when
	 * they play this card and possess the correct combination of associated wine tokens.
	 */
	private int victoryPoints;
	/**
	 * Residual Gold associated with this card. Awarded to a player when
	 * they play this card and possess the correct combination of associated wine tokens.
	 * Residual Gold is gold that is earned at the end of every year. A player's residual
	 * gold starts at zero and gets increased with every order they complete.
	 */
	private int residualGold;
	/**
	 * Each Wine Order card will have 1-3 wineTokens that make up the goal that must
	 * be satisfied to obtain the rewards.
	 */
	private WineToken[] wineTokens;
	
	public Order(int val) {
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
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 1);
			wineTokens[1] = new WineToken("White", 3);
		case 1 :
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 2);
			wineTokens[1] = new WineToken("Red", 2);
		case 2:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 3);
			wineTokens[1] = new WineToken("White", 1);	
		case 3:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("White", 5);
		case 4:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("Blush", 4);
		//VP: 3
		case 5:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 4);
			wineTokens[1] = new WineToken("White", 2);
		case 6:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 3);
			wineTokens[1] = new WineToken("White", 3);
		case 7:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 2);
			wineTokens[1] = new WineToken("White", 4);
		case 8:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 4);
			wineTokens[1] = new WineToken("Red", 3);
		case 9:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 4);
			wineTokens[1] = new WineToken("White", 3);
		case 10:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("Red", 6);
		case 11:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("White", 6);
		case 12:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("Blush", 6);
		//VP: 4
		case 13:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("Sparkling", 7);
		case 14:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("Blush", 8);
		case 15:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("White", 8);
		case 17:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 7);
			wineTokens[1] = new WineToken("White", 6);
		case 18:
			wineTokens = new WineToken[3];
			wineTokens[0] = new WineToken("Red", 4);
			wineTokens[1] = new WineToken("Red", 3);
			wineTokens[2] = new WineToken("Red", 2);
		case 19:
			wineTokens = new WineToken[3];
			wineTokens[0] = new WineToken("White", 4);
			wineTokens[1] = new WineToken("White", 3);
			wineTokens[2] = new WineToken("White", 2);
		case 20:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 3);
			wineTokens[1] = new WineToken("Red", 5);
		case 21:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 3);
			wineTokens[1] = new WineToken("Red", 5);
		case 22:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 4);
			wineTokens[1] = new WineToken("Blush", 4);
		case 23:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 4);
			wineTokens[1] = new WineToken("Blush", 4);
		//VP: 5
		case 24:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 6);
			wineTokens[1] = new WineToken("White", 6);
		case 25:
			wineTokens = new WineToken[3];
			wineTokens[0] = new WineToken("Red", 2);
			wineTokens[1] = new WineToken("White", 2);
			wineTokens[2] = new WineToken("Blush", 5);
		case 26:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 3);
			wineTokens[1] = new WineToken("Blush", 7);
		case 27:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 3);
			wineTokens[1] = new WineToken("Blush", 7);
		case 28:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 7);
			wineTokens[1] = new WineToken("Red", 6);
		case 29:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Red", 7);
			wineTokens[1] = new WineToken("Red", 6);
		case 30:
			wineTokens = new WineToken[1];
			wineTokens[0] = new WineToken("Blush", 9);
			
		//VP: 6
		case 31:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("Blush", 7);
			wineTokens[1] = new WineToken("Blush", 6);
		case 32:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 3);
			wineTokens[1] = new WineToken("Sparkling", 7);
		case 33:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 2);
			wineTokens[1] = new WineToken("Sparkling", 8);
		case 34:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 4);
			wineTokens[1] = new WineToken("Sparkling", 7);
		case 35:
			wineTokens = new WineToken[2];
			wineTokens[0] = new WineToken("White", 3);
			wineTokens[1] = new WineToken("Sparkling", 8);
		}
	}
	/**
	 * Displays information about this card to the console
	 */
	public String toString() {
		String answer = "Order  | Needed: ";
		for(int i = 0 ; i < wineTokens.length; i++) {
			answer += wineTokens[i].getColor() + " " + wineTokens[i].getValue() + " , ";
		}
		return answer + "Reward: " + victoryPoints + " VP, Income: " + residualGold;
	}
	@Override
	public int compareTo(Card card) {
		return this.compareTo(card);
	}

}
