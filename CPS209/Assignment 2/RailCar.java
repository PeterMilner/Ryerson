package assignment2;

/**
 * Assignment 2
 * CPS209
 * @author Peter Milner
 */
import java.awt.* ;
import java.awt.geom.* ;

/**
   This class describes a vehicle that looks like a flatbed 
   railcar.  The railcar should be assigned a unique number 
   displayed on its body. The railcar should have variable and 
   methods to allow it to be linked to another vehicle (consider
   whether this variable and associated methods should be
   inherited). This railcar should also have variables and
   methods so that a storage container can be loaded and unloaded
   Add other variables and methods you think are necessary.
 */

public class RailCar extends Vehicle
{
	//Constants used for drawing and the bound
	public static final int UNIT = 10 ;
	public static final int U6 = 6 * UNIT ;
	public static final int U5 = 5 * UNIT ;
	public static final int U4 = 4 * UNIT ;
	public static final int U3 = 3 * UNIT ;
	public static final int U2 = 2 * UNIT ;
	public static final int U15 = UNIT + UNIT / 2 ;
	public static final int U05 =  UNIT / 2 ;
	public static final int BODY_WIDTH = U3 ;
	public static final int BODY_HEIGHT = U2 ;
	//Bound for the railcar used for dragging and overlapping. Uses previously defined constants.
	private Rectangle bound = new Rectangle(super.getX(),super.getY(),BODY_WIDTH*2,BODY_HEIGHT); 
	private int number; // Unique number that each RailCar has
	private boolean isTrailer = false; //Check for whether this RailCar is a trailer
	private Container container; //The storage container on the cart, if it has one
	/*
	 * Default constructor for a RailCar. This isn't used. Creates a RailCar at (0,0).
	 */
	public RailCar()
	{
		super();
	}
	/*
	 * Constructor for the RailCar that assigns the coordinate and the RailCar number. Also assigns the bound.
	 *  @param num The number for the RailCar
	 *  @param x The x position for the RailCar
	 *  @param y The y position for the RailCar
	 */
	public RailCar(int num, int x, int y)
	{
		super(x,y);
		number=num;
		super.setBound(bound);
	}

	/**
	 * This method sets the container of the RailCar
	 * @param c The container to assign
	 */
	public void setContainer(Container c)
	{
		container=c;
		container.setLocation(super.getX()+(BODY_WIDTH/2), super.getY()-(BODY_HEIGHT)-1);
	}

	/**
	 * Returns the container of this RailCar
	 * @return the container
	 */
	public Container getContainer()
	{
		return container;
	}

	/**
	 * Nulls the container of this RailCar
	 */
	public void removeContainer()
	{
		container = null;
	}

	/* 
	 * Overridden Vehicle method which adds a trailer to this RailCar
	 * @param the RailCar to add
	 */
	public void addTrailer(RailCar c)
	{
		RailCar current = this;
		//Goes through the linked list of trailers to the end
		while(current.getTrailer()!=null)
		{
			current = current.getTrailer();
		}
		current.setTrailer(c);
	}

	/* 
	 * Overridden Vehicle method which returns the trailer at the end of linked trailers
	 * @return the RailCar at the end
	 */
	public RailCar getEndTrailer()
	{
		RailCar current = this;
		//Goes through the linked list of trailers to the end
		while(current.getTrailer()!=null)
		{
			current = current.getTrailer();
		}
		return current;
	}
	/* 
	 * Overridden Vehicle method which returns the trailer at the second-last end of linked trailers
	 * @return the RailCar at the second-last end
	 */
	public RailCar getSecondEndTrailer()
	{
		RailCar current = this;
		RailCar returnCar = new RailCar();
		//Goes through the linked list of trailers to the end
		while(current.getTrailer()!=null)
		{
			if(current.getTrailer().getTrailer()==null)
				returnCar = current;
			current = current.getTrailer();
		}
		return returnCar;
	}

	/**
	 * Sets the boolean on whether this RailCar is a trailer
	 * @param t the Trailer status
	 */
	public void setTrailerStatus(boolean t)
	{
		isTrailer=t;
	}

	/**
	 * Returns the boolean on whether this RailCar is a trailer
	 * @return isTrailer the Trailer Status
	 */
	public boolean getTrailerStatus()
	{
		return isTrailer;
	}

	/* 
	 * Overridden Vehicle method which updates the location of the trailers recursively depending on the first RailCar's position
	 * Then updates the bounds by called the update method
	 */
	public void updateTrailers()
	{
		RailCar current = this;
		int x = current.getX();
		int y = current.getY();
		while(current.getTrailer()!=null)
		{
			x+=BODY_WIDTH;
			current = current.getTrailer();
			current.setLocation(x+=BODY_WIDTH+6,y);
			current.update();

		}

	}

	/* 
	 * Overridden Vehicle method which moves the bound of this RailCar to the location of the RailCar.
	 * Also moves the container to the location of the RailCar.
	 */
	public void update()
	{
		bound.move(super.getX(), super.getY());
		if(container!=null)
		{
			container.setLocation(super.getX()+(BODY_WIDTH/2), super.getY()-(BODY_HEIGHT)-1);
		}
	}

	/**
	 * Returns the number of this RailCar
	 * @return the number
	 */
	public int getNumber(){
		return number;
	}

	/**
       Draw the RailCar
       @param g2 the graphics context
	 */
	public void draw(Graphics2D g2)
	{

		int xLeft = getX() ;
		int yTop = getY() ;

		Rectangle2D.Double body 
		= new Rectangle2D.Double(xLeft, yTop + UNIT, U6, UNIT);      
		Ellipse2D.Double frontTire 
		= new Ellipse2D.Double(xLeft + UNIT, yTop + U2, UNIT, UNIT);
		Ellipse2D.Double rearTire
		= new Ellipse2D.Double(xLeft + U4, yTop + U2, UNIT, UNIT);

		// the bottom of the front windshield
		Point2D.Double r1 
		= new Point2D.Double(xLeft + UNIT, yTop + UNIT);
		// the front of the roof
		Point2D.Double r2 
		= new Point2D.Double(xLeft + U2, yTop);
		// the rear of the roof
		Point2D.Double r3 
		= new Point2D.Double(xLeft + U4, yTop);
		// the bottom of the rear windshield
		Point2D.Double r4 
		= new Point2D.Double(xLeft + U5, yTop + UNIT);

		// the right end of the hitch
		Point2D.Double r5 
		= new Point2D.Double(xLeft + U6, yTop + U15);
		// the left end of the hitch
		Point2D.Double r6 
		= new Point2D.Double(xLeft + U6 + U05, yTop + U15);

		Line2D.Double frontWindshield 
		= new Line2D.Double(r1, r2);
		Line2D.Double roofTop 
		= new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield
		= new Line2D.Double(r3, r4);
		Line2D.Double hitch
		= new Line2D.Double(r5, r6);

		g2.draw(body);
		g2.draw(hitch);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(body) ;
		g2.drawString("" + getNumber(), xLeft + U2, yTop + U2) ;

		//Recursively draws all the trailers of this RailCar
		if(this.getTrailer()!=null)
			this.getTrailer().draw(g2);
		if(container!=null)
		{
			container.draw(g2);
		}

	}



}



