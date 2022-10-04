package com.jstfs.practice.datastructure.tree.binary;

import com.jstfs.common.utils.MyStringUtils;

/**
 * 二叉树节点
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:21
 */
public class BinaryTreeNode {
	private int index = Integer.MIN_VALUE;		//序号
	private String data;						//数据
	private BinaryTreeNode leftChild;			//左子节点
	private BinaryTreeNode rightChild;			//右子节点
	
	private boolean leftThreadFlag 	= false;	//左线索标识 [true-左节点是前驱线索, false-左节点是子节点]
	private boolean rightThreadFlag = false;	//右线索标识 [true-右节点是后继线索, false-右节点是子节点]
	
	private BinaryTreeNode parent;				//父节点,为了后序遍历线索二叉树增加的
	
	public BinaryTreeNode() {
	}
	
	public BinaryTreeNode(int index) {
		this.index = index;
	}
	
	public BinaryTreeNode(int index, String data) {
		this.index = index;
		this.data = data;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(parent != null) {
			sb.append("[" + parent.getIndex() + "] ");
		}
		if(leftThreadFlag) {
			sb.append("◀");
		} else {
			sb.append("◁");
		}
		
		sb.append("[");
		sb.append(index);
		if(MyStringUtils.isNoneEmpty(data)) {
			sb.append("-" + data + "]");
		} else {
			sb.append("]");
		}
		
		if(rightThreadFlag) {
			sb.append("▶");
		} else {
			sb.append("▷");
		}
		
		return sb.toString();
	}
	
	public Object getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public BinaryTreeNode getLeftChild() {
		return leftChild;
	}
	
	/**
	 * 可以链式构建树
	 */
	public BinaryTreeNode setLeftChild(BinaryTreeNode leftChild) {
		this.leftChild = leftChild;
		return leftChild;
	}
	public BinaryTreeNode getRightChild() {
		return rightChild;
	}
	/**
	 * 可以链式构建树
	 */
	public BinaryTreeNode setRightChild(BinaryTreeNode rightChild) {
		this.rightChild = rightChild;
		return rightChild;
	}
	public void setLeftAndRight(BinaryTreeNode leftChild, BinaryTreeNode rightChild) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean getLeftThreadFlag() {
		return leftThreadFlag;
	}
	public void setLeftThreadFlag(boolean leftThreadFlag) {
		this.leftThreadFlag = leftThreadFlag;
	}
	public boolean getRightThreadFlag() {
		return rightThreadFlag;
	}
	public void setRightThreadFlag(boolean rigthThreadFlag) {
		this.rightThreadFlag = rigthThreadFlag;
	}
	public BinaryTreeNode getParent() {
		return parent;
	}
	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}
}
