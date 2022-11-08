package com.jstfs.practice.datastructure.tree.binary;

/**
 * 普通的二叉树
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:04
 */
public class BinaryTree extends AbstractBinaryTree {
	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree(true);
		bt.printTree();
		System.out.println(bt.getLever());
		System.out.println(bt.deleteNode("2-卢俊义"));
		bt.printTree();
		System.out.println(bt.getLever());
		System.out.println(bt.deleteNode("3-吴用"));
		bt.printTree();
		System.out.println(bt.getLever());
	}
	
	/**
	 * 默认构造方法,构建一棵空二叉树
	 */
	public BinaryTree() {
		this(false);
	}
	
	/**
	 * 构建一棵空二叉树或者默认的二叉树
	 * 
	 * @param defualtNode	true-构建默认的二叉树, false-构建一棵空二叉树
	 */
	public BinaryTree(boolean defualtNode) {
		if(defualtNode) {
			build();
		}
	}
	
	/**
	 * 遍历到节点后的动作(默认实现仅仅是打印出遍历到的节点)
	 * 
	 * @param node	遍历到的节点
	 */
	@Override
	public void ergodicAction(TreeNode node) {
		System.out.println(node);
	}
}
