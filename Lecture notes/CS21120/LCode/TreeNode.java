public class TreeNode
{
	private Comparable data;
   private TreeNode left;
   private TreeNode right;

   public TreeNode(Comparable data)
   {
		this.data = data;
   }

   public TreeNode(Comparable data, TreeNode left, TreeNode right)
   {
		this.data = data;
		this.left = left;
		this.right = right;
   }

   public void setData(Comparable data)
   {
   	this.data = data;
   }

   public void setLeft(TreeNode left)
   {
   	this.left = left;
   }

   public void setRight(TreeNode right)
   {
   	this.right = right;
   }

   public Comparable getData()
   {
   	return data;
   }

   public TreeNode getLeft()
   {
   	return left;
   }

   public TreeNode getRight()
   {
   	return right;
   }
}