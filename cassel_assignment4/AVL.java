package cp213;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Cassel Robson
 * @author David Brown
 * @version 2022-11-20
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

    /**
     * Returns the balance item of node. If greater than 1, then left heavy, if less
     * than -1, then right heavy. If in the range -1 to 1 inclusive, the node is
     * balanced. Used to determine whether to rotate a node upon insertion.
     *
     * @param node The TreeNode to analyze for balance.
     * @return A balance number.
     */
    private int balance(final TreeNode<T> node) {

	return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());
    }

    /**
     * Performs a left rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> node) {
	if (!(node.getRight() == null)) {
	    final TreeNode<T> oldRight = node.getRight();
	    node.setRight(oldRight.getLeft());
	    oldRight.setLeft(node);
	    node.updateHeight();
	    oldRight.updateHeight();
	    return oldRight;
	}
	return null;
    }

    /**
     * Performs a right rotation around node.
     *
     * @param node The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> node) {

	final TreeNode<T> oldLeft = node.getLeft();
	node.setLeft(oldLeft.getRight());
	oldLeft.setRight(node);
	node.updateHeight();

	return oldLeft;
    }

    /**
     * Auxiliary method for insert. Inserts data into this AVL.
     *
     * @param node The current node (TreeNode).
     * @param cs   Data to be inserted into the node.
     * @return The inserted node.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedStore<T> cs) {

	int balance = 0;

	if (node == null) {
	    node = new TreeNode<T>(cs);
	    node.getCs().incrementCount();

	} else {
	    final int result = node.getCs().compareTo(cs);

	    if (result > 0) {
		node.setLeft(this.insertAux(node.getLeft(), cs));
		node.updateHeight();
		balance = this.balance(node);

		if (balance > 1 && this.balance(node.getLeft()) >= 0) {
		    node = this.rotateRight(node);
		} else if (balance > 1 && this.balance(node.getLeft()) > 0) {
		    node.setLeft(this.rotateLeft(node.getLeft()));
		    node = this.rotateLeft(node);
		}
	    } else if (result < 0) {
		node.setRight(this.insertAux(node.getRight(), cs));
		node.updateHeight();
		balance = this.balance(node);
		if (balance < -1 && this.balance(node.getRight()) <= 0) {
		    node = this.rotateLeft(node);

		} else if (balance < -1 && this.balance(node.getRight()) > 0) {
		    node.setRight(this.rotateRight(node.getRight()));
		    node = this.rotateLeft(node);
		}
	    } else {
		node.getCs().incrementCount();
	    }
	}
	return node;
    }

    /**
     * Auxiliary method for valid. Determines if a subtree based on node is a valid
     * subtree. An AVL must meet the BST validation conditions, and additionally be
     * balanced in all its subtrees - i.e. the difference in height between any two
     * children must be no greater than 1.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, TreeNode<T> minNode, TreeNode<T> maxNode) {

	boolean valid = false;

	if (node == null) {
	    valid = true;
	} else if (Math.max(this.nodeHeight(node.getLeft()), this.nodeHeight(node.getRight())) != node.getHeight()
		- 1) {
	    valid = false;
	} else if (node.getLeft() != null && node.getLeft().getCs().compareTo(node.getCs()) >= 0
		|| node.getRight() != null && node.getRight().getCs().compareTo(node.getCs()) <= 0) {
	    valid = false;
	} else if (Math.abs(this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight())) > 1) {
	    valid = false;
	} else {
	    valid = this.isValidAux(node.getLeft(), minNode, maxNode)
		    && this.isValidAux(node.getRight(), minNode, maxNode);
	}

	return valid;
    }

    /**
     * Determines whether two AVLs are identical.
     *
     * @param target The AVL to compare this AVL against.
     * @return true if this AVL and target contain nodes that match in position,
     *         item, count, and height, false otherwise.
     */
    public boolean equals(final AVL<T> target) {
	return super.equals(target);
    }

}
