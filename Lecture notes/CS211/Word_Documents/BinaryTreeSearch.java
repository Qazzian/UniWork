

/*----------------------------------------------------------------------*/

// Program 8.31 Constructing and Searching a Binary Search Tree */

import java.io.*;
   
public class BinaryTreeSearch  {    
 
 // construct the binary search tree of Fig. 8.29, then search for ORD and DCA
 public static void main(String args[]) {
 
 // begin by constructing an empty binary search tree
    BinarySearchTree T = new BinarySearchTree(); // T is initially empty
   
 // then use the insertion method to insert the keys of Table 8.30
    T.insert("ORY");           // "ORY" is the key in the root of tree T
    T.insert("JFK");
    T.insert("BRU");              // insert the remaining keys into T in
    T.insert("DUS");                    // the order given by Table 8.30
    T.insert("ZRH");
    T.insert("MEX");
    T.insert("ORD");
    T.insert("NRT");
    T.insert("ARN");
    T.insert("GLA");
    T.insert("GCM");
      
 // print out the tree that was constructed
    System.out.println("The tree of Fig. 8.29 has been constructed and is:"); 
    T.print();
    System.out.println();
   
 // search for the node with key "ORD"
    System.out.println("We now search for the node with key \"ORD\"");
    TreeNode N = T.find("ORD");
    System.out.println("Key of node that was found = " + N.key);
    System.out.println();
   
 // now search for a key that is not in the tree
    System.out.println("We now search for the node with key \"DCA\"");
    TreeNode P = T.find("DCA");
    if (P != null) {
      System.out.println("Key of node that was found = " + P.key);
    } else {
      System.out.println("Node that was found = null");
    }
    System.out.println();
     
 }// end init()
 
}// end BinaryTreeSearch applet
 
/*-------------------------------------------------------------*/
 
class TreeNode {                            // define the TreeNode class
  ComparisonKey     key;
  TreeNode          llink;
  TreeNode          rlink;
}
 
/*-------------------------------------------------------------*/
class BinarySearchTree {
   
 // there is one private data field in a BinarySearchTree that holds a
 // pointer to the root node of the binary search tree (or holds null for
 // an empty tree)
    
    private TreeNode rootNode;
   
 // the various methods of the BinarySearchTree class follow:
  
  /*------------*/
 
     /** this private auxiliary method is used by the insert method */
 
     private TreeNode insertKey(TreeNode T, ComparisonKey K) {
       
       if (T == null) {
         TreeNode N = new TreeNode();            // construct a new TreeNode
         N.key = K;                                      // set its key to K
         return N;                                          // and return it
       } else {
         if ( K.compareTo(T.key) < 0 ) {   // if K is less than T's key then
           T.llink = insertKey(T.llink, K);  // insert K in T's left subtree
           return T;
         } else {
           T.rlink = insertKey(T.rlink, K);    // otherwise, insert K in T's
           return T;                                        // right subtree
         }
       }
       
     }//end insertKey()
 
  /*------------*/
  
     /** this method inserts a new node containing key K into the tree */
     
     void insert(ComparisonKey K) {
       
       // use the recursive auxiliary method insertKey 
       // to do the actual work of insertion
         rootNode = insertKey(rootNode,K);
       
     }//end insert()
  
  /*------------*/
  
     /** this is an overloaded version of insert() that takes a String argument */
  
     void insert(String K) {
         rootNode = insertKey(rootNode,new StringKey(K));
     }//end insert()
 
  /*------------*/
    
     /** the find() method returns a pointer to the TreeNode containing */
     /** key K. Otherwise, it returns null if K is not in the tree */
     
     TreeNode find(ComparisonKey K) {   
                                      
       TreeNode T = rootNode;
       int result;
       
       while (T != null) {
         if ((result = K.compareTo(T.key)) < 0) {
           T = T.llink;
         } else if (result == 0) {
           return T;
         } else {          
           T = T.rlink;
         }//end if
       }
       
       return T;                        // return null, if search failed
       
     }//end find()
  

  /*------------*/
     
     /** overloaded version of find() that accepts a String argument */
     
     TreeNode find(String K) {
       return find(new StringKey(K));
     }//end find()
  
    
  /*------------*/
    
     /** the private printNode() method is an auxiliary */
     /** recursive method used by the print() method */
     
     private void printNode(TreeNode N) {
       
       if (N != null) {                // do nothing if the node is null
         System.out.print("(");
         printNode(N.llink);
         System.out.print("  " + N.key + "  ");
         printNode(N.rlink);
         System.out.print(")");
       }
       
     }//end printNode
     
 
  /*------------*/
  
     /** the print() method prints a parenthesized version */
     /** of the tree showing the subtree structure */
     
     void print() {
       
       printNode(rootNode);
       System.out.println();
 
     }//end print()
 
   /*------------*/
   
}//end class BinarySearchTree
 
 
/*-------------------------------------------------------------*/
 
 
interface ComparisonKey {
   
 // if k1 and k2 are ComparisonKeys, k1.compareTo(k2) is
 // 0, +1, or -1 according as k1 == k2,  k1 > k2, or k1 < k2 in
 // the order defined by the compareTo method
   
    int compareTo(ComparisonKey value);
   
   
 // converts item to printable string
   
    String toString();   
 
}//end interface
 
/*-------------------------------------------------------------*/
 
 
class StringKey implements ComparisonKey {
     
   // the key data field holds the String that is the value of the key
     
      private String key;
     
  /*-----------------*/
     
   // the single String-argument constructor sets the key to its argument
     
      StringKey(String value) {
        key = value; 
      }
     
  /*-----------------*/
     
   // the toString() method converts a StringKey into a string
     
      public String toString() {
        return key;  
      }
 
  /*-----------------*/
   
   // the k1.compareTo(k2) method is a three-way comparison of two
   // keys, k1 and k2, that returns 0, 1, or -1 when k1 == k2, k1 > k2,
   // or k1 < k2, respectively
     
      public int compareTo(ComparisonKey value) {
        String a = this.key;
        String b = ((StringKey)value).key;
        return a.compareTo(b);    // uses the inherited compareTo method
      }                              // defined already for Java Strings
     
  /*-----------------*/
   
}//end StringKey class
 
 
/*-------------------------------------------------------------*/


