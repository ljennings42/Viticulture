
public class Card_Summer implements Card, Comparable<Card>{
//visitor Card
	private String name;
	private String ability;
	private String imgPath;
	private final String[] nameSet = {"Surveyor", "Broker", "Entertainer", "Wine Critic",
			"Contractor", "Tour Guide", "Novice Guide", "Uncertified Broker", "Vendor",
			"Planter", "Handyman", "Buyer", "Auctioneer", "Peddler", "Landscaper",
			"Blacksmith", "Architect", "Uncertified Architect", "Horticulturist",
			"Patron"};
	private final String[] abilitySet = {"Gain 2 gold for each empty field you own OR gain 1 VP"
			+ "for each planted field you own", "Pay 9 gold to gain 3 VP OR lose 2 VP"
			+ "to gain 6 gold", 
			"Entertainer Ability", "Draw 2 Winter Visitor Cards OR discard 1 Grape Card"
			+ "of value 7 or more to gain 4 VP","Choose 2: Gain 1 VP, build 1 structure"
			+ "or plant 1 Grape card", 
			"Gain 4 Gold OR harvest 1 field", "Novice Guide Ability", "Uncertified Broker Ability",
			"Vendor Ability", "Planter Ability", "Handyman Ability", "Buyer Ability", 
			"Auctioneer Ability", "Peddler Ability", "Landscaper Ability",
			"Build a structure at a 2 Gold discount. If it is a 5 Gold or a 6 Gold Structure"
			+ "also gain 1 VP", "Architect Ability", "Uncertified Architect Ability", 
			"Horticulturist Ability", "Patron Ability"};
	
	public Card_Summer(int val) {
		name = nameSet[val];
		ability = abilitySet[val];
		imgPath = "images/surveyor";
	}
	
	public String toString() {
		return "Sum | " + name ;
	}

	public String getName() {
		return name;
	}
	public String getAbility() {
		return ability;
	}

	
	@Override
	public int compareTo(Card card) {
		
		return this.compareTo(card);
	}
}
