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


    public boolean isEmpty() {
        return (this.root == null);
    }

    public boolean contains(int item) {
        // provided as an example
        if (this.isEmpty()) {
            return false;
        } else if (item == this.root) {
            return true;
        } else if (item < this.root) {
            return this.left.contains(item);
        }
        return this.right.contains(item);

    }


    public void insert(int item) {
        /*Insert <item> into this tree.
        */
        if (this.isEmpty())
        {
            // make new leaf
            this.root = item;
            this.left= new BST();
            this.right=new BST();
        } else if (item <= this.root) {
            this.left.insert(item);
        }
        else {
            this.right.insert(item);
        }
    }


    public void delete(int item) {
        /*Remove *one* occurrence of <item> from this BST.

        Do nothing if <item> is not in the BST.
        */
        if (this.isEmpty()) {
            return;
        }
        else if (item == this.root) {
            this.deleteRoot();
        }
        else if (item < this.root) {
            this.left.delete(item);
        }
        else {
            this.right.delete(item);
        }
    }

    private void deleteRoot() {
        /* Remove the root of this tree.

        Precondition: this tree is *non-empty*.

         */
        if (this.left.isEmpty() && this.right.isEmpty()){
            this.root = null;
            this.right = null;
            this.left = null;
        }
        else if (this.left.isEmpty()){
            // Promote right subtree
            this.root = this.right.root;
            this.right = this.right.right;
            this.left = this.right.left;
        }
        else if (this.right.isEmpty()){
            // Promote left subtree
            this.root = this.left.root;
            this.left = this.left.left;
            this.right = this.left.right;
        }
        else{
            // Both subtrees are non empty
            this.root = this.left.extractMax();
        }

    }


    private int extractMax() {
        /* Remove and return the maximum item stored in this tree.

        Precondition: this tree is *non-empty*.

         */

        if (this.right.isEmpty()){
            int max_item = this.root;
            // Promote left subtree
            this.root = this.left.root;
            this.left = this.left.left;
            this.right = this.left.right;
            return max_item;
        }
        else
            return this.right.extractMax();
    }

    public int height() {
        /* Return the height of this BST

         */
        if (this.isEmpty()) {
            return 0;
        }
        else {
            return Math.max(this.left.height(), this.right.height()) + 1;
        }
    }

    public int count(int item) {
        /* Return the number of occurences of <item> in BST

         */
        if (this.isEmpty()) {
            return 0;
        }
        else if (item < this.root) {
            return this.left.count(item);
        } else if (item == this.root) {
            return 1+ this.right.count(item) + this.left.count(item);
        }
        else{
            return this.right.count(item);
        }
    }

    public int getSize() {
        /* Return number of items in this BST

         */
        if (this.isEmpty()) {
            return 0;
        }
        else {
            return 1 + this.left.getSize() + this.right.getSize();
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
