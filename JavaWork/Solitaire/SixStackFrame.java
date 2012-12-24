import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * SixStackFrame is the user interface for the game SixStack. It contains the code for implementing the card game as a 
 * JFrame. Use this class to load the game and play in a window.
 */
public class SixStackFrame extends JFrame{

	// /////////////// //
	// Class Constants // 
	// /////////////// //
	
	/** The extention for the Image files **/
	public static final String GIF_EXT = ".gif";
	
	/** The relative Directory of the Image files **/
	public static final String GIF_DIR = "gifs/";
	
	/** The height of the Image files **/
	public static final int GIF_HEIGHT = 96;
	
	/** The width of the Image files **/
	public static final int GIF_WIDTH = 71;
	
	/** The background colour for the game **/
	public static final Color DARK_GREEN = new Color(0,128,0);

	
	// ////////////////// //
	// Instanse Variables //
	// ////////////////// //

	/** An Instance of the Game Engin **/
	private SixStack theGame = new SixStack();
	
	/** Stores the users selected move **/
	private int UserMove;
	
	/** Stores a value for when testing validity of moves and game compleation state **/
	private boolean testValue;
	
	
	// ////////////// //
	// AWT Components //
	// ////////////// //

	// Menu Bar. Will store options for the Game
	private JMenuBar menuBar 	= new JMenuBar();
	 private JMenu gameMenu 		= new JMenu("Game");
		private JMenuItem dealItem 	= new JMenuItem("Deal Game"); // most important of the commands
		private JMenuItem optionItem	= new JMenuItem("Options");
		private JMenuItem exitItem   	= new JMenuItem("Exit");
	 private JMenu helpMenu 		= new JMenu("Help");
		private JMenuItem howItem   	= new JMenuItem("How to Play");
		private JMenuItem aboutItem  	= new JMenuItem("About");
	
	// Game Variables
	private String cardBack = "BackR2";
	
	
	/**
	 * Sets up the basic paramiters for the Frame and calls methods for game specific paramiters
	 */
	public SixStackFrame(){
		super("Six Stack"); 												// Set the Window title
		Toolkit defaultProp = this.getToolkit();					
		Dimension screenSize = defaultProp.getScreenSize();
		this.setBounds(50,50,200,200);								// Just some basic boundry marks
		getContentPane().setBackground(DARK_GREEN);				// apply my background color
		this.addWindowListener(new WindowHandler());
		
		this.initFrame();		// start drawing the frame.
		this.initEmptyBoard();
		
		this.pack();			// Pack it all up to fit
	}
	
	/**
	 * Calls all the submethods for initialising the Frame.
	 * In order of calling the methods are initMenuBar(), 
	 */
	public void initFrame(){
		this.initMenuBar();
	}
	
	/**
	 * Creates the Menu bar for the SixStack Game Window. 
	 * Includes Mnemonics with the menu items.
	 */
	public void initMenuBar(){
		setJMenuBar(menuBar);			// The Bar
		 menuBar.add(gameMenu);			// Add the Menus and MenuItems defined above
		 gameMenu.setMnemonic('G');	// Give them Mnemonics
		  gameMenu.add(dealItem);
		  dealItem.setMnemonic('D');
		  gameMenu.add(optionItem);
		  optionItem.setMnemonic('O');
		  gameMenu.add(exitItem);
		  exitItem.setMnemonic('X');
		 menuBar.add(helpMenu);			// Start the help Menu
		 helpMenu.setMnemonic('H');
		  helpMenu.add(howItem);
		  howItem.setMnemonic('P');
		  helpMenu.add(aboutItem);
		  aboutItem.setMnemonic('A');
	}
	
	public void initEmptyBoard(){
		ImagePanel turnPileImage = new ImagePanel();
		turnPileImage.setBackImage();
		getContentPane().add(turnPileImage);
	}
	
	/** Exit the Application */
	private void exitTool()
	{
		System.out.println("Exiting SixStack.");
		System.exit (0);
	}
	
	/** Starts the application */
	public static void main(String[] args)throws Exception{
		SixStackFrame gameWindow = new SixStackFrame();
		gameWindow.setVisible(true);
	}
	
	/**
	 * Handles events within the window.
	 */
	public class WindowHandler extends WindowAdapter{
		
		/** When closing the window */
		public void windowClosing(WindowEvent event){
			exitTool();	// Calls the Exit tool method in the Main class
		}
	} // End of inner class WindowHandler
	
	/** Handels commands from the Menu Bar */
	public class GameMenuListener implements ActionListener{
		// Variables
		private int commandCode;
		
		// Constructor
		GameMenuListener(int command){
			this.commandCode = command;
		}
		
		public void actionPerformed(ActionEvent e){
			switch(commandCode){
				case 1:			// Put code in here
					break;
				case 2:			// & here
					break;
				default:			// & so on
			}
		}
	} // End of inner class GameMenuListener
	
	/** Class Representing a panel displying the card images */
	public class ImagePanel extends JPanel{

		private Image image;
		
		public ImagePanel(){
		}

		public void setFaceImage(Card theCard){
			String imageFile = GIF_DIR + theCard.getFileName() + GIF_EXT;
			ImageIcon faceImage = new ImageIcon(imageFile);
		}

		public void setOverlapImage(){
		}

		public void setBackImage(){
			String imageFile = GIF_DIR + cardBack + GIF_EXT;
			ImageIcon faceImage = new ImageIcon(imageFile);
			this.image = faceImage.getImage();
		}
	}
}

