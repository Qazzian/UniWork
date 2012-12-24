//package swing.browser;

/*
 * Import both the AWT package and the swing package
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
// As we are working with trees also need to import 
// the tree package. Note JTree is in the swing package.
import javax.swing.tree.*;
import java.util.*;

/**
 * This class provides a simple graphical browser for viewing 
 * class inheritance hierarchies.
 * @author John Hunt
 * @version 1.0
 */
public class Browser extends JFrame {
   private DefaultMutableTreeNode root;
   private String classname;
   private Class classObject;
   private JTree tree;
   private JScrollPane treeView;
   
   /**
    * Used to initiate the execution of the application
    */
   public static void main (String args []) {
      if (args.length == 0) 
         System.out.println("Usage: java Browser <fully qualified class name>");
      else
         new Browser(args[0]);
   }

   /**
    * Constructor used to create an instance of a Browser on a particular
    * class. For example:
    * <p>
    * new Browser("java.awt.Frame");
    * <p>
    * @param classname The class for which the inheritance hierarchy 
    * is to be displayed.
    */ 
   public Browser(String classname) {
      super("Browser on : " + classname);
      
      // Create the class hierachy for classname
      setClass(classname);
      
      // Get the content pane
      Container pane = getContentPane();
     
      // Handle window events
      addWindowListener(new WindowHandler());
      
      //-------------------------------------
      // Create a tool bar
      //-------------------------------------
      JToolBar toolBar = new JToolBar();
      
      // Instantiate the handler for the toolbar buttons
      ActionHandler handler = new ActionHandler(this);
      
      // Define the new buttons
      ImageIcon buttonIcon = new ImageIcon("load.gif");
      JButton button = new JButton(buttonIcon);
      button.setToolTipText("New class hierarchy");
      button.addActionListener(handler);
      toolBar.add(button);
      
      // Now create the exit button
      buttonIcon = new ImageIcon("exit.gif");
      button =new JButton(buttonIcon);
      button.setToolTipText("Exit browser");
      button.setActionCommand("Exit");
      button.addActionListener(handler);
      toolBar.add(button);
      
      // Add the tool bar to the window
      // using the content pane.
      pane.add(toolBar, BorderLayout.NORTH);

      // Set the look and feel to the windows look and feel 
      if (!UIManager.getLookAndFeel().getName().equals("Windows")) {
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);      
         } catch (Exception exc) {
            System.err.println("could not load Windows LookAndFeel");
         }
      } 
      
      // Set the size of the window and make it visible
      pack();
      setVisible(true);
      
   }

   /**
    * Used to create the tree to be displayed by the
    * JTree GUI component.
    *
    * @return DefaultMutableNode root of tree
    */
   private DefaultMutableTreeNode createTreeNodes() {
       Vector names = new Vector(10,10);    // Holds list of classes in reverse order
       DefaultMutableTreeNode root = null;   // Root of tree 
       
       // Use reflection to obtain the class object for the
       // specified classname. Using this object obtain the 
       // superclass. Repeat this process until the class Object
       // has been found - all classes inherit from Object in Java.       
       try {
          classObject = Class.forName(classname);
          Class superClass = classObject.getSuperclass();
          String superclassname = superClass.getName();
          names.addElement(superclassname);   
                 
          while (!superclassname.equals("java.lang.Object")) {
             superClass = superClass.getSuperclass();        
             superclassname = superClass.getName();          
             names.addElement(superclassname);             
          }
          
          names.insertElementAt(classname, 0);
          names.trimToSize();
          
          // the vector of class names will be converted into 
          // a tree data structure - note single inheritance means a
          // single branched tree.
          root = TreeGenerator.createDataModel(names);
 
       
       } catch (ClassNotFoundException e) {System.out.println("Error: Class not found");}
       
       return root;
   }
   
   
   /**
    * Takes a string representing the class to generate the hierarchy for and 
    * constructs a tree model which is placed in a JTree GUI component which 
    * is added to the windows content pane.
    *
    * @param classname Name of class to display
    */
   protected void setClass(String classname) {
      this.classname = classname;
      
      // Get the widnows content frame
      Container container = getContentPane();
      
      // If a tree is currently being displayed, remove it from the window
      if (treeView != null)
         container.remove(treeView);
      //Create the nodes.
      root = createTreeNodes();
      tree = new JTree(root);
      tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
   
      //Create the scroll pane and add the tree to it. 
      treeView = new JScrollPane(tree);
         
      // Add tree view to the content pane
      container.add(treeView);
      
      // Change title at top of window
      setTitle("Browser on : " + classname);
   }
}
   
/**
 * Helper class used to handle window events
 */
 class WindowHandler extends WindowAdapter {
    public void windowClosing(WindowEvent e) {System.exit(0);}
}   

/**
 * Provides the listener for the tool bar in the Browser class
 */
class ActionHandler implements ActionListener {
   Browser frame;
   /**
    * Constructor takes a reference to the Browser object so that it can
    * reference this object when a new class is to be displayed
    */
   public ActionHandler(Browser f) {
      frame = f;
   }
   public void actionPerformed(ActionEvent event) {
      String cmd = event.getActionCommand();
      // Select the action to perform depending on the button pressed
      if (cmd.equals("Exit")) {
         // An example of using an JOptionPane
         int response = JOptionPane.showConfirmDialog(frame, 
                                                      "Do you wish to exit", 
                                                      "Confirm Exit", 
                                                      JOptionPane.YES_NO_OPTION);
         if (response == JOptionPane.YES_OPTION) {
             System.exit(0);
         }
      } else {
         // Use a CLassRequestDialog to obtain the name of the
         // new class to display. Use the setClass method of Browser
         // to change the class being displayed and repack the window.
         ClassRequestDialog crd = new ClassRequestDialog(frame);
         crd.setVisible(true);
         String newclass = crd.getText();
                          
         // Check to see if the cancel button was selected (returns null)
         if (!newclass.equals("")) {
            frame.setClass(newclass);
            frame.pack();
         }
      }
   }

}

