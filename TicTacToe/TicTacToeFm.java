import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * This class combines the Minimax and TttBoard classes as well some of the 
 * standard java swing api to creata a fully playable, human v computer, game of Tic
 * Tac Toe person against the computer. This class requires no paramiters to run
 * either from a command prompt or as a call to it's constructor. If this class is
 * being called by its constructor, ensure that the instance of TicTacToeFm is
 * declared as shown. eg.<BR><code> TicTacToeFm myGame = new TicTacToeFm();<BR>
 *						myGame.show(); </code><BR>
 * Once show() has been called this class will take care of itself. It creates a
 * simple java form including a menu with basic functions and a set of nine buttons
 * which can be clicked on to play the game.
 *
 * @author  Ian Wallis
 * @version 1.0 Initial Release.
 * @version sinc jdk1.3
 */
public class TicTacToeFm extends JFrame {

	// ////////// //
   // Constants. //
   // ////////// //
	
	/* These are the file names and locations for the pictures. */
	private ImageIcon cross  = new ImageIcon("images/cross.gif");
	private ImageIcon nought = new ImageIcon("images/nought.gif");
	private ImageIcon blank = new ImageIcon("images/blank.gif");
	
	// /////////////////// //
   // Instance Variables. //
   // /////////////////// //
	
	private TttBoard theGame = new TttBoard();
	private Minimax theBrain;
	
	/* Set the human player as player 1 */
	private int humanPlayer = 1;
	/* Set the Computer player as player 2 */
	private int computerPlayer = 2;
	
	/* This will be referanced as the depth that the Minimax class will search to 
	 * when calculating the best move. Default is 4. */
	private int skill = 5;
	/* a record of who was the last winner so that the other player starts. */
	private int lastWinner = 0;
	
	// Swing component declaration 
	private JMenuBar 		jMenuBar1;
	private JMenu 			jmGame;
	private JMenuItem 	jmiNewGame;
	private JMenuItem 	jmiOptions;
	private JSeparator 	jsGame1;
	private JMenuItem 	jmiExit;
	private JMenu 			jmHelp;
	private JMenuItem 	jmiHowToPlay;
	private JMenuItem 	jmiAbout;
	/* 2D array of buttons represents the positions of the board */
	private JButton[][] positions = new 
					 JButton[TttConstants.BOARD_HEIGHT][TttConstants.BOARD_WIDTH];

	
	// ///////////// //
   // Constructors. //
   // ///////////// //

	/** 
	 * Creates the form for playing Tic Tac Toe.
	 */
	public TicTacToeFm() {
		initComponents();
		initGame();
		pack();
	}

	/**
	 * Called from the constructor and when a game is won or drawn 
	 * to set up a new Game.
	 */
	private void initGame() {
		theGame.clearBoard();
		theGame.setPlayer(lastWinner);
		theGame.changePlayer();
		buttonRefresh();
		if(theGame.getPlayer() == computerPlayer){
			doComputerMove();
		}
	}
	
	/** 
	 * This method is called from within the constructor to
	 * initialize the form and its components.
	 */
	private void initComponents() {
		
		// Set up the Content Pane.
		getContentPane().setLayout(new GridLayout(3, 3));
		this.setBounds(100,100,150,150);
		setTitle("Tic Tac Toe");
		setResizable(true);
		addWindowListener(new WindowHandler());
		
		// Initialise all the components.
		jMenuBar1 	= new JMenuBar();
		jmGame 		= new JMenu();
		jmiNewGame 	= new JMenuItem();
		jmiOptions 	= new JMenuItem();
		jsGame1 		= new JSeparator();
		jmiExit 		= new JMenuItem();
		jmHelp 		= new JMenu();
		jmiHowToPlay = new JMenuItem();
		jmiAbout 	= new JMenuItem();
		for(int h=0; h < TttConstants.BOARD_HEIGHT; h++){
		 for(int w=0; w < TttConstants.BOARD_WIDTH; w++){
				positions[h][w] = new JButton();
		}}
		
		// Set up the menu bar
		jmGame.setText("Game");
		jmGame.setMnemonic('g');
		jmGame.setText("Game");
		 jmiNewGame.setText("New Game");
		 jmiNewGame.setActionCommand("New Game");
		 jmiNewGame.addActionListener(new MenuListener());
		  jmGame.add(jmiNewGame);
		 jmiOptions.setText("Options");
		 jmiOptions.setActionCommand("Options");
		 jmiOptions.addActionListener(new MenuListener());
		  jmGame.add(jmiOptions);
		  jmGame.add(jsGame1); // Add the Seperator
		 jmiExit.setText("Exit");
		 jmiExit.setActionCommand("Exit");
		 jmiExit.addActionListener(new MenuListener() );
		  jmGame.add(jmiExit);
		jMenuBar1.add(jmGame);
		  
		jmHelp.setText("Help");
		 jmHelp.setMnemonic('h');
		 jmHelp.setText("Help");
		jmiHowToPlay.setText("How to Play");
		jmiHowToPlay.setActionCommand("How to Play");
		jmiHowToPlay.addActionListener(new MenuListener());
			jmHelp.add(jmiHowToPlay);
		jmiAbout.setText("About");
		jmiAbout.setActionCommand("About");
		jmiAbout.addActionListener(new MenuListener());
			jmHelp.add(jmiAbout);
		jMenuBar1.add(jmHelp);
		
		setJMenuBar(jMenuBar1);
		
		// Add all the buttons.
		for(int h=0; h< TttConstants.BOARD_HEIGHT ;h++) {
		for(int w=0; w< TttConstants.BOARD_WIDTH ;w++) {
			positions[h][w].setActionCommand(h+","+w);
			positions[h][w].setDefaultCapable(false);
			positions[h][w].addActionListener(new ButtonListener());
			positions[h][w].setMinimumSize(new Dimension(50,50));
			getContentPane().add(positions[h][w]);
		}}
	}
	
	/**
	 * This method will do the move with the given height and width position for the
	 * current player. Checks that the move is valid befor performing. After the move
	 * the method checks for a winning combination or a draw. If the game is not yet
	 * over the changePlayer() method is called for the game. If the new
	 * <code>currentPlayer</code> is the computer player
	 * <code>doComputerMove()</code> is called.
	 * @param theHeight 			The row to move in.
	 * @param thewidth			The column to move in.
	 */
	public void doMove(int theHeght, int theWidth){
		if( theGame.canMove(theHeght,theWidth) ) {
			theGame.addMove(theHeght,theWidth);
			System.out.println(theGame.toString());
			// Set the Icon for the position
			System.out.println("Doing Move "+theHeght+","+theWidth);
			buttonRefresh();
			// Now check for a winner
			if( theGame.hasWon(theGame.getPlayer()) ) {
				String winString = "";
					if(theGame.getPlayer() == computerPlayer){
						winString = "Bad luck. You Lose.";
					}else{
						winString = "Well Done, You have won!";
					}
				JOptionPane.showMessageDialog(this, winString);
				lastWinner = theGame.getPlayer();
				initGame();
			// Check to see if the game is a draw.
			} else if(theGame.isDraw()){
				JOptionPane.showMessageDialog(this, "The Game is a Draw!");
				lastWinner = theGame.getPlayer();
				initGame();
			// If no win or draw change player and find the next move.
			} else {
				theGame.changePlayer();
				if(theGame.getPlayer() == computerPlayer) {
					doComputerMove();
				}
			}
		}
	}
	
	/**
	 * Does the computers move. Finds the best move for the computer by calling
	 * <code>getBestMove(TttBoard, int)</code> in class Minimax parsing in he current
	 * board layout of the current game (<code>theGame</code>) and the number
	 * representing the computer player. Once the best move has been found it is
	 * parsed into <code>doMove()</code> which then performs the move as any other. 
	 */
	public void doComputerMove(){
		theBrain = new Minimax();
		theBrain.setMaxDepth(skill);
	 try{
		theBrain.setPlayers(computerPlayer);
		int[] theMove = theBrain.getBestMove(theGame,computerPlayer);
		System.out.println("The computer is going to move "+theMove[0]+","+theMove[1]);
		doMove(theMove[0],theMove[1]);
	 } catch (Exception e) {
	 	System.out.println("Problem with Computer Move");
		e.printStackTrace();
	 }
	}
	
	/**
	 * Repaints the icons on all the buttons. The reson it does all the buttons is so
	 * that any unusual properties can be seen visualy by the human player. As the
	 * method changes or resets icons it prints out what it is doing at the command
	 * prompt so that the user can see what it is supposed to be changing. Usefull if
	 * the image icons have been moved out of their default location.
	 */
	private void buttonRefresh(){
		for(int h=0; h<TttConstants.BOARD_HEIGHT;h++){
		 for(int w=0; w<TttConstants.BOARD_WIDTH;w++){
		 	int thePlayer = theGame.getPlayerAt(h,w);
			switch(thePlayer) {
			case 1:
				positions[h][w].setIcon(cross);
				System.out.println("Should be adding a cross icon.");
				break;
			case 2:
				positions[h][w].setIcon(nought);
				System.out.println("shoud be adding a nought icon.");
				break;
			default:
				positions[h][w].setIcon(blank);
				System.out.println("Icon was set to blank.");
				break;
			}
		}}
		System.out.println("Finnished changing the icons.");
	}
	
	/**
	 * This method changes the skill level of the computer player.
	 */
	public void setSkill(int theSkill){
		skill = theSkill;
	}
	
	/** Exit the Application */
	private void exitForm() {
		System.exit (0);
	}

	/**
	 * This method is called by the Java Virtual Machine to create an instance of 
	 * this class and to show the form.
	 */
	public static void main (String args[]) {
		new TicTacToeFm ().show ();
	}
	
	// ////////////////////// //
	// Inner Class Definition //
	// ////////////////////// //
	
	/**
	 * Handles events within the window.
	 */
	private class WindowHandler extends WindowAdapter {

		/**
		 * Captures the <code>windowClosing()</code> event
		 */
		public void windowClosing(WindowEvent event) {
			exitForm();
		}
	}

	/**
	 * This inner class listens for actions prformed by the buttons.
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * When an action is performed on a button this method reads the
		 * <code>actionCommand</code> String and uses it to work out where the human
		 * player has selected to move. Then calls <code>doMove(int ,int)</code>
		 * feeding in the height and the width value of the selected move.
		 */
		public void actionPerformed(ActionEvent event) {
			String buttonString = event.getActionCommand();
			Character heightChar  = new Character( buttonString.charAt(0) );
			int heightInt = Integer.parseInt( heightChar.toString() );
			Character widthChar = new Character( buttonString.charAt(2) );
			int widthInt = Integer.parseInt( widthChar.toString() );
			// Do the Move based on ^ these values.
			doMove(heightInt,widthInt);
		}
	}	
	
	/**
	 * Listens for actions performed by the menu bar and its items.
	 */
	public class MenuListener implements ActionListener {
		
		/**
		 * Reads the name of the menu Item which created he event and responds
		 * appropriatly. Not all the manu items have been fully implemented as yet,
		 * plus they still print something to the command prompt to show that the
		 * JMenuItem has been activated.
		 */
		public void actionPerformed(ActionEvent event) {
			String menuString = event.getActionCommand();
			if(menuString.equals("New Game")) {
				System.out.println("Starting a new game.");
				initGame();
			}
			if(menuString.equals("Options")) {
				// should call a method which shows a dialog box with changable game 
				// options such as the skill of the computer player.
				System.out.println("Opening options menu.");
			}
			if(menuString.equals("Exit")) {
				System.out.println("Exiting the game");
				exitForm();
			}
			if(menuString.equals("How to Play")) {
				// should show a dialog box which gives the rules of the game, how to 
				// play the game and what options there are for the game.
				System.out.println("Showing the rules of the game");
			}
			if(menuString.equals("About")) {
				System.out.println("About the game;");
				// Should print this in a dialog box.
				System.out.println("\t\t Tic Tac Toe \n\tCreated by Ian wallis" +
					"\n\tCopyright Ian Wallis,\n\tThe Department of Computer Science,"+
					"\n\tThe University Of Wales, Aberystwyth."+
					"\n\t\t for more information email ifw9@aber.ac.uk");
			}
		}
		
	} // End Inner class definitions. //
	
	
}	// End of class //
