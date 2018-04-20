package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
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
	private BinTree left;
	private BinTree right;
	private Object data;
	private List<BinTree> list;
	
	public BinTree(BinTree left1,BinTree right1,Object data1){
		this.left = left1;
		this.right = right1;
		this.data = data1;
	}
	public BinTree(Object data){
		this(null,null,data);
	}
	public BinTree(){
		super();
	}
	public Object getData(){
		return this.data;
	}
	public BinTree getRoot(){
		return this.root;
	}
	public void createTree(Object[] objs){
		list = new ArrayList<>();
		for(Object obj : objs){
			list.add(new BinTree(obj));
		}
		root = list.get(0);
		for(int i=0;i<list.size() / 2;i++){
			list.get(i).left = list.get(i*2+1);
			if(i*2+2<list.size()){
				list.get(i).right = list.get(i*2+2);
			}
		}
	}
	public void visit(Object obj){
		System.out.println(obj);
	}
	public void preOrder(BinTree root){
		if(root != null){
			visit(root.getData());
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public static void main(String[] args) {
		BinTree binTree = new BinTree();
		Object[] objs = { 0, 1, 2, 3, 4, 5, 6, 7 };
		binTree.createTree(objs);
		binTree.preOrder(binTree.getRoot()); //先序遍历
		// binTree.inorder(binTree.getRoot()); //中序遍历
		// binTree.afterorder(binTree.getRoot()); //后序遍历
	}
}
