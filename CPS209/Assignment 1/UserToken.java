package assignment1;

import java.awt.Color;


/**
 * @author petermilner
 * The subclass of GameToken used to create the token for the user with it's own unique visible policy
 * Implements VisibleShape
 * 
 */
public class UserToken extends GameToken implements VisibleShape{
	
	/**
	 * UserToken constructor as a subclass of GameToken
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 * @param int width - width of token (box)
	 * @param int height - height of token (box)
	 */
	public UserToken(int x, int y, int width, int height) {
		super(x, y, width, height);
		changeColor(Color.BLUE);//User tokens are blue by default
		
	}
	/**
	 * UserToken constructor as a subclass of GameToken. Used when a pattern type is specified
	 * @param 
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 * @param int width - width of token (box)
	 * @param int height - height of token (box)
	 */
	public UserToken(int patternType, int x, int y, int width, int height){
		super(patternType, x, y, width, height);
		changeColor(Color.BLUE); //User tokens are blue by default
	}
	
	 /** 
	 * Sets the policy for how the token will be visible
	 * If more than 3 seconds has elapsed, the token and pattern for the token will become visible
	 */
	public void setVisibilityPolicy(){
		   if(GameComponent.getElapsed()<=3){
			   setPatternVisibility(false);
		   		changeVisibility(false);
		   }
			else{
				setPatternVisibility(true);
				changeVisibility(true);
			}
	   }

}
