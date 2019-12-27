import java.util.ArrayList;

public class Player {
	private ArrayList<Card> hand = new ArrayList<Card>();
	private int playerId;
	private int victoryPoints;
	private int gold;
	
	private int numWorkers;
	private boolean grandeWorker;
	private int residualPayment;
	private ArrayList<Structure> structuresList = new ArrayList<Structure>();
	
	private int redTokens;
	private int whiteTokens;
	
	private int redWine;
	private int whiteWine;
	private int blushWine;
	private int sparklingWine;
	
	public Player(int playerId, Card_Mama mama, Card_Papa papa) {
		this.playerId = playerId;
		victoryPoints = 0;
		numWorkers = 2;
		grandeWorker = true;
		residualPayment = 0;
		
		redTokens = 0;
		whiteTokens = 0;
		redWine = 0;
		whiteWine = 0;
		blushWine = 0;
		sparklingWine = 0;
		
	}
	
	public int getGold() {
		return gold;
	}
	
	public int getNumWorkers() {
		return numWorkers;
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
	public String toString() {
		return "Player ID: " + playerId + " | Victory Points: " + victoryPoints
				+ " | Hand: " + hand;
	}

	public void draw(Deck deck) {
		hand.add(deck.getCards().get(0));
		deck.getCards().remove(0);
		
	}
}
