/*
 * TicTacToeFm.java
 *
 * Created on 24 February 2001
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

//package TicTacToe;

/**
 *
 * @author  Ian Wallis
 * @version 0.5 Development. 
 * @version sinc jdk1.3
 */
public class TicTacToeFm extends JFrame {

	// constant declaration
	private ImageIcon cross  = new ImageIcon("images/cross.gif");
	private ImageIcon nought = new ImageIcon("images/nought.gif");
	private ImageIcon blank = new ImageIcon("images/blank.gif");
	
	// Variable declaration
	private TttBoard theGame = new TttBoard();
	private Minimax theBrain;
	
	private int humanPlayer = 1;
	private int computerPlayer = 2;
	
	/* This will be referanced as the depth that the Minimax class will search to 
	 * when calculating the best move. Default is 4. */
	private int skill = 4;
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
	
	private JButton[][] positions = new 
					 JButton[TttConstants.BOARD_HEIGHT][TttConstants.BOARD_WIDTH];
	// End of variables declaration


	/** 
	 * Creates the standard form for playing Tic Tac Toe.
	 */
	public TicTacToeFm() {
		initComponents();
		initGame();
		pack();
	}

	/**
	 * Initialises a new Game.
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
	 * initialize the form.
	 */
	private void initComponents() {
		
		// Start content Pane
		getContentPane().setLayout(new GridLayout(3, 3));
		this.setBounds(100,100,150,150);
		setTitle("TicTacToe");
		setResizable(true);
		addWindowListener(new WindowHandler());
		
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
		}	}
		
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
		// End Menu bar
		
			// Add all the buttons.
		for(int h=0; h< TttConstants.BOARD_HEIGHT ;h++) {
		for(int w=0; w< TttConstants.BOARD_WIDTH ;w++) {
			positions[h][w].setActionCommand(h+","+w);
			positions[h][w].setDefaultCapable(false);
			positions[h][w].addActionListener(new ButtonListener());
			positions[h][w].setMinimumSize(new Dimension(50,50));
			getContentPane().add(positions[h][w]);
		}}
		
		setJMenuBar(jMenuBar1);
		
	}
	
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
			} else if(theGame.isDraw()){
				JOptionPane.showMessageDialog(this, "The Game is a Draw!");
				lastWinner = theGame.getPlayer();
				initGame();
			} else {
				theGame.changePlayer();
				if(theGame.getPlayer() == computerPlayer) {
					doComputerMove();
				}
			}
		}
	}
	
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
				System.out.println("No icon was added.");
				break;
			}
		}}
		System.out.println("Finnished changing the icons.");
	}
	
	public void setSkill(int theSkill){
		skill = theSkill;
	}
	
	/*public void setSkill(){
		showDialog()
	}*/
	
	

	/** Exit the Application */
	private void exitForm() {
		System.exit (0);
	}

	
	public static void main (String args[]) {
		new TicTacToeFm ().show ();
	}
	
	// ////////////////////// //
	// Inner Class Definition //
	// ////////////////////// //
	
	/**
	 * Handles events within the window.
	 */
	public class WindowHandler extends WindowAdapter {

		public void windowClosing(WindowEvent event) {
			exitForm();
		}
	}

	/**
	 * Listens to Button events.
	 */
	public class ButtonListener implements ActionListener {

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
	
	public class MenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			String menuString = event.getActionCommand();
			if(menuString.equals("New Game")) {
				System.out.println("Starting a new game.");
				initGame();
			}
			if(menuString.equals("Options")) {
				System.out.println("Opening options menu.");
			}
			if(menuString.equals("Exit")) {
				System.out.println("Exiting the game");
				exitForm();
			}
			if(menuString.equals("How to Play")) {
				System.out.println("Showing the rules of the game");
			}
			if(menuString.equals("About")) {
				System.out.println("About the game;");
				System.out.println("\t\t Tic Tac Toe \n\tCreated by Ian wallis" +
					"\n\tCopyright Ian Wallis,\n\tThe Department of Computer Science,"+
					"\n\tThe University Of Wales, Aberystwyth."+
					"\n\t\t for more information email ifw9@aber.ac.uk");
			}
		
		}
	
	} // End Inner class definitions.
	
	
}	// End of class!
