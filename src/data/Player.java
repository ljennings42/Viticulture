package data;
import java.util.ArrayList;

import card.Card;
import card.Vine;
import card.Mama;
import card.Papa;
/**
* Holds all information and methods associated with a player
* <p>
* This game does not belong to me, I am merely adapting it as a crappy console game as a personal project.
* You can buy the actual game at the official site.
* @see <a href="https://stonemaiergames.com/games/viticulture/">Official Site</a>
* @see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
*/

public class Player {
	//Player instance variables
	/**
	 * Player hand is a list of cards
	 */
	private ArrayList<Card> hand = new ArrayList<Card>();
	/**
	 * A List containing string representations of structures to show which are owned by this player
	 */
	private ArrayList<String> structuresList = new ArrayList<String>();
	/**
	 * Each player has exactly 3 fields, each of which can be in a state of active or sold.
	 * Each field has a max value of 5, 6, or 7. This array is populated in the Player constructor
	 */
	private Field[] fields = new Field[3];
	/**
	 * Each player has one red and one white crushpad, each with 9 slots that hold one WineToken. WineTokens
	 * in the crushpad are considered "grape tokens" by the game rules.
	 * Grapes can be sold from the crushpad; 1-3 sell for 1 gold. 4-6 sell for 2 gold. 7-9 sell for 3 gold.
	 * Grapes are transferred from the crushpad to the cellar to create wine.
	 */
	private WineToken[] crushPadRed = new WineToken[9];
	/**
	 * Current highest value wine token in red crush pad.
	 */
	private int crushPadRedValue = 0;
	/**
	 * Each player has one red and one white crushpad, each with 9 slots that hold one WineToken. WineTokens
	 * in the crushpad are considered "grape tokens" by the game rules.
	 * Grapes can be sold from the crushpad; 1-3 sell for 1 gold. 4-6 sell for 2 gold. 7-9 sell for 3 gold.
	 * Grapes are transferred from the crushpad to the cellar to create wine.
	 */
	private WineToken[] crushPadWhite = new WineToken[9];
	/**
	 * Current highest value wine token in white crush pad.
	 */
	private int crushPadWhiteValue = 0;
	/**
	 * Each player starts with a small cellar. 
	 */
	private WineToken[][] smallCellar = new WineToken[2][3];
	private int cellarValRed = 0;
	private int cellarValWhite = 0;
	private int cellarValBlush = 0;
	private int cellarValSparkling = 0;
	/**
	 * Each player can acquire a medium cellar. Medium cellar is needed for a large cellar.
	 */
	private WineToken[][] mediumCellar = new WineToken[3][3];
	private boolean hasMediumCellar = false;
	/**
	 * Each player can acquire a large cellar. Medium cellar is needed for a large cellar.
	 */
	private WineToken[][] largeCellar = new WineToken[4][3];
	private boolean hasLargeCellar = false;
	
	
	/**
	 * Tags this player to differentiate between other players. e.g. player 1 and player 2
	 */
	private int playerId;
	/**
	 * Keeps track of this player's victory points. Can be negative. When it reaches 20, player wins the game
	 */
	private int victoryPoints;
	/**
	 * Positive integer that keeps track of this player's gold. 
	 */
	private int gold;
	/**
	 * Positive integer that keeps track of how much residual payment is earned at the end of the year.
	 * Residual payment is earned from completing wine orders
	 */
	private int residualPayment;
	/**
	 * Positive integer that keeps track of this player's starting position. Players
	 * choose their wake up positions at the start of each year.
	 */
	private int wakeUpPos;
	/**
	 * Positive integer that that keeps track of the current maximum normal workers a player starts
	 * their year with. Every time the player trains a worker, totalWorkers increases. This total
	 * does not include the grande worker or the temp worker.
	 */
	private int totalWorkers;
	/**
	 * Positive integer that keeps track of the number of workers that have been used this year.
	 */
	private int usedWorkers;
	/**
	 * Boolean that keeps track of whether the grande worker has been exhausted so far this year.
	 * Every year the player starts with one grande worker.
	 */
	private boolean grandeWorker;
	/**
	 * Boolean that keeps track of whether there is a temp worker available this year. Temp worker
	 * is given when the player selects the #6 wake up position. 
	 */
	private boolean tempWorker;
	
	/**
	 * Boolean that keeps track of whether this player has passed and is ready to progress to the
	 * next season. Only to summer when players
	 * may want to reserve some of their workers for winter.
	 */
	private boolean turnDone;

	
	/**
	 * A player is inherits resources via a Mama and Papa card
	 * @param playerId
	 * @param mama
	 * @param papa
	 * @param papaOption
	 * @param decks
	 */
	public Player(int playerId, Mama mama, Papa papa, int papaOption, Deck[] decks) {
		this.playerId = playerId;
		victoryPoints = 0;
		totalWorkers = 2;
		usedWorkers = 0;
		grandeWorker = true;
		tempWorker = false;
		residualPayment = 0;
		wakeUpPos = 0;
		
		String[] cardsGiven = mama.getCardsGiven();
		for(int i = 0; i < cardsGiven.length; i++) {
			if(cardsGiven[i] != null) {
				switch(cardsGiven[i]) {
					case "vines" :
						draw(decks[0]);
						break;
					case "summer" :
						draw(decks[1]);
						break;
					case "orders" :
						draw(decks[2]);
						break;
					case "winter" :
						draw(decks[3]);
				}
			}
		}
		
		gold = papa.getGoldGiven();
		if(papaOption == 1) {
			//worker
			if(papa.getOption1().equals("Worker")) {
				totalWorkers++;
			}else if(papa.getOption1().equals("1 VP")) {
				victoryPoints++;
			}else {
				structuresList.add(papa.getOption1());
			}
		}else {
			gold += papa.getOption2();
		}
		
		//populate fields
		for(int i = 0; i < 3; i++) {
			fields[i] = new Field(5 + i);
		}
		
	}//end constructor
	
	//Player methods//
	
	//getters//
	public int getGold() {return gold;}
	public int getTotalWorkers() {return totalWorkers;}
	public int getRemainingWorkers() { return totalWorkers - usedWorkers;}
	public boolean getGrande() {return grandeWorker;}
	public boolean getTurnDone() { return turnDone;}
	public int getWakeUpPos() {return wakeUpPos;}
	public int getID() { return playerId;}
	public int getVP() { return victoryPoints;}
	public Field[] getAllFields() { return fields;}
	public Field getField(int field) { return fields[field];}
	public ArrayList<String> getStructures(){ return structuresList;}
	public WineToken[] getCrushPadRed(){return crushPadRed;}
	public WineToken[] getCrushPadWhite(){return crushPadWhite;}
	public ArrayList<Card> getHand(){return hand;}
	
	//Setters
	public void setWakeUpPos(int pos) {wakeUpPos = pos;}
	public void setVP(int victoryPoints) {this.victoryPoints = victoryPoints;}
	/**
	 * Player side of harvesting a given field. First it gets the harvest from a player's
	 * field by calling the field's harvestField() method. This creates a WineToken[]
	 * containing some quantity of red and/or white WineTokens. Then we sort the 
	 * tokens into either the red or white crushpad. If we have 2 tokens of the 
	 * same value going into the same crushpad, one needs to get devalued until 
	 * a free spot in the crushpad is found.
	 * @param field
	 * @see 
	 */
	public void addHarvestToCrushPad(int field) {
		//returns ArrayList<WineToken> 
		//crushPad.addAll(fields[field].harvestField());
		//harvest contains red and white grape tokens, need to sort
		WineToken[] harvest = fields[field].harvestField();
		int counter = 0;
		for(int i = 1; i <= 9; i++) {
			if(harvest[counter].getValue() == i) {
				if(harvest[counter].getColor().equals("Red")) {	
					//if slot in crushpad is occupied, then devalue current token
					//until an empty slot is found
					//go backward from point we started
					for(int j = i; j > 0; j--) {
						if(crushPadRed[j] == null) {
							crushPadRed[j-1] = harvest[counter];
							break;
						}else {
							harvest[counter].devalue();
						}
					}
				}else {
					//crushPadWhite[i] = harvest[counter]
					for(int j = i; j > 0; j--) {
						if(crushPadWhite[j] == null) {
							crushPadWhite[j-1] = harvest[counter];
							break;
						}else {
							harvest[counter].devalue();
						}
					}
				}
			}
		}
	}
	/**
	 * if there is at least one wine token in the red or white crush pad, or in
	 * any of the cellars, then discard the one of least value.
	 */
	public boolean discardWineToken() {
		if(crushPadRedValue > 0){
			crushPadRed[crushPadRedValue - 1] = null;
			System.out.printf("Discarded a %d value wine token from the Red Crush Pad.\n", crushPadRedValue);
			crushPadRedValue--;
			return true;
		}else if(crushPadWhiteValue > 0) {
			crushPadWhite[crushPadWhiteValue - 1] = null;
			System.out.printf("Discarded a %d value wine token from the White Crush Pad.\n", crushPadWhiteValue);
			crushPadWhiteValue--;
			return true;
		}else if(cellarValRed > 0) {
			if(cellarValRed <= 3) {
				smallCellar[0][cellarValRed - 1] = null;
				System.out.printf("Discarded a %d value wine token from the small cellar.\n", crushPadRedValue);
			}

			
		}
		//there are no wine tokens to discard
		return false;
		
	}
	/**
	 * Given an index of a field (0,1, or 2; remember a player has 3 fields),
	 * and a vine card, call that field's plantVine method, and also discard said vine.
	 * @param field
	 * @param vine
	 */
	public void plantField(int field, Vine vine) {
		fields[field].plantVine(vine);
		discard(vine);
	}
	/**
	 * Given an index of a field, call that field's sellField() method, and player's gold
	 * @param field
	 */
	public void sellField(int field) {
		fields[field].sellField();
		gold +=fields[field].getMaxValue();
	}

	/**
	 * Given an index of a field, call that field's buyField() method, and player's gold
	 * @param field
	 */
	public void buyField(int field) {
		fields[field].buyField();
		gold -=fields[field].getMaxValue();
	}

	/**
	 * At the end of the year, tokens in the crushPads and in the cellars increase
	 * in value.
	 */
	public void ageGrapes() {
		for(int i = 0; i < 9; i++) {
			if(crushPadRed[i] != null) {
				crushPadRed[i].age();
			}
			if(crushPadWhite[i] != null) {
				crushPadWhite[i].age();
			}
		}
		if(crushPadRedValue > 0) {
			crushPadRedValue++;
		}
		if(crushPadWhiteValue > 0) {
			crushPadWhiteValue++;
		}
	}
	
	public void sellGrape(String crushPad, int grapeVal) {
		if(grapeVal <= 3) {
			gold += 1;
		}else if(grapeVal > 3 && grapeVal <= 6) {
			gold += 2;
		}else {
			gold += 3;
		}
		if(crushPad.equals("r")) {
			crushPadRed[grapeVal - 1] = null;
			if(grapeVal == crushPadRedValue) {
				//need to update new max value of red crush pad
				int tempMax = 0;
				for(int i = 0; i < 9; i++) {
					if(crushPadRed[i] != null) {
						tempMax = i + 1;
					}
				}
				crushPadRedValue = tempMax;
			}
		}else {
			crushPadWhite[grapeVal - 1] = null;
			if(grapeVal == crushPadWhiteValue) {
				//need to update new max value of white crush pad
				int tempMax = 0;
				for(int i = 0; i < 9; i++) {
					if(crushPadWhite[i] != null) {
						tempMax = i + 1;
					}
				}
				crushPadWhiteValue = tempMax;
			}
		}
	}
	
	public boolean hasGrapes() {
		for(int i = 0; i < 9; i++) {
			if(crushPadRed[i] != null) {
				return true;
			}
			if(crushPadWhite[i] != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * A player knows whether it has passed and is ready to go on the next season,
	 * regardless of whether the other player is ready. This method is a switch
	 * that swaps turnDone on or off.
	 */
	public void setTurnDone() { 
		if(turnDone) {
			turnDone = false;
		}else {
			turnDone = true;
		}
	}
	
	/**
	 * Add given structure to this player's structuresList, and subtract that
	 * structure's cost from this player's gold
	 */
	public void buildStructure(Structure structure) {
		structuresList.add(structure.getName());
		gold -= structure.getCost();
		
	}
	
	
	public void updateGold(int gold) {this.gold += gold;}
	
	public void updateVP(int vp) {victoryPoints += vp;}
	
	public void addWorker() {totalWorkers++;}//increase total capacity of workers
	
	public void useWorker() {usedWorkers++;}//place a worker
	
	public void useGrande() {grandeWorker = false;}
	
	/**
	 * At year end, reset usedWorkers back to 0, and grandeWorker back to 1
	 */
	public void resetWorkers() {
		usedWorkers = 0;
		grandeWorker = true;
	} 
	
	/**
	 * Adds front of given deck to this player's hand, and removes that card from deck
	 * @param deck
	 */
	public void draw(Deck deck) {
		hand.add(deck.getCards().get(0));
		deck.getCards().remove(0);
	}

	/**
	 * Removes a given card from this player's hand
	 * @param card
	 */
	public void discard(Card card) {
		hand.remove(card);
	}
	
	/**
	 * Displays high level information about this player to the console.
	 */
	public String toString() {
		String answer = "Player " + playerId + " | VP : " + victoryPoints + " | Gold: " + gold 
				+ " | workers remaining: " + (totalWorkers - usedWorkers) + " | Grande: ";
		if(grandeWorker) {
			answer += 1;
		}else {
			answer += 0;
		}
		answer += " | Cards in Hand : " + hand.size() + " | Structures: " + structuresList;
		return answer;
	}
	
	/**
	 * Displays detailed information about this player's hand to the console 
	 * @return
	 */
	public String printHand() {
		String answer =  "Player " + playerId + " hand : " + "\n";
		for(Card c : hand) {
			answer += c.toString() + "\n";
		}
		return answer;
	}
	
	/**
	 * Displays detailed information about this player's crush pad to the console
	 * @return
	 */
	public String printCrushPad() {
		String answer =  "Player " + playerId + " Crush Pad " + "\n";	
		answer += "   Red :    Price:   White: \n";
		int whiteIndex = 1;
		int costIndex = 1;
		for(int i = 1; i <= 9; i++) {
			answer += "[";
			if(crushPadRed[i-1] != null) {
				answer += "*";
			}else {
				answer += i;
			}
			answer += "] ";
			
			if(i % 3 == 0) {
				answer += "- " + costIndex + " - ";
				costIndex++;
				for(int j = 0; j < 3; j++) {
					answer += "[";
					if(crushPadWhite[whiteIndex - 1] != null) {
						answer += "*";
					}else {
						answer += whiteIndex;
					}
					whiteIndex++;
					answer += "] ";
				}
				answer += "\n";
			}	
		}
		return answer;
	}
	
	/**
	 * Displays detailed information about this player's 3 fields pad to the console
	 * @return
	 */
	public String printFields() {
		String answer =  "Player " + playerId + " fields : " + "\n";
		for(int i = 0; i < 3; i++) {
			answer += fields[i].toString();
		}
		return answer;
	}

	public int getCellarValue() {
		// get the highest value wine token in the cellar
		return 1;
	}
}
