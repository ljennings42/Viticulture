package data;

import java.util.ArrayList;

import card.Card;
import card.Vine;
/**
 * A field can be planted with vines. Each player owns 3 fields.
 * A field can be harvest once per year, yielding some quantity of WineTokens
 * depending on the vines planted in it. The total value of vines cannot exceed
 * the max value of a field.
 * <p>
 * Note: the board game rules differentiates between grape tokens and wine tokens, 
 * but the "data structure" is functionally identical. See WineToken for more detail
 * <p>
 * A field can also be sold for it's maxValue, or bought back for it's maxValue
 * <p>
 * Structures that affect fields: Some vines require a Trellis and/or Irrigation
 * in order to plant them in a field. Windmill: gain 1 VP when planting a vine
 * (max 1 per year). Yoke: Can Place a worker here as an action to Uproot or Harvest
 * one field once per year.
 * @author leahj
 * @see https://stonemaiergames.com/games/viticulture/rules/
 *
 *
 */
public class Field {

	/**
	 * Fields have a maxValue of 5, 6, or 7. This determines the maximum total value
	 * of vines that can be planted there, as well as the amount of gold they are worth.
	 * This is determined in player constructor.
	 * @param maxValue
	 */
	private int maxValue; 
	/**
	 * The current total value of all the vines currently planted in this field
	 */
	private int currentValue; 
	/**
	 * Keeps track of whether this field is active or has been sold.
	 */
	private boolean active;
	/**
	 * Keeps track of the vines that have been planted in this field
	 */
	private ArrayList<Vine> plantedVines = new ArrayList<Vine>();
	
	/**
	 * Fields have a maxValue of 5, 6, or 7. This determines the maximum total value
	 * of vines that can be planted there, as well as the amount of gold they are worth.
	 * This is determined in player constructor.
	 * @param maxValue
	 */
	public Field(int maxValue) {
		this.maxValue = maxValue;
		currentValue = 0;
		active = true;
	}
	
	/**
	 * Given a Vine card, we check whether there is room for this vine in this field.
	 * Then we add the vine card to plantedVines ArrayList, and then update currentValue
	 * with value of this vine.
	 * @param vine
	 */
	public void plantVine(Vine vine) {
		if((currentValue + vine.getValue()) <= maxValue) {
			plantedVines.add(vine);
			currentValue += vine.getValue();
		}else {
			System.out.println("Can't plant vine here, exceeds max value of field");
		}
	}
	
	/**
	 * Field side of harvesting. This method is called by the player when they
	 * want to harvest. First, we determine the appropriate length of WineToken[] harvest
	 * by counting the number of WineTokens that will be yielded from all cards in this field.
	 * Pinot is the only grape that creates 2 tokens(1 red and 1 white). Then, we populate
	 * the WineToken[] harvest with new appropriate WineTokens depending on the "Colors" (NOT variety)
	 * of a given vine. Remember, Colors can be "Red" or "White" for grape tokens, Variety e.g. is "Syrah".
	 * @see com.my.package.Class#method()
	 * @return WineToken[]
	 */
	public WineToken[] harvestField(){
		//public WineToken(String color, int value)
		//ArrayList<WineToken> harvest = new ArrayList<WineToken>();
		int counter = 0;
		for(Vine vine : plantedVines) {
			//WineToken wineToken = new WineToken(g.getVariety(),vine.getValue());
			//Pinot is the only vine that creates a red AND white token
			if(vine.getVariety().equals("Pinot")) {
				counter++;
			}
			counter++;
			//harvest.add(wineToken);
		}
		
		WineToken[] harvest = new WineToken[counter];
		counter = 0;
		for(Vine vine : plantedVines) {
			//determine if vine has red and/or white tokens
			if(vine.getVariety().equals("Pinot")) {
				harvest[counter] = new WineToken("Red",vine.getValue());
				counter++;
				harvest[counter] = new WineToken("White",vine.getValue());
			}else if(vine.hasRed()) {
				harvest[counter] = new WineToken("Red",vine.getValue());
			}else {
				harvest[counter] = new WineToken("White",vine.getValue());
			}
			counter++;
		}
		return harvest;
	}
	/**
	 * If this field is active, set active to false, and return the amount that it sells for
	 * @return
	 */
	public int sellField() {
		if(active) {
			active = false;
			return maxValue;
		}else {
			return 0;
		}
	}
	/**
	 * If this field is not active, set active to true, and return a negative integer that
	 * will subtract from the player's gold
	 * @return
	 */
	public int buyField(){
		if(active) {
			return 0;
		}else {
			active = true;
			return -maxValue;
		}
	}
	
	public int getMaxValue() {
		return maxValue;
	}
	
	/**
	 * If nothing has been planted in this field then it returns true
	 * @return
	 */
	public boolean isEmpty() {
		return currentValue == 0;
	}
	/**
	 * If this field has not been sold and is active returns true
	 * @return
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * Returns ArrayList of Vine cards that have been planted in this field
	 * @return
	 */
	public ArrayList<Vine> getPlantedGrapes() {
		return plantedVines;
	}
	/**
	 * return a vine card from this field to the player's hand. This can be done
	 * as a winter or summer action if the player owns a Yoke. Player needs to select
	 * one vine card from the field, but will leave that to other implementation
	 */
	public void uproot(ArrayList<Card> hand, int vine) {
		hand.add(plantedVines.get(vine));
		plantedVines.remove(vine);
	}
	/**
	 * Displays this field's max value and the vines that have been planted in this field. 
	 */
	public String toString() {
		String answer = "Field : " + maxValue + " | ";;
		if(isActive() == false) {
			answer += " SOLD ";
		}else {
			for(Vine vine: plantedVines) {
				answer += vine.toString() + " , ";
			}
		}
		return answer += "\n";
	}
}
