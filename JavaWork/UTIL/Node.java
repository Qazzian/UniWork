/**
 * A Node is used within Linked Data structures. A Linked Data 
 * structure should contain at least a top level Node and posibly end 
 * Nodes or midlevel Nodes depending on the structure. Each Node 
 * contains links to the Next Node in the Structure and to previouse 
 * Nodes. 
 * At the moment it is only posible to use this Node in a Linear
 * structure but I am working on a structure which will be able to 
 * link to multiple next and Previouse Nodes
 */
public class Node{

    // /////////////////// //
    // Instance variables. //
    // /////////////////// //
    
    /** The next Node in the List **/
    protected Node next = null;
    /** the Previous Node in the List **/
    protected Node prev = null;
    /** The data Object stored in the Node **/
    protected Object data   = null;
    
    // //////////// //
    // Constructors //
    // //////////// //
    
    /**
     * Creates a new Empty Node with no Links.
     */
    public Node(){
    }
    
    /**
     * Creates a List node with the set data but no Links.
     * @param Object obj The data to be set to the node.
     */
    public Node(Object obj){
        if(obj != null){
            data = obj;
            next = prev = null;
        }
    }
    
    // /////// //
    // Methods //
    // /////// //
    
    public void setData(Object obj){
        data = obj;
    }
    
    public Object getData(){
        return data;
    }
    
    public void setNext(Node nextNode){
        next = nextNode;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setPrev(Node prevNode){
        prev = prevNode;
    }
    
    public Node getPrev(){
        return prev;
    }
    
    /**
     * Calls the toString method of the object in `data'
     * @return String The a String representation of the object in `data'
     */
    public String toString(){
        String tmpS = "The Object contained in this Node is;\n " +
            data.toString();
        return tmpS;
    }
}