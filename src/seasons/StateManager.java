package seasons;

import data.Game;
import old.Driver;


//helpers package typically includes classes with static methods
public class StateManager {
	
    //an enum is like making our own variable
	/*public enum GameState {
		SPRING, SUMMER, FALL, WINTER, YEAREND
	}
	 */
	//look at what part of the program and edit appropriate things

	
	public static Spring spring;
	public static Summer summer;
	public static Fall fall;
	public static Winter winter;
	public static YearEnd yearEnd;
	
	private Game game;

	public StateManager(Game game) {
		this.game = game;
	}
	
	public static void progress1Year() {
		//iterate through gamestate
	
		for(int i = 0; i < 5; i++) {
			//i =
			switch(i) {
			case 0 :
				spring.update(game);
				break;
			case 1 :
				Summer.update();
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

