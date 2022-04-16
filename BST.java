/***
 * Class to define to BST
 * @author Gabrielle Dias
 * @version 0.1
 * Date of creation: April 15, 2022
 * Last Date Modified: April 15, 2022
 */


/***
 * check if input BST is a valid input
 * @author gabrielledias
 *
 * @param <E> for the E you are searching for
 */
public class BST<E extends Comparable<E>> { // O(n)
	private TreeNode root;
	private int size;
	private class TreeNode{
		E value;
		TreeNode left;
		TreeNode right;
		TreeNode(E val){
			value = val;
			left = right = null;
		}
	}

	BST(){
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public void clear() {
		root = null;
	}

	/***
	 * check if input contains is a valid input
	 * @param value for the value you are searching for
	 * @return whether the contains input is valid or no
	 */
	// Search method
	public int contains(E value) { // O(n) 
		int iterations = 0;
		TreeNode node = root;
		while (node != null) {
			iterations++;
			if( value.compareTo(node.value) < 0)
				node = node.left;
			else if (value.compareTo(node.value)> 0)
				node = node.right;
			else
				return iterations;;
		}
		return iterations;
	}

	/***
	 * check if input add is a valid input
	 * @param value for the value you are searching for
	 * @return whether the add input is valid or no
	 */
	// Method add()
	public int add(E value) { // O(n)
		int iterations = 0;
		if (root == null) // first node to be inserted
			root = new TreeNode(value);
		else {
			TreeNode parent, node;
			parent = null; node = root;
			while (node != null) {// Looking for a leaf node
				iterations++;
				parent = node;
				if(value.compareTo(node.value) < 0) {
					node = node.left; }
				else if (value.compareTo(node.value) > 0) {
					node = node.right; }
				else
					return iterations; // duplicates are not allowed
			}
			if (value.compareTo(parent.value)< 0)
				parent.left = new TreeNode(value);
			else
				parent.right = new TreeNode(value);
		}
		size++;
		return iterations;
	}

	/***
	 * check if input remove is a valid input
	 * @param value for the value you are searching for
	 * @return whether the remove input is valid or no
	 */
	// Method remove()
	public int remove(E value) { // O(n)
		int iterations = 0;
		TreeNode parent, node;
		parent = null; node = root;
		// Find value first
		while (node != null) {
			iterations++;
			if (value.compareTo(node.value) < 0) {
				parent = node;
				node = node.left;
			}
			else if (value.compareTo(node.value) > 0) {
				parent = node;
				node = node.right;
			}
			else
				break; // value found
		}

		if (node == null) // value not in the tree
			return iterations;
		// Case 1: node has no children
		if(node.left == null && node.right == null){
			if(parent == null){ // delete root
				root = null;
			}
			else{
				changeChild(parent, node, null);
			}
		}

		// case 2: node has one right child
		else if(node.left == null){
			if (parent == null){ // delete root
				root = node.right;
			}
			else{
				changeChild(parent, node, node.right);
			}
		}
		// case 2: node has one left child
		else if(node.right == null){
			if (parent == null){ // delete root
				root = node.left;
			}
			else{
				changeChild(parent, node, node.left);
			}
		}

		// case 3: node has two children
		else {
			TreeNode rightMostParent = node;
			TreeNode rightMost = node.left;
			// go right on the left subtree
			while (rightMost.right != null) {
				iterations++;
				rightMostParent = rightMost;
				rightMost = rightMost.right;
			}
			// copy the value of rigthMost to node
			node.value = rightMost.value;
			//delete rigthMost
			changeChild(rightMostParent, rightMost,
					rightMost.left);
		}
		size--;
		return iterations;
	}

	/***
	 * check if input change child is a valid input
	 * @param parent for the parent you are searching for
	 * @param node for the node you are searching for
	 * @param newChild for the new child you are searching for
	 */
	private void changeChild(TreeNode parent,
			TreeNode node, TreeNode newChild){
		if(parent.left == node)
			parent.left = newChild;
		else
			parent.right = newChild;
	}

	/***
	 * check if input inorder is a valid input
	 */
	// Recursive method inorder()
	public void inorder() { // O(n)
		inorder(root);
	}
	private void inorder(TreeNode node) {
		if (node != null) {
			inorder(node.left);
			System.out.print(node.value + " ");
			inorder(node.right);
		}
	}

	/***
	 * check if input preorder is a valid input
	 */
	// Recursive method preorder()
	public void preorder() { // O(n)
		preorder(root);
	}
	private void preorder(TreeNode node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}

	/***
	 * check if input postorder is a valid input
	 */
	// Recursive method postorder()
	public void postorder() { // O(n)
		postorder(root);
	}
	private void postorder(TreeNode node) {
		if (node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.value + " ");
		}
	}
	public int height() {
		return height(root);
		
	}
	
	/***
	 * check if input height is a valid input
	 * @param node for the node you are searching for
	 * @return whether the height input is valid or no
	 */
	// Recursive Helper Method
	public int height(TreeNode node) {
		if(node == null)
			return 0;
		else {
			int hLeft = height(node.left);
			int hRight = height(node.right);
			return 1 + Math.max(hLeft, hRight);
		}	
	}
	
	/***check if input is balanced is a valid input
	 * 
	 * @return whether the is balanced input is valid or no
	 */
	public boolean isBalanced() {
		return isBalanced(root);
	}
	// Recursive Helper Method
	public boolean isBalanced(TreeNode node) { // O(n)
		if(node == null)
			return true;
		else {
			int hLeft = height(node.left);
			int hRight = height(node.right);
			if(Math.abs(hLeft - hRight) > 1) {
				return false;
			}
			return isBalanced(node.left) && isBalanced(node.right);
		}
}
}
