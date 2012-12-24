//package swing.browser;

// As we are working with trees need to import 
// the tree package. Note JTree is in the swing package.
import javax.swing.tree.*;
import java.util.*;

public class TreeGenerator {

  /**
    * This method converts a vector into a tree data model.
    * @param vector of contents of tree
    * @returns DefaultmutableTreeNode
    */
    public static DefaultMutableTreeNode createDataModel(Vector tree) {
       DefaultMutableTreeNode top, last, current = null;   // Root of tree 
                  
       // Get the node at the top of the tree
       top = new DefaultMutableTreeNode((String)tree.lastElement());
       tree.removeElement(tree.lastElement());
       last = top;
          
       // Process vector of strings in reverse order
       // adding each node to the tree 
          for (int i = tree.size(); i > 0; i--) {
            Object element = tree.elementAt(i - 1);
            if (element instanceof String) {   
               current = new DefaultMutableTreeNode(element);
            } else {
               current = createDataModel((Vector)element);
            }
            last.add(current);
            last = current;   
          }      
          return top;
    }
    
}

