package assignment1;

import javax.swing.* ;
import java.awt.Color;
import java.awt.Graphics ;
import java.awt.Graphics2D ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author petermilner
 * The main component of the game
 * Handles the clicking events, timers, win checks and contains the draw method
 */
public class GameComponent extends JComponent{
	private SystemToken[] tokens;
	private Timer timer;
	private int score;
	private UserToken userToken;
	private static long startTime; 
	private int count; //Used for multiple attempt score checking
	
	/**
	 * Constructor for the game Component
	 */
	/**
	 * 
	 */
	public GameComponent(){
		userToken = new UserToken(8,30,50,50); // Token for the user to use, set to the upper left corner under the score and time
		userToken.update(); // Updates the token to contain the pattern
		tokens = new SystemToken[12]; // Creates array for 12 SystemTokens
		Random rand = new Random(); //Random generator
		ArrayList<SystemToken> tokenlist = new ArrayList<SystemToken>(); //Arraylist created for space controlling 
		tokenlist.add(new SystemToken(0,0,100,100)); //"Fake" token simply to reserve upper left corner area for time, score and user token spawn
		for(int i = 0; i<tokens.length; i++){ //Loops through tokenlist
			//Space Control
			int x=0;
			int y=0;
			boolean spaceControl = false; //Boolean for whether space control is achieved for the current token iteration
			while(!spaceControl){ //While loop to keep attempting space control
					//x and y values generated under the frame constraints 
					x = rand.nextInt(500); 
					y = rand.nextInt(400);
					spaceControl=true;//Space control set to true in order to be "tested" to be proved wrong
					SystemToken tester = new SystemToken(x, y, 50, 50); //A tester token
					//Checks if the tester token overlaps any previously created tokens using space control
					for(SystemToken tok : tokenlist){ 
						if(tok.overlaps(tester))
							spaceControl = false;
					}
					if (spaceControl){ // If space trolling is achieved
						tokenlist.add(tester); //Adds tester token to token list
						tokens[i] = tester; //Adds the tester to the official list of SystemTokens
						break;//Breaks from the space control while loop
					}
			}
				
		}
		//Timer
		timer = new Timer(10, new ActionListener(){ //Performs the following action every 10 miliseconds
			public void actionPerformed(ActionEvent e){
				
				for(int i = 0; i<tokens.length; i++){ //Sets the visibility policy for all system tokens
					tokens[i].setVisibilityPolicy();
				}
				userToken.setVisibilityPolicy(); //Sets visibility policy for user token
				repaint(); //Repaints
				if(won()){ //Stops the timer if won and creates a "you win" pop-up
					timer.stop();
					JOptionPane.showMessageDialog(null, "You WIN! \nin " + getElapsed() + " seconds. \nYour score was: " + score, "ALERT!", JOptionPane.OK_OPTION);
				}
					
			}
		});
		
		timer.start(); //Starts timer
		startTime = System.currentTimeMillis(); //Time when the timer started
		
		
		/**
		 * @author petermilner
		 * Innerclass to implement MouseListener
		 */
		class GameListener implements MouseListener
		{
			public void mousePressed(MouseEvent event){
				if(userToken.getPVisibility()){ //Only works if the userToken pattern is visible
					int x = event.getX();
					int y = event.getY();
					if(SwingUtilities.isLeftMouseButton(event)){ //Left mouse button operations
						//Puts the usertoken object to the location of the click
						userToken.setLocation(x-(int)userToken.getWidth()/2, y-(int)userToken.getHeight()/2);
						userToken.update();
						
					}
					else if (SwingUtilities.isRightMouseButton(event)){ // Right mouse button operations
						int p = userToken.getPattern(); //Takes the current pattern of user token
						if(p==3) //If the current pattern is the last one, resets to last
							userToken.changePattern(0);
						else{ //Else, iterates to the next pattern
							p++;
							userToken.changePattern(p);;
						}
						userToken.update(); //Updates the pattern
					}
					
					
					// If the user token is clicked onto a system token
					// If first match attempt, awarded 2 points
					// If second match attempt, awarded 1 point
					for(int i=0; i< tokens.length; i++){
						if (tokens[i].overlaps(userToken)){
							count++;
							if(userToken.equals(tokens[i])){
								if(count<=1 ){
									score+=2;
								} else if (count==2 )
									score++;
								//Sets the pattern to be visible and for the token to be green
								tokens[i].setPatternVisibility(true);
								tokens[i].changeColor(Color.GREEN);
								count=0; // Match attempts reset to 0
								
							}
							//Score is decremented by one if match attempted more than twice
							else{
								if(count>2)
									score--;
							}
						}
					}
					repaint();//Repaints 
				}
			}
			
			public void mouseReleased(MouseEvent event){}
			public void mouseClicked(MouseEvent event){}
			public void mouseEntered(MouseEvent event){}
			public void mouseExited(MouseEvent event){}
			
		}
		MouseListener listener = new GameListener();
		this.addMouseListener(listener);
		
	}
	/**
	 * Returns the total elapsed time using timer
	 * @return long - the current
	 */
	public static long getElapsed(){
		return (System.currentTimeMillis()-startTime)/1000;
	}
	/**
	 * Checks if the user has won by checking the color of all the system tokens
	 * @return boolean - whether the user has won
	 */
	public boolean won(){
		int counter=0;
		for(int i =0; i<tokens.length; i++){
			if(tokens[i].getColor()==Color.GREEN)
				counter++;
		}
		if (counter==tokens.length) return true;
		return false;
	}
	/** 
	 * Paints the score, time, system tokens and user token
	 * @param Graphics2D g2 - Completes the drawing using Graphics2D
	 */
	public void paintComponent(Graphics g)
	{
		//Score Text top left
		g.drawString("Score: " + score, 8, 12);
		//Timer text if user hasn't won yet
		if(!won())
			g.drawString("Time: "+ getElapsed() , 8, 24);
		//Game Tokens
		Graphics2D g2 = (Graphics2D) g;
		for(GameToken token : tokens) {
			if(token.getVisibility()){
				token.update();
				g2.setColor(token.getColor());
				token.draw(g2);
			}
		}
		//User Token
		g2.setColor(userToken.getColor());
		if(userToken.getVisibility())
			userToken.draw(g2);
		
		
	}
}
