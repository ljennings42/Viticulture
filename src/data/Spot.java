package data;
/**
 * A spot is an action space for Summer and Winter actions. Each spot can hold
 * up to one worker from either Player 1 or Player2 in a two player game. An occupied
 * spot can always be overriden by either player's grande worker.
 * @see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class Spot {
	
	//holds a player worker
	//has a string title associated
	//prints differently depending on worker
	/**
	 * Name set for spots that informs player of action they are associated with.
	 * SpotTitles 0-5 will be assigned to summerSpots[] and spotTitles 6-11 will
	 * be assigned to winterSpots[] in the driver
	 */
	private String[] spotTitles = {"Draw Vine Card","Give a Tour", "Build Structures",//summer
			"Play a Visitor Card", "Sell Grapes/Field", "Plant Vines", 
			"Draw Wine Order Card", "Harvest Field", "Gain 1 Gold", //winter
			"Play Winter Visitor Card","Make Wine Tokens", "Fill Wine Order"};
	/**
	 * the title that is assigned to this spot
	 */
	private String title; //title/directions associated with a spot
	/**
	 * Player ID of worker occupying this spot
	 */
	private int player; //represent which player's worker may be occupying spot
	/**
	 * true if occupied, false if available
	 */
	private boolean isOccupied; //if occupied, need a grande worker
	/**
	 * int val is used to iterate through each one and populate arrays of summerSpots[]
	 * and winterSpots[] in the driver
	 * @param val
	 */
	public Spot(int val) {
		//0-5 is summer, 6-11 is winter
		title = spotTitles[val];
		isOccupied = false;
	}
	public String getTitle() {
		return title;
	}
	/**
	 * if spot is occupied then a player needs to override using their grande worker
	 * @return
	 */
	public boolean getIsOccupied() {
		return isOccupied;
	}
	
	public int getPlayer() {
		return player;
	}
	/**
	 * Occupies this spot with a player. Set isOccupied to true and get player ID
	 * @param player
	 */
	public void occupy(Player player) {
		isOccupied = true;
		this.player =  player.getID();
	}
	/**
	 * Displays information about this spot to the console
	 */
	public String toString() {
		String answer = "";
		if(isOccupied) {
			answer += "[p"+ player + "] " + title;
		}else {
			answer +="[  ] " + title;
		}
		return answer;
	}

}
