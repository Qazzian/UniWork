import java.io.*;

/**
 * A tree specific for searching a TicTacToe game.
 * Contains an inner class definition for the Nodes.
 *
 * @see TttBoard
 */
public class TttTree implements Serializable{

	private TttNode root;
	private TttNode currentParent = root;
	private TttNode currentChild = root;
	


   /**
    * Creates a new TttTree without any Nodes in it.
    */
	public TttTree(){
	}

   /**
    * Creates a new TttTree with the given Node as the root.
    * @param theRoot The TttNode to set as the Root for the new Tree.
    */
	public TttTree(TttNode theRoot){
		root = theRoot;
	}

   /**
    * Creates a new TttTree with the given TttBoard as part of the root node.
    * @param theRoot TheTicTacToe Board to put in the root node.
	 */
	public TttTree(TttBoard theRoot){
		root = new TttNode(theRoot);
	}

   /**
    * Sets the root Node to the given TttNode.
    * Also sets currentParent and currentChild to the root Node.
    * @param theRoot The new Node to set as the root of this tree.
    */
	public void setRoot(TttNode theRoot){
		root = theRoot;
	}

	/**
    * Sets the root of the tree to a node with the given TttBoard.
    * Also resets the current parent and child Nodes to the root.
    * @param theRoot The new root node for the tree.
    */
	public void setRoot(TttBoard theRoot){
		root = new TttNode(theRoot);
	}
	
	public TttNode getRoot() {
		return root;
	}

   /**
    * Returns the Next child along in the currentParent node.
    * Sets currentChild to the returned Node.
    * @return the next child along in currentParent.
    * @exception java.lang.NullPointerException If there are no more children in
    * currentParent.
    */
	public TttNode getNextSibling() throws java.lang.NullPointerException{
			 // Find the index of the next node
		int nextLoc = currentChild.getLocation() + 1;
			// Check that the location is valid, else throw null pointer exception
		if( currentParent.getChild(nextLoc) == null ) {
			throw new NullPointerException
			("There are no more children in the currentParent");
		}
			// Get the next child and return it.
		currentChild = currentParent.getChild(nextLoc);
		return currentChild;
	}

   /**
    * Returns the first child of the current child.
    * Also sets currentParent to currentChild and then currentChild to the 
	 * returend TttNode.
    * @return The first child TttNode of the currentChild.
    * @exception java.lang.NullPointerException If the currentChild has no children.
    */
	public TttNode getNextGeneration() throws java.lang.NullPointerException{
		if(currentChild.getChild(0) == null) {
			throw new NullPointerException("No More Generations");
		}
		currentParent = currentChild;
		currentChild = currentParent.getChild(0);
		return currentChild;
	}
}