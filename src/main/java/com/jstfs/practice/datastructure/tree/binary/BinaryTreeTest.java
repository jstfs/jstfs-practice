package com.jstfs.practice.datastructure.tree.binary;

/**
 * 二叉树测试
 * 
 * @createBy	落叶
 * @createTime	2022年9月16日 下午11:41:43
 */
public class BinaryTreeTest {
	public static void main(String[] args) {
		BinaryTreeTest btt = new BinaryTreeTest();
		BinaryTree bt = btt.build();
		
		bt.ergodic(TreeErgodicTypeEnum.ERGODIC_DLR);
		System.out.println("=======================================");
		bt.ergodic(TreeErgodicTypeEnum.ERGODIC_LDR);
		System.out.println("=======================================");
		bt.ergodic(TreeErgodicTypeEnum.ERGODIC_LRD);
	}
	
	private BinaryTree build() {
		BinaryTree bt = new BinaryTree();
		
		TreeNode node1 = new TreeNode(1, "宋江");
		TreeNode node2 = new TreeNode(2, "卢俊义");
		TreeNode node3 = new TreeNode(3, "吴用");
		TreeNode node4 = new TreeNode(4, "公孙胜");
		TreeNode node5 = new TreeNode(5, "关胜");
		TreeNode node6 = new TreeNode(6, "林冲");
		TreeNode node7 = new TreeNode(7, "秦明");
		TreeNode node8 = new TreeNode(8, "呼延灼");
		
		node1.setLeftChild(node2);
		node1.setRightChild(node3);
		
		node2.setLeftChild(node4);
		node2.setRightChild(node5);
		
		node3.setLeftChild(node6);
		node3.setRightChild(node7);
		
		node4.setLeftChild(node8);
		
		bt.setRootNode(node1);
		
		return bt;
	}
}
