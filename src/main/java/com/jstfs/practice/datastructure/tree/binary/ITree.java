package com.jstfs.practice.datastructure.tree.binary;

interface ITree {
	/**
	 * 构建一棵树(初始化树的节点)
	 */
	void build();
	/**
	 * 遍历到元素之后的动作
	 * 
	 * @param node	遍历到的元素
	 */
	void ergodicAction(TreeNode node);
	
	/**
	 * 查找指定元素
	 * 
	 * @param data	要查找元素的数据
	 */
	TreeNode searchNode(Object data);
	/**
	 * 根据指定的查找顺序查找指定元素
	 * 
	 * @param typeEnum	查找的顺序
	 * @param data		要查找元素的数据
	 */
	TreeNode searchNode(TreeErgodicTypeEnum typeEnum, Object data);
	/**
	 * 删除节点,如果成功删掉就返回该节点
	 */
	TreeNode deleteNode(Object data);
}
