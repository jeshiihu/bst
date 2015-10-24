
public class bst {
	public node root;
	
	public bst() { // create an empty tree
		root = null;
	}

	public boolean search(int key) {
		node current = root;
		while(current != null) {
			if(current.key == key) return true;
			else if(current.key > key) current = current.left;
			else current = current.right;
		}
		return false;
	}
	
	void insert(int key) {
		if(root == null) root = new node(key); // when tree is empty
		else insert(root, key);
	}
	private void insert(node n, int key) {
		if(n.key == key) return; // duplicate
		node insertNode = new node(key);
		
		if(insertNode.key < n.key) {
			if(n.left == null) {
				n.left = insertNode;
				insertNode.parent = n;
			} else insert(n.left, key);
		}
		if(insertNode.key > n.key) {
			if(n.right == null) {
				n.right = insertNode;
				insertNode.parent = n;
			} else insert(n.right, key);
		}
	}
	
	void isEmpty() {
		if (root == null) System.out.println("BST is empty.");
	}
	void clearTree(){
		root = null;
	}
	void printTree(){
		System.out.println("---Printing Tree---");
		if(root == null) System.out.println("  BST is empty");
		else printTree(root); 
	}
	private void printTree(node n){
		if(n.left != null) printTree(n.left);
		if(n==root) System.out.println("  Node:"+n.key + ", Parent:null");
		else {
			if(n.parent.left == n) System.out.println("  Node:"+n.key + ", Parent:left of " + n.parent.key);
			if(n.parent.right == n) System.out.println("  Node:"+n.key + ", Parent:right of " + n.parent.key);
		}
		if(n.right != null) printTree(n.right);
	}

	public static void main (String[] arg){
	bst tree = new bst();
	testInsert(tree);
	}

	private static void testInsert(bst tree) { // change keys to test tree
		System.out.println("== Testing Insertion ==");
		System.out.println("Insert: 1, 2, 3, 4, 5, 6, 7");
			tree.insert(1);
			tree.insert(2);
			tree.insert(3);
			tree.insert(4);
			tree.insert(5);
			tree.insert(6);
			tree.insert(7);
			tree.printTree();
			System.out.println("");
			tree.clearTree();
	}
}


class node {
	public int key;
	public node left, right, parent;

	public node(int key) {
		this(key, null, null);
	}
	
	public node(int key, node left, node right) {
		this.key = key;
		this.left = left;
		this.right = right;
		if(left != null) left.parent = this;
		if(right != null) right.parent = this;
		this.parent = null;
	}
}