package card;
/**
 * One mama card and one papa card are given to each player at the beginning of the game.
 * These cards determine the starting resources for each player. The papa always gives a grande
 * worker, a quantity of gold, and two options for a third bonus that the player selects.
 * The third option always consists of two choices: gold or some other bonus; a structure, 
 * 1 additional worker, or 1 additional VP
 * @author leahj
 * @see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Papa implements Card {
	/**
	 * Name of this papa card
	 */
	private String name;
	/**
	 * The amount of gold that this papa will bequeath
	 */
	private int goldGiven;
	/**
	 * A string representation of whatever the first option for the third bonus. 
	 * This option can be a structure, 1 additional victory point, or 1 additional worker
	 */
	private String option1; 
	/**
	 * An integer representing the amount of gold for the second option for the third bonus
	 */
	private int option2; 
	/**
	 * Name set of papa names to be assigned by randomly generated val int
	 */
	private final String[] papaNames = {"Andrew","Paul", "Jerry", "Matthew",
			"Christian", "Stephan", "Steven", "Jay", "Trevor", "Matt", 
			"Josh", "Joel", "Kozi", "Raymond", "Gary", "Rafael", "Morten", "Alan"};
	/**
	 * Val is an integer that will be randomly generated to create a randomly spawned
	 * papa card
	 * @param val
	 */
	public Papa(int val) {
		name = papaNames[val];
		switch(val) {
		case 0 : //Andrew
			goldGiven = 4;
			option1 = "Trellis";
			option2 = 2;
			break;
		case 1 : //Paul
			goldGiven = 5;
			option1 = "Trellis";
			option2 = 1;
		case 2 : //Jerry
			goldGiven = 2;
			option1 = "Windmill";
			option2 = 4;
		case 3 : //Matthew
			goldGiven = 1;
			option1 = "Windmill";
			option2 = 5;;
			break;
		case 4 : //Christian
			goldGiven = 3;
			option1 = "Irrigation";
			option2 = 3;
			break;
		case 5 : //Stephan
			goldGiven = 4;
			option1 = "Irrigation";
			option2 = 2;
			break;
		case 6 : //Steven
			goldGiven = 6;
			option1 = "Yoke";
			option2 = 1;
			break;
		case 7 :  //Jay
			goldGiven = 5;
			option1 = "Yoke";
			option2 = 2;
			break;
		case 8 : //Trevor
			goldGiven = 1;
			option1 = "Tasting Room";
			option2 = 5;
			break;
		case 9:  //Matt
			goldGiven = 0;
			option1 = "Tasting Room";
			option2 = 6;
			break;
		case 10 : //Josh
			goldGiven = 3;
			option1 = "Medium Cellar";
			option2 = 4;
			break;
		case 11 : //Joel
			goldGiven = 4;
			option1 = "Medium Cellar";
			option2 = 3;
			break;
		case 12 : //Kozi
			goldGiven = 2;
			option1 = "Cottage";
			option2 = 4;
			break;
		case 13 : //Raymond
			goldGiven = 3;
			option1 = "Cottage";
			option2 = 3;
			break;
		case 14 : //Gary
			goldGiven = 3;
			option1 = "Worker";
			option2 = 3;
			break;
		case 15 ://Rafael
			goldGiven = 2;
			option1 = "Worker";
			option2 = 4;
			break;
		case 16 :  //Morten
			goldGiven = 4;
			option1 = "1 VP";
			option2 = 3;
			break;
		case 17 : //Alan
			goldGiven = 5;
			option1 = "1 VP";
			option2 = 2;
			break;
		}
	}
	/**
	 * Displays papa information to the console
	 */
	public String toString() {
		String answer  = name + ", " + goldGiven + " Gold, 1 Grande Worker";
		//answer += "and " + option1 + " or " + option2 + " Gold"; 
		return answer;
	}
	public String getName() {
		return name;
	}
	
	public int getGoldGiven() {
		return goldGiven;
	}
	
	public String getOption1() {
		return option1;
	}
	
	public int getOption2() {
		return option2;
	}
}
