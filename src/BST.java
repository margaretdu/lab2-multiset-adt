/**
 * A minimal implementation of a binary search tree. See the python version for
 * additional documentation.
 * You can also see <a href="https://www.teach.cs.toronto.edu/~csc148h/notes/binary-search-trees/bst_implementation.html">
 *     CSC148 Course Notes Section 8.5 BST Implementation and Search</a>
 * if you want a refresher on BSTs, but it is not required to complete this assignment.
 */
public class BST {
    // we use Integer here so that we can set the root to null. This is the same idea as
    // how the Python code uses None in the BST implementation.
    private Integer root;

    private BST left;
    private BST right;

    public BST(int root) {
        this.root = root;
        this.left = new BST();
        this.right = new BST();
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        root = null;
        // left and right default to being null
    }

    /** Return whether this BST is empty. */
    public boolean isEmpty() {
        return root == null;
    }

    /** Return whether {@code item} is in this BST. */
    public boolean contains(int item) {
        // provided as an example
        if (this.isEmpty()) {
            return false;
        } else if (item == root) {
            return true;
        } else if (item < root) {
            return left.contains(item);
        }
        return right.contains(item);

    }

    /** Insert {@code item} into this tree. */
    public void insert(int item) {
        if (this.isEmpty()) {
            // Make new leaf
            root = item;
            left = new BST();
            right = new BST();
        } else if (item <= root){
            left.insert(item);
        } else {
            right.insert(item);
        }
    }

    /**
     * Remove *one* occurrence of {@code item} from this BST.
     * Do nothing if {@code item} is not in the BST. */
    public void delete(int item) {
        if (!this.isEmpty()) {
            if (root == item) {
                this.deleteRoot();
            } else if (item < root) {
                left.delete(item);
            } else {
                right.delete(item);
            }
        }
    }

    /**
     * Remove the root of this tree.
     * <p>
     * Precondition: this tree is *non-empty*. */
    private void deleteRoot() {
        if (left.isEmpty() && right.isEmpty()) {
            root = null;
            left = null;
            right = null;
        } else if (left.isEmpty()) {
            // "Promote" the right subtree.
            root = right.root;
            left = right.left;
            right = right.right;
        } else if (right.isEmpty()) {
            // "Promote" the left subtree
            root = left.root;
            right = left.right;
            left = left.left;
        } else {
            // Both subtrees are non-empty. Can choose to replace the root
            // from either the max value of the left subtree, or the min value
            // of the right subtree.
            root = left.extractMax();
        }
    }

    /**
     * Remove and return the maximum item stored in this tree.
     * <p>
     * Precondition: this tree is *non-empty*. */
    private int extractMax() {
        if (right.isEmpty()) {
            Integer max_item = root;
            // "Promote" the left subtree
            root = left.root;
            right = left.right;
            left = left.left;
            return max_item;
        } else {
            return right.extractMax();
        }
    }

    /** Return the height of this BST. */
    public int height() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return Math.max(left.height(), right.height()) + 1;
        }
    }


    /** Return the number of occurrences of {@code item} in this BST. */
    public int count(int item) {
        if (this.isEmpty()) {
            return 0;
        } else if (root > item) {
            return left.count(item);
        } else if (root.equals(item)) {
            return 1 + left.count(item) + right.count(item);
        } else {
            return right.count(item);
        }
    }

    /** Return the number of items in this BST. */
    public int getSize() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return 1 + left.getSize() + right.getSize();
        }
    }

    public static void main(String[] args) {
        // You can also add code here to do some basic testing if you want,
        // but make sure it doesn't contain syntax errors
        // or else we won't be able to run your code on MarkUs since the file won't
        // compile. Always make sure to run the self tests on MarkUs after you update your code.
        BST bst = new BST();
        int a = 1;
        bst.insert(a);
        System.out.println(bst.contains(a));
    }

}
