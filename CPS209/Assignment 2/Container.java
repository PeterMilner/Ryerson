package assignment2;
/**
 * Assignment 2
 * CPS209
 * @author Peter Milner
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Container is a class that represents the object in a stack in the simulation which is put onto trailers
 */
public class Container {

	private String letter; //Letter the each container has
	private int x; // x coordinate of location of Container
	private int y; // y coordinate of location of Container
	Rectangle rect = new Rectangle(x,y,30,30); //The rectangle shape of the Containers

	/**
	 * Default Constructor for the container, assigns the letter and location at (0,0). Not used.
	 * @param l letter to be assigned
	 */
	public Container(String l)
	{
		letter = l;
	}
	/**
	 * Constructor for the container, assigns the letter and the location at the x and y coordinates in parameters.
	 * @param l letter to be assigned
	 * @param x x coordinate to be assigned
	 * @param y y coordinate to be assigned
	 */
	public Container(String l, int x, int y)
	{
		letter = l;
		this.x =x;
		this.y=y;
		rect.move(x,y);
	}

	/**
	 * Returns the letter of the Container
	 * @return String of the letter
	 */
	public String getLetter()
	{
		return letter;
	}

	/**
	 * Sets the x coordinate of the Container
	 * @param x the coordinate for x
	 */
	public void setX(int x){
		this.x=x;
	}

	/**
	 * Sets the y coordinate of the Container
	 * @param y the coordinate for y
	 */
	public void setY(int y){
		this.y=y;
	}

	/**
	 * Returns the height of the Container which is the rectangle
	 * @return double, the height
	 */
	public double getHeight()
	{
		return rect.getHeight();
	}

	/**
	 * Sets the location of the Container
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void setLocation(int x, int y)
	{
		this.x=x;
		this.y=y;
		rect.setLocation(x, y);
	}

	/**
	 * Returns the x coordinate of the Container
	 * @return the x coordinate
	 */
	public int getX(){
		return x;
	}

	/**
	 * Returns the x coordinate of the Container
	 * @return the x coordinate
	 */
	public int getY(){
		return y;

	}
	/**
    Draws the Container
    @param g2 the graphics context
	 */
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.GREEN);
		g2.draw(rect);
		g2.setColor(Color.BLACK);
		g2.drawString(letter, x+8, y+20);
	}
}
