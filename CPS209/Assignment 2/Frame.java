package assignment2;

/**
 * Assignment 2
 * CPS209
 * @author Peter Milner
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * The Frame class contains the menu
 */
public class Frame extends JFrame 
{
	private Component component;

	/**
      Constructs the frame.
	 */
	public Frame()
	{  
		component = new Component();
		this.add(component);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createStackMenu());
		menuBar.add(createListMenu());
	}

	/**
      Creates the File menu.
      @return the menu
	 */
	public JMenu createFileMenu()
	{
		JMenu menu = new JMenu("File");
		menu.add(createNewMenuItem("New"));
		menu.add(createNewExitItem("Exit"));
		return menu;
	}
	/**
   Creates the Stack menu.
   @return the menu
	 */
	public JMenu createStackMenu()
	{
		JMenu menu = new JMenu("Stack");
		menu.add(createPopMenuItem("Pop"));
		menu.add(createPushMenuItem("Push"));
		return menu;
	}
	/**
   Creates the List menu.
   @return the menu
	 */
	public JMenu createListMenu()
	{
		JMenu menu = new JMenu("List");
		menu.add(createAddFirstMenuItem("Add First"));
		menu.add(createAddLastMenuItem("Add Last"));
		menu.add(createRemoveFirstMenuItem("Remove First"));
		menu.add(createRemoveLastMenuItem("Remove Last"));
		return menu;
	}
	/**
	 * This method creates a menu item for the List menu.
	 * Runs the addFirst method in component.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createAddFirstMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				component.addFirst();
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the List menu.
	 * Runs the addLast method in component.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createAddLastMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				component.addLast();
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the List menu.
	 * Runs the removeFirst method in component.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createRemoveFirstMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				component.removeFirst();
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the List menu.
	 * Runs the removeLast method in component.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createRemoveLastMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				component.removeLast();
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the Stack menu.
	 * Runs the storagePop method in component.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createPopMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				component.storagePop();
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the Stack menu.
	 * Runs the storagePush method in component.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createPushMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				component.storagePush();
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the File menu.
	 * Runs the restart method in component.
	 * Creates a new simulation.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createNewMenuItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				component.setVisible(false);
				component.restart();
				component.setVisible(true);
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
	/**
	 * This method creates a menu item for the File menu.
	 * Closes application.
	 * @param name The name of the JMenuItem
	 * @return the menu
	 */
	public JMenuItem createNewExitItem(final String name)
	{
		class FaceItemListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}
		JMenuItem menu = new JMenuItem(name);
		ActionListener listener = new FaceItemListener();
		menu.addActionListener(listener);
		return menu;
	}
}


