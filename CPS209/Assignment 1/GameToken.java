package assignment1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * @author petermilner
 * The super class for all tokens in the game.
 * Implements VisibleShape
 */

public class GameToken implements VisibleShape
{
	private boolean visible=true; //Whether the token is visible
	public 	Rectangle bbox; //Rectangle (box) the makes up the token
	protected Pattern pattern; //The pattern that belongs to the token
	private	Color color; //Color of the token

	/**
	 * Constructor for a gametoken
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 * @param int width - width of token (box)
	 * @param int height - height of token (box)
	 */
	public GameToken(int x, int y, int width, int height)
	{
		Rectangle box = new Rectangle(x, y, width, height);
		Pattern p = new Pattern(box);
		pattern = p;
		bbox=box;

	}
    
	/**
	 * Constructor for a game token
	 * @param int patternType - specified pattern for the token to be
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 * @param int width - width of token (box)
	 * @param int height - height of token (box)
	 */
	public GameToken(int patternType, int x, int y, int width, int height)
	{
		Rectangle box = new Rectangle(x, y, width, height);
		Pattern p = new Pattern(patternType, box);
		pattern = p;
		bbox=box;
	}

	/**
	 * Changes visibility of the token
	 * @param boolean - v
	 */
	public void changeVisibility (boolean v){
		visible=v;
	}
	/**
	 * Updates the pattern belonging to the token by calling the pattern update method
	 */
	public void update(){
		pattern.update(bbox);
	}
	/**
	 * Checks visibility of the pattern belonging to the token by calling the pattern update method
	 * @return boolean - if the pattern is visible
	 */
	public boolean getPVisibility(){
		return pattern.getVisibility();
	}
	/**
	 * Sets whether the pattern will be visible using the assigned pattern
	 * @param boolean v - whether the pattern will be visible
	 */
	public void setPatternVisibility(boolean v){
		  pattern.setVisiblity(v);
	   }
	/**
	 * Checks visibility of the token
	 * @return boolean - whether the token is visible 
	 */
	public boolean getVisibility(){
		return visible;
	}
	/**
	 * Sets the size of the token (the box)
	 * @param int w - width of the token
	 * @param int h - height of the token
	 */
	public void setSize(int w, int h){
		bbox.setSize(w,h);
	}
	/**
	 * Returns the box (rectangle) that makes up the token's border
	 * @return the rectangle object (box)
	 */
	public Rectangle getBox(){
		return bbox;
	}
	/**
	 * Returns width of the token (box)
	 * @return double -width of token (box)
	 */
	public double getWidth(){
		return bbox.getWidth();
	}
	/**
	 * Returns height of the token (box)
	 * @return double- height of token (box)
	 */
	public double getHeight(){
		return bbox.getHeight();
	}
	/**
	 * Sets the location using coordinates of the token (box)
	 * @param int x - x coordinate
	 * @param int y - y coordinate
	 */
	public void setLocation(int x, int y){
		bbox.setLocation(x,y);
	}
	/**
	 * Returns x coordinate
	 * @return double -x coordinate
	 */
	public double getX(){
		return bbox.getX();
	}
	/**
	 * Returns y coordinate
	 * @return double - y coordinate
	 */
	public double getY(){
		return bbox.getY();
	}

	/**
	 * Returns the type of pattern that the token has
	 * @return int - the type of pattern 
	 */
	public int getPattern(){
		return pattern.getType();
	}
	/**
	 * Changes the type of pattern that the token has
	 * @param int p - the type of pattern
	 */
	public void changePattern(int p){
		pattern.setType(p);
	}
	/**
	 * Returns the color of the token
	 * @return Color- the color of the token
	 */
	public Color getColor(){
		return color;
	}
	/**
	 * Changes the color of the token
	 * @param Color c - the color for the token to be
	 */
	public void changeColor(Color c){
		color=c;
	}
	/**
	 * Compares whether one game token is equal to another one by their pattern
	 * @param Object other- the "other" token to compare to
	 * @return boolean - whether the tokens match
	 */
	public boolean equals(Object other)
	{
		return this.getPattern()==((GameToken) other).getPattern();
	}
	/** 
	 * Draws the box of the token (the border) and calls for the pattern to draw
	 * @param Graphics2D g2 - Completes the drawing using Graphics2D
	 */
	public void draw(Graphics2D g2){
		((Graphics2D) g2).draw(bbox);
		pattern.draw(g2);

	}

	/**
	 * Sets the policy for how the token will be visible
	 * This is here simply to implement  VisibleShape.java, 
	 * This method is overridden in the two subclasses.
	 */
	public void setVisibilityPolicy(){
		changeVisibility(true);
	}

	/** 
	 * Checks if one token overlaps (is on top) of another token
	 * @param VisibleShape other - the other token to compare to
	 * @return boolean - whether there is overlapping
	 */
	public boolean overlaps(VisibleShape other){
		Rectangle box = ((GameToken)other).getBox();
		if (this.bbox.intersects(box))
			return true;
		else return false;
	}   
}
