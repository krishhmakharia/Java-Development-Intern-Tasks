
class BinarySearchTree {
    Node root;

    // Constructor
    BinarySearchTree() {
        root = null;
    }

    // Insert a new key
    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        // If tree is empty, create a new node
        if (root == null) {
            root = new Node(key);
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root;
    }

    // Search a key
    boolean search(int key) {
        return searchRec(root, key);
    }

    boolean searchRec(Node root, int key) {
        if (root == null) {
            return false;
        }
        if (root.key == key) {
            return true;
        }
        return key < root.key
                ? searchRec(root.left, key)
                : searchRec(root.right, key);
    }

    // Delete a key
    void delete(int key) {
        root = deleteRec(root, key);
    }

    Node deleteRec(Node root, int key) {
        if (root == null) return root;

        // Traverse tree
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        }
        // Found the node to delete
        else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Node with two children
            // Get inorder
            root.key = minValue(root.right);

            // Delete the inorder
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        int minVal = root.key;
        while (root.left != null) {
            minVal = root.left.key;
            root = root.left;
        }
        return minVal;
    }

    // In-order traversal
    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Pre-order traversal
    void preorder() {
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Post-order traversal
    void postorder() {
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    // Driver code
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.print("In-order traversal: ");
        bst.inorder();

        System.out.print("Pre-order traversal: ");
        bst.preorder();

        System.out.print("Post-order traversal: ");
        bst.postorder();

        System.out.println("Search 40: " + bst.search(40));
        System.out.println("Search 90: " + bst.search(90));

        bst.delete(20);
        System.out.print("In-order after deleting 20: ");
        bst.inorder();

        bst.delete(30);
        System.out.print("In-order after deleting 30: ");
        bst.inorder();

        bst.delete(50);
        System.out.print("In-order after deleting 50: ");
        bst.inorder();
    }
}
