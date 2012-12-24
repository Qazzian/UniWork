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
	/** The name of the selected Design for the back of the cards. **/
	private String cardBack = "BackR2";
	
	// ////////////// //
	// AWT Components //
	// ////////////// //

	// Menu Bar. The user options for the Game
	private JMenuBar menuBar 	= new JMenuBar();
	 private JMenu gameMenu 		= new JMenu("Game");
		private JMenuItem dealItem 	= new JMenuItem("Deal Game"); 
		private JMenuItem optionItem	= new JMenuItem("Options");
		private JMenuItem exitItem   	= new JMenuItem("Exit");
	 private JMenu helpMenu 		= new JMenu("Help");
		private JMenuItem howItem   	= new JMenuItem("How to Play");
		private JMenuItem aboutItem  	= new JMenuItem("About");
		
	// Menu Item Mnemonic Constants
	public static final char GAME_MENU_MN   = 'G';
	public static final char DEAL_ITEM_MN   = 'D';
	public static final char OPTION_ITEM_MN = 'O';
	public static final char EXIT_ITEM_MN   = 'X';
	public static final char HELP_MENU_MN   = 'H';
	public static final char HOW_ITEM_MN    = 'P';
	public static final char ABOUT_ITEM_MN  = 'A';
	
	// Card buttons. click on these to select the next card to discard
	private JButton[] smallStacks = new JButton[SixStack.SMALL_STACK_NUM];
	private JButton	turnCards 	= new JButton();
	private JButton	discardCards = new JButton();
	
	
	/**
	 * Sets up the basic paramiters for the Frame and calls methods for game specific paramiters
	 */
	public SixStackFrame(){
		super("Six Stack");	// Set the Window title
		Toolkit defaultProp = this.getToolkit();					
		Dimension screenSize = defaultProp.getScreenSize();
		this.setBounds(50,50,200,200);					// Just some basic boundry marks
		getContentPane().setBackground(DARK_GREEN);	// apply my background color
		this.addWindowListener(new WindowHandler());
		this.initMenuBar();		// start drawing the frame.
		this.initEmptyBoard();
		this.pack();			// Pack it all up to fit
	}
	
	/**
	 * Creates the Menu bar for the SixStack Game Window. 
	 * Includes Mnemonics with the menu items.
	 */
	public void initMenuBar(){
		setJMenuBar(menuBar);			// The Bar
		 menuBar.add(gameMenu);			// Add the Menus and MenuItems defined above
		 gameMenu.setMnemonic(GAME_MENU_MN);	// And the Mnemonics defined above
		  gameMenu.add(dealItem);
		  dealItem.setMnemonic(DEAL_ITEM_MN);
				// Add the action Listener so the program can react to User commands
		  dealItem.addActionListener(new GameMenuListener(dealItem));
		  gameMenu.add(optionItem);
		  optionItem.setMnemonic(OPTION_ITEM_MN);
		  optionItem.addActionListener(new GameMenuListener(optionItem));
		  gameMenu.add(exitItem);
		  exitItem.setMnemonic(EXIT_ITEM_MN);
		  exitItem.addActionListener(new GameMenuListener(exitItem));
		 menuBar.add(helpMenu);			// Start the help Menu
		 helpMenu.setMnemonic(HELP_MENU_MN);
		  helpMenu.add(howItem);
		  howItem.setMnemonic(HOW_ITEM_MN);
		  howItem.addActionListener(new GameMenuListener(howItem));
		  helpMenu.add(aboutItem);
		  aboutItem.setMnemonic(ABOUT_ITEM_MN);
		  aboutItem.addActionListener(new GameMenuListener(aboutItem));
	}
	
	public void Init cardFrames(){
	
	
	}
	
	public void initEmptyBoard(){
		ImagePanel turnPileImage = new ImagePanel(GIF_DIR + cardBack + GIF_EXT);
		getContentPane().add(turnPileImage);
		// turnPileImage.paintIcon(getContentPane(),
	}

	/** Exit the Application */
	private void exitTool(){
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
		private char commandCode;
		
		// Constructor
		GameMenuListener(AbstractButton jm){
			 this.commandCode = (char)jm.getMnemonic();
		}
		
		public void actionPerformed(ActionEvent e){
			switch(commandCode){
				case 'D':	// Deal Menu
					System.out.println("Dealing a new game.");
					break;
				case 'O':	// Option Menu
					System.out.println("Options Menu.");
					break;
				case 'X':	// Exit Menu
					exitTool();
					break;
				case 'P':	// How to Play Menu
					System.out.println("How To Play.");
					break;
				case 'A':	// About Menu
					System.out.println("About SixStack\n Created by Ian Wallis (ifw9@aber.ac.uk)\n Last Update 01/02/2001");
					break;
				default:
			}
		}
	} // End of inner class GameMenuListener
	
	/** Class Representing a panel displying the card images */
	public class ImagePanel extends JPanel{

		Image image;
		
		public ImagePanel(Image theImage){
			this.image = theImage;
		}
		
		public ImagePanel(String theFileName){
			I
			this.image = new Image(new File(theFileName));
		}

		public ImagePanel(Card theCard){
			String imageFile = GIF_DIR + theCard.getFileName() + GIF_EXT;
			this.image = new Image(new File(imageFile));
		}
		
		public void paint(Graphics g){
			g.drawImage(image, 0, 0, this);
		}
	}
}

