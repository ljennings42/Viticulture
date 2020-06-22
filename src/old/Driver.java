package old;
import card.*;
import card.Summer;
import data.Deck;
import data.Player;
import data.Spot;
import data.Structure;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Driver {
	
	//return updated variables
	public static int swapPlayers(Player[] playerArr, int currentPlayer) {
		if(currentPlayer == 1) {
			
			//if player 1 is done, and player 2 is not done, then swap
			if(playerArr[0].getTurnDone() == true && playerArr[1].getTurnDone() == false) {
				currentPlayer++;
			}
			//if player 1 is not done, player 2 is done, do not swap
			//else if(playerArr[0].getTurnDone() == false && playerArr[1].getTurnDone() == true) {
				//playerArr[0].setTurnDone();
				
			//}
			else if(playerArr[0].getTurnDone() == false  && playerArr[1].getTurnDone() == false) {
				currentPlayer++;
			}
		}
		//current player 2
		else {
			//if player 2 is done, and player 1 is not done, then swap
			if(playerArr[1].getTurnDone() == true && playerArr[0].getTurnDone() == false) {
				currentPlayer--;
			}//if player 1 is not done, player 2 is done, do not swap
			//else if(playerArr[0].getTurnDone() == false && playerArr[1].getTurnDone() == true) {
				//playerArr[0].setTurnDone();
				//break;
			//both players not done, swap normally
				//return currentPlayer;
			//}
			else if(playerArr[0].getTurnDone() == false  && playerArr[1].getTurnDone() == false) {
				currentPlayer--;
			}	
		}
		return currentPlayer;
		
	}
	
	public static void main(String[] args) {
		//initialize method
		Driver driver = new Driver();
		Deck vineDeck = new Deck("grape");
		Deck summerDeck = new Deck("summer");
		Deck orderDeck = new Deck("orders");
		Deck winterDeck = new Deck("winter");
		Deck[] deckArr = {vineDeck, summerDeck, orderDeck, winterDeck};
		
		Scanner scanner = new Scanner(System.in);
		int inputInt;
		String inputString;
		boolean gameLoop = true;
		int currentPlayer = 1;
		int currentSeason = 1;
		int year = 1;
		
		boolean turnEnd = false;
		
		Random rand = new Random();
		int seed = rand.nextInt(18);
		int seed2 = rand.nextInt(18);
		//reroll so we don't get the same parent
		while(seed2 == seed) { 
			seed2 = rand.nextInt(18);
		}
		Mama mama1 = new Mama(seed);
		Mama mama2 = new Mama(seed2);

		Papa papa1 = new Papa(seed);
		Papa papa2 = new Papa(seed2);

		System.out.println("Welcome to Viticulture!");
		System.out.println("To learn more about the game, explore the rules here:"
				+ " https://stonemaiergames.com/games/viticulture/rules/");
		System.out.println("Player 1 , your parents are : ");
		System.out.println("Mama : " + mama1.toString());
		System.out.println("Papa : " + papa1.toString());
		System.out.println("Choose ");
		System.out.println("1 - " + papa1.getOption1());
		System.out.println("2 - " + papa1.getOption2() + " Gold");
		
		inputInt = scanner.nextInt();
		Player player1 = new Player(1, mama1, papa1, inputInt, deckArr);
		
		System.out.println("Player 2 , your parents are : ");
		System.out.println("Mama : " + mama2.toString());
		System.out.println("Papa : " + papa2.toString());
		System.out.println("Choose ");
		System.out.println("1 - " + papa2.getOption1());
		System.out.println("2 - " + papa2.getOption2() + " Gold");
		
		inputInt = scanner.nextInt();
		Player player2 = new Player(2, mama2, papa2, inputInt, deckArr);
		Player[] playerArr = {player1, player2};
		
		//create summer spots
		Spot[] summerSpots = new Spot[6];
		for(int i = 0; i < 6; i++) {
			summerSpots[i] = new Spot(i);
		}
		//create winter spots
		Spot[] winterSpots = new Spot[6];
		for(int i = 6; i < 12; i++) {
			winterSpots[i-6] = new Spot(i);
		}
		//create Structures
		Structure[] structures = new Structure[8];
		for(int i = 0; i < 8; i++) {
			structures[i] = new Structure(i);
		}

		System.out.println("YEAR " + year);
		
		while(gameLoop) {
			//System.out.print("\033[H\033[2J");
			//System.out.println("VITICULTURE");
			//System.out.println("Current Player: " + currentPlayer);
			//System.out.println("YEAR " + year);
			int oppositePlayer;
			if(currentPlayer == 1) {
				oppositePlayer = 2;
			}else {
				oppositePlayer = 1;
			}
			
			switch(currentSeason) {
			case 1 :
				System.out.println("............................................................"
						+ "............................................................");
				System.out.println("SPRING" );
				System.out.println(playerArr[currentPlayer - 1]);
				System.out.println("Place your wake up token");
				String[] wakeUpSpots = {"1 - No Bonus","2 - Draw 1 Vine Card", "3 - Gain 1 Gold",
				"4 - Draw either a Summer or Winter Visitor card", "5 - Gain 1 Victory Point",
				"6 - Gain Temp worker for this year"};
				for(int i = 0; i < 6 ; i++) {
					if(i != (player1.getWakeUpPos()-1)) {
						System.out.println(wakeUpSpots[i]);
					}
				}
				inputInt = scanner.nextInt();
				boolean loopCheck = true;
				while(loopCheck) {
					//check that current player's selection is not the same as previous player's
					if(inputInt != playerArr[oppositePlayer - 1].getWakeUpPos()) {
						switch(inputInt) {
						case 1 :
							System.out.println("No Bonus, but you get to go first!");
							playerArr[currentPlayer - 1].setWakeUpPos(1);
							break;
						case 2 :
							System.out.println("You draw 1 Vine Card");
							playerArr[currentPlayer - 1].draw(vineDeck);
							playerArr[currentPlayer - 1].setWakeUpPos(2);
							break;
						case 3 : 
							System.out.println("You gain 1 Gold");
							playerArr[currentPlayer - 1].updateGold(1);
							playerArr[currentPlayer - 1].setWakeUpPos(3);
							break;
						case 4 :
							System.out.println("Choose to Draw a Summer or Winter Visitor card");
							System.out.println("1 - Summer");
							System.out.println("2 - Winter");
							
							 inputInt = scanner.nextInt();
							 
							 if(inputInt == 1) {
								 playerArr[currentPlayer - 1].draw(summerDeck);
							 }else {
								 playerArr[currentPlayer - 1].draw(winterDeck);
							 }
							 playerArr[currentPlayer - 1].setWakeUpPos(4);
							break;
						case 5 :
							System.out.println("You gain 1 Victory Point");
							playerArr[currentPlayer - 1].updateVP(1);
							playerArr[currentPlayer - 1].setWakeUpPos(5);
							break;
						case 6 :
							System.out.println("You gain 1 temp worker this year");
							playerArr[currentPlayer - 1].addWorker();
							playerArr[currentPlayer - 1].setWakeUpPos(6);
							break;
						default :
							System.out.println("invalid input");
							break;
						}
						loopCheck = false;
					}else {
						System.out.println("Player " + oppositePlayer + " has already"
								+ " selected that position. Please choose another wakeup position.");
						for(int i = 0; i < 6 ; i++) {
							if(i != (player1.getWakeUpPos()-1)) {
								System.out.println(wakeUpSpots[i]);
							}
						}
						inputInt = scanner.nextInt();
					}
				}
				
				//let player 2 go to complete season
				if(currentPlayer == 1) {
					currentPlayer++;
				}else {
					//set player order for rest of the year
					if(playerArr[0].getWakeUpPos() < playerArr[1].getWakeUpPos()) {
						currentPlayer--;
						System.out.println("Player 1 is the early riser");
					}else {
						System.out.println("Player 2 is the early riser");
					}
					currentSeason++;
				}
				System.out.println("............................................................"
						+ "............................................................");
				break;
			////////////////////////////////////////////////////////////////////	
			case 2 :
				System.out.println("SUMMER" );
				System.out.println(playerArr[currentPlayer - 1]);
				System.out.println(playerArr[currentPlayer - 1].printHand());
				System.out.println("w - Place a worker on a summer action space");
				System.out.println("p - Pass turn; forego placing any more workers");
				inputString = scanner.next();
				int summerSpot;
				while(inputString.equals("w") == false && inputString.equals("p") == false) {
					System.out.println("invalid input. Please enter \"w\" or \"p\"");
					inputString = scanner.next();
				}
				if(inputString.equals("p")) {
					System.out.println("Player " + currentPlayer + " passes");
					playerArr[currentPlayer - 1].setTurnDone();
				}else if(inputString.equals("w")){
					//check that player has enough workers to continue
					if(playerArr[currentPlayer - 1].getRemainingWorkers() > 0 || playerArr[currentPlayer - 1].getGrande()) {
						System.out.println("Choose an unoccupied space. Or use a Grande worker"
								+ " on an occupied space");
						for(int i = 0; i < 6; i++) {
							System.out.println((i+1) + " - " + summerSpots[i]);
						}
						//inputInt = scanner.nextInt();
						summerSpot = scanner.nextInt();
						boolean usedGrande = false;
						//check that the user selects an appropriate space
						//if space is occupied or no Grande Worker, ask to reselect
						while(summerSpots[summerSpot - 1].getIsOccupied()) {
							System.out.println("That spot is occupied.");
							if( playerArr[currentPlayer - 1].getGrande() == false) {
								System.out.println("You have no more Grande Workers, choose another space");
							}else {
								System.out.println("g - use Grande Worker");
								System.out.println("or choose another space");
								inputString = scanner.next();
								if(inputString.equals("g")) {
									System.out.println("Used Grande Worker on " + summerSpots[summerSpot - 1].getTitle());
									usedGrande = true;
									playerArr[currentPlayer - 1].useGrande();
									break;
								}
							}
							summerSpot = scanner.nextInt();
						}
						//proceed to logic on success
						switch(summerSpot) {
						//draw a vine card
						case 1 :
							System.out.println("Player " + currentPlayer + " draws a vine card");
							//Deck[] deckArr = {grapeDeck, summerDeck, orderDeck, winterDeck};
							playerArr[currentPlayer - 1].draw(deckArr[0]);
							break;
						//give a tour
						case 2 :
							System.out.println("Player " + currentPlayer + " gives a tour. You gain 2 gold!");
							playerArr[currentPlayer - 1].updateGold(2);
							//check for wine cellar bonus
							if(playerArr[currentPlayer - 1].getStructures().contains("Tasting Room")) {
								//if player has at least one wine token in cellar
								if(playerArr[currentPlayer - 1].getCellar().size() > 0) {
									System.out.println("You get 1 VP from Tasting Room");
									playerArr[currentPlayer - 1].updateVP(1);
								}
							}
							break;
						//build a structure
						case 3 : 
							System.out.println("Choose a structure to build. You have " + playerArr[currentPlayer - 1].getGold() + " Gold");
							int counter = 1;
							Structure[] buildableStructures;
							for(int i = 0; i < 7; i++) {
								//if player does not already own structure then proceed
								if(playerArr[currentPlayer - 1].getStructures().contains(structures[i].getName()) == false) {
										System.out.println(counter + " - " + structures[i].getName() + " , cost: " + structures[i].getCost());
										counter++;
								}
							}
							if(playerArr[currentPlayer - 1].getStructures().contains("Medium Cellar")) {
								System.out.println(counter + " - " + structures[7].getName() + " , cost: " + structures[7].getCost() + " Gold");
							}
							
							//need to make a new truncated array for user to access
							buildableStructures = new Structure[counter];
							counter = 0;
							for(int i = 0; i < 7; i++) {
								//if player does not already own structure then proceed
								if(playerArr[currentPlayer - 1].getStructures().contains(structures[i].getName()) == false) {
										buildableStructures[counter] = structures[i];
										counter++;
								}
							}
							
							int inputStructure = scanner.nextInt();
							//check that player has enough gold to cover cost of structure
							while(inputStructure > 0) {
								if(playerArr[currentPlayer - 1].getGold() >= buildableStructures[inputStructure - 1].getCost()) {
								
									playerArr[currentPlayer - 1].buildStructure(buildableStructures[inputStructure - 1]);
									break;
								}else {
									System.out.println("You don't have enough gold, please choose a different structure or ");
									System.out.println("0 - pass turn");
									inputStructure = scanner.nextInt();
								}
							}
							if(inputStructure > 0) {
								System.out.println("Player " + currentPlayer + " has built a " + buildableStructures[inputStructure-1].getName());
							}
							break;
						//play a visitor card
						case 4 :
							//check if there are any visitor cards to play
							counter = 0;
							for(Card c : playerArr[currentPlayer - 1].getHand()) {
								//System.out.println(c.toString());
								//System.out.println(c instanceof Card);
								if(c instanceof Summer) {
									//System.out.println("you have a summer card");
									counter++;
								}
							}
							System.out.println("Number of summer cards: " + counter);
							if(counter > 0 ) {
								System.out.println("Choose a Summer Visitor Card to play");
								counter = 1;
								for(Card c : playerArr[currentPlayer - 1].getHand()) {
									if(c instanceof Summer) {
										System.out.println(counter + " - " + c.toString());
										counter++;
									}
								}
								System.out.println("You played a Summer Visitor Card");
							}else {
								System.out.println("You don't have any Summer Visitor Cards");
							}
							break;
						//sell one or more grapes and/or fields	
						case 5 :
							System.out.println("g - Sell one or more grapes");
							System.out.println("f - Sell one unplanted field");
							inputString = scanner.next();
							if(inputString.equals("g")) {
								System.out.println( playerArr[currentPlayer - 1].printCrushPad());
								if(playerArr[currentPlayer - 1].hasGrapes()) {
									System.out.println("Choose a crushPad");
									System.out.println("r - Red");
									System.out.println("w - White");
									inputString = scanner.next();
									System.out.println("Choose a grape; 1-9");
									inputInt = scanner.nextInt();
									
									playerArr[currentPlayer - 1].sellGrape(inputString, inputInt);
									
								}else {
									System.out.println("You have no grapes in your crushpad");
								}
								

							}else if(inputString.equals("f")) {
								System.out.print("Choose a field to Sell \n");
								System.out.print("1 - " + playerArr[currentPlayer - 1].getField(0).toString());
								System.out.print("2 - " + playerArr[currentPlayer - 1].getField(1).toString());
								System.out.print("3 - " + playerArr[currentPlayer - 1].getField(2).toString());
								
								inputInt = scanner.nextInt();
								if(playerArr[currentPlayer - 1].getField(inputInt).getPlantedGrapes().size() == 0) {
									playerArr[currentPlayer - 1].sellField(inputInt - 1);
									System.out.println("You have sold a field");
									System.out.println(playerArr[currentPlayer - 1].printFields());
								}else {
									System.out.println("You can't sell a field that has grapes planted in it.");
								}	
							}
							
							break;
						case 6 :
							System.out.println("Choose a vine to plant");
							//display only the vine cards for user to choose from
							counter = 0;
							for(Card c : playerArr[currentPlayer - 1].getHand()) {
								if(c instanceof Vine) {
									counter++;
		 						}
							}
							
							if(counter > 0) {
								Vine[] vineHand = new Vine[counter];
								counter = 0;
								for(Card c : playerArr[currentPlayer - 1].getHand()) {
									if(c instanceof Vine) {
										vineHand[counter] = (Vine) c;
										counter++;
										//System.out.println("foo");
									}
								}
								
								for(int i = 0; i < vineHand.length; i++) {
									System.out.println((i+1) + " - " + vineHand[i].toString());
								}
								//check that player has structures needed
								inputInt = scanner.nextInt();
								boolean vineCheck = true;
								Vine selectedVine = null;
								while(vineCheck) {
									if(vineHand[inputInt - 1].getPrerequisites().equals("")) {
										selectedVine = vineHand[inputInt - 1];
										vineCheck = false;	
									}
									else if(vineHand[inputInt - 1].getPrerequisites().equals("Trellis") && playerArr[currentPlayer - 1].getStructures().contains("Trellis")) {
										selectedVine = vineHand[inputInt - 1];
										vineCheck = false;		
									}else if(vineHand[inputInt - 1].getPrerequisites().equals("Irrigation") && playerArr[currentPlayer - 1].getStructures().contains("Irrigation")) {
										selectedVine = vineHand[inputInt - 1];
										vineCheck = false;
									}else if(vineHand[inputInt - 1].getPrerequisites().equals("Trellis, Irrigation") && playerArr[currentPlayer - 1].getStructures().contains("Trellis, Irrigation")) {
										selectedVine = vineHand[inputInt - 1];
										vineCheck = false;
									}else {
										System.out.println("You need a " + vineHand[inputInt - 1].getPrerequisites() + " to plant that."
												+ " Please choose a different vine to plant or");
										System.out.println("0 - to pass turn");
										inputInt = scanner.nextInt();
										if(inputInt == 0) {
											vineCheck = false;
										}
									}
								}
								
								if(selectedVine != null) {
									System.out.println("Choose a field to plant in");
									System.out.print("1 - " + playerArr[currentPlayer - 1].getField(0).toString());
									System.out.print("2 - " + playerArr[currentPlayer - 1].getField(1).toString());
									System.out.print("3 - " + playerArr[currentPlayer - 1].getField(2).toString());
									
									inputInt = scanner.nextInt();
									 
									playerArr[currentPlayer - 1].plantField(inputInt - 1, selectedVine);
									
									System.out.println("You planted a " + selectedVine.getVariety());
									
									System.out.println(playerArr[currentPlayer - 1].printFields());
								}else {
									System.out.println("You chose to pass turn");
								}
								
							}else {
								System.out.println("You have no vines to plant");
							}
							
							break;
						default :
							System.out.println("invalid input");
							break;
						}
						
						//handle updating works at end because same logic regardless of case
						summerSpots[summerSpot - 1].occupy(playerArr[currentPlayer - 1]);
						if(usedGrande == false) {
							//when only a grande worker is left and defaults to grande
							if(playerArr[currentPlayer - 1].getRemainingWorkers() <= 0 && playerArr[currentPlayer - 1].getGrande()) {
								playerArr[currentPlayer - 1].useGrande();
							}else {
								playerArr[currentPlayer - 1].useWorker();
							}
						}
					}else {
						//System.out.println("Player " + currentPlayer + " has no more workers. Pass turn");
						playerArr[currentPlayer - 1].setTurnDone();
					}
					
					//turnEnd = true;
				}else {
					System.out.println("invalid input try again");
				}
				//check if player has used all their workers
				if(playerArr[currentPlayer - 1].getRemainingWorkers() == 0 && playerArr[currentPlayer - 1].getGrande()== false) {
					System.out.println("Player " + playerArr[currentPlayer - 1].getID() + " has used all their workers and passes turn.");
					playerArr[currentPlayer - 1].setTurnDone();
				}
				
				//swap players
				//if one player is done and the other isn't, it should swap players then stay on same player
				//current player 1
				currentPlayer = swapPlayers(playerArr, currentPlayer);
				
				
				//driver.swapPlayers(playerArr, currentPlayer);
				//if both players are done then progress to next season	
				if(playerArr[0].getTurnDone() && playerArr[1].getTurnDone()) {
					currentSeason++;
				}
				System.out.println("............................................................"
						+ "............................................................");
				break;
			////////////////////////////////////////////////////////////////////
			case 3 :
				System.out.println("FALL" );
				System.out.println(playerArr[currentPlayer - 1]);
				System.out.println(playerArr[currentPlayer - 1].printHand());
				System.out.println("s - Draw a Summer Visitor card");
				System.out.println("w = Draw a Winter Visitor card");
				
				String inputFall = scanner.next();
				
				while(inputFall.equals("s") == false && inputFall.equals("w") == false) {

					System.out.println("invalid input. Please enter s or w");
					
					System.out.println(playerArr[currentPlayer - 1].printHand());
					System.out.println("s - Draw a Summer Visitor card");
					System.out.println("w = Draw a Winter Visitor card");
					inputFall = scanner.next();
				}
				if(inputFall.equals("s")) {
					System.out.println("Player " + currentPlayer + " draws a Summer Visitor Card");
					playerArr[currentPlayer - 1].draw(summerDeck);
				}else if(inputFall.equals("w")){
					System.out.println("Player " + currentPlayer + " draws a Winter Visitor Card");
					playerArr[currentPlayer - 1].draw(winterDeck);
				}else {
					System.out.println("invalid input");
				}
				
				
				//swap players
				//if one player is done and the other isn't, it should swap players then stay on same player
				
				//current player 1
				if(currentPlayer == 1) {
					
					//if player 1 is done, and player 2 is not done, then swap
					if(playerArr[0].getTurnDone() == true && playerArr[1].getTurnDone() == false) {
						currentPlayer++;
					}
					//if player 1 is not done, player 2 is done, do not swap
					else if(playerArr[0].getTurnDone() == false && playerArr[1].getTurnDone() == true) {
						//playerArr[0].setTurnDone();
						break;
					//both players not done, swap normally
					}else if(playerArr[0].getTurnDone() == false  && playerArr[1].getTurnDone() == false) {
						currentPlayer++;
					}
					
				}
				//current player 2
				else {
					//if player 2 is done, and player 1 is not done, then swap
					if(playerArr[1].getTurnDone() == true && playerArr[0].getTurnDone() == false) {
						currentPlayer--;
					}//if player 1 is not done, player 2 is done, do not swap
					else if(playerArr[0].getTurnDone() == false && playerArr[1].getTurnDone() == true) {
						//playerArr[0].setTurnDone();
						break;
					//both players not done, swap normally
					}else if(playerArr[0].getTurnDone() == false  && playerArr[1].getTurnDone() == false) {
						currentPlayer--;
					}
					
				}
					
				//if both players are done then progress to next season	
				if(playerArr[0].getTurnDone() && playerArr[1].getTurnDone()) {
					currentSeason++;
				}
				
				break;
			case 4 :
				System.out.println("WINTER" );
				System.out.println(playerArr[currentPlayer - 1]);
				System.out.println("Place a worker on a winter action space");
				int inputWinter = scanner.nextInt();
				
				
				
				//swap players
				if(currentPlayer == 1) {
					playerArr[0].setTurnDone();
					currentPlayer++;
				}else {
					playerArr[1].setTurnDone();
					currentPlayer--;
				}
					
				//if both players are done then progress to next season	
				if(playerArr[0].getTurnDone() && playerArr[1].getTurnDone()) {
					currentSeason++;
				}
				
				break;
			case 5 :
				System.out.println("Year End");
				System.out.println("update and display player stuff");
				player1.resetWorkers();
				player2.resetWorkers();
				year++;
				inputString = scanner.next();
				if(inputString != "") {
					currentSeason = 1;
				}
				System.out.println("YEAR " + year);
				break;
				
			}
			
			
		}
	}
}
