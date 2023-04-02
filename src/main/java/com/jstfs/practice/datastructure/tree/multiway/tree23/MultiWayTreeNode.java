package com.jstfs.practice.datastructure.tree.multiway.tree23;

/**
 * 2-3树的节点
 * 
 * @createBy	落叶
 * @createTime	2022年11月10日 下午11:14:52
 */
public class MultiWayTreeNode {
	private Object smallData;							//节点上较小的数据
	private Object bigData;								//节点上较大的数据
	
	private MultiWayTreeNode 	leftChild;						//左子节点
	private MultiWayTreeNode 	rightChild;						//右子节点
	private MultiWayTreeNode 	parent;							//父节点
	
	public MultiWayTreeNode() {
	}
	
	public MultiWayTreeNode(Object data) {
		this.smallData = smallData;
	}
}
