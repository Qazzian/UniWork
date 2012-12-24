
/**
 * This is the basic frame for the game of TicTacToe. stores the layout of the 
 * board and contains the methods needed to manipulate the board and add places 
 * to to the board. 
 */
public class TttBoard	extends java.lang.Object
								implements java.io.Serializable {

	// /////////////////// //
	// Instantce Variables //
	// /////////////////// //							
 
 	/** Stores the player moves in the chosen locations **/
	private int[][] layout = new int[TttConstants.BOARD_HEIGHT]
											  [TttConstants.BOARD_WIDTH];
	/** The Player who is to make the next move. **/
	private int currentPlayer = 0;
	/** The winning value of this board. @see calcValue() **/
	private int value = 0;
	
	
	// //////////// //
	// Constructors //
	// //////////// //
	
	/**
	 * Creates a new clear TicTacToe board.
	 */
	public TttBoard() {
		this.clearBoard();
	}
	
	/**
	 * Creates a new TicTacToe Board with the give positions filled with the 
	 * given players symbol. The indexes of both arrays will be matched up to 
	 * find the location of each positon specified. Start from the top left hand 
	 * corner of the board when using the coordinate system
	 */
	public TttBoard(int[] xPos, int[] yPos, int thePlayer) {
		this.clearBoard();
		this.addMoves(xPos, yPos, thePlayer);
	}
	
	/**
	 * Creates a new TttBoard by setting a copy of the given layout as 
	 * the Layout for the new Board.
	 * @param 	theLayout	The layout of anther Board. Should be of a size specified 
	 								by BOARD_HEIGHT and BOARD_WIDTH fields in TttConstants.
	 */
	public TttBoard(int[][] theLayout){
		for(int h=0; h<TttConstants.BOARD_HEIGHT;h++) {
		 for(int w=0; w<TttConstants.BOARD_WIDTH;w++) {
		 	layout[h][w] = theLayout[h][w];
		}}
	}
	
	// ////////////////// //
	// Read/Write Methods //
	// ////////////////// //
	
	/**
	 * Set all the positions in the board to the empty value.
	 */
	public void clearBoard() {
		for(int i=0; i<TttConstants.BOARD_HEIGHT; i++) {
		for(int j=0; j<TttConstants.BOARD_WIDTH;  j++) {
			layout[i][j] = TttConstants.EMPTY_POS;
		}
		}
	}
	
	/**
	 * Add the next move using the value of currentPlayer to choose 
	 * the piece to place in the given location.
	 */
	public void addMove(int xPos, int yPos) {
		addMove(xPos,yPos,currentPlayer);
	}
	
	/**
	 * Places the symbol for the given player in the given position.
	 * This method does not check to see if the move is allowed as 
	 * part of the game rules. This will have to be done in the game engine.
	 * @param xPos The x coordinate of the position to check.
	 * @param yPos The y coordinate of the Position to check.
	 * @param thePlayer The player for who the move is to be made. 
	 */
	public void addMove(int xPos, int yPos, int thePlayer)
							throws java.lang.IndexOutOfBoundsException {
		if( (xPos >= layout.length) || (xPos < 0)){
			throw new java.lang.IndexOutOfBoundsException
								(xPos + "is out side the width of the board!");
		}
		if( (yPos >= layout[1].length) || (yPos < 0)){
			throw new java.lang.IndexOutOfBoundsException
								(yPos + "is out side the height of the board!");
		}
		layout[xPos][yPos] = thePlayer;
	}
	
	/**
	 * Places the symbol for the given player in all the given positions.
	 * @param xPos The x coordinates of each of the positions to fill.
	 * @param yPos the coresponding y coordinates of each of the positions to fill.
	 * @param thePlayer The player symbol to put in each of the positions.
	 */
	public void addMoves(int[] xPos, int[] yPos, int thePlayer)
							throws java.lang.IndexOutOfBoundsException {
 		int i = 0; // start to loop through the board positions
		while( (i < xPos.length) && (i < yPos.length) ) {
				// Add each position by calling the addMove() method 
				// as it preforms the necessary checks.
			this.addMove(xPos[i], yPos[i], thePlayer);
			i++;
		}
	}
	
	/**
	 * Returns the player symbol at the given position.
	 * @param xPos The x Coordinate of the position to look at.
	 * @return int The player code as defined in the TttConstants Class.
	 */
	public int getPlayerAt(int xPos, int yPos) 
							throws java.lang.IndexOutOfBoundsException {
		if( (xPos >= layout.length) || (xPos < 0)){
			throw new java.lang.IndexOutOfBoundsException
								(xPos + "is out side the bounds of the board!");
		}
		if( (yPos >= layout[1].length) || (yPos < 0) ) {
			throw new IndexOutOfBoundsException
								(yPos + "is out side the bounds of the board!");
		}
		return layout[xPos][yPos];
	}
	
	/**
	 * Sets the current player number to the given player number.
	 * @param The new number to represent the current player.
	 */
	public void setPlayer(int thePlayer) {
		currentPlayer = thePlayer;
	}
	
	/**
	 * Changes to the next Player and returns the int code for that player.
	 */
	public int changePlayer(){
		switch(currentPlayer){
			case 1:
				currentPlayer = 2;
				break;
			case 2:
			default:
				currentPlayer = 1;
				break;
		}
		return currentPlayer;
	}
	
	/**
	 * Returns the int value for the current player.
	 * @return the Current player.
	 */
	public int getPlayer(){
		return currentPlayer;
	}
	
	public int getOtherPlayer(){
		return getOtherPlayer(currentPlayer);
	}
	
	public int getOtherPlayer(int theCurrentPlayer) {
		switch(theCurrentPlayer){
		 case 1:
		 	return 2;
		 default:
		 	return 1;
		}
	}
	
	/**
	 * Calculates the winning value of the boared from the given players point of view.
	 * +10 indicates a win for given player, -10 a win for opponent player, 0 is even.
	 * Anything else is based uppon the number of possible wins for either player in the 
	 * current situation. For instace if player 1 has 4 possible wins and player 2 has 
	 * 1 possible win and we are evaluating for player 2 the score will be 1 - 4 = -3
	 * @return The winning value of the boared from Player ones point of view.
	 */
	public int calcValue(int thePlayer){
		value = canWin(thePlayer);
		//value -= canWin(getOtherPlayer(thePlayer));
		return value;
		
	}
	
	public void setValue(int theValue){
		value = theValue;
	}
	
	public int getValue(){
		return value;
	}
	
	/**
	 * Returns the layout of the board in a 2dimensional integer array.
	 * @return int[][] The layout of the TttBoard.
	 */
	public int[][] getLayout(){
		return layout;
	}


	// ///////////// //
	// Check Methods //
	// ///////////// //
	
	/**
	 * Checks the board to see if it is possible to make any more moves. 
	 * ie If the board has been filled.
	 * @return true If it is not posible to place any other moves.
	 * @return false If there is at least one empty space in the board.
	 */
	public boolean isDraw() {
		for(int i=0; i<TttConstants.BOARD_HEIGHT; i++)
		for(int j=0; j<TttConstants.BOARD_WIDTH;  j++) {
			if(layout[i][j] == TttConstants.EMPTY_POS) {
				return false; // If there is an empty space the board is not full
			}
		}
		return true; // If not already returned, the board must be full.
	}
	
	/**
	 * Checks the given position to see if it is a leagel move.
	 * @param xPos The x coordinate of the position to check.
	 * @param yPos The y coordinate of the Position to check.
	 * @return true If the position exists and is not already filled.
	 * @return false If given coordinates are out side of the board or the 
	 * 	position is filled.
	 */
	public boolean canMove(int xPos, int yPos) {
	 if(layout[xPos][yPos] == TttConstants.EMPTY_POS){
	 	return true;
	 } 
	 else return false;
	}
	
	/**
	 * Checks to see if the given player has one the game, 
	 * @param thePlayer the Player to check winning positions for.
	 * @return true If the given player has won the game.
	 * @return false If the given player has not won the game.
	 */
	public boolean hasWon(int thePlayer) {
		int player = thePlayer;
		boolean won = false;
		for(int l=0; l < TttConstants.WIN_POSITIONS.length; l++) {
			TttBoard test = TttConstants.WIN_POSITIONS[l];
			won = true;
			for(int h=0; h < TttConstants.BOARD_HEIGHT; h++) {
				for (int w=0; w < TttConstants.BOARD_WIDTH; w++) {
					if(test.getPlayerAt(h,w) == TttConstants.WINNING_POS) {
						if(this.layout[h][w] != player) {
							won = false;
			}	}	}	}
			if(won) {  // If the player has won on this board stop the search.
				return won;
			}
		} 
		return false; // If having searched all the winning boards and there is no match.
	}
	
	/**
	 * A check to see if it is possible for the given player to win
	 * @param thePlayer The player to check if it possible to win for.
	 * @return The number of wins there are possible for the given player.
	 */
	public int canWin(int thePlayer){
		int player = thePlayer;
		int theValue = 0;
		boolean winable;
		for(int l=0; l < TttConstants.WIN_POSITIONS.length; l++){
			TttBoard test = TttConstants.WIN_POSITIONS[l];
			winable = true;
			for(int h=0; h < TttConstants.BOARD_HEIGHT; h++) {
			 for (int w=0; w < TttConstants.BOARD_WIDTH; w++) {
			 	if(test.getPlayerAt(h,w) == TttConstants.WINNING_POS) {
					if(this.layout[h][w] == getOtherPlayer()) {
						winable = false;
			}}	}	}
			if(winable){
				theValue++;
			}
		}
		return theValue;
	}
	
	// ///////////////////// //
	// Testing and Debugging //
	// ///////////////////// //
	
	/**
	 * Returns a String representation of this board on one line.
	 * @return A String representation of the board.
	 */
	public String toString() {
		String tmpS = "";
		for(int i=0; i<TttConstants.BOARD_HEIGHT; i++) {
			for(int j=0; j<TttConstants.BOARD_WIDTH;  j++) {
				tmpS += layout[i][j] + ",";
			}
			tmpS +="\t";
		}
		tmpS += " Value of "+value;
		return tmpS;
	}
	
	/**
	 * Starts a Text version of TicTacToe to play at a command prompt.
	 * This version of the game is for two players against each other.
	 */
	public void textGame(){
		TextInputReader t = new TextInputReader();
		int row = 0;
		String colS = "";
		clearBoard();
		changePlayer();
		while(row != -1){
			int colI = -1; // If player inputs an invalid column -1 will cause an exception
		 try{
			System.out.println("\n\n\n" + textBoard() + "\n");
			System.out.println("Player " + currentPlayer + " choose Row (-1 to quit);");
			row = t.readInt();
			if(row == -1){
				return;
			}
			colS = t.readText("choose column;");
				// Exchange the letter for an index value.
			if( (colS.equals("a")) || (colS.equals("A")) ){
				colI = 0;
			}
			if( (colS.equals("b")) || (colS.equals("B")) ){
				colI = 1;
			}
			if( (colS.equals("c")) || (colS.equals("C")) ){
				colI = 2;
			}
			System.out.println(currentPlayer + " chose "+row+" "+colS+" ("+colI+") ");
				// Now carry out the move.
			if(canMove(row,colI)) { 			// If you'r allowed
				addMove(row,colI,currentPlayer);
				if(hasWon(currentPlayer)) { 	// What if that was the winning move?
					System.out.println("Player " + currentPlayer + " has Won!");
					System.out.println(textBoard());
					row = -1;		// Quit if player has won.
				} else { 			// If not won print a condolance.
					System.out.println("Player " + currentPlayer + " has NOT Won :-(");
				}
				changePlayer(); // Once all that is done swap to the next player
			} else {	// If the move was not valid, will cause prog to ask player for another
				System.out.println("\n\n\t!!!!Cannot Move There!!!!"); 
			}
		 } catch(Exception e){ // catches any exceptions and continues with the game
		 	e.printStackTrace();
		 } // End try-catch
		} // End While statment
	}// End Method.
	
	/**
	 * Returns a text layout of the TicTacToe Board
	 */
	public String textBoard(){
		String boardString = "  A B C\n";
		for(int i=0; i<TttConstants.BOARD_HEIGHT; i++) {
			boardString += i + " ";
			for(int j=0; j<TttConstants.BOARD_WIDTH;  j++) {
				int contents = layout[i][j];
				String toPrint = "";
				switch(contents){
				case TttConstants.PLAYER_ONE:
					toPrint = "O";
					break;
				case TttConstants.PLAYER_TWO:
					toPrint = "X";
					break;
				case TttConstants.EMPTY_POS:
				case TttConstants.WINNING_POS:
				default:
					toPrint = "_";
				}
				boardString += toPrint + "|";
			}
			boardString += "\n";
		}
		return boardString;
	}

	/**
	 * A test method to check the integrity and quality of the class and its methods.
	 */
	public static void main(String[] args)throws Exception {
		TttBoard theGame = new TttBoard();
		TextInputReader t = new TextInputReader();
		int options = 0;
		while(options != -1) {
		 try{
			System.out.println("1; Start new Game. \n-1; Quit.");
			options = t.readInt();
			switch(options) {
			case 1:
				theGame.textGame(); // Starts a text version of the game
				break;
			case -1:
				break;
			default:
			}
		 }catch(Exception e) {
		 	 e.printStackTrace( );
		 }
		}
	}
}