/**
 * A TicTacToe Node stores a TttBoard for any given position in a tree of TttBoards.
 * Also contains an array of child nodes as well as storing this nodes parent node 
 * and its position in the parent node. this class then has methods for manipulating
 * these data variables.
 * 
 * @author Ian Wallis (ifw9)
 * @version jdk1.3 Last revised 15/03/2001
 * copyright Ian Wallis, Department of Computer Science, 
 * The University of Wales, Aberystwyth.
 */
public class TttNode implements java.io.Serializable {

	// /////////////////// //
   // Instance variables. //
   // /////////////////// //
	
	/* Holds the Board data for this node */
	private TttBoard data;
	/* The Parent Node of the Node */
	private TttNode parent;
	/* All the child nodes from this node */
	private TttNode[] children = new TttNode[9];
	/* the number of children currently held by this Node. */
	private int childNum = 0;
	/* A score value for the Board contained within this Node */
	private int score = 0;
	/* A record of where this node is in the parent node */
	private int location;

	
	// ///////////// //
   // Constructors. //
   // ///////////// //
	
	/**
	 * Creates an empty TttNode. All values set to their default or null.
	 */
	public TttNode() {
		data = new TttBoard();
	}

	/**
	 * Creates a new TttNode with the given TttBoard as the stored data.
	 * @param theBoard		The board layout which will be stored by this Node.
	 */
	public TttNode(TttBoard theBoard) {
		data = theBoard;
	}

	
	// ////////////////////// //
   // Read/Write properties. //
   // ////////////////////// //
	
	/**
	 * Creates a new TttNode with the Given Parent and 
	 * TttBoard as Data.
	 * @param theBoard		The board layout which will be stored by this Node.
	 * @param theParent		The TttNode in which this TttNode is stored.
	 */
	public TttNode(TttBoard theBoard, TttNode theParent) {
		data = theBoard;
		parent = theParent;
	}

	/**
	 * Sets the Parent Node for this Node.
	 * @param theParent		The TttNode in which this TttNode is stored.
	 */
	public void setParent(TttNode theParent) {
		parent = theParent;
	}

	/**
	 * Returns the parent node of this node. Enables you to search back up the tree 
	 * as well as down it.
	 * @return 				The node which is storing this Node.
	 */	
	public TttNode getParent() {
		return parent;
	}

	/**
	 * Sets the index position of this node in its parent node.
	 * @param theLoc		The index of this node in its parents array of children.
	 */
	public void setLocation(int theLoc) {
		location = theLoc;
	}

	/**
	 * Returns the index of this node in its parent node.
	 * @return				The index of this node in its parents array of children.
	 */
	public int getLocation(){
		return location;
	}

	/**
	 * Creates a new Child node to this Node. Takes the data given, 
	 * parsing it into a new node and then calls <code> addChild(TttNode) </code> 
	 * with the new TttNode.
	 * @param theBoard		The TttBoard which the new chiled Node will Store.
	 */
	public void addChild(TttBoard theBoard){
		addChild(new TttNode(theBoard));
	}

	/**
	 * Adds the given TttNode to the children of this TttNode. 
	 * @param TttNode 		The Node to add to the children of this Node.
	 */
	public void addChild(TttNode theNode){
		theNode.setParent(this);
		children[childNum] = theNode;
		childNum++;
	}

	/**
	 * Returns the child TttNode at the given index of this Nodes array of children.
	 * @param theIndex		The position of thr requested child Node.
	 * @return 					The child Node stored in the given position.
	 */
	public TttNode getChild(int theIndex){
		return children[theIndex];
	}
	
	/**
	 * Returns the number of children held by this node.
	 * @return 		The Number of Children held by this Node. Also represents the next 
	 * 				free location in the array of children.
	 */
	public int getChildNum() {
		return childNum;
	}
	
	/**
	 * Sets the data for this Node.
	 * @param theBoard 		The TttBoard which is to be held in this TttNode.
	 */
	public void setData(TttBoard theBoard){
		data = theBoard;
	}
	
	/**
	 * Returns the tttBoard stored by this TttNode.
	 * @return 		The TttBoard stored as the data of this TttNode.
	 */
	public TttBoard getData() {
		return data;
	}
	
	/**
	 * Sets the value of the board layout.
	 * @param theScore		The value of the Board for the current player of the 
	 * 							Board.
	 */
	public void setScore(int theScore) {
		data.setValue(theScore);
	}
	
	/**
	 * Returns the score of the TttBoard stored in this TttNode.
	 * @return 		The score of the TttBoard based on the currentPlayer of the Board.
	 */
	public int getScore() {
		return data.getValue();
	}
	
	
}