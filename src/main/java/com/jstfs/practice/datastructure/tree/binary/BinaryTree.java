package com.jstfs.practice.datastructure.tree.binary;

/**
 * 二叉树
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:04
 */
public class BinaryTree {
	private TreeNode rootNode;	//根节点
	
	public void ergodic(TreeErgodicTypeEnum typeEnum) {
		if(rootNode == null) {
			throw new RuntimeException("二叉树为空");
		}
		
		if(typeEnum == null) {
			throw new RuntimeException("遍历方式不能为空");
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			ergodicDlr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			ergodicLdr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			ergodicLrd(rootNode);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
	}
	
	/**
	 * 前序遍历
	 */
	private void ergodicDlr(TreeNode node) {
		if(node == null) {
			return ;
		}
		
		System.out.println(node);
		ergodicDlr(node.getLeftChild());
		ergodicDlr(node.getRightChild());
	}
	
	/**
	 * 中序遍历
	 */
	private void ergodicLdr(TreeNode node) {
		if(node == null) {
			return ;
		}
		
		ergodicLdr(node.getLeftChild());
		System.out.println(node);
		ergodicLdr(node.getRightChild());
	}
	
	/**
	 * 后序遍历
	 */
	private void ergodicLrd(TreeNode node) {
		if(node == null) {
			return ;
		}
		
		ergodicLrd(node.getLeftChild());
		ergodicLrd(node.getRightChild());
		System.out.println(node);
	}

	public TreeNode getRootNode() {
		return rootNode;
	}
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
}
