package card;

import data.Deck;
import data.Player;
/**
 * Summer Visitor Card is one of 2 kinds of visitor decks that can have a variety of rules and bonus
 * @author leahj
 *@see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Summer implements Card, Comparable<Card>{
	/**
	 * Name of this summer visitor card
	 */
	private String name;
	/**
	 * Ability of this summer visitor card
	 */
	private String ability;
	//private String imgPath;
	/**
	 * Nameset that is used to populate the deck
	 */
	private final String[] nameSet = {"Surveyor", "Broker", "Entertainer", "Wine Critic",
			"Contractor", "Tour Guide", "Novice Guide", "Uncertified Broker", "Vendor",
			"Planter", "Handyman", "Buyer", "Auctioneer", "Peddler", "Landscaper",
			"Blacksmith", "Architect", "Uncertified Architect", "Horticulturist",
			"Patron"};
	/**
	 * Ability set that is used to populate the deck
	 */
	private final String[] abilitySet = {
			//Surveyor
			"Gain 2 gold for each empty field you own OR gain 1 VP for each planted field you own", 
			"Pay 9 gold to gain 3 VP OR lose 2 VP to gain 6 gold", 
			"Pay 4 gold to draw 3 winter cards OR discard 1 wine token and 3 visitor cards to gain 3 VP", 
			"Draw 2 Winter Visitor Cards OR discard 1 Grape Card"
			+ "of value 7 or more to gain 4 VP","Choose 2: Gain 1 VP, build 1 structure"
			+ "or plant 1 Grape card", 
			"Gain 4 Gold OR harvest 1 field", "Novice Guide Ability", "Uncertified Broker Ability",
			"Vendor Ability", "Planter Ability", "Handyman Ability", "Buyer Ability", 
			"Auctioneer Ability", "Peddler Ability", "Landscaper Ability",
			"Build a structure at a 2 Gold discount. If it is a 5 Gold or a 6 Gold Structure"
			+ "also gain 1 VP", "Architect Ability", "Uncertified Architect Ability", 
			"Horticulturist Ability", "Patron Ability"};
	/**
	 * int val is used to assign name and associated ability
	 * @param val
	 */
	public Summer(int val) {
		name = nameSet[val];
		ability = abilitySet[val];
		//imgPath = "images/surveyor";
	}
	/**
	 * Displays information about this summer visitor card to the console
	 */
	public String toString() {
		return "Summer | " + name + " , " + ability;
	}

	public String getName() {
		return name;
	}
	public String getAbility() {
		return ability;
	}

	/**
	 * Handles the majority of the card logic. Each card has their own rule set and
	 * can affect various game components.
	 * @param selection
	 * @param currentPlayer
	 * @param playerArr
	 * @param deckArr
	 */
	public void playSummer(int selection, int currentPlayer, Player[] playerArr, Deck[] deckArr) {
		
		switch(name) {
		case "Surveyor" :
			//"Gain 2 gold for each empty field you own OR gain 1 VP for each planted field you own"
			int emptyFields = 0;
			for(int i = 0; i < 3; i++) {
				if(playerArr[currentPlayer].getField(i).isEmpty()) {
					emptyFields++;
				}
			}
			if(selection == 1) {
				playerArr[currentPlayer].updateGold(2*emptyFields);
			}else {
				playerArr[currentPlayer].updateVP(3 - emptyFields);
			}
			break;
		case "Broker" :
			//	"Pay 9 gold to gain 3 VP OR lose 2 VP to gain 6 gold", 
			if(selection == 1) {
				if(playerArr[currentPlayer].getGold() >= 9) {
					playerArr[currentPlayer].updateGold(-9);
					playerArr[currentPlayer].updateVP(3);
				}else {
					System.out.println("not enough gold, cant use Broker");
				}
			}else {
				playerArr[currentPlayer].updateGold(6);
				playerArr[currentPlayer].updateVP(-2);
			}
		case "Entertainer" :
			//"Pay 4 gold to draw 3 winter cards OR discard 1 wine token and 3 visitor cards to gain 3 VP"
			if(selection == 1) {
				//Deck[] deckArr = {grapeDeck, summerDeck, orderDeck, winterDeck};
				if(playerArr[currentPlayer].getGold() >= 4) {
					playerArr[currentPlayer].updateGold(-4);
					playerArr[currentPlayer].draw(deckArr[3]);
					playerArr[currentPlayer].draw(deckArr[3]);
					playerArr[currentPlayer].draw(deckArr[3]);
				}else {
					System.out.println("not enough gold, cant use Entertainer");
				}
			}else {
				//check that player has at least 1 wine token in cellar or crushpad
				//if(playerArr[currentPlayer].getCellar().size() > 0 || playerArr[currentPlayer].getCrushPad().size() > 0){
				//playerArr[currentPlayer].
			}
			
		default :
			System.out.println("Unimplemented summer card");
			break;
		}
	}
	
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}
}
