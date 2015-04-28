package assignment2;
/**
 * Assignment 2
 * CPS209
 * @author Peter Milner
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;



/**
 * Component is a subclass of JComponent and contains the vehicles, storage boxes and their data exchanges through MouseListeners
 */
public class Component extends JComponent{

	ArrayList <RailCar> cars= new ArrayList<RailCar>(); //ArrayList of RailCars
	TrainEngine train; //The TrainEngine
	Container [] containers = new Container [5]; //Array of containers to be in storage
	Rectangle bottom; //Bottom rectangle to "hold' the containers
	Stack <Container> storage = new Stack<Container>(); //The stack to be used on the containers
	private Vehicle highlighted; //The variable holding which vehicle is currently selected
	private int clickCount; //Variable to count how many clicks have occurred, used for setup

	/**
	 *  The constructor of the component which does the setup of the component
	 *  Creates the vehicles,containers,stack,container holder (bottom)
	 */
	public Component(){

		/**
		 * The MouseListener class that contains the mousePressed, mouseDragged and mouseReleased methods
		 *
		 */
		class Listener implements MouseListener, MouseMotionListener
		{
			public void mousePressed(MouseEvent e)
			{
				int x = e.getX();
				int y = e.getY();
				if (clickCount==0) // Creates TrainEngine
				{
					train =(new TrainEngine(x,y));
					clickCount++;
				}
				else if(clickCount<6){ //Creates RailCars
					cars.add(new RailCar(clickCount,x,y));
					clickCount++;
				}
				else if(clickCount == 6) //Creates containers and then pushes them into the stack
				{
					clickCount++;

					for(int i =0; i<5; i++)
						containers[i] = new Container(String.valueOf((char)(i+65)),x,y-i*30);
					for(int i =0; i<5; i++)
						storage.push(containers[i]);
					//Creates and puts the bottom at the end of the containers
					bottom = new Rectangle(containers[4].getX()-10,(int) (containers[0].getY()+containers[4].getHeight()),50,10);
				}
				else  
				{
					//The setup is done. Therefore, mousePressed now simply does the vehicle selections if the mouse
					//is in the bounds of a vehicle
					if(train.contains(x,y))
					{
						highlighted = train;
					}
					else
					{
						for(RailCar c : cars){
							if(c.contains(x,y))
							{
								highlighted = c;
								break;
							}
							else
							{
								highlighted = null;
							}
						}
					}	
				}
				repaint();
			}
			public void mouseDragged(MouseEvent e)
			{
				if(clickCount>6 && highlighted!=null) //If the setup is done and a vehicle is highlighted
				{
					if(highlighted==train) //If the train is highlighted, drags the TrainEngine and  possible trailers after the mouse movement
					{
						train.setLocation(e.getX(), e.getY());
						train.update();
						if(train.getTrailer()!=null)
							train.updateTrailers();
					}
					else
					{
						if(!((RailCar) highlighted).getTrailerStatus()) //If the train is highlighted, drags the RailCar and possible trailers after the mouse movement
						{
							highlighted.setLocation(e.getX(), e.getY());
							highlighted.update();
							if(highlighted.getTrailer()!= null)
								highlighted.updateTrailers();
						}	
					}

					repaint();
				}
			}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e)
			{
				//mouseReleased is used for clipping trailers together by checking if the highlighted vehicle is in the bounds of another
				if(clickCount>6 && highlighted != train)
				{
					RailCar sel = (RailCar) highlighted; //If in the bounds of a RailCar, adds to end of it or it's possible trailers
					for(RailCar c : cars)
					{
						if (sel != null && sel.overLap(c.getBound()) && !sel.getTrailerStatus() && sel!=c){
							c.addTrailer(sel);
							sel.setTrailerStatus(true);
							c.updateTrailers();
							break;

						}
						else if(sel != null && sel.overLap(train.getBound()) && !sel.getTrailerStatus())
						{
							//If in the bounds of a TrainEngine, adds to end of it or it's possible trailers
							train.addTrailer(sel);
							sel.setTrailerStatus(true);
							train.updateTrailers();

						}
					}

				}


				repaint();

			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseMoved(MouseEvent e) {


			}
		}
		//Adds the two mouseListeners
		MouseListener listener = new Listener();
		MouseMotionListener listener2 = new Listener();
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener2);

	}

	/**
	 * Used for restarting the game. Nulls all the instance variables
	 */
	public void restart()
	{
		cars = new ArrayList<RailCar>();
		train = null;
		containers = new Container [5];
		clickCount = 0;
		storage = new Stack <Container>();
		bottom=null;
	}

	/**
	 * Used for popping a container from the storage stack and assigning it to a railCar
	 */
	public void storagePop()
	{
		if(highlighted != null)
		{
			if(highlighted == train) //Assigns the highlighted TrainEngine to it's trailer since it cannot hold containers
			{
				highlighted = ((TrainEngine) highlighted).getTrailer();

			}
			if(((RailCar) highlighted).getTrailer() == null && ((RailCar) highlighted).getContainer()==null)
				//If trailer does not have more trailers, assigns the container to the trailer
			{
				Container current = storage.pop();
				((RailCar) highlighted).setContainer(current);

			}
			else 
				//Otherwise, assigns container to first open trailer
			{
				RailCar load = (RailCar) highlighted;
				while(load.getTrailer()!=null && load.getContainer() != null)
					load = load.getTrailer();
				if(load.getContainer()==null){
					Container current = storage.pop();
					load.setContainer(current);
				}

			}
			repaint();
		}

	}
	/**
	 * Pushes the container of the selected vehicle back into the storage stack
	 */
	public void storagePush()
	{
		if(highlighted!=null)
		{
			RailCar load = (RailCar) highlighted;
			if(highlighted == train) //Assigns the highlighted TrainEngine to it's trailer since it cannot hold containers
			{
				load = ((TrainEngine) highlighted).getTrailer();
			}
			//Checks for first trailer with a container, removes it from trailer, calls containerPush method
			while(load.getTrailer()!=null && load.getContainer()==null){
				load = load.getTrailer();
			}
			containerPush(load.getContainer());
			storage.push(load.getContainer());
			load.removeContainer();

			repaint();
		}
	}

	/**
	 * Responsible for actually inserting the given container back into the storage stack
	 * @param c the container to be pushed back
	 */
	public void containerPush(Container c)
	{

		int x = (int) (bottom.getX()+10);
		int y = (int) (bottom.getY()-c.getHeight());
		//Iterates through the stack until the end
		java.util.Iterator<Container> iterator = (java.util.Iterator<Container>) storage.iterator();
		while (iterator.hasNext()) {
			Container contain = iterator.next();
			x=(int) (contain.getX() );
			y=(int) (contain.getY() - contain.getHeight());
		}
		c.setLocation(x, y);
	}
	/**
	 * Adds the highlighted trailer to the beginning of the linked trailers on the train
	 */
	public void addFirst()
	{
		if(highlighted!=null && highlighted!= train)
		{
			RailCar currentFirst = train.getTrailer();
			train.setTrailer((RailCar) highlighted);
			train.getTrailer().setTrailer(currentFirst);
			((RailCar) highlighted).setTrailerStatus(true);
			train.updateTrailers();
			repaint();
		}
	}

	/**
	 * Adds the highlighted trailer to the end of the linked trailers on the train
	 */
	public void addLast()
	{
		if(highlighted!=null && highlighted!= train)
		{
			if(train.getTrailer()==null)
				addFirst();
			else{
				train.addTrailer((RailCar) highlighted);
				((RailCar) highlighted).setTrailerStatus(true);
				train.updateTrailers();
				repaint();
			}
		}
	}

	/**
	 * Removes the first trailer from the linked trailers on the train
	 */
	public void removeFirst()
	{
		if(train.getTrailer()!=null)//Checks if train has trailers
		{
			if(train.getTrailer().getTrailer()==null){ //If first trailer doesn't have a trailer, remove first
				RailCar removed = train.getTrailer();
				train.setTrailer(null);
				Random rand = new Random();
				int x = rand.nextInt(800);
				int y = rand.nextInt(600);
				removed.setLocation(x,y);
				removed.update();
				removed.setTrailerStatus(false);
				repaint();
			}
			else{ //Otherwise remove first and assign second to be the trains trailer
				RailCar removed = train.getTrailer();
				train.setTrailer(removed.getTrailer());
				Random rand = new Random();
				int x = rand.nextInt(800);
				int y = rand.nextInt(600);
				removed.setLocation(x,y);
				removed.setTrailerStatus(false);
				train.updateTrailers();
				removed.update();
				repaint();
			}
		}
	}

	/**
	 * Removes the last trailer from the linked trailers on the train
	 */
	public void removeLast()
	{
		if(train.getTrailer()!=null) //Checks if train has trailers
		{
			if(train.getTrailer().getTrailer()==null){ //If first trailer doesn't have a trailer, remove first
				RailCar removed = train.getTrailer();
				train.setTrailer(null);
				Random rand = new Random();
				int x = rand.nextInt(800);
				int y = rand.nextInt(600);
				removed.setLocation(x,y);
				removed.setTrailer(null);
				removed.setTrailerStatus(false);
				removed.update();
				repaint();
			}
			else{ //Otherwise remove the last trailer and update the second last trailer to have no trailer
				RailCar removed = train.getEndTrailer();
				train.getSecondEndTrailer().setTrailer(null);
				Random rand = new Random();
				int x = rand.nextInt(800);
				int y = rand.nextInt(600);
				removed.setLocation(x,y);
				removed.setTrailer(null);
				train.updateTrailers();
				removed.update();
				removed.setTrailerStatus(false);
				repaint();
			}
		}
	}

	/* 
	 * Paints the vehicles, the container and the container storage
	 * @param g Graphics used to draw
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		//Draws railcars if they are not trailers
		for(RailCar c : cars)
		{
			if(c.getTrailerStatus()==false)
			{
				if(c == highlighted)	
					g2.setColor(Color.RED);
				c.draw(g2);
				g2.setColor(Color.BLACK);
			}
		}
		//Draws train
		if(train!=null)
		{
			if (train == highlighted)
			{
				train.drawingSelectedTrain(true);
				g2.setColor(Color.RED);
			}
			train.draw(g2);
			train.drawingSelectedTrain(false);
			g2.setColor(Color.BLACK);
		}
		//Draws containers
		java.util.Iterator<Container> iterator = (java.util.Iterator<Container>) storage.iterator();
		while (iterator.hasNext()) {
			Container rect = iterator.next();
			rect.draw(g2);
		}
		//Draws rectangle that "holds" the containers in the component image
		if(bottom!=null)
			g2.draw(bottom);
	}

}
