package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import card.Card;
import card.Mama;
import card.Papa;
import card.Summer;
import card.Vine;
import card.Winter;
import data.Deck;
import data.Player;
import data.Spot;
import data.WineToken;

public class DriverTests {
	
	Deck grapeDeck = new Deck("grape");
	Deck summerDeck = new Deck("summer");
	Deck orderDeck = new Deck("orders");
	Deck winterDeck = new Deck("winter");
	Deck[] deckArr = {grapeDeck, summerDeck, orderDeck, winterDeck};
	
	Mama mama1 = new Mama(1);
	Mama mama2 = new Mama(2);
	Papa papa1 = new Papa(1);
	Papa papa2 = new Papa(2);
	
	Player player1 = new Player(1, mama1, papa1, 1, deckArr);
	Player player2 = new Player(2, mama2, papa2, 1, deckArr);
	Player[] playerArr = {player1, player2};
	
	@Test
	public void testSpots() {
	
		Spot[] summerSpots = new Spot[6];
		for(int i = 0; i < 6; i++) {
			summerSpots[i] = new Spot(i);
		}

		System.out.println("Choose an unoccupied space. Or use a Grande worker"
				+ "on an occupied space");
		
		for(int i = 0; i < 6; i++) {
			System.out.println((i+1) + " - " + summerSpots[i]);
		}
		
		System.out.println("You have drawn a vine card");
		//Deck[] deckArr = {grapeDeck, summerDeck, orderDeck, winterDeck};
		playerArr[0].draw(deckArr[0]);
		summerSpots[0].occupy(playerArr[0]);
		summerSpots[2].occupy(playerArr[1]);
		
		for(int i = 0; i < 6; i++) {
			System.out.println((i+1) + " - " + summerSpots[i]);
		}
	}
	
	@Test
	public void testSummerCard() {
		//public Summer(int val) {
		ArrayList<Card> hand = new ArrayList<Card>();
		Winter winterCard = new Winter(1);
		Summer summerCard = new Summer(1);
		
		hand.add(summerCard);
		hand.add(winterCard);
		for(Card c : summerDeck.getCards()) {
			System.out.println(c instanceof Summer);
		}
		for(Card c : hand) {
			System.out.println(c instanceof Summer);
		}		
	}

	@Test
	public void testSurveyor() {
		Summer surveyor = new Summer(0);
		System.out.println(surveyor.toString());
		Winter winterCard = new Winter(1);
		
		ArrayList<Card> hand = new ArrayList<Card>();
	
		hand.add(surveyor);
		hand.add(winterCard);
		System.out.println(hand.size());
		
		System.out.println(playerArr[1]);
		
		for(Card c : hand) {
			System.out.println("foo");
			if(c instanceof Summer) {
				Summer summerCard = (Summer) c;
				//summerCard.playSummer(1, 1, playerArr);
				System.out.println("played surveyor");
			}else {
				System.out.println("not a summer card");
			}
		}
		
		System.out.println(playerArr[1]);
	}
	
	@Test
	public void testBroker() {
		Summer surveyor = new Summer(1);
		playerArr[1].addGold(9);
		System.out.println(playerArr[1]);
		//surveyor.playSummer(1, 1, playerArr);
		System.out.println(playerArr[1]);
		//surveyor.playSummer(2, 1, playerArr);
		System.out.println(playerArr[1]);
	}
	
	@Test
	public void testCrushPad() {
		System.out.println(playerArr[0].printHand());
		System.out.println(playerArr[0].printFields());
		System.out.println(playerArr[0].printCrushPad());
		
		Vine[] vines = new Vine[3];
		int counter = 0;
		for(Card c : playerArr[0].getHand()) {
			if(c instanceof Vine) {
				vines[counter]  = (Vine) c;
				counter++;
			}
		}
		
		
		playerArr[0].plantField(1, vines[0]);
		
		System.out.println(playerArr[0].printFields());
		
		//test field harvest method
		WineToken[] harvest = playerArr[0].getField(1).harvestField();
		System.out.println("initial harvest");
		for(int i = 0; i < harvest.length; i++) {
			System.out.print(harvest[i].getColor());
			System.out.println(" " + harvest[i].getValue());
			//System.out.println(harvest[i].get);
		}
		
		
		playerArr[0].addHarvestToCrushPad(1);
		
		System.out.println("CrushPad Red");
		for(int i = 0; i < playerArr[0].getCrushPadRed().length; i++) {
			System.out.println(playerArr[0].getCrushPadRed()[i]);
		}
		System.out.println("CrushPad White");
		for(int i = 0; i < playerArr[0].getCrushPadWhite().length; i++) {
			System.out.println(playerArr[0].getCrushPadWhite()[i]);
		}
		
		
		System.out.println(playerArr[0].printFields());
		System.out.println(playerArr[0].printCrushPad());
		
		
	}
	
	
}
