package data;
/*
 * Initialize game state
 */

import java.util.Random;
import java.util.Scanner;

import card.Mama;
import card.Papa;
import old.Driver;

public class Game {

	//initialize method
	public Deck vineDeck;
	public Deck summerDeck;
	public Deck orderDeck;
	public Deck winterDeck;
	public Deck[] deckArr = new Deck[4];
	private int currentPlayer, year;
	private Player player1, player2;
	private Player[] playerArr = new Player[2];
	String[] wakeUpSpots = {"1 - No Bonus","2 - Draw 1 Vine Card", "3 - Gain 1 Gold",
			"4 - Draw either a Summer or Winter Visitor card", "5 - Gain 1 Victory Point",
			"6 - Gain Temp worker for this year"};
	private Spot[] summerSpots, winterSpots;
	private Structure[] structures;
	public Mama mama1, mama2;
	public Papa papa1, papa2;
	private int currentSeason;
	
	//default is a 2 player game
	public Game() {
		currentPlayer = 1;
		year = 1;
		currentSeason = 0;
		Random rand = new Random();
		int seed = rand.nextInt(18);
		int seed2 = rand.nextInt(18);		
		//reroll so we don't get the same parent. better way to do this?
		while(seed2 == seed) { 
			seed2 = rand.nextInt(18);
		}
		mama1 = new Mama(seed);
		mama2 = new Mama(seed2);
		papa1 = new Papa(seed);
		papa2 = new Papa(seed2);
		
		vineDeck = new Deck("grape");
		summerDeck = new Deck("summer");
		orderDeck = new Deck("orders");
		winterDeck = new Deck("winter");
		deckArr[0] = vineDeck;
		deckArr[1] = summerDeck;
		deckArr[2] = orderDeck;
		deckArr[3] = winterDeck;
	}

	public void createPlayers(int choice1, int choice2) {
		
		player1 = new Player(1, mama1, papa1, choice1, deckArr);
		player2 = new Player(2, mama2, papa2, choice2, deckArr);	
		playerArr[0] = player1;
		playerArr[1] = player2;
	}

	//various init stuff
	public void createBoard() {
		//create summer spots
		summerSpots = new Spot[6];
		for(int i = 0; i < 6; i++) {
			summerSpots[i] = new Spot(i);
		}
		//create winter spots
		winterSpots = new Spot[6];
		for(int i = 6; i < 12; i++) {
			winterSpots[i-6] = new Spot(i);
		}
		//create Structures
		structures = new Structure[8];
		for(int i = 0; i < 8; i++) {
			structures[i] = new Structure(i);
		}
	}

	public Player[] getPlayerArr() {
		return playerArr;
	}
	
	public int getCurrentPlayerID() {
		return currentPlayer;
	}
	
	public Player getCurrentPlayer() {
		if(currentPlayer == 1) {
			return player1;
		}else {
			return player2;
		}
	}
	
	public void updateYear() {
		year++;
	}
	
	public void updateCurrentPlayer() {
		if(currentPlayer == 1) {
			currentPlayer = 2;
		}else {
			currentPlayer = 1;
		}
	}
	
	public int getOppositePlayer() {
		if(currentPlayer == 1) {
			return 2;
		}else {
			return 1;
		}
	}
	
	//check if both players are ready to progress to next turn
	public boolean getPlayersDone() {
		return playerArr[0].getTurnDone() && playerArr[1].getTurnDone();
	}
	
	public int getFirstPlayer() {
		int min = 10;
		int player = 0;
		for(int i =0; i < playerArr.length; i++) {
			if(playerArr[i].getWakeUpPos() < min) {
				player = playerArr[i].getID();
			}
		}
		return player;
	}

	public String printBoardSpring() {
		String ans = "";
		
		for(int i = 0; i < 6 ; i++) {
			if(i != (player1.getWakeUpPos()-1)) {
				ans += wakeUpSpots[i] + "\n";
			}
		}
		return ans;
	}
	
	public String printBoardSummer(){
		String ans = "";
		for(int i = 0; i < 6; i++) {
			ans += " - " + summerSpots[i];
		}
		return ans;
	}
	
	public void progressSeason() {
		currentSeason++;
	}
	//is the game currently in a state of active
		public boolean isActive() {
			return true;
		}
}
