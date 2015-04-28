
import java.awt.*;
import javax.swing.*;

/**
* This program finds a template image in a search image
* and writes the search image with the template, in a red box inside of it. 
* @author Peter Milner
* @version November 16th 2014
*/
public class MatcherTester{
	
	
	public static void main (String[] args){
		Picture p1;
		p1 = new Picture("sceneColor.png");
		Picture p2;
		p2 = new Picture("waldoColor.png");
		ImageIcon icon = new ImageIcon ("waldoColor.png");
		JOptionPane.showMessageDialog(null, "Would you like to find this man?", "Caution", JOptionPane.OK_OPTION, icon);
		long startTime = System.nanoTime();
		JOptionPane.showMessageDialog(null, "Searching...", "Loading", JOptionPane.INFORMATION_MESSAGE, icon);
		Point p = find2DMin(sadMatch(p1, p2));
		displayMatch(p1, p);
		p1.write("WaldoBoxed.jpg");
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		JOptionPane.showMessageDialog(null, "Duration Time: " + (duration*1.0e-9) + " seconds" + "\nThe boxed waldo in the image was written to WaldoBoxed.jpg", "Successful run, congratulations!", JOptionPane.INFORMATION_MESSAGE, icon);

	}
	/**
	* Calculates SAD values by going through search image and checking
	* the template image at every point
	* @param s This is the picture to be checked
	* @param t This is the template image used for checking
	* @return A 2D array containing the calculated SAD values
	*/
	public static int [] [] sadMatch(Picture s, Picture t){ //s = search image, t = template image
		int [][] sadValues = new int [s.getHeight()-t.getHeight()][s.getWidth() - t.getWidth()];			
		double [][] sValues = new double [s.getHeight()][s.getWidth()];		
		//Creates 2d array of the int value of average pixel colors for every pixel in the search image		
		for (int i = 0; i < s.getHeight(); i++){
			for (int j = 0; j < s.getWidth(); j++){
				sValues[i][j] = s.getPixel(j,i).getAverage();
			}
		}
		
		double [][] tValues = new double [t.getHeight()][t.getWidth()];	
		//Creates 2d array of the int value of average pixel colors for every pixel in the template image	
		for (int i = 0; i <t.getHeight(); i++){
			for (int j = 0; j < t.getWidth(); j++){
				tValues[i][j] = t.getPixel(j,i).getAverage();
			}
		}
		//First two loops go through the search image
		for (int i = 0; i < sadValues.length; i++){
			for (int j = 0; j < sadValues[0].length; j++){
				//The last two loops go through the template image
				for(int a = 0; a < t.getHeight(); a++){
					for (int b=0; b < t.getWidth(); b++){
						// Reduces speed by including a threshold.
						if (sadValues[i][j] > 5325)//the calculated SAD value at waldo (2031 for grayscale, 5325 for color)
							break;
						/*Adds the sadValue for that point in the search image
						* by taking a absolute subtraction of the search image value iteration
						* plus the template image iteration from the template image iteration
						*/
						sadValues[i][j] += Math.abs(sValues[i+a][j+b] - tValues[a][b]);
						
					}
				}
			}
		}
		return sadValues;
		
	}
	/**
	* Finds the smallest SAD value in the SAD array and outputs as a point
	* @param SAD The 2D array of SAD values calculated
	* @return The smallest SAD value as a Point object
	*/
	public static Point find2DMin(int [][] SAD){
		int x = 0;
		int y = 0;	
		int min = SAD[0][0];	
		//loops going through SAD 2d array checking for minimum
		for(int i = 0; i < SAD.length; i++){
			for (int j = 0; j <SAD[0].length; j++){
				if (SAD[i][j] < min){

					x = i;
					y = j;
					min = SAD[i][j];
				}
			}
		}
		
		return new Point(y, x);
	}
	/**
	* Draws a rectangle using SimpleTurtle at the specified point in the picture
	* @param s This is the picture to be drawn on
	* @param p This is the specified point in the picture that is the top left of the rectangle
	*/
	public static void displayMatch(Picture s, Point p){
		int x = (int) p.getX();
		int y = (int)p.getY();		
		SimpleTurtle p1 = new SimpleTurtle(x, y, s);
		p1.setPenWidth(3);
		p1.setColor(Color.RED);
		int height = 110;
		int width = 57;
		p1.turnRight();
		p1.forward(width);
		p1.turnRight();
		p1.forward(height);
		p1.turnRight();
		p1.forward(width);
		p1.turnRight();
		p1.forward(height);
		p1.turnRight();
		s.show();
		
	}
	
	
	
			

}
