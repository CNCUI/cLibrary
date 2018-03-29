package algorithm;

import java.util.ArrayList;
import java.util.List;
/**
 * 二叉树
 *					0						先序遍历：0  1  3  7  4  2  5  6
 *				  /	  \						中序遍历：7  3  1  4  0  5  2  6
 *				1		2					后序遍历：7  3  4  1  5  6  2  0
 *			   / \	   / \
 *			  3	  4   5	  6
 *			/
 *		   7
 */
public class BinTree {
	private BinTree root;
	private BinTree leftChild;
	private BinTree rightChild;
	private Object data;
	private List<BinTree> datas;// 存储所有的节点

	public BinTree(BinTree lChild, BinTree rChild, Object data) {
		super();
		this.leftChild = lChild;
		this.rightChild = rChild;
		this.data = data;
	}

	public BinTree(Object data) {
		this(null, null, data);
	}

	public BinTree() {
		super();
	}

	public void createTree(Object[] objs) {
		datas = new ArrayList<BinTree>();
		for (Object object : objs) {
			datas.add(new BinTree(object));
		}
		root = datas.get(0);// 将第一个作为根节点
		for (int i = 0; i < objs.length / 2; i++) { // objs.length/2 标识二叉树的层数
			datas.get(i).leftChild = datas.get(i * 2 + 1);			//奇数左孩子
			if (i * 2 + 2 < datas.size()) {// 避免偶数的时候 下标越界
				datas.get(i).rightChild = datas.get(i * 2 + 2);		//偶数右孩子
			}
		}
	}

	// 先序遍历
	public void preorder(BinTree root) {
		if (root != null) {
			visit(root.getData());
			preorder(root.leftChild);
			preorder(root.rightChild);
		}

	}

	// 中序遍历
	public void inorder(BinTree root) {
		if (root != null) {
			inorder(root.leftChild);
			visit(root.getData());
			inorder(root.rightChild);
		}

	}

	// 后序遍历
	public void afterorder(BinTree root) {
		if (root != null) {
			afterorder(root.leftChild);
			afterorder(root.rightChild);
			visit(root.getData());
		}

	}

	private void visit(Object obj) {
		System.out.print(obj + " ");
	}

	public Object getData() {
		return data;
	}

	public BinTree getRoot() {
		return root;
	}

	public static void main(String[] args) {
		BinTree binTree = new BinTree();
		Object[] objs = { 0, 2, 4, 5, 7, 8, 10, 11 };
		binTree.createTree(objs);
		binTree.preorder(binTree.getRoot()); //先序遍历
		// binTree.inorder(binTree.getRoot()); //中序遍历
		// binTree.afterorder(binTree.getRoot()); //后序遍历
	}
}
