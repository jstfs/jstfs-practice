package com.jstfs.practice.datastructure.tree.binary.redblack;

import com.jstfs.practice.datastructure.tree.binary.TreeNode;

/**
 * 红黑树节点
 * 
 * @createBy	落叶
 * @createTime	2022年11月13日 下午9:56:42
 */
public class RBTreeNode extends TreeNode {

	private boolean redFlag = true;		//节点的红黑标识(新插入的节点默认为红色,因为这样违反红黑树规则的概率较小)

	public RBTreeNode() {
	}
	
	public RBTreeNode(Object data) {
		super(data);
	}
	
	public boolean getRedFlag() {
		return redFlag;
	}
	public void setRedFlag(boolean redFlag) {
		this.redFlag = redFlag;
	}
	
	/**
	 * 打印树时,节点的输出方式
	 */
	public String printNode() {
		return getData().toString() + (getRedFlag() ? "-红" : "-黑"); 
	}
	
	@Override
	public String toString() {
		String str = super.toString();
		
		if(getRedFlag()) {
			return str.replace("]", "-红]");
		} else {
			return str.replace("]", "-黑]");
		}
	}
}
