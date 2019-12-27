import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	private ArrayList<Card> cardList;
	private String deckType;
	
	public Deck(String cardType) {
		deckType = cardType;
		cardList = new ArrayList<Card>();
		switch(cardType) {
		case "grape" :
			for(int i = 0; i < 8; i++){
				if(i == 0) {
					for(int sangioveseCount = 0; sangioveseCount < 4; sangioveseCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 1) {
					for(int malvasiaCount = 0; malvasiaCount < 4; malvasiaCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 2) {
					for(int pinotCount = 0; pinotCount < 6; pinotCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 3) {
					for(int syrahCount = 0;syrahCount < 5; syrahCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 4) {
					for(int trebbianoCount = 0;trebbianoCount < 5; trebbianoCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 5) {
					for(int merlotCount = 0;merlotCount < 5; merlotCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 6) {
					for(int sauvBlancCount = 0;sauvBlancCount < 5; sauvBlancCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else if(i == 7) {
					for(int cabCount = 0; cabCount < 4; cabCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}else {
					for(int chardCount = 0; chardCount < 4; chardCount++) {
						Card_Grape card = new Card_Grape(i);
						cardList.add(card);
					}
				}
				
			}
			break;
		case "summer" : 
			for(int i = 0; i < 20; i++){
				Card_Summer card = new Card_Summer(i);
				cardList.add(card);
			}
			break;
		case "orders" :
			for(int i = 0; i < 36; i++){
				Card_Order card = new Card_Order(i);
				cardList.add(card);
			}
			break;
		case "winter" :
			for(int i = 0; i < 20; i++){
				Card_Winter card = new Card_Winter(i);
				cardList.add(card);
			}
			break;
		default :
			break;
		}
		
		
	}
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
