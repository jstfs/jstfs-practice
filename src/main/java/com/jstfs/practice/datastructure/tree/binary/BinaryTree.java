package com.jstfs.practice.datastructure.tree.binary;

import java.util.List;

import com.jstfs.common.utils.MyCollectionUtils;
import com.jstfs.common.utils.MyStringUtils;

/**
 * 二叉树
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:04
 */
public class BinaryTree implements ITree {
	private TreeNode 			rootNode;					//根节点
	
	private TreeNode 			headNode;					//头结点,其左子结点指向根节点,用于前序和中序线索化遍历
	private TreeNode 			preNode;					//用于二叉树线索化或者遍历线索化二叉树时,保存上一个操作过的节点
	private TreeErgodicTypeEnum threadedErgodicType = null;	//记录使用了哪种遍历顺序线索化
	private int					lever;						//记录树的度(层数)
	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree(true);
		bt.printTree();
	}
	
	/**
	 * 默认构造方法,构建一棵空二叉树
	 */
	public BinaryTree() {
		this(false);
	}
	
	/**
	 * 是否使用默认节点构建二叉树
	 * 
	 * @param defualtNode	true-使用默认节点构建二叉树, false-构建一棵空二叉树
	 */
	public BinaryTree(boolean defualtNode) {
		if(defualtNode) {
			this.build();
		}
	}
	
	/**
	 * 使用默认的节点构建一颗二叉树
	 */
	@Override
	public void build() {
		headNode = new TreeNode("0-替天行道");
		rootNode = new TreeNode("1-宋江", 1);
		
		TreeNode node2 = new TreeNode("2-卢俊义");
		TreeNode node3 = new TreeNode("3-吴用");
		TreeNode node4 = new TreeNode("4-公孙胜");
		TreeNode node5 = new TreeNode("5-关胜");
		TreeNode node6 = new TreeNode("6-林冲");
		TreeNode node7 = new TreeNode("7-秦明");
		TreeNode node8 = new TreeNode("8-呼延灼");
		
		rootNode.setLeftChild(node2);
		rootNode.setRightChild(node3);
		
		node2.setLeftChild(node4);
		node2.setRightChild(node5);
		node3.setLeftChild(node6);
		node3.setRightChild(node7);
		
		node4.setLeftChild(node8);
		
		headNode.setLeftChild(rootNode);
	}
	
	/**
	 * 遍历二叉树,默认使用中序遍历
	 */
	public void ergodic() {
		ergodic(TreeErgodicTypeEnum.ERGODIC_LDR);
	}
	
	/**
	 * 根据指定的遍历方式遍历二叉树
	 */
	public void ergodic(TreeErgodicTypeEnum typeEnum) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序遍历
			ergodicDlr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序遍历
			ergodicLdr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序遍历
			ergodicLrd(rootNode);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
	}
	
	/**
	 * 线索化二叉树,默认使用前序遍历
	 */
	public void threaded() {
		threaded(TreeErgodicTypeEnum.ERGODIC_LDR);
	}
	
	/**
	 * 根据指定的遍历方式线索化二叉树
	 */
	public void threaded(TreeErgodicTypeEnum typeEnum) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序线索化
			threadedDlr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序线索化
			threadeLdr(rootNode);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序线索化
			threadeLrd(rootNode);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
		
		//线索化最后一个节点:将其右节点指向head节点
		if(preNode.getRightChild() == null) {
			preNode.setRightChild(headNode);
			preNode.setRightThreadFlag(true);
		}
		
		//记录线索化的顺序
		threadedErgodicType = typeEnum;
	}
	
	/**
	 * 遍历线索二叉树(根据线索化时使用的遍历方式来遍历)
	 */
	public void ergodicThreaded() {
		String checkResult = check(threadedErgodicType);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(threadedErgodicType.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序遍历
			ergodicThreadedDlr();
		} else if(threadedErgodicType.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序遍历
			ergodicThreadedLdr();
		} else if(threadedErgodicType.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序遍历
			ergodicThreadedLrd();
		} else {
			throw new RuntimeException("不支持的遍历方式:" + threadedErgodicType.getType());
		}
	}
	
	/**
	 * 查找指定元素,默认使用中序遍历来查找
	 */
	@Override
	public TreeNode searchNode(Object data) {
		return searchNode(TreeErgodicTypeEnum.ERGODIC_LDR, data);
	}
	
	/**
	 * 根据指定的遍历方式查找指定序号的元素
	 */
	@Override
	public TreeNode searchNode(TreeErgodicTypeEnum typeEnum, Object data) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序查找
			return searchDlr(rootNode, data);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序查找
			return searchLdr(rootNode, data);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序查找
			return searchLrd(rootNode, data);
		} else {
			throw new RuntimeException("不支持的遍历方式:" + typeEnum.getType());
		}
	}
	
	/**
	 * 删除节点,将该节点的子树也都删掉
	 * 
	 * @return	是否删除成功
	 */
	@Override
	public TreeNode deleteNode(Object data) {
		if(rootNode == null) {
			return null;
		}
		
		if(rootNode.getData().equals(data)) {
			rootNode = null;
			return rootNode;
		}
		
		return deleteNode(rootNode, data);
	}
	
	/**
	 * 设置每个节点的层数,rootNode的层数必须先初始化为1
	 */
	public void setNodeLever(TreeNode node) {
		int nextLever = 0;
		if(node.getLeftChild() != null) {
			nextLever = node.getLever() + 1;
			node.getLeftChild().setLever(nextLever);
			if(nextLever > lever) {
				lever = nextLever;
			}
			setNodeLever(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			nextLever = node.getLever() + 1;
			node.getRightChild().setLever(nextLever);
			if(nextLever > lever) {
				lever = nextLever;
			}
			setNodeLever(node.getRightChild());
		}
	}
	
	/**
	 * 打印树
	 */
	public void printTree() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(rootNode.getData()).append("]").append("\n");
		
		List<TreeNode> nodes = MyCollectionUtils.newList(TreeNode.class);
		nodes.add(rootNode);
		
		printTree(nodes, sb);
		
		System.out.println(sb);
	}
	
	/**
	 * 遍历到元素后的动作,默认实现是直接打印
	 */
	@Override
	public void ergodicAction(TreeNode node) {
		System.out.println(node);
	}
//======================================= public/private方法分界线 TODO =============================================================================	
	
	/**
	 * 前序遍历
	 */
	private void ergodicDlr(TreeNode node) {
		ergodicAction(node);
		
		if(node.getLeftChild() != null) {
			ergodicDlr(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			ergodicDlr(node.getRightChild());
		}
	}
	
	/**
	 * 中序遍历
	 */
	private void ergodicLdr(TreeNode node) {
		if(node.getLeftChild() != null) {
			ergodicLdr(node.getLeftChild());
		}
		
		ergodicAction(node);
		
		if(node.getRightChild() != null) {
			ergodicLdr(node.getRightChild());
		}
	}
	
	/**
	 * 后序遍历
	 */
	private void ergodicLrd(TreeNode node) {
		if(node.getLeftChild() != null) {
			ergodicLrd(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			ergodicLrd(node.getRightChild());
		}
		
		ergodicAction(node);
	}
	
	/**
	 * 前序线索化
	 */
	private void threadedDlr(TreeNode node) {
		if(node.getLeftChild() == null) {
			/**
			 * 将前序线索化的第一个节点的左子节点指向head节点
			 * 虽然前序遍历的第一个节点一定是根节点,但根节点有可能没有左子树
			 * 这样的话,其左子结点也可以指向headNode
			 */
			if(preNode == null) {
				node.setLeftChild(headNode);
			} else {
				node.setLeftChild(preNode);
			}
			node.setLeftThreadFlag(true);
		}
		
		if(preNode != null && preNode.getRightChild() == null) {
			preNode.setRightChild(node);
			preNode.setRightThreadFlag(true);
		}
		preNode = node;
		
		if(node.getLeftChild() != null) {
			threadedDlr(node.getLeftChild());
		}
		
		if(node.getRightChild() != null) {
			threadedDlr(node.getRightChild());
		}
	}
	
	/**
	 * 中序线索化
	 */
	private void threadeLdr(TreeNode node) {
		if(node.getLeftChild() != null) {
			threadeLdr(node.getLeftChild());
		}

		if(node.getLeftChild() == null) {
			if(preNode == null) {
				//将中序线索化的第一个节点的左子节点指向head节点
				node.setLeftChild(headNode);
			} else {
				node.setLeftChild(preNode);
			}
			node.setLeftThreadFlag(true);
		}
		if(preNode != null && preNode.getRightChild() == null) {
			preNode.setRightChild(node);
			preNode.setRightThreadFlag(true);
		}
		preNode = node;

		if(node.getRightChild() != null) {
			threadeLdr(node.getRightChild());
		}
	}
	
	/**
	 * 后序线索化,同时设置父节点,使二叉链表变成三叉链表
	 */
	private void threadeLrd(TreeNode node) {
		if(!node.getLeftThreadFlag() && node.getLeftChild() != null) {
			//设置父节点,后序遍历用到
			node.getLeftChild().setParent(node);
			threadeLrd(node.getLeftChild());
		}
		
		if(!node.getRightThreadFlag() && node.getRightChild() != null) {
			//设置父节点,后序遍历用到
			node.getRightChild().setParent(node);
			threadeLrd(node.getRightChild());
		}
		
		if(node.getLeftChild() == null) {
			if(preNode == null) {
				//将后序线索化的第一个节点的左子节点指向head节点
				node.setLeftChild(headNode);
			} else {
				node.setLeftChild(preNode);
			}
			node.setLeftThreadFlag(true);
		}
		if(preNode != null && preNode.getRightChild() == null) {
			preNode.setRightChild(node);
			preNode.setRightThreadFlag(true);
		}
		preNode = node;
	}
	
	/**
	 * 前序遍历线索二叉树
	 */
	private void ergodicThreadedDlr() {
		TreeNode node = rootNode;
		while(node != null && node != headNode) {
			ergodicAction(node);
			
			if(!node.getLeftThreadFlag()) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild(true);
			}
		}
	}
	
	/**
	 * 中序遍历线索二叉树
	 */
	private void ergodicThreadedLdr() {
		TreeNode node = rootNode;
		while(node != null && node != headNode) {
			while(!node.getLeftThreadFlag()) {
				node = node.getLeftChild();
			}
			
			ergodicAction(node);
			
			while(node.getRightThreadFlag() && node.getRightChild(true) != headNode) {
				node = node.getRightChild(true);
				ergodicAction(node);
			}
			
			node = node.getRightChild();
		}
	}
	
	/**
	 * 后续遍历线索二叉树
	 */
	private void ergodicThreadedLrd() {
		TreeNode node = rootNode;
		preNode = null;
		
		while(node != null) {
			/**
			 * 先找到最左边的子节点
			 * [node.getLeftChild() != preNode] 这个条件很重要
			 * 如果不加这个条件,那么通过[node = node.getParent();]返回到父节点之后,会继续在其左子树中循环
			 */
			while(node.getLeftChild() != null && !node.getLeftThreadFlag() && node.getLeftChild() != preNode) {
				node = node.getLeftChild();
			}
			
			/**
			 * 如果最左边的子节点有右子节点,那么这段跳过
			 * 如果最左边的子节点没有右子节点,那么一定有后继节点,则跟着后继节点一直遍历
			 */
			while(node != null && node.getRightThreadFlag()) {
				ergodicAction(node);
				preNode = node;
				node = node.getRightChild(true);
			}
			
			/**
			 * 后序遍历的最后一个节点一定是根节点
			 */
			if(node == rootNode) {
				ergodicAction(node);
				return;
			}
			
			/**
			 * 如果当前节点是从右子节点返回的
			 * 说明当前节点的所有子节点都遍历完了
			 * 那么就遍历当前节点,并返回到其父节点
			 */
			while(node != null && node.getRightChild() == preNode) {
				ergodicAction(node);
				preNode = node;
				node = node.getParent();
			}
			
			if(node != null && !node.getRightThreadFlag()) {
				node = node.getRightChild();
			}
		}
	}
	
	/**
	 * 前序查找
	 */
	private TreeNode searchDlr(TreeNode node, Object data) {
		if(node.getData().equals(data)) {
			return node;
		}
		
		if(node.getLeftChild() != null) {
			TreeNode result = searchDlr(node.getLeftChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchDlr(node.getRightChild(), data);
			if(result != null) {
				return result;
			}
		}

		return null;
	}
	
	/**
	 * 中序查找
	 */
	private TreeNode searchLdr(TreeNode node, Object data) {
		if(node.getLeftChild() != null) {
			TreeNode result = searchLdr(node.getLeftChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getData().equals(data)) {
			return node;
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchLdr(node.getRightChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		return null;
	}
	
	/**
	 * 后序查找
	 */
	private TreeNode searchLrd(TreeNode node, Object data) {
		if(node.getLeftChild() != null) {
			TreeNode result = searchLrd(node.getLeftChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchLrd(node.getRightChild(), data);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getData().equals(data)) {
			return node;
		}
		
		return null;
	}
	
	/**
	 * 删除节点
	 */
	private TreeNode deleteNode(TreeNode node, Object data) {
		TreeNode delNode = null;
		if(node.getLeftChild() != null && node.getLeftChild().getData().equals(data)) {
			delNode = node.getLeftChild();
			node.setLeftChild(null);
			return delNode;
		}
		if(node.getRightChild() != null && node.getRightChild().getData().equals(data)) {
			node.setRightChild(null);
			delNode = node.getRightChild();
			return delNode;
		}
		
		if(node.getLeftChild() != null) {
			delNode = deleteNode(node.getLeftChild(), data);
			if(delNode != null) {
				return delNode;
			}
		}
		if(node.getRightChild() != null) {
			delNode = deleteNode(node.getRightChild(), data);
			if(delNode != null) {
				return delNode;
			}
		}
		
		return null;
	}

	/**
	 * 前置检查
	 */
	private String check(TreeErgodicTypeEnum typeEnum) {
		if(rootNode == null) {
			return "二叉树为空";
		}
		if(typeEnum == null) {
			return "遍历方式不能为空";
		}
		return null;
	}
	
	/**
	 * 打印树
	 * 
	 * [左子结点|右子节点]
	 * "○": 表示该位置为空
	 * "=": 分隔一组左右子节点和另一组兄弟左右子节点的符号
	 * 
	 * @param preLeverNodes		上层的所有节点
	 */
	private void printTree(List<TreeNode> preLeverNodes, StringBuilder sb) {
		List<TreeNode> nodes = MyCollectionUtils.newList(TreeNode.class);
		
		for(int i = 0; i < preLeverNodes.size(); i++) {
			TreeNode node = preLeverNodes.get(i);
			
			sb.append("[");
			
			if(node.getLeftChild() != null && !node.getLeftThreadFlag()) {
				nodes.add(node.getLeftChild());
				sb.append(node.getLeftChild().getData());
			} else {
				sb.append("○");
			}
			
			sb.append("|");
			
			if(node.getRightChild() != null && !node.getRightThreadFlag()) {
				nodes.add(node.getRightChild());
				sb.append(node.getRightChild().getData());
			} else {
				sb.append("○");
			}
			
			sb.append("]");
			
			if(i != preLeverNodes.size() - 1) {
				sb.append("=");
			}
		}
		sb.append("\n");
		
		if(nodes.size() > 0) {
			printTree(nodes, sb);
		}
	}

	public TreeNode getRootNode() {
		return rootNode;
	}
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	public int getLever() {
		return lever;
	}
}
