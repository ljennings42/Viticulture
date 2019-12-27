
public class Card_Winter implements Card, Comparable<Card>{

	private String name;
	//private String ability;
	private final String[] nameSet = {"Promoter", "Mentor", "Innkeeper", "Marketer",
			"Crush Expert", "Jack-of-All-Trades", "Harvest Expert", "Teacher", 
			"Benefactor", "Uncertified Teacher", "Judge", "Harvester", "Professor",
			"Master Vintner", "Assessor", "Queen", "Merchant", "Crusher", 
			"Uncertified Oenologist","Oenologist"};
	
	
	public Card_Winter(int val) {
		name = nameSet[val];
		//ability = abilitySet[val];
	}
	
	public String toString() {
		return "Wint | " + name ;
	}

	public String getName() {
		return name;
	}
	
	
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}
}
