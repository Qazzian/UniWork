import java.io.*;

/**
 * Stores all the constant values needed to implement a game of TicTacToe.
 * 
 * @author Ian Wallis (ifw9)
 * @version jdk1.3 Last revised 22/02/2001
 * copyright Ian Wallis, Department of Computer Science, 
 * The University of Wales, Aberystwyth.
 */
public class TttConstants{
	
	/** the board height of a standard game of TicTacToe **/
	public final static int BOARD_HEIGHT = 3;
	/** The board width of a standard game of TicTacToe **/
	public final static int BOARD_WIDTH = 3;

	/** Defines the winning combinations in a standard game of TicTacToe. **/
	public final static TttBoard[] WIN_POSITIONS = initWins();
	
	
	// Defines the integer codes for the occupier of each space
	/** The Integer code for an empty space. **/
	public final static int EMPTY_POS 	= 0;
	/** The Integer code for a spae occupied by Player one **/
	public final static int PLAYER_ONE 	= 1;
	/** The Integer code for a space occupied by Player two **/
	public final static int PLAYER_TWO 	= 2;
	/** The Integer code for comparing a winning space **/
	public final static int WINNING_POS = 3;
	
	/**
	 * Returns an Array of TttBoard with all the seperate possible winning positions.
	 * Use this generated array to test other boards for winning positions.
	 * @return an Array of the possible winning TttBoards.
	 */
	public static TttBoard[] initWins() {
		TttBoard[] tmpArr = new TttBoard[8];
		int[] i = {0, 0, 0};
		int[] j = {0, 1, 2};
		tmpArr[0] = new TttBoard(i, j, TttConstants.WINNING_POS);
		tmpArr[1] = new TttBoard(j, i, TttConstants.WINNING_POS);
		
		for(int k=0;k<3;k++){i[k]++;}
		tmpArr[2] = new TttBoard(i, j, TttConstants.WINNING_POS);
		tmpArr[3] = new TttBoard(j, i, TttConstants.WINNING_POS);
		
		for(int k=0;k<3;k++){i[k]++;}
		tmpArr[4] = new TttBoard(i,j,TttConstants.WINNING_POS);
		tmpArr[5] = new TttBoard(j,i,TttConstants.WINNING_POS);
		
		for(int k=0;k<3;k++){i[k] = i[k] - k;}
		tmpArr[6] = new TttBoard(j,j,TttConstants.WINNING_POS);
		tmpArr[7] = new TttBoard(j,i,TttConstants.WINNING_POS);
		return tmpArr;
	}
	
	/**
	 * A simple test method to check the integrity of the constants.
	 */
	public static void main(String[] args) throws Exception{
		System.out.println("Printing the layouts of the winning boards.");
		for(int i=0; i< TttConstants.WIN_POSITIONS.length; i++){
			System.out.println( TttConstants.WIN_POSITIONS[i].toString() );
		}
	}

}