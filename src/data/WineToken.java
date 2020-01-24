package data;
/**
 * When WineTokens are created from the field harvest the color can be either 
 * Red or White and they are considered grape tokens. When wine is created from 
 * the crushpad the color can be Red, White, Blush, or Sparkling depending on the
 * player's choice and the cellar available.
 * @author leahj
 * @see <a href="https://stonemaiergames.com/games/viticulture/rules/">Viticulture Rulebook</a>
 */
public class WineToken {
	/**
	 * When in the crushpad, can be Red or White; when put into the cellar color
	 * can be Red, White, Blush, or Sparkling
	 */
	String color;
	/**
	 * current value of this token
	 */
	int value;
	/**
	 * When WineToken is harvested from field, color can be Red or White. When
	 * moved from crushPad to cellar can be made into Red, White, Blush, or Sparkling
	 * The value determines where it sits in the crushpad or cellar, as well as its value
	 * @param color
	 * @param value
	 */
	public WineToken(String color, int value) {
		this.color = color;
		this.value = value;
	}
	
	public String getColor() {
		return color;
	}
	
	public int getValue() {
		return value;
	}
	/**
	 * At the end of each year the value of each token automatically increases by one
	 */
	public void age() {
		value++;
	}
	/**
	 * When harvesting grapes, if there are two tokens trying to occupy the same spot, 
	 * one must be devalued.
	 */
	public void devalue() {
		value--;
	}
}
