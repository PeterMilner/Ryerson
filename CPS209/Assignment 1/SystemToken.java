package assignment1;

import java.awt.Color;

import java.util.Random;

/**
 * @author petermilner
 * The subclass of GameToken used to create the token for the game with it's own unique visible policy
 * Implements VisibleShape
 * 
 */
public class SystemToken extends GameToken implements VisibleShape{

	/**
	 * SystemToken constructor as a subclass of GameToken
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 * @param int width - width of token (box)
	 * @param int height - height of token (box)
	 */
	public SystemToken(int x, int y, int width, int height) {
		super(x, y, width, height);
		changeColor(Color.RED);//The system tokens are red by default

	}
	/**
	 * SystemToken constructor as a subclass of GameToken. Used when a pattern type is specified
	 * @param 
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 * @param int width - width of token (box)
	 * @param int height - height of token (box)
	 */
	public SystemToken(int patternType, int x, int y, int width, int height){
		super(patternType, x, y, width, height);
		changeColor(Color.RED);//The system tokens are red by default
	}
	/** 
	 * Sets the policy for how the token will be visible
	 * If more than 3 seconds has elapsed, the token and pattern for the token will disappear 
	 * unless the token is green which occurs when the token is already found
	 * 1/1000th chance to make the appear for 3 seconds (referred to as blinking)
	 */
	Random rand = new Random();
	long blinkingStartTime; //Time when the token first appears
	boolean isBlinking;//If the token is already blinking
	public void setVisibilityPolicy(){
		if(GameComponent.getElapsed()>=3 && getColor() != Color.GREEN){
			if(isBlinking){ //If it's already blinking, check if 3 seconds are up
				if((System.currentTimeMillis()-blinkingStartTime)/1000 >=3)
					isBlinking=false;
				setPatternVisibility(true);
			}
			//Chance to blink if not blinking already:
			else if(rand.nextInt(999) == 1){
				blinkingStartTime = System.currentTimeMillis();
				isBlinking=true;
				setPatternVisibility(true);
			}
			else //Not visible if isnt blinking or doesnt become blinking
				setPatternVisibility(false);
		}
		else
			setPatternVisibility(true); 
	}

}
