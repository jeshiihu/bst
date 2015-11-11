
public class bst {
	public node root;
	
	public bst() { // create an empty tree
		root = null;
	}
	
	public node search(int key) {
		node current = root;
		while(current != null) {
			if(current.key == key) return current;
			else if(current.key > key) current = current.left;
			else current = current.right;
		}
		return current;
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
	node getSucc(node n) {
		node succ = null;
		node succParent = null;
		node current = n.left;
		while(current!=null) {
			succParent = succ;
			succ = current;
			current = current.right;
		}
		if(succ!=n.left){
			succParent.right = succ.left;
			succ.left = n.left;
		}
		return succ;
	}
	void delete(int key) {
		if(search(key) == null) return; //tree does not contain this key, do nothing
		node removeNode = search(key);
		if(removeNode.left==null && removeNode.right==null) {
			if(removeNode.parent.left==removeNode) removeNode.parent.left = null;
			if(removeNode.parent.right==removeNode) removeNode.parent.right = null;
		}
		if(removeNode.left!=null && removeNode.right!=null) 
			deleteTwoChild(removeNode);
		if(removeNode.left==null&&removeNode.right!=null || removeNode.left!=null&&removeNode.right==null)
			deleteOneChild(removeNode);

	}
	void deleteTwoChild(node n) {
		node succ = getSucc(n);
		System.out.println(succ.key);
		if(n==root){
			root = succ;
		}else if(n.parent.left==n){
			n.parent.left = succ;

		}else if(n.parent.right==n){
			n.parent.right = succ;
		}			
		succ.right = n.right;
		succ.parent = n.parent;
		n.left.parent = succ;
		n.right.parent = succ;
	}
	void deleteOneChild(node n) {
		node child = (n.right == null) ? n.left: n.right; //if n.r is null, return l, else return r
		if(n.parent.left==n) {
			n.parent.left = child;
			child.parent = n.parent;
		}
		if(n.parent.right==n) {
			n.parent.right = child;
			child.parent = n.parent;
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
	testDelete(tree);
	tree.clearTree();
	}

	private static void testInsert(bst tree) { // change keys to test tree
		System.out.println("== Testing Insertion ==");
		System.out.println("Insert: 1, 2, 3, 4, 5, 6, 7");
			tree.insert(6);
			tree.insert(3);
			tree.insert(9);
			tree.insert(1);
			tree.insert(8);
			tree.insert(11);
			tree.insert(0);
			tree.insert(2);
			tree.printTree();
			System.out.println("");
	}
	private static void testDelete(bst tree) { // change keys to test tree
		System.out.println("== Testing Deletion ==");
//			tree.delete(9); System.out.println("deleted 9");
//			tree.delete(8); System.out.println("deleted 8");
			tree.delete(6); System.out.println("deleted 15");
			tree.printTree();
			System.out.println("");
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