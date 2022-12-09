package com.jstfs.practice.datastructure.tree.binary;

import java.util.List;

import com.jstfs.common.utils.MyCollectionUtils;

/**
 * 普通的二叉树
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:04
 */
public class BinaryTree extends AbstractBinaryTree {
	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.defaultBuild();
		
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
	 * 添加节点
	 */
	@Override
	public void addNode(TreeNode newNode) {
		if(getRootNode() == null) {
			setRootNode(newNode);
		} else {
			List<TreeNode> nextLeverNodeList = MyCollectionUtils.newList(TreeNode.class, getRootNode());
			addNode(nextLeverNodeList, newNode); 
		}
		
		System.out.println("增加节点 " + newNode + " 后,打印树...");
		printTree();
		
		afterAddNode(newNode);
	}
	
	/**
	 * 添加节点成功后的动作,空实现
	 */
	@Override
	public void afterAddNode(TreeNode newNode) {
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
	
//======================================= public/private 方法分界线 TODO =========================================================================
	
	/**
	 * @param nodeList	依次尝试给这些节点上添加
	 * @param newNode	要添加的节点
	 */
	private void addNode(List<TreeNode> nodeList, TreeNode newNode) {
		List<TreeNode> nextLeverNodeList = MyCollectionUtils.newList(TreeNode.class);
		for(TreeNode node : nodeList) {
			if(node.getLeftChild() == null) {
				node.setLeftChild(newNode);
				newNode.setParent(node);
				return;
			} else {
				nextLeverNodeList.add(node.getLeftChild());
			}
			
			if(node.getRightChild() == null) {
				node.setRightChild(newNode);
				newNode.setParent(node);
				return;
			} else {
				nextLeverNodeList.add(node.getRightChild());
			}
		}
		
		addNode(nextLeverNodeList, newNode);
	}

	/**
	 * 用默认的水浒好汉座次顺序构建一棵二叉树
	 */
	private void defaultBuild() {
		Object[] dataAry = new Object[] {
				"1-宋江",
				"2-卢俊义",
				"3-吴用",
				"4-公孙胜",
				"5-关胜",
				"6-林冲",
				"7-秦明",
				"8-呼延灼"
		};
		
		build(dataAry);
		
		TreeNode headNode = new TreeNode("0-替天行道");
		headNode.setLeftChild(getRootNode());
		setHeadNode(headNode);
	}
}
