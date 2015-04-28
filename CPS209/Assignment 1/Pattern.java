package assignment1;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;


/**
 * @author petermilner
 * The class used to create and update the patterns for the tokens
 */
public class Pattern 
{

	private boolean visible=true; //Whether the patter is visible
	public static final int SLASH = 0; //One slash pattern
	public static final int X = 1; // An "X" pattern
	public static final int CIRCLE  = 2; // Circle pattern
	public static final int PLUS = 3; // A "plus" sign pattern
	Line2D slash_SLASH = new Line2D.Double(); //Line2D object for the slash pattern
	Line2D slash1_X = new Line2D.Double(); //First Line2D object for "X" pattern
	Line2D slash2_X = new Line2D.Double(); //Second Line2D object for "X" pattern
	Ellipse2D.Double circle_CIRCLE= new Ellipse2D.Double();//Ellipse2D object for circle pattern
	Line2D slash1_PLUS = new Line2D.Double();//First Line2D object for "plus" pattern
	Line2D slash2_PLUS = new Line2D.Double();//Second Line2D object for "plus" pattern
	int type;//Number containing what type of pattern is held

	/**
	 * Constructor for a pattern for token using the box formed in GameToken class
	 * Generates a random pattern type since none specified
	 * @param Rectangle bbox - Box that resides in the GameToken class
	 */
	public Pattern(Rectangle bbox)
	{
		Random rand = new Random();
		type=rand.nextInt(4);
	}

	/**
	 * Constructor for a pattern for token using the box formed in GameToken class
	 * @param int type - Assigns what pattern to be used
	 * @param Rectangle bbox - Box that resides in the GameToken class
	 */
	public Pattern(int type, Rectangle bbox)
	{
		this.type=type;
	}
	/**
	 * Returns the visibility of the pattern
	 * @return boolean - whether pattern is visible
	 */
	public boolean getVisibility(){
		return visible;
	}
	/**
	 * Returns the type of pattern 
	 * @return int - Pattern type
	 */
	public int getType()
	{
		return type;
	}
	/**
	 * Sets the pattern type 
	 * @param int type - the type to set to
	 */
	public void setType(int type){
		this.type = type;
	}
	/**
	 * Sets the visibility of the pattern
	 * @param boolean v - Whether the pattern will be visible or not
	 */
	public void setVisiblity(boolean v){
		visible=v;
	}
	
	/**
	 * Updates and draws the patterns depending what type the token is and the location of the token
	 * The shape objects for the type are drawn and updated using the box of the token
	 * The other shape objects belonging to other types are assigned to zero values
	 * @param Rectangle bbox - 
	 */
	public void update(Rectangle bbox)
	{
		switch(this.type) //Switch case for different pattern types
		{
		case 0: //Updates the slash pattern object(s), zero's the rest
			slash_SLASH = new Line2D.Double (bbox.getX(),bbox.getY(),bbox.getX() + bbox.getWidth(),
					bbox.getY() + bbox.getHeight());
			slash1_X = new Line2D.Double(0,0,0,0);
			slash2_X = new Line2D.Double(0,0,0,0);
			circle_CIRCLE = new Ellipse2D.Double(0,0,0,0);
			slash1_PLUS = new Line2D.Double(0,0,0,0);
			slash2_PLUS = new Line2D.Double(0,0,0,0);
			break;
		case 1://Updates the "X" pattern object(s), zero's the rest
			slash1_X  = new Line2D.Double (bbox.getX(),bbox.getY(),bbox.getX() + bbox.getWidth(),
					bbox.getY() + bbox.getHeight());
			slash2_X = new Line2D.Double (bbox.getX(),bbox.getY()+bbox.getHeight(),bbox.getX() + bbox.getWidth(),
					bbox.getY());
			slash_SLASH = new Line2D.Double(0,0,0,0);
			circle_CIRCLE = new Ellipse2D.Double(0,0,0,0);
			slash1_PLUS = new Line2D.Double(0,0,0,0);
			slash2_PLUS = new Line2D.Double(0,0,0,0);
			break;
		case 2://Updates the circle pattern object(s), zero's the rest
			circle_CIRCLE = new Ellipse2D.Double(bbox.getX(),bbox.getY(),bbox.getWidth(), bbox.getWidth());
			slash_SLASH = new Line2D.Double(0,0,0,0);
			slash1_X = new Line2D.Double(0,0,0,0);
			slash2_X = new Line2D.Double(0,0,0,0);
			slash1_PLUS = new Line2D.Double(0,0,0,0);
			slash2_PLUS = new Line2D.Double(0,0,0,0);
			break;
		case 3: //Updates the "plus" pattern object(s), zero's the rest
			slash1_PLUS = new Line2D.Double(bbox.getX()+(bbox.getWidth())/2, bbox.getY(), bbox.getX()+(bbox.getWidth())/2, bbox.getY() + bbox.getHeight());
			slash2_PLUS = new Line2D.Double(bbox.getX(), bbox.getY()+(bbox.getHeight())/2, bbox.getX()+bbox.getWidth(), bbox.getY()+(bbox.getHeight())/2);
			circle_CIRCLE = new Ellipse2D.Double(0,0,0,0);
			slash_SLASH = new Line2D.Double(0,0,0,0);
			slash1_X = new Line2D.Double(0,0,0,0);
			slash2_X = new Line2D.Double(0,0,0,0);
		}
	}

	/** 
	 *Checks if one pattern is equal to another token's pattern.
	 * @return boolean - whether the two patterns are equal
	 */
	public boolean equals(Object other)
	{
		return(this.type==((Pattern) other).type);
	}

	/** 
	 * Draws the shape objects for all the patterns, if the pattern is visible
	 * @param Graphics2D g2 - Completes the drawing using Graphics2D
	 */
	public void draw(Graphics2D g2)
	{
		if(visible){
			((Graphics2D) g2).draw(slash_SLASH);
			((Graphics2D) g2).draw(slash1_X);
			((Graphics2D) g2).draw(slash2_X);
			((Graphics2D) g2).draw(circle_CIRCLE);
			((Graphics2D) g2).draw(slash1_PLUS);
			((Graphics2D) g2).draw(slash2_PLUS);
		}
	}
}
