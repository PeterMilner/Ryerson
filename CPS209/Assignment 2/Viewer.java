package assignment2;

/**
 * Assignment 2
 * CPS209
 * @author Peter Milner
 */
import javax.swing.JFrame;



public class Viewer {
	public static void main(String[] args) {

		final int WIDTH = 800 ; //Width of window
		final int HEIGHT = 600 ; //Height of window

		//Creates new Frame instance
		JFrame frame = new Frame();

		//Adding the component to frame, setting size of frame, setting the DefaultCloseOperation and setting to visible
		frame.setSize(WIDTH, HEIGHT) ;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		frame.setVisible(true) ;

	}





}
