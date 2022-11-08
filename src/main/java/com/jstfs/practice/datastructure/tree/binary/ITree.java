package com.jstfs.practice.datastructure.tree.binary;

/**
 * 通用的树接口
 * 
 * @createBy	落叶
 * @createTime	2022年10月30日 下午12:44:33
 */
interface ITree {
	/**
	 * 获得根节点
	 */
	TreeNode getRootNode();
	/**
	 * 设置根节点
	 */
	void setRootNode(TreeNode rootNode);
	/**
	 * 获得头节点
	 */
	TreeNode getHeadNode();
	/**
	 * 设置头节点
	 */
	void setHeadNode(TreeNode headNode);
	/**
	 * 构建一棵树
	 */
	void build();
	/**
	 * 用整型数据作为data构建一棵树
	 */
	void build(int[] dataAry);
	/**
	 * 遍历二叉树
	 */
	void ergodic();
	/**
	 * 根据指定的遍历方式遍历二叉树
	 * 
	 * @param typeEnum	指定的遍历方式
	 */
	public void ergodic(TreeErgodicTypeEnum typeEnum);
	/**
	 * 遍历到节点后的动作
	 * 
	 * @param node	遍历到的节点
	 */
	void ergodicAction(TreeNode node);
	/**
	 * 线索化二叉树
	 */
	void threaded();
	/**
	 * 根据指定的遍历方式线索化二叉树
	 * 
	 * @param typeEnum 指定的遍历方式
	 */
	void threaded(TreeErgodicTypeEnum typeEnum);
	/**
	 * 遍历线索二叉树(只能使用线索化时指定的遍历方式来遍历)
	 */
	void ergodicThreaded();
	/**
	 * 查找指定节点
	 * 
	 * @param data	要查找的节点的数据
	 */
	TreeNode searchNode(Object data);
	/**
	 * 根据指定的查找顺序查找指定节点
	 * 
	 * @param typeEnum	查找的顺序
	 * @param data		要查找的节点的数据
	 */
	TreeNode searchNode(TreeErgodicTypeEnum typeEnum, Object data);
	/**
	 * 删除节点
	 * 
	 * @param data	要删除的节点的数据
	 * @return		删除掉的节点
	 */
	TreeNode deleteNode(Object data);
	/**
	 * 打印树
	 */
	void printTree();
	/**
	 * 获得树的高度
	 */
	int getLever();
	/**
	 * 添加节点
	 */
	void addNode(TreeNode newNode);
}
