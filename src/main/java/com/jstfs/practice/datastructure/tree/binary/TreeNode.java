package com.jstfs.practice.datastructure.tree.binary;

/**
 * 二叉树节点
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:21
 */
public class TreeNode implements Comparable<TreeNode> {
	private int 		index = Integer.MIN_VALUE;		//序号,用于完全二叉树的节点顺序
	private String 		data;							//节点的数据(也可以用来表示节点的权重)
	private TreeNode 	leftChild;						//左子节点
	private TreeNode 	rightChild;						//右子节点
	private TreeNode 	parent;							//父节点,为了后序遍历线索二叉树增加的
	private int			weight = Integer.MIN_VALUE;		//节点的权重(用于赫夫曼树的节点之间的比较)
	private boolean 	leftThreadFlag 	= false;		//左线索标识 [true-左节点是前驱节点, false-左节点是子节点]
	private boolean 	rightThreadFlag = false;		//右线索标识 [true-右节点是后继节点, false-右节点是子节点]
	
	public TreeNode() {
	}
	
	public TreeNode(int weight) {
		this.weight = weight;
	}
	
	public TreeNode(int index, String data) {
		this.index = index;
		this.data = data;
	}
	
	/**
	 * ← :表示左子节点是前驱节点
	 * ◀	 :表示左子节点是常规的子节点
	 * ◁ :表示左子节点不存在
	 * → :表示右子节点是后继结点
	 * ▶ :表示右子节点是常规的子节点
	 * ▷ :表示右子节点不存在
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(parent != null && parent.getIndex() != Integer.MIN_VALUE) {
			sb.append("[" + parent.getIndex() + "] ");
		}
		
		if(leftChild != null) {
			if(leftThreadFlag) {
				sb.append("←");
			} else {
				sb.append("◀");
			}
		} else {
			sb.append("◁");
		}

		sb.append("[");
		
		if(index != Integer.MIN_VALUE) {
			sb.append(index).append("-").append(data);
			if(weight != Integer.MIN_VALUE) {
				sb.append("-").append(weight);
			}
		} else {
			if(weight != Integer.MIN_VALUE) {
				sb.append(weight);
			}
		}
		
		sb.append("]");
		
		if(rightChild != null) {
			if(rightThreadFlag) {
				sb.append("→");
			} else {
				sb.append("▶");
			}
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
	public TreeNode getLeftChild() {
		return leftChild;
	}
	
	/**
	 * 可以链式构建树
	 */
	public TreeNode setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
		return leftChild;
	}
	public TreeNode getRightChild() {
		return rightChild;
	}
	/**
	 * 可以链式构建树
	 */
	public TreeNode setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
		return rightChild;
	}
	public void setLeftAndRight(TreeNode leftChild, TreeNode rightChild) {
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
	public TreeNode getParent() {
		return parent;
	}
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public int compareTo(TreeNode o) {
		return this.weight - o.weight;
	}
}
