package data;
import card.*;
import data.*;
import seasons.*;
import seasons.Summer;
import seasons.Winter;

import java.util.Random;
import java.util.Scanner;

public class Driver {
	

	public static void main(String[] args) {
		
		
		Scanner scanner = new Scanner(System.in);
		Game game = new Game();
		
		int[] playerChoices = text_gameStart(game, scanner);
		game.createPlayers(playerChoices[0],playerChoices[1]);
	
		while(game.isActive()) {

			for(int i = 0; i < 5; i++) {
				//i =
				switch(i) {
				case 0 :
					spring(game, scanner);
					break;
				case 1 :
					summer(game, scanner);
					break;
				case 2 :
					Fall.update();
					break;
				case 3 :
					Winter.update();
					break;
				case 4 :
					YearEnd.update();
					break;
				}
			}
		}
	}
	
	
	public static void spring(Game game, Scanner scanner) {
		System.out.print("SPRING" );
		System.out.println("....................................................."
				+ "............................................................");
		System.out.println("--Wake Up Positions--");
		System.out.println(game.printBoardSpring());
		//System.out.println(playerArr[currentPlayer - 1]);
		
		int playersDone = 0;
		while(playersDone < 2) {
			System.out.println("Player " + game.getCurrentPlayerID() + " Enter a place for your wake up token");
			int wakeUpSel = scanner.nextInt();
			//check that current player's selection is not the same as previous player's
			if(wakeUpSel != game.getPlayerArr()[game.getOppositePlayer()-1].getWakeUpPos()) {
				switch(wakeUpSel) {
				case 1 :
					System.out.println("No Bonus, but you get to go first!");
					game.getCurrentPlayer().setWakeUpPos(1);
					break;
				case 2 :
					System.out.println("You draw 1 Vine Card");
					game.getCurrentPlayer().draw(game.vineDeck);
					game.getCurrentPlayer().setWakeUpPos(2);
					break;
				case 3 : 
					System.out.println("You gain 1 Gold");
					game.getCurrentPlayer().updateGold(1);
					game.getCurrentPlayer().setWakeUpPos(3);
					break;
				case 4 :
					System.out.println("Choose to Draw a Summer or Winter Visitor card");
					System.out.println("1 - Summer");
					System.out.println("2 - Winter");
					
					 wakeUpSel = scanner.nextInt();
					 
					 if(wakeUpSel == 1) {
						 game.getCurrentPlayer().draw(game.summerDeck);
					 }else {
						 game.getCurrentPlayer().draw(game.winterDeck);
					 }
					 game.getCurrentPlayer().setWakeUpPos(4);
					break;
				case 5 :
					System.out.println("You gain 1 Victory Point");
					game.getCurrentPlayer().updateVP(1);
					game.getCurrentPlayer().setWakeUpPos(5);
					break;
				case 6 :
					System.out.println("You gain 1 temp worker this year");
					game.getCurrentPlayer().addWorker();
					game.getCurrentPlayer().setWakeUpPos(6);
					break;
				default :
					System.out.println("invalid input");
					break;
				}
				game.updateCurrentPlayer();
				playersDone++;
			}else {
				System.out.println("Player " + game.getOppositePlayer() + " has already"
						+ " selected that position. Please choose another wakeup position.");
				game.printBoardSpring();
				wakeUpSel = scanner.nextInt();
			}
		}
		
		System.out.println("Player " + game.getFirstPlayer() + " you are the early riser");
	}
	
	public static void summer(Game game, Scanner scanner) {
		System.out.println("SUMMER" );
		System.out.println(game.getCurrentPlayer());
		System.out.println(game.getCurrentPlayer().printHand());
		System.out.println("w - Place a worker on a summer action space");
		System.out.println("p - Pass turn; forego placing any more workers");
		String inputString = scanner.next();
		
		int summerSpot;
		while(inputString.equals("w") == false && inputString.equals("p") == false) {
			System.out.println("invalid input. Please enter \"w\" or \"p\"");
			inputString = scanner.next();
		}
		if(inputString.equals("p")) {
			System.out.println("Player " + game.getCurrentPlayerID() + " passes");
			game.getCurrentPlayer().setTurnDone();
		}else if(inputString.equals("w")){
			//check that player has enough workers to continue
			if(game.getCurrentPlayer().getRemainingWorkers() > 0 || game.getCurrentPlayer().getGrande()) {
				System.out.println("Choose an unoccupied space. Or use a Grande worker"
						+ " on an occupied space");
				System.out.println(game.printBoardSummer());
				//inputInt = scanner.nextInt();
				summerSpot = scanner.nextInt();
				//boolean usedGrande = false;
				//check that the user selects an appropriate space
				//if space is occupied or no Grande Worker, ask to reselect
				while(game.summerSpots[summerSpot - 1].getIsOccupied()) {
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
	}
	public static int[] text_gameStart(Game game, Scanner scanner) {
	
		int[] playerChoices = new int[2];
		
		System.out.println("Welcome to Viticulture!");
		System.out.println("Player 1 , your parents are : ");
		System.out.println("Mama : " + game.mama1.toString());
		System.out.println("Papa : " + game.papa1.toString());
		System.out.println("Choose ");
		System.out.println("1 - " + game.papa1.getOption1());
		System.out.println("2 - " + game.papa1.getOption2() + " Gold");
		
		playerChoices[0] = scanner.nextInt();
		
		System.out.println("Player 2 , your parents are : ");
		System.out.println("Mama : " + game.mama2.toString());
		System.out.println("Papa : " + game.papa2.toString());
		System.out.println("Choose ");
		System.out.println("1 - " + game.papa2.getOption1());
		System.out.println("2 - " + game.papa2.getOption2() + " Gold");
		
		playerChoices[1]= scanner.nextInt();
		
		return playerChoices;
	}
	
	
}
