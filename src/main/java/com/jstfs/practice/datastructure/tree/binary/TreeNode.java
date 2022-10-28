package com.jstfs.practice.datastructure.tree.binary;

/**
 * 二叉树节点
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:21
 */
public class TreeNode implements Comparable<TreeNode> {
	private Object 		data;							//节点上的数据
	private TreeNode 	leftChild;						//左子节点
	private TreeNode 	rightChild;						//右子节点
	
	private TreeNode 	parent;							//父节点(用于后序遍历线索二叉树)
	private Integer		weight = Integer.MIN_VALUE;		//节点的权重(用于赫夫曼树的节点之间的比较)
	private boolean 	leftThreadFlag 	= false;		//左线索标识(用于二叉树的线索化,[true-左节点是前驱节点, false-左节点是子节点])
	private boolean 	rightThreadFlag = false;		//右线索标识(用于二叉树的线索化,[true-右节点是后继节点, false-右节点是子节点])
	private int			lever;							//节点所在层数(根节点为第1层)
	
	public TreeNode() {
	}
	
	public TreeNode(Object data) {
		this.data = data;
	}
	
	public TreeNode(Object data, int lever) {
		this.data = data;
		this.lever = lever;
	}
	
	@Override
	public int compareTo(TreeNode o) {
		return this.weight - o.weight;
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
		
		if(weight != Integer.MIN_VALUE) {
			sb.append(data).append("-").append(weight);
		} else {
			sb.append(data);
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
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * 获得左子结点,默认不包含线索化的左子结点
	 */
	public TreeNode getLeftChild() {
		return getLeftChild(false);
	}
	/**
	 * 获得左子结点
	 * 
	 * @param containsThreadedNode	是否包含线索化的左子结点
	 */
	public TreeNode getLeftChild(boolean containsThreadedNode) {
		if(containsThreadedNode || !leftThreadFlag) {
			return leftChild;
		}
		
		return null;
	}
	public void setLeftChild(TreeNode leftChild) {
		this.leftChild = leftChild;
	}
	/**
	 * 获得右子结点,默认不包含线索化的右子结点
	 */
	public TreeNode getRightChild() {
		return getRightChild(false);
	}
	/**
	 * 获得右子结点
	 * 
	 * @param containsThreadedNode	是否包含线索化的右子结点
	 */
	public TreeNode getRightChild(boolean containsThreadedNode) {
		if(containsThreadedNode || !rightThreadFlag) {
			return rightChild;
		}
		
		return null;
	}
	public void setRightChild(TreeNode rightChild) {
		this.rightChild = rightChild;
	}
	public void setLeftAndRight(TreeNode leftChild, TreeNode rightChild) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
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
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public int getLever() {
		return lever;
	}
	public void setLever(int lever) {
		this.lever = lever;
	}
}
