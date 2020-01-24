package card;

/**
 * One mama card and one papa card are given to each player at the beginning of the game.
 * These cards determine the starting resources for each player. The mama card always
 * gives 2 workers and some quantity of cards to start with.
 * @author leahj
 * @see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Mama implements Card {
	/**
	 * The name of this mama
	 */
	private String name;
	/**
	 * Each mama holds up to 3 cards that they will bequeath. This is a string representation
	 * of the cards that will be given, the player constructor will use this array
	 * to determine what cards to draw.
	 */
	private String[] cardsGiven = new String[3];
	/**
	 * Name set that holds the names that will be assigned to the mama. 
	 */
	private final String[] mommaNames = {"Emily", "Nicole", "Teruyo", "Danyel", "Alena", "Margaret",
			"Jess", "Christine", "Ariel", "Laura", "Casey", "Rebecca", "Deann", "Alyssa",
			"Nici", "Falon", "Margot", "Naja"};
	
	/**
	 * Val is an integer that will be randomly generated to create a randomly spawned
	 * mama card
	 * @param val
	 */
	public Mama(int val) {
		name = mommaNames[val];
		switch(val) {
		case 0 : //Emily
			/*
			cardsGiven[0] = "vines";
			cardsGiven[1] = "summer";
			cardsGiven[2] = "summer";*/
			//testing
			cardsGiven[0] = "vines";
			cardsGiven[1] = "vines";
			cardsGiven[2] = "vines";
			break;
		case 1 : //Nicole
			cardsGiven[0] = "vines";
			cardsGiven[1] = "winter";
			break;
		case 2 : //Teruyo
			cardsGiven[0] = "vines";
			cardsGiven[1] = "vines";
			cardsGiven[2] = "winter";
			break;
		case 3 : //Danyel
			cardsGiven[0] = "summer";
			cardsGiven[1] = "summer";
			cardsGiven[2] = "winter";
			break;
		case 4 : //Alena
			cardsGiven[0] = "vines";
			cardsGiven[1] = "summer";
			cardsGiven[2] = "orders";
			break;
		case 5 : //Margaret
			cardsGiven[0] = "vines";
			cardsGiven[1] = "vines";
			cardsGiven[2] = "summer";
			break;
		case 6 : //Jess
			cardsGiven[0] = "summer";
			cardsGiven[1] = "orders";
			cardsGiven[2] = "orders";
			break;
		case 7 : //Christine
			cardsGiven[0] = "vines";
			cardsGiven[1] = "winter";
			cardsGiven[2] = "winter";
			break;
		case 8 : //Ariel
			cardsGiven[0] = "summer";
			cardsGiven[1] = "orders";
			break;
		case 9 : //Laura
			cardsGiven[0] = "vines";
			cardsGiven[1] = "orders";
			cardsGiven[2] = "orders";
			break;
		case 10 : //Casey
			cardsGiven[0] = "orders";
			cardsGiven[1] = "orders";
			cardsGiven[2] = "winter";
			break;
		case 11 : //Rebecca
			cardsGiven[0] = "summer";
			cardsGiven[1] = "summer";
			cardsGiven[2] = "orders";
			break;
		case 12 : //Deann
			cardsGiven[0] = "vines";
			cardsGiven[1] = "orders";
			cardsGiven[2] = "winter";
			break;
		case 13 : //Alyssa
			cardsGiven[0] = "vines";
			cardsGiven[1] = "summer";
			cardsGiven[2] = "winter";
			break;
		case 14 : //Nici
			cardsGiven[0] = "vines";
			cardsGiven[1] = "vines";
			cardsGiven[2] = "orders";
			break;
		case 15 : //Falon
			cardsGiven[0] = "orders";
			cardsGiven[1] = "winter";
			cardsGiven[2] = "winter";
			break;
		case 16 : //Margot
			cardsGiven[0] = "summer";
			cardsGiven[1] = "orders";
			cardsGiven[2] = "winter";
			break;
		case 17 : //Naja
			cardsGiven[0] = "summer";
			cardsGiven[1] = "winter";
			cardsGiven[2] = "winter";
			break;
		}
	}
	
	/**
	 * Displays mama information to the console
	 */
	public String toString() {
		return name + ", 2 Workers, Cards: " + cardsGiven[0] + " , " + cardsGiven[1] + " , " + cardsGiven[2];
	}

	public String[] getCardsGiven() {
		return cardsGiven;
	}

	
}
