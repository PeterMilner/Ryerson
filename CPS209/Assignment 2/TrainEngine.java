package assignment2;

/**
 * Assignment 2
 * CPS209
 * @author Peter Milner
 */
import java.awt.geom.* ;
import java.awt.* ;

/**
 * Train Engine is a vehicle that can pull a chain of RailCars
 */
public class TrainEngine extends Vehicle
{
	/**
       Constants
	 */
	private static final double WIDTH = 35 ;
	private static final double UNIT = WIDTH / 5 ;
	private static final double LENGTH_FACTOR = 14 ; // length is 14U
	private static final double HEIGHT_FACTOR = 5 ; // height is 5U
	private static final double U_3 = 0.3 * UNIT ; 
	private static final double U2_5 = 2.5 * UNIT ; 
	private static final double U3 = 3 * UNIT ; 
	private static final double U4 = 4 * UNIT ; 
	private static final double U5 = 5 * UNIT ; 
	private static final double U10 = 10 * UNIT ; 
	private static final double U10_7 = 10.7 * UNIT ; 
	private static final double U12 = 12 * UNIT ; 
	private static final double U13 = 13 * UNIT ; 
	private static final double U14 = 14 * UNIT ; 
	private boolean isSelectedDrawing; //Check for whether this TrainEngine is selected and is being currently drawn
	//Bound for the TrainEngine used for dragging and overlapping. Uses previously defined constants.
	private Rectangle bound = new Rectangle(super.getX(),super.getY(),(int)U14,(int)U5);
	/*
	 * Default constructor for a TrainEngine. This isn't used. Creates a TrainEngine at (0,0).
	 */
	public TrainEngine()
	{
		super();
	}
	/*
	 * Constructor for the TrainEngine that assigns the coordinates. Also assigns the bound.
	 *  @param x The x position for the TrainEngine
	 *  @param y The y position for the TrainEngine
	 */
	public TrainEngine(int x, int y){
		super(x,y);
		super.setBound(bound);
	}

	/**
	 * Sets the isSelectedDrawing to the parameter boolean
	 * @param d whether isSelectedDrawing true or false
	 */
	public void drawingSelectedTrain(boolean d)
	{
		isSelectedDrawing=d;
	}

	/* 
	 * Overridden Vehicle method which adds a trailer to this RailCar
	 * @param the RailCar to add
	 */
	public void addTrailer(RailCar c)
	{
		//If the TrainEngine doesn't have a trailer, assign it to the TrainEngine itself
		if(super.getTrailer()==null) 
			this.setTrailer(c);
		else{
			RailCar current = this.getTrailer();
			current.addTrailer(c);
		}

	}

	/* 
	 * Overridden Vehicle method which updates the location of the trailers recursively depending on the TrainEngine's position
	 * Then updates the bounds by called the update method
	 */
	public void updateTrailers()
	{
		RailCar current = super.getTrailer();
		int x = this.getX();
		int y = this.getY();
		current.setLocation(x+(int)U14, y);

		current.updateTrailers();
	}
	/* 
	 * Overridden Vehicle method which returns the trailer at the end of linked trailers
	 * @return the RailCar at the end
	 */
	public RailCar getEndTrailer()
	{
		return super.getTrailer().getEndTrailer();
	}
	/* 
	 * Overridden Vehicle method which returns the trailer at the second-last end of linked trailers
	 * @return the RailCar at the second-last end
	 */
	public RailCar getSecondEndTrailer()
	{
		return getTrailer().getSecondEndTrailer();
	}

	/**
    Draws the TrainEngine
    @param g2 the graphics context
	 */
	public void draw(Graphics2D g2)
	{
		if(this.getTrailer()!=null){
			RailCar current = this.getTrailer();
			current.draw(g2);
		}
		int x1 = getX() ;
		int y1 = getY() ;
		Rectangle2D.Double hood = new Rectangle2D.Double(x1, y1 + UNIT, 
				U3, U3 ) ;
		g2.setColor(Color.blue) ;
		g2.fill(hood) ;

		Rectangle2D.Double body = new Rectangle2D.Double(x1 + U3,
				y1,
				U10, U4) ;
		g2.setColor(Color.blue) ;
		g2.fill(body) ;

		Line2D.Double hitch = new Line2D.Double(x1 + U13,
				y1 + U2_5,
				x1 + U14, 
				y1 + U2_5) ;
		g2.setColor(Color.black) ;
		g2.draw(hitch) ;

		Ellipse2D.Double wheel1 = new Ellipse2D.Double(x1 + U_3, 
				y1 + U4, 
				UNIT, UNIT) ;
		g2.setColor(Color.black) ;
		if(isSelectedDrawing)
			g2.setColor(Color.RED);
		g2.fill(wheel1) ;

		Ellipse2D.Double wheel2 = new Ellipse2D.Double(x1 + 1.3 * UNIT, 
				y1 + U4, 
				UNIT, UNIT) ;
		g2.setColor(Color.black) ;
		if(isSelectedDrawing)
			g2.setColor(Color.RED);
		g2.fill(wheel2) ;

		Ellipse2D.Double wheel3 = new Ellipse2D.Double(x1 + 2.3 * UNIT, 
				y1 + 4 * UNIT, 
				UNIT, UNIT) ;
		g2.setColor(Color.black) ;
		if(isSelectedDrawing)
			g2.setColor(Color.RED);
		g2.fill(wheel3) ;

		Ellipse2D.Double wheel4 = new Ellipse2D.Double(x1 + U10_7, 
				y1 + U4, 
				UNIT, UNIT) ;
		g2.setColor(Color.black) ;
		if(isSelectedDrawing)
			g2.setColor(Color.RED);
		g2.fill(wheel4) ;

		Ellipse2D.Double wheel5 = new Ellipse2D.Double(x1 + U12, 
				y1 + U4, 
				UNIT, UNIT) ;
		g2.setColor(Color.black) ;
		if(isSelectedDrawing)
			g2.setColor(Color.RED);
		g2.fill(wheel5) ;

		Ellipse2D.Double wheel6 = new Ellipse2D.Double(x1 + 9.7 * UNIT, 
				y1 + U4, 
				UNIT, UNIT) ;
		g2.setColor(Color.black) ;
		if(isSelectedDrawing)
			g2.setColor(Color.RED);
		g2.fill(wheel6) ;

	}
}
