public class BinarySearchTree
{
	private TreeNode root;

/* These are the iterative versions of insert and remove

   public void insert(Comparable data)
   {
   	// Implement insert here!!!

      // Special case - the tree  is empty
      if (isEmpty ())
      {
        root = new TreeNode (data);
      }
      else
      {
        // General case
        TreeNode cursor = root;
        TreeNode newNode = new TreeNode (data);
        while (true)
        {
          if (data.compareTo (cursor.getData ()) <= 0)
          {
            // go left
            if (cursor.getLeft () == null)
            {
              cursor.setLeft (newNode);
              break;
            }
            cursor = cursor.getLeft ();
          }
          else
          {
            // go right
            if (cursor.getRight () == null)
            {
              cursor.setRight (newNode);
              break;
            }
            cursor = cursor.getRight ();
          }

        }
      }
        
   }

	public boolean remove(Comparable element)
	{
   	TreeNode cursor = root;
      TreeNode parentOfCursor = null;
      boolean isLeft = true;

      while (cursor != null)
      {
      	int compared = element.compareTo(cursor.getData());

         if (compared == 0)
			{
         	// Case where cursor is at a leaf node
            if ( (cursor.getLeft() == null) && (cursor.getRight() == null) )
            {
            	if (parentOfCursor == null)
               	root = null;
               else
               {
               	if (isLeft)
                  	parentOfCursor.setLeft(null);
                  else
                  	parentOfCursor.setRight(null);
               }
            }

            // Case where cursor has only one child, on the right
				else if (cursor.getLeft() == null)
            {
            	if (parentOfCursor == null)
               	root = root.getRight();
               else
               {
               	if (isLeft)
                  	parentOfCursor.setLeft(cursor.getRight());
                  else
                  	parentOfCursor.setRight(cursor.getRight());
               }
            }

            // Case where cursor has only one child, on the left
				else if (cursor.getRight() == null)
            {
            	if (parentOfCursor == null)
               	root = root.getLeft();
               else
               {
               	if (isLeft)
                  	parentOfCursor.setLeft(cursor.getLeft());
                  else
                  	parentOfCursor.setRight(cursor.getLeft());
               }
            }

            else
            {
            	TreeNode leftSubTree = cursor.getLeft();
               cursor = cursor.getRight();
               if (isLeft)
               	parentOfCursor.setLeft(cursor);
               else
               	parentOfCursor.setRight(cursor);

               while (cursor.getLeft() != null)
               	cursor = cursor.getLeft();

					cursor.setLeft(leftSubTree);
            }

            return true;
         }

         else
         {
         	parentOfCursor = cursor;
				if (compared < 0)
            {
            	isLeft = true;
	         	cursor = cursor.getLeft();
            }
	         else
            {
            	isLeft = false;
	         	cursor = cursor.getRight();
            }
         }
      }

      // If cursor has become null, we've falled off the end of the tree
      return false;
   }
*/

	// And now for the recursive versions of insert and remove

   public void insert(Comparable data)
   {
		root = insert(data, root);
   }

   private TreeNode insert(Comparable data, TreeNode cursor)
   {
   	if (cursor == null)
      {
      	cursor = new TreeNode(data);
		}
      else
      {
			if (data.compareTo(cursor.getData()) <= 0)
         	cursor.setLeft(insert(data, cursor.getLeft()));
         else
         	cursor.setRight(insert(data, cursor.getRight()));
      }

      return cursor;
   }

   public boolean remove(Comparable data)
   {
   	try
      {
       	root = remove (data, root);
         return true;
      }
      catch (java.util.NoSuchElementException e)
      {
       	return false;
      }
   }

   // method to find the minimum node in a tree
   // assume cursor non-null
   private TreeNode findMin (TreeNode cursor)
   {
   	while (cursor.getLeft ( ) != null)
      {
       	cursor = cursor.getLeft ( );
      }
      return cursor;
   }

   // recursive remove method
   private TreeNode remove (Comparable data, TreeNode cursor)
   	throws java.util.NoSuchElementException
   {
    	if (cursor == null)
      {
       	throw new java.util.NoSuchElementException ( );
      }

      if (data.compareTo (cursor.getData ()) == 0)
      {
	      // found the element to be removed - remove it
         if (cursor.getLeft ( ) == null)
	      {
	       	cursor = cursor.getRight ( );
	      }
	      else if (cursor.getRight ( ) == null)
	      {
	       	cursor = cursor.getLeft ( );
	      }
	      else
	      {
          	TreeNode minimum = findMin (cursor.getRight ( ));
            minimum.setLeft (cursor.getLeft ( ));
            cursor = cursor.getRight ( );
	      }
      }
      else if (data.compareTo (cursor.getData ()) < 0)
      {
       	// go left
         cursor.setLeft (remove (data, cursor.getLeft ( )));
      }
      else
      {
       	// go right
         cursor.setRight (remove (data, cursor.getRight ( )));
      }

      return cursor;
   }

	public boolean isEmpty()
   {
		return root == null;
   }

   // Display the tree in order (left, root, right)

   public void inOrder()
   {
		inOrder(root);
   }

   private void inOrder(TreeNode cursor)
   {
		if (cursor != null)
      {
			inOrder(cursor.getLeft());
         System.out.println(cursor.getData());
			inOrder(cursor.getRight());
		}
   }

   // Display the tree "pre-order"

   public void preOrder()
   {
		preOrder(root);
   }

   private void preOrder(TreeNode cursor)
   {
		if (cursor != null)
      {
         System.out.println(cursor.getData());
			preOrder(cursor.getLeft());
			preOrder(cursor.getRight());
		}
   }

   // Display the tree "post-order"

   public void postOrder()
   {
		postOrder(root);
   }

   private void postOrder(TreeNode cursor)
   {
		if (cursor != null)
      {
			postOrder(cursor.getLeft());
			postOrder(cursor.getRight());
         System.out.println(cursor.getData());
		}
   }
}