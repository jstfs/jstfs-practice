package com.jstfs.practice.datastructure.tree.binary;

import com.jstfs.common.utils.MyStringUtils;

/**
 * 二叉树
 * 
 * @createBy	落叶
 * @createTime	2022年9月15日 下午11:54:04
 */
public class BinaryTree {
	private TreeNode 			rootNode;					//根节点
	private TreeNode 			headNode;					//头结点,其左子结点指向根节点,用于前序和中序线索化遍历
	private TreeNode 			preNode;					//用于二叉树线索化或者遍历线索化二叉树时,保存上一个操作过的节点
	private TreeErgodicTypeEnum threadedErgodicType = null;	//记录使用了哪种遍历顺序线索化
	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree(true);
		bt.ergodic(TreeErgodicTypeEnum.ERGODIC_LRD);
		bt.threaded(TreeErgodicTypeEnum.ERGODIC_LRD);
		System.out.println("线索化后=========================================");
		bt.ergodicThreaded();
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
	public void build() {
		rootNode = new TreeNode(1, "宋江");
		
		TreeNode node2 = new TreeNode(2, "卢俊义");
		TreeNode node3 = new TreeNode(3, "吴用");
		TreeNode node4 = new TreeNode(4, "公孙胜");
		TreeNode node5 = new TreeNode(5, "关胜");
		TreeNode node6 = new TreeNode(6, "林冲");
		TreeNode node7 = new TreeNode(7, "秦明");
		TreeNode node8 = new TreeNode(8, "呼延灼");
		
		rootNode.setLeftChild(node2).setLeftChild(node4).setLeftChild(node8);
		rootNode.setRightChild(node3).setRightChild(node7);
		node2.setRightChild(node5);
		node3.setLeftChild(node6);
		
		headNode = new TreeNode(0, "替天行道");
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
	 * 默认使用中序遍历来查找指定序号的元素
	 */
	public TreeNode serach(int index) {
		return serach(TreeErgodicTypeEnum.ERGODIC_LDR, index);
	}
	
	/**
	 * 根据指定的遍历方式查找指定序号的元素
	 */
	public TreeNode serach(TreeErgodicTypeEnum typeEnum, int index) {
		String checkResult = check(typeEnum);
		if(MyStringUtils.isNotEmpty(checkResult)) {
			throw new RuntimeException(checkResult);
		}
		
		if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_DLR.getType())) {
			//前序查找
			return searchDlr(rootNode, index);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LDR.getType())) {
			//中序查找
			return searchLdr(rootNode, index);
		} else if(typeEnum.getType().equals(TreeErgodicTypeEnum.ERGODIC_LRD.getType())) {
			//后序查找
			return searchLrd(rootNode, index);
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
	 * 根据指定序号的元素
	 * 
	 * @return	是否删除成功
	 */
	public boolean delete(int index) {
		if(rootNode.getIndex() == index) {
			rootNode = null;
			return true;
		}
		
		return deleteNode(rootNode, index);
	}
	
	/**
	 * 前序遍历
	 */
	private void ergodicDlr(TreeNode node) {
		System.out.println(node);
		
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
		
		System.out.println(node);
		
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
		
		System.out.println(node);
	}
	
	/**
	 * 前序遍历线索二叉树
	 */
	private void ergodicThreadedDlr() {
		TreeNode node = rootNode;
		while(node != null && node != headNode) {
			System.out.println(node);
			
			if(!node.getLeftThreadFlag()) {
				node = node.getLeftChild();
			} else {
				node = node.getRightChild();
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
			
			System.out.println(node);
			
			while(node.getRightThreadFlag() && node.getRightChild() != headNode) {
				node = node.getRightChild();
				System.out.println(node);
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
				System.out.println(node);
				preNode = node;
				node = node.getRightChild();
			}
			
			/**
			 * 后序遍历的最后一个节点一定是根节点
			 */
			if(node == rootNode) {
				System.out.println(node);
				return;
			}
			
			/**
			 * 如果当前节点是从右子节点返回的
			 * 说明当前节点的所有子节点都遍历完了
			 * 那么就遍历当前节点,并返回到其父节点
			 */
			while(node != null && node.getRightChild() == preNode) {
				System.out.println(node);
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
	private TreeNode searchDlr(TreeNode node, int index) {
		if(node.getIndex() == index) {
			return node;
		}
		
		if(node.getLeftChild() != null) {
			TreeNode result = searchDlr(node.getLeftChild(), index);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchDlr(node.getRightChild(), index);
			if(result != null) {
				return result;
			}
		}

		return null;
	}
	
	/**
	 * 中序查找
	 */
	private TreeNode searchLdr(TreeNode node, int index) {
		if(node.getLeftChild() != null) {
			TreeNode result = searchLdr(node.getLeftChild(), index);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getIndex() == index) {
			return node;
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchLdr(node.getRightChild(), index);
			if(result != null) {
				return result;
			}
		}
		
		return null;
	}
	
	/**
	 * 后序查找
	 */
	private TreeNode searchLrd(TreeNode node, int index) {
		if(node.getLeftChild() != null) {
			TreeNode result = searchLrd(node.getLeftChild(), index);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getRightChild() != null) {
			TreeNode result = searchLrd(node.getRightChild(), index);
			if(result != null) {
				return result;
			}
		}
		
		if(node.getIndex() == index) {
			return node;
		}
		
		return null;
	}

	/**
	 * 前序线索化
	 */
	private void threadedDlr(TreeNode node) {
		if(node.getLeftChild() == null) {
			if(preNode == null) {
				/**
				 * 将前序线索化的第一个节点的左子节点指向head节点
				 * 虽然前序遍历的第一个节点一定是根节点,但根节点有可能没有左子树
				 * 这样的话,其左子结点也可以指向headNode
				 */
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
		
		if(!node.getLeftThreadFlag() && node.getLeftChild() != null) {
			threadedDlr(node.getLeftChild());
		}
		
		if(!node.getRightThreadFlag() && node.getRightChild() != null) {
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
	 * 后序线索化
	 * 同时设置父节点,使二叉链表变成三叉链表
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
	 * 删除节点
	 */
	private boolean deleteNode(TreeNode node, int index) {
		if(node.getLeftChild() != null && node.getLeftChild().getIndex() == index) {
			node.setLeftChild(null);
			return true;
		}
		if(node.getRightChild() != null && node.getRightChild().getIndex() == index) {
			node.setRightChild(null);
			return true;
		}
		
		if(node.getLeftChild() != null && deleteNode(node.getLeftChild(), index)) {
			return true;
		}
		if(node.getRightChild() != null && deleteNode(node.getRightChild(), index)) {
			return true;
		}
		return false;
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

	public TreeNode getRootNode() {
		return rootNode;
	}
	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
}
