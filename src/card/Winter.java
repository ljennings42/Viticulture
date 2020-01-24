package card;
/**
 * Winter Visitor Card is one of 2 kinds of visitor decks that can have a variety of rules and bonus
 * @author leahj
 *@see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Winter implements Card, Comparable<Card>{
	/**
	 * Name of this winter visitor card
	 */
	private String name;
	/**
	 * Ability of this winter visitor card
	 */
	private String ability;
	/**
	 * Name set for winter visitors
	 */
	private final String[] nameSet = {"Promoter", "Mentor", "Innkeeper", "Marketer",
			"Crush Expert", "Jack-of-All-Trades", "Harvest Expert", "Teacher", 
			"Benefactor", "Uncertified Teacher", "Judge", "Harvester", "Professor",
			"Master Vintner", "Assessor", "Queen", "Merchant", "Crusher", 
			"Uncertified Oenologist","Oenologist"};
	/**
	 * Name set for winter visitor abilities
	 */
	//private final String[] abilitySet;
	
	public Winter(int val) {
		name = nameSet[val];
		//ability = abilitySet[val];
	}
	
	public String toString() {
		//      Summer |
		return "Winter | " + name ;
	}

	public String getName() {
		return name;
	}
	
	
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}
}
