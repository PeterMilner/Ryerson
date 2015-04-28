package assignment1;

import javax.swing.* ;


/**
 * @author petermilner
 * Creates the window for the game component and displays the game
 */
public class GameViewer{

	/**
	 * Main class, what starts the program and runs the game component
	 * @param args
	 */
	public static void main(String[] args) {
		
		final int WIDTH = 600 ; //Width of window
		final int HEIGHT = 500 ; //Height of window
		JFrame frame = new JFrame("Game Viewer") ; //Opening JFRAME
		//Pop-up which explains the instructions of the game. 
		JOptionPane.showMessageDialog(null, "The red tokens are the game tokens. \n"
				+ "The blue game token is the user-token. \n"
				+ "The user clicks the left mouse button to change the user-token’s position. \n"
				+ "The user clicks the right mouse button to cycle through the user-token’s pattern (4 token patterns) displayed inside the token’s bounding rectangle. \n"
				+ "The user chooses a pattern (using the right mouse button) and then clicks the left mouse button to set the user-token overtop one of the game tokens. \n"
				+ "If the pattern types match, the user’s score is incremented and the game token’s color is set to green. \n"
				+ "In this example, some of the game tokens show their pattern for a random number of seconds at the beginning of the game and then show only their bounding rectangle. \n"
				+ "Some of the game tokens randomly turn their pattern off and then back on for a short amount of time. (One in 1/1000th chance every 10 miliseconds.) \n"
				+ "The game begins (the blue user-token appears in the upper left) after 3 seconds. \n"
				+ "Thus, the user has 3 seconds to memorize the game token patterns. \n"
				+ "The score is decremented if the user unsuccessfully attempts to match a game token more than 2 times. \n"
				+ "In the figure above, the user has successfully matched 2 game tokens (colored green). \n"
				+ "All tokens not yet matched are colored red. When the user has successfully matched all tokens, the game is over. \n"
				+ "The number of elapsed seconds and the score are shown in the upper left of the figure."
				, "Instructions", JOptionPane.OK_OPTION);
		GameComponent component = new GameComponent();// Creates a GameComponent
		//Adding the component to frame, setting size of frame, setting the DefaultCloseOperation and setting to visible
		frame.add(component);
		frame.setSize(WIDTH, HEIGHT) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frame.setVisible(true) ;

	}
	

}
