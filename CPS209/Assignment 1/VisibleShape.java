package assignment1;

import java.awt.Graphics2D;


/**
 * @author petermilner
 * Interface to be used by the token class(es)
 */
public interface VisibleShape 
{
	void draw(Graphics2D g2);
	void setVisibilityPolicy();
	boolean overlaps(VisibleShape other);
}
