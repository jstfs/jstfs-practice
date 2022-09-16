package com.jstfs.practice.datastructure.tree.binary;

import com.jstfs.common.utils.MyStringUtils;

/**
 * 二叉树节点
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:21
 */
public class TreeNode {
	private int index = Integer.MIN_VALUE;	//序号
	private String data;					//数据
	private TreeNode leftChild;				//左子节点
	private TreeNode rightChild;			//右子节点
	
	private boolean leftThreadFlag;			//左线索标识 [true-左节点是前驱线索,false-左节点是子节点]
	private boolean rigthThreadFlag;		//右线索标识 [true-右节点是后继线索,false-右节点是子节点]
	
	public TreeNode() {
	}
	
	public TreeNode(int index) {
		this.index = index;
	}
	
	public TreeNode(int index, String data) {
		this.index = index;
		this.data = data;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(index);
		if(MyStringUtils.isNoneEmpty(data)) {
			sb.append("-" + data + "]");
		} else {
			sb.append("]");
		}
		return sb.toString();
	}
	
	public Object getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public TreeNode getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isLeftThreadFlag() {
		return leftThreadFlag;
	}
	public void setLeftThreadFlag(boolean leftThreadFlag) {
		this.leftThreadFlag = leftThreadFlag;
	}
	public boolean isRigthThreadFlag() {
		return rigthThreadFlag;
	}
	public void setRigthThreadFlag(boolean rigthThreadFlag) {
		this.rigthThreadFlag = rigthThreadFlag;
	}
}
