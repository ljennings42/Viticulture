package data;
import java.util.ArrayList;
import java.util.Collections;

import card.Card;
import card.Vine;
import card.Order;
import card.Summer;
import card.Winter;

/**
 * Can be made into a Vines, Summer Visitor, Winter Visitor, or Wine Orders deck or discard pile
 * @author leahj
 *@see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Deck {
	/**
	 * ArrayList that holds the cards currently in this deck
	 */
	private ArrayList<Card> cardList;
	/**
	 * String representation of the card type that will inhabit this deck.
	 */
	private String deckType;
	/**
	 * String representation of the type of card that will inhabit this deck.
	 * The deck is populated in different ways depending on the type of card.
	 * The deck is shuffled after populating.
	 * @param cardType
	 */
	public Deck(String cardType) {
		deckType = cardType;
		cardList = new ArrayList<Card>();
		switch(cardType) {
		case "grape" :
			for(int i = 0; i < 8; i++){
				if(i == 0) {
					for(int sangioveseCount = 0; sangioveseCount < 4; sangioveseCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 1) {
					for(int malvasiaCount = 0; malvasiaCount < 4; malvasiaCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 2) {
					for(int pinotCount = 0; pinotCount < 6; pinotCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 3) {
					for(int syrahCount = 0;syrahCount < 5; syrahCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 4) {
					for(int trebbianoCount = 0;trebbianoCount < 5; trebbianoCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 5) {
					for(int merlotCount = 0;merlotCount < 5; merlotCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 6) {
					for(int sauvBlancCount = 0;sauvBlancCount < 5; sauvBlancCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else if(i == 7) {
					for(int cabCount = 0; cabCount < 4; cabCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}else {
					for(int chardCount = 0; chardCount < 4; chardCount++) {
						Vine card = new Vine(i);
						cardList.add(card);
					}
				}
				
			}
			break;
		case "summer" : 
			for(int i = 0; i < 20; i++){
				Summer card = new Summer(i);
				cardList.add(card);
			}
			break;
		case "orders" :
			for(int i = 0; i < 36; i++){
				Order card = new Order(i);
				cardList.add(card);
			}
			break;
		case "winter" :
			for(int i = 0; i < 20; i++){
				Winter card = new Winter(i);
				cardList.add(card);
			}
			break;
		default :
			break;
		}
		
		shuffle();
	}
	/**
	 * Shuffles the deck using Collections.shuffle()
	 */
	public void shuffle() {
		Collections.shuffle(cardList);
	}
	
	public String getType() {
		return deckType;
	}
	
	public ArrayList<Card> getCards(){
		return cardList;
	}

}
